package org.acme.service;

import dao.UserDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    private final UserDAO userDao;

    @Inject
    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public boolean login(String email, String password){
        return userDao.autenticarLogin(email, password);
    }

}
