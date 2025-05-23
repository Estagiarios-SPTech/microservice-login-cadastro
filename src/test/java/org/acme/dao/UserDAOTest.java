package org.acme.dao;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.model.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@QuarkusTest
class UserDAOTest {
    @InjectMock
    Conexao conexao;

    @Inject
    UserDAO userDAO;

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