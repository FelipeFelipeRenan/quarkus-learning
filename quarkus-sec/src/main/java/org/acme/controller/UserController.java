package org.acme.controller;

import java.util.List;

import org.acme.model.UserEntity;
import org.acme.repository.UserRepository;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserRepository repository;

    @GET
    public Response getUsers(){
        List<UserEntity> users = repository.findAll();

    }

}