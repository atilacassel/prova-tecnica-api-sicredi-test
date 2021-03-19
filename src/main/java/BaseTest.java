package main.java;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public abstract class BaseTest {
    protected final static String PATH_BASE = "http://localhost:8080/api/v1/";
    protected final static String PATH_SIMULACOES = PATH_BASE + "simulacoes/";
    protected final static String PATH_RESTRICOES = PATH_BASE + "restricoes/";

    protected RequestSpecification prepararRequestComBody(String requestBody) {
        return given().contentType(JSON).body(requestBody).when();
    }

    protected RequestSpecification prepararRequest() {
        return given().contentType(JSON).when();
    }
}
