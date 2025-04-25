package com.restassured.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAPITest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void testGetPost() {
        given()
            .when()
            .get("/posts/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("userId", equalTo(1));
    }

    @Test
    public void testCreatePost() {
        String requestBody = "{"
            + "\"title\": \"foo\","
            + "\"body\": \"bar\","
            + "\"userId\": 1"
            + "}";

        given()
            .header("Content-type", "application/json")
            .and()
            .body(requestBody)
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("id", notNullValue());
    }
} 