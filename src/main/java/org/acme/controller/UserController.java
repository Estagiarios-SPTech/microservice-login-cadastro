package org.acme.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.User;
import org.acme.service.UserService;

@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/user")
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.User;
import org.acme.service.UserService;

@Path("teste")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    UserService userService;

    @POST
    @Path("/cadastrar")
    public Response endpointCadastrar(User user){
        if(user.getRole().equals("Colaborador")){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.ok(userService.cadastrarUsuario(user)).build();
    }

    @POST
    @Transactional
    @Path("/login")
    public Response login(User usuario){
        return Response.ok(userService.login(usuario.getEmail(), usuario.getPassword())).build();
    }
}
