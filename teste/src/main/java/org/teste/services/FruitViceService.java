package org.teste.services;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/fruit")
@RegisterRestClient
public interface FruitViceService {
    
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    FruityVice getFruityVice(@PathParam("name") String name);
}
