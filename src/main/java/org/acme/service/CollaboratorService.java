package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dao.CollaboratorDAO;
import org.acme.model.Employee;

import java.util.List;

@ApplicationScoped
public class CollaboratorService {
    @Inject
    CollaboratorDAO collaboratorDAO;

    @Inject
    UserService userService;

    public Employee cadastrarColaborador(Employee collaborator){
        if(collaborator == null){
            throw new RuntimeException("Collaborator não pode ser null");
        }
        System.out.println(collaborator);
        collaborator.setRt(userService.retornarUsuariosRelacionados(collaborator.getRt().getEmail()));
        collaborator.setManager(userService.retornarUsuariosRelacionados(collaborator.getManager().getEmail()));

        if(collaborator.getUser().getRole() == null ||
           !collaborator.getUser().getRole().equals("Colaborador")){
            throw new RuntimeException("Usuário cadastrado não é colaborador");
        }
        if(!collaborator.getRt().getRole().equals("RT") ||
           !collaborator.getManager().getRole().equals("Gerente")){
            throw new RuntimeException("Usuários informados nos campos errados");
        }
        collaborator.setUser(userService.cadastrarUsuario(collaborator.getUser()));
        return collaboratorDAO.insert(collaborator);
    }

    public List<Employee> acharColaboradoresRelacionados(Integer id){
        return collaboratorDAO.mostrarColaboradoresRelacionados(id);
    }
}
