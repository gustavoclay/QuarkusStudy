package org.acme.quickstart;

import javax.enterprise.context.ApplicationScoped;

/**
 * GreetingService
 */
@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return "Olá " + name + ". Primeira aplicação Quarkus";
    }
}