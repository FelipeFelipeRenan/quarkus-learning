package org.acme.controller;

import org.acme.dtos.LoginRequest;
import org.acme.dtos.TokenResponse;
import org.acme.model.UserEntity;
import org.acme.service.AuthService;
import org.acme.util.JWTGenerator;

import io.agroal.api.AgroalDataSource;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
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
    public Response login(LoginRequest request) {
        UserEntity user = authService.authenticate(request.getUsername(), request.getPassword());
        if (user != null) {
            // Gera token JWT
            String token = jwtGenerator.generateToken(user.getId(), user.getUsername());
            return Response.ok(new TokenResponse(token)).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
