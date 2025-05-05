package org.acme.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Collaborator;
import org.acme.service.CollaboratorService;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/collaborator")
public class CollaboratorController {
    @Inject
    CollaboratorService collaboratorService;

    @POST
    @RolesAllowed({"Admin", "RT"})
    @Path("/cadastrar")
    public Response cadastrar(Collaborator collaborator){
        return Response.ok(collaboratorService.cadastrarColaborador(collaborator)).build();
    }
}
