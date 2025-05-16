package org.acme.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.acme.model.User;
import org.acme.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class UserControllerTest {
    @Inject
    TokenService tokenService;

    private String tokenGeradoAdmin;

    @BeforeEach
    public void gerarToken() {
        User userAdmin = new User("test",
                "test@gmail.com",
                "Admin",
                "123");
        tokenGeradoAdmin = tokenService.gerarToken(userAdmin);
    }

    @Test
    public void cadastrarComRoleColaborador(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                    .post("user/cadastrar")
                .then()
                    .statusCode(401);
    }

    @Test
    public void cadastrarComRoleColaboradorAutenticado(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");

        given()
                .header("Authorization", "Bearer " + tokenGeradoAdmin)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("user/cadastrar")
                .then()
                .statusCode(400);
    }

    @Test
    public void cadastrarSemAutenticar(){
        User user = new User("test",
                "test@gmail.com",
                "Admin",
                "123");

        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("user/cadastrar")
                .then()
                .statusCode(401);
    }

    @Test
    public void cadastrarCaminhoPerfeito(){
        User user = new User("test",
                "test@gmail.com",
                "Gerente",
                "123");


        given()
                .header("Authorization", "Bearer " + tokenGeradoAdmin)
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                    .post("user/cadastrar")
                .then()
                    .statusCode(200);
    }
}