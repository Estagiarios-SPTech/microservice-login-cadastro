package org.acme.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.dao.UserDAO;
import org.acme.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class UserServiceTest {
//
//    public UserServiceTest() {
//    }
//
//    @Mock
//    private UserDAO userDAO;
//
//    @Mock
//    private TokenService tokenService;
//
//
//    @Inject
//    private UserService userService;
//
//    private User validUser;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        validUser = new User();
//        validUser.setEmail("fulano@email.com");
//        validUser.setPassword("senha123");
//    }
//
//    @Test
//     void loginIsNull() {
//        User user = userService.login();
//        assertNull(user);
//    }
//
//    @Test
//    void loginOk(){
//
//    }
}