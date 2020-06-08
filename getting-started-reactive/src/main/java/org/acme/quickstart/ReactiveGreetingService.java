package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ReactiveGreetingService {

    public Uni<String> greeting(String name) {
        return Uni.createFrom().item(name).onItem().apply(n -> String.format("hello %s", name));
    }

}