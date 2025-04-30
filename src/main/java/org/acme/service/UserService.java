package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.dao.UserDAO;
import org.acme.model.User;

@ApplicationScoped
public class UserService {
    @Inject
    UserDAO userDAO;

    public User cadastrarUsuario(User user){
        if(user.getName() == null || user.getEmail() == null ||
        user.getRole() == null || user.getPassword() == null
        ){
            throw new RuntimeException("Nenhum campo pode ser nulo");
        }
        if(user.getName().isBlank() || user.getEmail().isBlank() ||
          user.getRole().isBlank() || user.getPassword().isBlank()
        ){
            throw new RuntimeException("Nenhum campo pode estar vazio");
        }
        return userDAO.insert(user);
    }
}
