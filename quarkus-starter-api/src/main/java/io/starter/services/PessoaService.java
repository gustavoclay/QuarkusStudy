package io.starter.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import io.starter.domain.Pessoa;
import io.starter.repositories.PessoaRepository;

@ApplicationScoped
public class PessoaService {

    @Inject
    PessoaRepository repository;

    @Transactional
    public Pessoa save(Pessoa pessoa) {
        repository.persist(pessoa);
        return pessoa;
    }

    public List<Pessoa> list() {
        return repository.listAll();
    }

}