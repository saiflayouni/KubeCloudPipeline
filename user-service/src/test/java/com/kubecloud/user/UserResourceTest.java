package com.kubecloud.user;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UserResourceTest {

    @Test
    public void testGetAllEndpoint() {
        given()
          .when().get("/users")
          .then()
             .statusCode(200);
    }

    @Test
    public void testGetEndpoint() {
        given()
          .pathParam("id", "1")
          .when().get("/users/{id}")
          .then()
             .statusCode(200)
             .body("name", is("Alice"));
    }
}
