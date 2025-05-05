package org.acme.controller;

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
    public UserService userService;

    @POST
    @Transactional
    @Path("/login")
    public Response login(User usuario){
        return Response.ok(userService.login(usuario.getEmail(), usuario.getPassword())).build();
    }
}
