package org.acme.controller;

import org.acme.dtos.LoginRequest;
import org.acme.dtos.TokenResponse;
import org.acme.model.UserEntity;
import org.acme.service.AuthService;
import org.acme.util.JWTGenerator;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    private AuthService authService;
    @Inject 
    private JWTGenerator jwtGenerator;

    @POST
    @Path("/login")
    @Transactional
    public Response login(LoginRequest request) {
        try {
            UserEntity user = authService.authenticate(request.getUsername(), request.getPassword());
            if (user != null) {
                System.out.println("entrou no if");
                System.out.println(user.password);
                System.out.println(user.username);
                System.out.println(user.getId());
                // Gera token JWT
                String token = jwtGenerator.generateToken(user.getId(), user.getUsername());
                System.out.println(token);
                return Response.ok(new TokenResponse(token)).build();
            } else {
                System.out.println("entrou no else");
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            // Log de exceção para depuração
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
