package org.teste.controller;

import java.util.List;

import org.teste.model.Fruit;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/fruit")
public class FruitResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> fruits(){
        return Fruit.listAll();
    }

    @Transactional
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response newFruit(Fruit fruit){
        fruit.id = null;
        fruit.persist();
        return Response.status(Status.CREATED).entity(fruit).build();
    }
}
