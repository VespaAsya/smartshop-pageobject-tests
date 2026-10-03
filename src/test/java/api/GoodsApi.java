package api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GoodsApi {

    private final RequestSpecification request;

    public GoodsApi(String baseUrl, String username, String password) {
        request = given()
                .baseUri(baseUrl)
                .auth().preemptive().basic(username, password)
                .contentType("application/json")
                .filter(new AllureRestAssured());
    }

    @Step("API: получить список товаров")
    public Response getGoods() {
        return given().spec(request)
                .when().get("/goods/list");
    }

    @Step("API: добавить товар '{name}' с ценой {price}")
    public Response addGood(String name, int price) {
        return given().spec(request)
                .body("{\"name\":\"" + name + "\",\"price\":" + price + "}")
                .when().post("/goods/add");
    }

    @Step("API: отправить невалидный товар без названия")
    public Response addGoodWithoutName(int price) {
        return given().spec(request)
                .body("{\"price\":" + price + "}")
                .when().post("/goods/add");
    }

    @Step("API: получить товар с id={id}")
    public Response getGood(long id) {
        return given().spec(request)
                .when().get("/goods/{id}", id);
    }

    @Step("API: изменить товар с id={id} на '{name}', цена {price}")
    public Response updateGood(long id, String name, int price) {
        return given().spec(request)
                .body("{\"name\":\"" + name + "\",\"price\":" + price + "}")
                .when().patch("/goods/{id}", id);
    }

    @Step("API: удалить товар с id={id}")
    public Response deleteGood(long id) {
        return given().spec(request)
                .when().delete("/goods/{id}", id);
    }
}
