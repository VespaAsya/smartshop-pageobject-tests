package tests;

import api.GoodsApi;
import asserts.GoodsApiAssert;
import config.TestConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.GoodsSteps;

@Tag("api")
class GoodsApiTest {

    private GoodsApi goodsApi;
    private GoodsApiAssert goodsApiAssert;
    private GoodsSteps goodsSteps;

    @BeforeEach
    void setUp() {
        goodsApi = new GoodsApi(TestConfig.BASE_URL, TestConfig.ADMIN_LOGIN, TestConfig.ADMIN_PASSWORD);
        goodsApiAssert = new GoodsApiAssert();
        goodsSteps = new GoodsSteps(goodsApi, goodsApiAssert);
    }

    @Test
    void createAndGetProductTest() {
        String name = "API товар " + System.currentTimeMillis();
        long id = goodsSteps.createProduct(name, 120);

        Response response = goodsApi.getGood(id);

        goodsApiAssert.statusCodeIs(response, 200);
        goodsApiAssert.responseContainsProduct(response, name);
    }

    @Test
    void updateProductTest() {
        long id = goodsSteps.createProduct("Товар до обновления " + System.currentTimeMillis(), 130);
        String updatedName = "Обновлённый API товар " + System.currentTimeMillis();

        Response updateResponse = goodsApi.updateGood(id, updatedName, 140);
        Response getResponse = goodsApi.getGood(id);

        goodsApiAssert.statusCodeIs(updateResponse, 200);
        goodsApiAssert.statusCodeIs(getResponse, 200);
        goodsApiAssert.responseContainsProduct(getResponse, updatedName);
    }

    @Test
    void deleteProductTest() {
        long id = goodsSteps.createProduct("Товар для удаления " + System.currentTimeMillis(), 150);

        Response deleteResponse = goodsApi.deleteGood(id);
        Response getResponse = goodsApi.getGood(id);

        goodsApiAssert.statusCodeIs(deleteResponse, 200);
        goodsApiAssert.statusCodeIs(getResponse, 404);
    }

    @Test
    void invalidProductReturnsBadRequestTest() {
        Response response = goodsApi.addGoodWithoutName(100);

        goodsApiAssert.statusCodeIs(response, 400);
    }

    @Test
    void getGoodsListTest() {
        Response response = goodsApi.getGoods();

        goodsApiAssert.statusCodeIs(response, 200);
    }
}
