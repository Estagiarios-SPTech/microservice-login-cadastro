package org.acme.dao;

import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;


@QuarkusTest
class UserDAOTest {
    @InjectMock
    Conexao conexao;

    @Inject
    UserDAO userDAO;

    public UserDAOTest() {}

    private Conexao mockConexao;
//    private UserDAO userDAO;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setup() throws SQLException {
        // conexao mockada
        mockConexao = Mockito.mock(Conexao.class);


        // userdao mockada
        userDAO = new UserDAO(mockConexao);

        // mock das coisas do mysql
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        // padrao de comportamento
        when(mockConexao.conectarBanco()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

@Test
void authenticateLoginSucess() throws SQLException {
    when(mockResultSet.next()).thenReturn(true);
    when(mockResultSet.getInt("id")).thenReturn(1);
    when(mockResultSet.getString("name")).thenReturn("Fulano");
    when(mockResultSet.getString("email")).thenReturn("fulano@email.com");
    when(mockResultSet.getString("role")).thenReturn("USER");
    when(mockResultSet.getString("password")).thenReturn("senha123");

    User userAuthenticated = userDAO.autenticarLogin("fulano@email.com", "senha123");

    assertNotNull(userAuthenticated, "O usuário autenticado não deve ser nulo");
    assertEquals("fulano@email.com", userAuthenticated.getEmail(), "O email deve corresponder");
    assertEquals(1, userAuthenticated.getId(), "O ID deve corresponder");
}

    @Test
    void authenticateLoginFailed() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        User userAuthenticated = userDAO.autenticarLogin("fulano@email.com", "senha123!'");

        assertNull(userAuthenticated, "O usuário tem que ser nulo");
    }

    @Test
    void authenticateLoginEmpty() throws SQLException {
        when(mockResultSet.next()).thenReturn(false);

        User userAuthenticated = userDAO.autenticarLogin("", "");

        assertNull(userAuthenticated, "O usuário autenticado tem que ser nulo");
    }

    @Test
    void authenticateLoginThrowsException() throws SQLException {
        when(mockConexao.conectarBanco()).thenThrow(new SQLException("Erro simulado de banco de dados"));

        try {
            userDAO.autenticarLogin("email@teste.com", "senha");
            fail("Deveria ter lançado RuntimeException");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Erro ao autenticar login"));
            assertTrue(e.getCause() instanceof SQLException);
        }
    }

    @Test
    public void erroAoRealizarInsercao() throws SQLException {
        User user = new User();

        when(conexao.conectarBanco()).thenThrow(new SQLException());

        RuntimeException erro = assertThrows(RuntimeException.class, () ->{
            userDAO.insert(user);
        });

        assertEquals("Insercao mal-sucedida", erro.getMessage());
    }

    @Test
    public void chavePrimariaNaoCriada() throws SQLException {
        User user = new User();

        Connection mockConnection = spy(Connection.class);
        PreparedStatement mockPreparedStatement = spy(PreparedStatement.class);
        ResultSet mockResultSet = spy(ResultSet.class);

        when(conexao.conectarBanco()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        assertNull(userDAO.insert(user), "O método deve retornar null");
    }

    @Test
    public void erroAoEncontrarUser() throws SQLException {
        when(conexao.conectarBanco()).thenThrow(new SQLException());

        RuntimeException erro = assertThrows(RuntimeException.class, () ->{
            userDAO.findById(2);
        });

        assertEquals("Erro ao procurar o usuário", erro.getMessage());
    }
}
