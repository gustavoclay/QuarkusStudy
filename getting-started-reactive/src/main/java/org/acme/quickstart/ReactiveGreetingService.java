package org.acme.quickstart;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class ReactiveGreetingService {

    public Multi<String> greeting(int count, String name) {
        return Multi.createFrom().ticks().every(Duration.ofSeconds(1)).onItem()
                .apply(n -> String.format("hello %s - %d", name, n)).transform().byTakingFirstItems(count);
    }

}