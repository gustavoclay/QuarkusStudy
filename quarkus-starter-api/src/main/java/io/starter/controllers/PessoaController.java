package io.starter.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.starter.domain.Pessoa;
import io.starter.services.PessoaService;

@Path("pessoas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaController {

    @Inject
    PessoaService service;

    @POST
    public Response save(Pessoa pessoa) {
        Pessoa pessoaEntity = service.save(pessoa);
        return Response.ok(pessoaEntity).status(Status.CREATED).build();
    }

    @GET
    public List<Pessoa> list() {
        return service.list();
    }

}