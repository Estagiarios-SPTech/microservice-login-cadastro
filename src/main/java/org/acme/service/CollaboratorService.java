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
        if(collaborator == null){
            throw new RuntimeException("Collaborator não pode ser null");
        }

        if(collaborator.getRt().getId() == null ||
        collaborator.getManager().getId() == null){
            throw new RuntimeException("Os usuários associados não podem ter o id nulo");
        }

        collaborator.setRt(userService.retornarUsuariosRelacionados(collaborator.getRt().getId()));
        collaborator.setManager(userService.retornarUsuariosRelacionados(collaborator.getManager().getId()));

        if(collaborator.getCollaborator().getRole() == null ||
           !collaborator.getCollaborator().getRole().equals("Colaborador")){
            throw new RuntimeException("Usuário cadastrado não é colaborador");
        }
        else if(collaborator.getCollaborator().getRole().equals("Colaborador") &&
           collaborator.getRt().getRole().equals("RT") &&
           collaborator.getManager().getRole().equals("Gerente")){
            collaborator.setCollaborator(userService.cadastrarUsuario(collaborator.getCollaborator()));
            return collaboratorDAO.insert(collaborator);
        }
        else{
            throw new RuntimeException("Usuários informados nos campos errados");
        }
    }
}
