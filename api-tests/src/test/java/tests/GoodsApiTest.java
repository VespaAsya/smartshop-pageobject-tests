package tests;

import api.RestApiBuilder;
import api.basicApi.BasicGoodsApi;
import asserts.ApiAssert;
import config.TestConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.GoodsSteps;

@Tag("api")
class GoodsApiTest {

    private BasicGoodsApi goodsApi;
    private ApiAssert apiAssert;
    private GoodsSteps goodsSteps;

    @BeforeEach
    void setUp() {
        goodsApi = new BasicGoodsApi(new RestApiBuilder().build());
        apiAssert = new ApiAssert();
        goodsSteps = new GoodsSteps(goodsApi, apiAssert);
    }

    @Test
    @Tag("api-smoke")
    void createAndGetProductTest() {
        String name = uniqueName(TestConfig.API_PRODUCT_PREFIX);
        long id = goodsSteps.createProduct(name, TestConfig.API_CREATE_PRICE);

        Response response = goodsApi.getGood(id);

        apiAssert.statusCodeIs(response, TestConfig.HTTP_OK);
        apiAssert.responseContainsProduct(response, name);
    }

    @Test
    void updateProductTest() {
        long id = goodsSteps.createProduct(
                uniqueName(TestConfig.API_PRODUCT_BEFORE_UPDATE_PREFIX),
                TestConfig.API_BEFORE_UPDATE_PRICE
        );
        String updatedName = uniqueName(TestConfig.API_UPDATED_PRODUCT_PREFIX);

        Response updateResponse = goodsApi.updateGood(id, updatedName, TestConfig.API_UPDATED_PRICE);
        Response getResponse = goodsApi.getGood(id);

        apiAssert.statusCodeIs(updateResponse, TestConfig.HTTP_OK);
        apiAssert.statusCodeIs(getResponse, TestConfig.HTTP_OK);
        apiAssert.responseContainsProduct(getResponse, updatedName);
    }

    @Test
    void deleteProductTest() {
        long id = goodsSteps.createProduct(
                uniqueName(TestConfig.API_DELETE_PRODUCT_PREFIX),
                TestConfig.API_DELETE_PRICE
        );

        Response deleteResponse = goodsApi.deleteGood(id);
        Response getResponse = goodsApi.getGood(id);

        apiAssert.statusCodeIs(deleteResponse, TestConfig.HTTP_OK);
        apiAssert.statusCodeIs(getResponse, TestConfig.HTTP_NOT_FOUND);
    }

    @Test
    void invalidProductReturnsBadRequestTest() {
        Response response = goodsApi.addGoodWithoutName(TestConfig.API_INVALID_PRICE);

        apiAssert.statusCodeIs(response, TestConfig.HTTP_BAD_REQUEST);
    }

    @Test
    @Tag("api-smoke")
    void getGoodsListTest() {
        Response response = goodsApi.getGoods();

        apiAssert.statusCodeIs(response, TestConfig.HTTP_OK);
    }

    private String uniqueName(String prefix) {
        return prefix + " " + System.currentTimeMillis();
    }
}
