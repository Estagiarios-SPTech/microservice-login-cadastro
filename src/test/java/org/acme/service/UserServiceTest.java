package org.acme.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import org.acme.dao.UserDAO;
import org.acme.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class UserServiceTest {
    @Mock
    private UserDAO userDAO;

    @Mock
    private TokenService tokenService;

    @InjectMock
    private UserService userService;

    @Test
    void login() {

    }
}