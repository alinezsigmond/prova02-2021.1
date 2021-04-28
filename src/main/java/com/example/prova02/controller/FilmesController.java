package com.example.prova02.controller;

import com.example.prova02.DAO.FilmesDAO;
import com.example.prova02.model.Filmes;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("filmes")
public class FilmesController {
    private FilmesDAO fDAO = new FilmesDAO();

    @GET
    @Produces("application/json")
    public Response listar() {
        return Response.ok(new Gson().toJson(fDAO.listaFilmes())).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response cadastra(Filmes filme) {
        try {
            fDAO.criaFilme(filme);
            return Response.status(Response.Status.CREATED).entity(filme).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("criatabela")
    @Produces("application/json")
    public Response criaTabela() {
        FilmesDAO fDAO = new FilmesDAO();
        fDAO.criaTabelaFilmes();
        return Response.ok(new Gson().toJson("Tabela FILMES criada com sucesso!")).build();
    }
}
