package org.acme.service;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.dao.UserDAO;
import org.acme.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loginSuccessful() {
        User loginRequest = new User();
        loginRequest.setEmail("fulano@email.com");
        loginRequest.setPassword("senha123");

        User foundUser = new User(1, "Fulano", "fulano@email.com", "Colaborador", "senha123");
        String expectedToken = "jwt-token-123456";

        when(userDAO.autenticarLogin("fulano@email.com", "senha123")).thenReturn(foundUser);
        when(tokenService.gerarToken(foundUser)).thenReturn(expectedToken);

        String resultToken = userService.login(loginRequest);

        assertEquals(expectedToken, resultToken, "O token retornado deve ser igual ao esperado");
    }

    @Test
    void loginUserNotFound() {
        User loginRequest = new User();
        loginRequest.setEmail("usuario@inexistente.com");
        loginRequest.setPassword("senha123");

        when(userDAO.autenticarLogin("usuario@inexistente.com", "senha123")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.login(loginRequest);
        }, "Deve lançar RuntimeException");

        assertEquals("Login inválido", exception.getMessage(), "A mensagem de erro deve ser: Login invalido");
    }

    @Test
    void loginWithNullEmail() {
        User loginRequest = new User();
        loginRequest.setEmail(null);
        loginRequest.setPassword("senha123");

        when(userDAO.autenticarLogin(null, "senha123")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.login(loginRequest);
        }, "Deve lançar RuntimeException");

        assertEquals("Login inválido", exception.getMessage(), "A mensagem de erro deve ser: Login invalido");
    }

    @Test
    void loginWithEmptyEmail() {
        User loginRequest = new User();
        loginRequest.setEmail("");
        loginRequest.setPassword("senha123");

        when(userDAO.autenticarLogin("", "senha123")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.login(loginRequest);
        }, "Deve lançar RuntimeException");

        assertEquals("Login inválido", exception.getMessage(), "A mensagem de erro deve ser: Login invalido");
    }

    @Test
    void loginWithNullPassword() {
        User loginRequest = new User();
        loginRequest.setEmail("fulano@email.com");
        loginRequest.setPassword(null);

        when(userDAO.autenticarLogin("fulano@email.com", null)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.login(loginRequest);
        }, "Deve lançar RuntimeException");

        assertEquals("Login inválido", exception.getMessage(), "A mensagem de erro deve ser: Login invalido");
    }

    @Test
    void loginWithEmptyPassword() {
        User loginRequest = new User();
        loginRequest.setEmail("fulano@email.com");
        loginRequest.setPassword("");

        when(userDAO.autenticarLogin("fulano@email.com", "")).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.login(loginRequest);
        }, "Deve lançar RuntimeException");

        assertEquals("Login inválido", exception.getMessage(), "A mensagem de erro deve ser: Login invalido");
    }

    @Test
    void loginWithNullUser() {
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            userService.login(null);
        }, "Deve lançar NullPointerException");
    }
}