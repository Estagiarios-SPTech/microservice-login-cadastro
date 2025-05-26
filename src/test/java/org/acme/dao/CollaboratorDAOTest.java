package org.acme.dao;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.model.Collaborator;
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
class CollaboratorDAOTest {
    @InjectMock
    Conexao conexao;

    @Inject
    CollaboratorDAO collaboratorDAO;

    @Test
    public void erroAoRealizarInsercao() throws SQLException {
        Collaborator collaborator = new Collaborator();

        when(conexao.conectarBanco()).thenThrow(new SQLException());

        RuntimeException erro = assertThrows(RuntimeException.class, () ->{
            collaboratorDAO.insert(collaborator);
        });

        assertEquals("Insercao mal-sucedida", erro.getMessage());
    }

    @Test
    public void insertSemChaveGerada() throws SQLException {
        User user = new User(1);
        User rt = new User(2);
        User gerente = new User(3);
        Collaborator collaborator = new Collaborator(user, rt, gerente);

        Connection mockConnection = spy(Connection.class);
        PreparedStatement mockPreparedStatement = spy(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(conexao.conectarBanco()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(false);

        Collaborator resultado = collaboratorDAO.insert(collaborator);

        assertNull(resultado, "O método deve retornar null quando não há chaves geradas");
    }
}