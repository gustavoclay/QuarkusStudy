package io.starter.repositories;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.starter.domain.Pessoa;

@ApplicationScoped
public class PessoaRepository implements PanacheRepository<Pessoa> {

}