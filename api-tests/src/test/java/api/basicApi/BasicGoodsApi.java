package api.basicApi;

import config.TestConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BasicGoodsApi {

    private final RequestSpecification request;

    public BasicGoodsApi(RequestSpecification request) {
        this.request = request;
    }

    @Step("API: получить список товаров")
    public Response getGoods() {
        return given().spec(request)
                .when().get(TestConfig.GOODS_LIST_PATH);
    }

    @Step("API: добавить товар '{name}' с ценой {price}")
    public Response addGood(String name, int price) {
        return given().spec(request)
                .body(Map.of(
                        TestConfig.PRODUCT_NAME_FIELD, name,
                        TestConfig.PRODUCT_PRICE_FIELD, price
                ))
                .when().post(TestConfig.GOODS_ADD_PATH);
    }

    @Step("API: отправить невалидный товар без названия")
    public Response addGoodWithoutName(int price) {
        return given().spec(request)
                .body(Map.of(TestConfig.PRODUCT_PRICE_FIELD, price))
                .when().post(TestConfig.GOODS_ADD_PATH);
    }

    @Step("API: получить товар с id={id}")
    public Response getGood(long id) {
        return given().spec(request)
                .when().get(TestConfig.GOODS_ITEM_PATH, id);
    }

    @Step("API: изменить товар с id={id} на '{name}', цена {price}")
    public Response updateGood(long id, String name, int price) {
        return given().spec(request)
                .body(Map.of(
                        TestConfig.PRODUCT_NAME_FIELD, name,
                        TestConfig.PRODUCT_PRICE_FIELD, price
                ))
                .when().patch(TestConfig.GOODS_ITEM_PATH, id);
    }

    @Step("API: удалить товар с id={id}")
    public Response deleteGood(long id) {
        return given().spec(request)
                .when().delete(TestConfig.GOODS_ITEM_PATH, id);
    }
}
