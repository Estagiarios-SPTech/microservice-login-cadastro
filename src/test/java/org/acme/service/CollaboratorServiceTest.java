package org.acme.service;

import io.quarkus.test.junit.QuarkusTest;
import org.acme.dao.CollaboratorDAO;
import org.acme.model.Employee;
import org.acme.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
class CollaboratorServiceTest {
    @Mock
    CollaboratorDAO collaboratorDAO;

    @Mock
    UserService userService;

    @InjectMocks
    CollaboratorService collaboratorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void classeCollaboratorNull(){
        Employee collaborator = null;

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
        Employee collaborator = new Employee(user,
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
        Employee collaborator = new Employee(user,
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
        Employee collaborator = new Employee(user,
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
        Employee collaborator = new Employee(user,
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
        rt.setRole("RT");
        User gerente = rt;
        Employee collaborator = new Employee(user,
                rt,
                gerente);

        when(userService.retornarUsuariosRelacionados(any(Integer.class))).thenReturn(rt);

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
        User gerente = new User(2);
        gerente.setRole("Gerente");
        User rt = gerente;
        Employee collaborator = new Employee(user,
                rt,
                gerente);

        when(userService.retornarUsuariosRelacionados(any(Integer.class))).thenReturn(gerente);

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
        Employee collaborator = new Employee(user,
                rt,
                gerente);

        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
            collaboratorService.cadastrarColaborador(collaborator);
        });

        assertEquals("Usuário cadastrado não é colaborador" ,mensagemErro.getMessage());
    }

//    @Test
//    public void colaboradorComResponsaveisInexistente(){
//        User user = new User("test",
//                "test@gmail.com",
//                "Colaborador",
//                "123");
//        User rt = new User(0);
//        User gerente = new User(0);
//        Collaborator collaborator = new Collaborator(user,
//                rt,
//                gerente);
//
//        RuntimeException mensagemErro = assertThrows(RuntimeException.class, () ->{
//            collaboratorService.cadastrarColaborador(collaborator);
//        });
//
//        assertEquals("Usuário inexistente" ,mensagemErro.getMessage());
//    }

    @Test
    public void cadastrarColaborador(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(2);
        rt.setRole("RT");
        User gerente = new User(3);
        gerente.setRole("Gerente");
        Employee collaborator = new Employee(user,
                rt,
                gerente);

        when(userService.retornarUsuariosRelacionados(2)).thenReturn(rt);

        when(userService.retornarUsuariosRelacionados(3)).thenReturn(gerente);

        when(collaboratorDAO.insert(collaborator)).thenReturn(collaborator);

        assertEquals(collaborator, collaboratorService.cadastrarColaborador(collaborator));
    }
}