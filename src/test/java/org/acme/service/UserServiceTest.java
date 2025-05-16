package org.acme.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserServiceTest {
    @Inject
    UserService userService;

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

        User usuarioRetornado = userService.cadastrarUsuario(user);

        assertEquals(user, usuarioRetornado);
    }

    @Test
    public void cadastroRT(){
        User user = new User("test",
                "test@gmail.com",
                "RT",
                "123");

        User usuarioRetornado = userService.cadastrarUsuario(user);

        assertEquals(user, usuarioRetornado);
    }

    @Test
    public void cadastroColaborador(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");

        User usuarioRetornado = userService.cadastrarUsuario(user);

        assertEquals(user, usuarioRetornado);
    }

    @Test
    public void cadastroAdmin(){
        User user = new User("test",
                "test@gmail.com",
                "Admin",
                "123");

        User usuarioRetornado = userService.cadastrarUsuario(user);

        assertEquals(user, usuarioRetornado);
    }
}