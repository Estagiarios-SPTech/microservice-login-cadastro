package org.acme.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.model.Collaborator;
import org.acme.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CollaboratorServiceTest {
    @Inject
    CollaboratorService collaboratorService;

    @Test
    public void classeCollaboratorNull(){
        Collaborator collaborator = null;

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Collaborator não pode ser null" ,mensagemErro.getMessage());
    }

    @Test
    public void cadastrarColaboradorRoleNulo(){
        User user = new User("test",
                "test@gmail.com",
                null,
                "123");
        User rt = new User(2);
        User gerente = new User(3);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Usuário cadastrado não é colaborador", mensagemErro.getMessage());
    }

    @Test
    public void cadastrarColaboradorNulo() {
        User user = new User(null);
        User rt = new User(2);
        User gerente = new User(3);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () -> {
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Usuário cadastrado não é colaborador" ,mensagemErro.getMessage());
    }

    @Test
    public void cadastrarColaboradorRTNulo(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(null);
        User gerente = new User(3);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Os usuários associados não podem ter o id nulo" ,mensagemErro.getMessage());
    }

    @Test
    public void cadastrarColaboradorGerenteNulo(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(2);
        User gerente = new User(null);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Os usuários associados não podem ter o id nulo" ,mensagemErro.getMessage());
    }

    @Test
    public void colaboradorComRTCorreto(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(2);
        User gerente = new User(2);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Usuários informados nos campos errados" ,mensagemErro.getMessage());
    }

    @Test
    public void colaboradorComGerenteCorreto(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(3);
        User gerente = new User(3);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Usuários informados nos campos errados" ,mensagemErro.getMessage());
    }

    @Test
    public void cadastrarColaboradorComRoleErrado(){
        User user = new User("test",
                "test@gmail.com",
                "Admin",
                "123");
        User rt = new User(2);
        User gerente = new User(3);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Usuário cadastrado não é colaborador" ,mensagemErro.getMessage());
    }

    @Test
    public void colaboradorComResponsaveisInexistente(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(0);
        User gerente = new User(0);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Usuário inexistente" ,mensagemErro.getMessage());
    }

    @Test
    public void cadastrarColaborador(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(2);
        User gerente = new User(3);
        Collaborator collaborator = new Collaborator(user,
                rt,
                gerente);


        assertEquals(collaborator, collaboratorService.cadastrarColaborador(collaborator));
    }
}