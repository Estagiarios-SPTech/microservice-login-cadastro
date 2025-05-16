package org.acme.model;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class CollaboratorTest {
    @Test
    public void testarToString(){
        User user = new User(1,
                "test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(1,
                "test",
                "test@gmail.com",
                "RT",
                "123");
        User gerente = new User(1,
                "test",
                "test@gmail.com",
                "Gerente",
                "123");

        Collaborator collaborator = new Collaborator(1 , user, rt, gerente);

        String esperado = "Collaborator{id=1, collaborator=User{id=1, name='test', email='test@gmail.com', role='Colaborador', password='123'}, rt=User{id=1, name='test', email='test@gmail.com', role='RT', password='123'}, manager=User{id=1, name='test', email='test@gmail.com', role='Gerente', password='123'}}";

        assertEquals(esperado, collaborator.toString());
    }
}