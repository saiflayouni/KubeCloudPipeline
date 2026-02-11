package com.kubecloudpipeline.task.controller;

import com.kubecloudpipeline.task.model.Task;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class TaskResourceTest {

    @Test
    void testGetAllTasks() {
        given()
          .when().get("/tasks")
          .then()
             .statusCode(200)
             .body(is("[]")); // Initially empty
    }

    @Test
    void testCreateAndGetTask() {
        Task newTask = new Task("Sprint Planning", "Plan Sprint-02 tasks");

        // 1. Create Task
        String taskId = given()
            .contentType(ContentType.JSON)
            .body(newTask)
            .when().post("/tasks")
            .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("title", is(newTask.getTitle()))
            .extract().path("id");

        // 2. Get Task by ID
        given()
            .when().get("/tasks/" + taskId)
            .then()
            .statusCode(200)
            .body("id", is(taskId))
            .body("title", is(newTask.getTitle()));
    }
}
