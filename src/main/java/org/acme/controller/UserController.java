package org.acme.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
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
//    @RolesAllowed({"Admin"})
    @Path("/cadastrar")
    public Response endpointCadastrar(User user){
        return Response.ok(userService.cadastrarUsuario(user)).build();
    }

    @POST
    @PermitAll()
    @Path("/login")
    public Response login(User usuario) {
        if (usuario == null || usuario.getEmail() == null || usuario.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Email e senha são obrigatórios")
                    .build();
        }

        try {
            return Response.ok(userService.login(usuario)).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
