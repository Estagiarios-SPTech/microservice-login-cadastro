package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dao.UserDAO;
import org.acme.model.User;

@ApplicationScoped
public class UserService {
    @Inject
    UserDAO userDAO;

    @Inject
    TokenService tokenService;

    public User cadastrarUsuario(User user){
        if(user.getName() == null || user.getEmail() == null ||
        user.getRole() == null || user.getPassword() == null
        ){
            throw new RuntimeException("Nenhum campo pode ser nulo");
        }
        else if(user.getName().isBlank() || user.getEmail().isBlank() ||
          user.getRole().isBlank() || user.getPassword().isBlank()
        ){
            throw new RuntimeException("Nenhum campo pode estar vazio");
        }
        else if(!user.getRole().equals("Admin") && !user.getRole().equals("RT") &&
                !user.getRole().equals("Gerente") && !user.getRole().equals("Colaborador")){
            throw new RuntimeException("Role não permitido");
        }
        return userDAO.insert(user);
    }

    public Boolean verificarEmailExistente(String email){
        if(email == null){
            throw new RuntimeException("O e-mail não pode ser nulo");
        }
        else if(email.isEmpty()){
            throw new RuntimeException("O e-mail não pode estar vazio");
        }

        return userDAO.verificarEmailExistente(email);
    }

    public User retornarUsuariosRelacionados(String name){
        if(userDAO.findByName(name) == null){
            throw new RuntimeException("Usuário inexistente");
        }
        return userDAO.findByName(name);
    }

    public String login(User user){
        User usuarioEncontrado =  userDAO.autenticarLogin(user.getEmail(), user.getPassword());
        if(usuarioEncontrado == null){
            throw new RuntimeException("Login inválido");
        }
        return tokenService.gerarToken(usuarioEncontrado);
    }
}
