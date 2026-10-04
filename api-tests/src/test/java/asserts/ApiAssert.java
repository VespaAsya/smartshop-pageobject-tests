package asserts;

import config.TestConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiAssert {

    @Step("Проверить код ответа API: {expectedStatusCode}")
    public void statusCodeIs(Response response, int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.statusCode());
    }

    @Step("Проверить, что ответ API содержит товар '{name}'")
    public void responseContainsProduct(Response response, String name) {
        assertTrue(response.asString().contains(name));
    }

    @Step("Проверить id созданного товара")
    public long createdProductId(Response response) {
        long id = response.jsonPath().getLong(TestConfig.CREATED_ID_JSON_PATH);
        assertTrue(id > 0, "Product id must be positive");
        return id;
    }
}
