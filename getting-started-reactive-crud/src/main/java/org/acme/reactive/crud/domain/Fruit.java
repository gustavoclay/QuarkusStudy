package org.acme.reactive.crud.domain;

import java.util.stream.StreamSupport;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Fruit {

    private Long id;

    private String name;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }

    public Fruit(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Multi<Fruit> findAll(PgPool client) {
        return client.query("SELECT id, name FROM fruits ORDER BY name ASC").execute().onItem()
                .produceMulti(set -> Multi.createFrom().items(() -> StreamSupport.stream(set.spliterator(), false)))
                .onItem().apply(Fruit::from);
    }

    public static Uni<Fruit> findById(PgPool client, Long id) {
        return client.preparedQuery("SELECT id, name FROM fruits WHERE id = $1").execute(Tuple.of(id)).onItem()
                .apply(RowSet::iterator).onItem().apply(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }

    public Uni<Long> save(PgPool client) {
        return client.preparedQuery("INSERT INTO fruits (name) VALUES ($1) RETURNING (id)").execute(Tuple.of(name))
                .onItem().apply(pgRowSet -> pgRowSet.iterator().next().getLong("id"));
    }

    public Uni<Boolean> update(PgPool client) {
        return client.preparedQuery("UPDATE fruits SET name = $1 WHERE id = $2").execute(Tuple.of(name, id)).onItem()
                .apply(pgRowSet -> pgRowSet.rowCount() == 1);
    }

    public static Uni<Boolean> delete(PgPool client, Long id) {
        return client.preparedQuery("DELETE FROM fruits WHERE id = $1").execute(Tuple.of(id)).onItem()
                .apply(pgRowSet -> pgRowSet.rowCount() == 1);
    }

    public static Fruit from(Row row) {
        return new Fruit(row.getLong("id"), row.getString("name"));

    }

}