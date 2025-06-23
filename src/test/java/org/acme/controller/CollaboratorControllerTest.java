package org.acme.controller;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.acme.model.Employee;
import org.acme.model.User;
import org.acme.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class CollaboratorControllerTest {
    @Inject
    TokenService tokenService;

    private String tokenGeradoAdmin;
    private String tokenGeradoRt;

    @BeforeEach
    public void gerarTokens() {
        User userAdmin = new User("test",
                "test@gmail.com",
                "Admin",
                "123");
        User userRt = new User("test",
                "test@gmail.com",
                "RT",
                "123");

        tokenGeradoAdmin = tokenService.gerarToken(userAdmin);
        tokenGeradoRt = tokenService.gerarToken(userRt);
    }

    @Test
    public void cadastrarColaboradorPeloAdmin(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(2);
        User gerente = new User(3);
        Employee collaborator = new Employee(user,
                rt,
                gerente);

        given()
                .contentType(ContentType.JSON)
                .body(collaborator)
                .header("Authorization", "Bearer " + tokenGeradoAdmin)
                .when()
                    .post("collaborator/cadastrar")
                .then()
                    .statusCode(200);
    }

    @Test
    public void cadastrarColaboradorPeloRT(){
        User user = new User("test",
                "test@gmail.com",
                "Colaborador",
                "123");
        User rt = new User(2);
        User gerente = new User(3);
        Employee collaborator = new Employee(user,
                rt,
                gerente);

        given()
                .contentType(ContentType.JSON)
                .body(collaborator)
                .header("Authorization", "Bearer " + tokenGeradoRt)
                .when()
                .post("collaborator/cadastrar")
                .then()
                .statusCode(200);
    }
}