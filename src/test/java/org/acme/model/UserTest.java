package org.acme.model;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserTest {
    @Test
    public void testarToString(){
        User user = new User(1,
                "test",
                "test@gmail.com",
                "Admin",
                "123");

        String esperado = "User{id=1, name='test', email='test@gmail.com', role='Admin', password='123'}";

        assertEquals(esperado, user.toString());
    }
}