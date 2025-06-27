package org.acme.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Employee;
import org.acme.service.CollaboratorService;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/collaborator")
public class CollaboratorController {
    @Inject
    CollaboratorService collaboratorService;

    @POST
//    @RolesAllowed({"Admin", "RT"})
    @Path("/cadastrar")
    public Response cadastrar(Employee collaborator){
        return Response.ok(collaboratorService.cadastrarColaborador(collaborator)).build();
    }

    @GET
    @Path("/employees/{id}")
    public Response teste(@PathParam("id") Integer id){
        return Response.ok(collaboratorService.acharColaboradoresRelacionados(id)).build();
    }
}
