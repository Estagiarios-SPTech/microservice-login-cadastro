package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dao.CollaboratorDAO;
import org.acme.model.Collaborator;

@ApplicationScoped
public class CollaboratorService {
    @Inject
    CollaboratorDAO collaboratorDAO;

    @Inject
    UserService userService;

    public Collaborator cadastrarColaborador(Collaborator collaborator){
        //validar para ser feito apenas o cadastro do colaborador
        collaborator.setCollaborator(userService.cadastrarUsuario(collaborator.getCollaborator()));
        collaborator.setRt(userService.retornarUsuariosRelacionados(collaborator.getRt().getId()));
        collaborator.setManager(userService.retornarUsuariosRelacionados(collaborator.getManager().getId()));

        if(collaborator.getCollaborator().getRole().equals("Colaborador") &&
           collaborator.getRt().getRole().equals("RT") &&
           collaborator.getManager().getRole().equals("Gerente")){
            return collaboratorDAO.insert(collaborator);
        }
        else{
            throw new RuntimeException("Usuários informados nos campos errados");
        }
    }
}
