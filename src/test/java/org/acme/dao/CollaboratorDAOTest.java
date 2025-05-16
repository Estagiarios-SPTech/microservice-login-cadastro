package org.acme.dao;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.Conexao;
import org.acme.model.Collaborator;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
}