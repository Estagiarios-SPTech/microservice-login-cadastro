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
}
