package org.acme.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.dao.UserDAO;
import org.acme.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class UserServiceTest {

    public UserServiceTest() {
    }

    @Inject
    private UserDAO userDAO;

    @Inject
    private TokenService tokenService;

    @Inject
    private UserService userService;

    @Test
    void login() {

    }
}