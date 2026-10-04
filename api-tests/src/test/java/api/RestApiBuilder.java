package api;

import config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestApiBuilder {

    public RequestSpecification build() {
        return given()
                .baseUri(TestConfig.BASE_URL)
                .auth().preemptive().basic(TestConfig.ADMIN_LOGIN, TestConfig.ADMIN_PASSWORD)
                .contentType("application/json")
                .filter(new AllureRestAssured());
    }
}
