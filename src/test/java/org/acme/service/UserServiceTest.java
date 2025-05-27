package org.acme.service;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.dao.UserDAO;
import jakarta.inject.Inject;
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
    public void cadastrarNameNulo(){
        User user = new User(null,
                "test@gmail.com",
                "Gerente",
                "123");

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Nenhum campo pode ser nulo", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarEmailNulo(){
        User user = new User("test",
                null,
                "Gerente",
                "123");

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Nenhum campo pode ser nulo", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarRoleNulo(){
        User user = new User("test",
                "test@gmail.com",
                null,
                "123");

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Nenhum campo pode ser nulo", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarPasswordNulo(){
        User user = new User("test",
                "test@gmail.com",
                "Gerente",
                null);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Nenhum campo pode ser nulo", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarNameVazio(){
        User user = new User("",
                "test@gmail.com",
                "Gerente",
                "123");

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Nenhum campo pode estar vazio", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarEmailVazio(){
        User user = new User("test",
                "",
                "Gerente",
                "123");

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Nenhum campo pode estar vazio", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarRoleVazio(){
        User user = new User("test",
                "test@gmail.com",
                "",
                "123");

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Nenhum campo pode estar vazio", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarPasswordVazio(){
        User user = new User("test",
                "test@gmail.com",
                "Gerente",
                "");

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Nenhum campo pode estar vazio", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarRoleNaoPermitido(){
        User user = new User("test",
                "test@gmail.com",
                "Estudante",
                "123");

        RuntimeException mensagemErro = assertThrows(RuntimeException.class,() -> {
            userService.cadastrarUsuario(user);
        });

        assertEquals( "Role não permitido", mensagemErro.getMessage());
    }

    @Test
    public void cadastroGerente(){
        User user = new User("test",
                "test@gmail.com",
                "Gerente",
                "123");

        when(userDAO.insert(user)).thenReturn(user);
        User usuarioRetornado = userService.cadastrarUsuario(user);

        assertEquals(user, usuarioRetornado);
    }

    @Test
    public void cadastroRT(){
        User user = new User("test",
                "test@gmail.com",
                "RT",
                "123");

        when(userDAO.insert(user)).thenReturn(user);
        User usuarioRetornado = userService.cadastrarUsuario(user);

        assertEquals(user, usuarioRetornado);
    }

    @Test
    public void cadastroColaborador(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");

        when(userDAO.insert(user)).thenReturn(user);
        User usuarioRetornado = userService.cadastrarUsuario(user);

        assertEquals(user, usuarioRetornado);
    }

    @Test
    public void cadastroAdmin(){
        User user = new User("test",
                "test@gmail.com",
                "Admin",
                "123");

        when(userDAO.insert(user)).thenReturn(user);
        User usuarioRetornado = userService.cadastrarUsuario(user);

        assertEquals(user, usuarioRetornado);
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
