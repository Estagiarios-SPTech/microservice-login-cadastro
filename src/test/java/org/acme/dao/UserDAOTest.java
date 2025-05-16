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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@QuarkusTest
class UserDAOTest {

    public UserDAOTest() {}

    private Connection connection;

    private PreparedStatement preparedStatement;

    @InjectMock
    private Conexao conexao;

    private UserDAO userDAO;

    @BeforeEach
    public void setup(){
        this.conexao = new Conexao();
        this.userDAO = new UserDAO(this.conexao);
    }

    @Test
    void authenticateLoginSucess() throws SQLException {

        User userAuthenticated = userDAO.autenticarLogin("fulano@email.com", "senha123");

        assertNotNull(userAuthenticated, "O usuário autenticado não deve ser nulo");

        assertEquals("fulano@email.com", userAuthenticated.getEmail(), "O email deve corresponder");

    }

    @Test
    void authenticateLoginFailed() {
        User userAuthenticated = userDAO.autenticarLogin("fulao@email.com", "senha123!'");

        assertNull(userAuthenticated, "O usuário tem que ser nulo");

    }

    @Test
    void authenticateLoginEmpty() {
        User userAuthenticated = userDAO.autenticarLogin("","");

        assertNull(userAuthenticated, "O usuário autenticado tem que ser nulo");
    }
}