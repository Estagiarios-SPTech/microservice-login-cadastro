package org.acme.controller;

import io.quarkus.test.InjectMock;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.model.User;
import org.acme.service.UserService;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@QuarkusTest
class UserControllerTest {
//        @InjectMock
//        UserService userService;
//
//        @Test
//        public void testLoginSuccess() { //verica só se o endoint funciona e se recebe um json, o token nao é realmente validado
//            User user = new User();
//            user.setEmail("fulano@email.com");
//            user.setPassword("senha123");
//
//            String mockToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ";
//            when(userService.login(any(User.class))).thenReturn(mockToken);
//
//            given()
//                    .contentType(ContentType.JSON)
//                    .body("{\"email\":\"fulano@email.com\",\"password\":\"senha123\"}")
//                    .when()
//                    .post("/user/login")
//                    .then()
//                    .statusCode(200)
//                    .body(is(mockToken));
//        }
//
//        @Test
//        public void testLoginWithMissingCredentials() {
//            given()
//                    .contentType(ContentType.JSON)
//                    .body("{\"email\":\"fulano@email.com\"}")
//                    .when()
//                    .post("/user/login")
//                    .then()
//                    .statusCode(400);
//        }
//
//        @Test
//        public void testLoginWithInvalidCredentials() {
//            when(userService.login(any(User.class))).thenThrow(new RuntimeException("Login inválido"));
//
//            given()
//                    .contentType(ContentType.JSON)
//                    .body("{\"email\":\"fulano@email.com\",\"password\":\"senha_errada\"}")
//                    .when()
//                    .post("/user/login")
//                    .then()
//                    .statusCode(500);
//        }
//
//        @Test
//        public void testLoginWithEmptyRequest() {
//            given()
//                    .contentType(ContentType.JSON)
//                    .body("{}")
//                    .when()
//                    .post("/user/login")
//                    .then()
//                    .statusCode(400);
//        }
//
//    @Test
//    public void testLoginWithNoRequest() {
//        given()
//                .contentType(ContentType.JSON)
//                .body("")
//                .when()
//                .post("/user/login")
//                .then()
//                .statusCode(400);
//    }

}