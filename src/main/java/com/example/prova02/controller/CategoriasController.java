package com.example.prova02.controller;

import com.example.prova02.DAO.CategoriasDAO;
import com.example.prova02.model.Categorias;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("categorias")
public class CategoriasController {
    private CategoriasDAO cDAO = new CategoriasDAO();

    @GET
    @Produces("application/json")
    public Response listar() {
        return Response.ok(new Gson().toJson(cDAO.listaCategorias())).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response cadastra(Categorias categoria) {
        try {
            cDAO.cadastraCategoria(categoria);
            return Response.status(Response.Status.CREATED).entity(categoria).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
