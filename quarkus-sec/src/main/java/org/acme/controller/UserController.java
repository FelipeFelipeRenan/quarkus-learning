package org.acme.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.acme.model.UserEntity;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @GET
    public List<UserEntity> getAll(){
        List<UserEntity> users = UserEntity.listAll();
        System.out.println("Total de usuários encontrados: " + users.size());

        return users.stream().collect(Collectors.toList());
    }

}
