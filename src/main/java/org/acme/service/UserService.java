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

    public User retornarUsuariosRelacionados(Integer id){
        if(userDAO.findById(id) != null){
            throw new RuntimeException("Usuário inexistente");
        }
        return userDAO.findById(id);
    }
    private final UserDAO userDao;

    @Inject
    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public boolean login(String email, String password){
        return userDao.autenticarLogin(email, password);
    }

}
