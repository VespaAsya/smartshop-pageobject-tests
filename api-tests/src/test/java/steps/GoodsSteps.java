package steps;

import api.basicApi.BasicGoodsApi;
import asserts.ApiAssert;
import config.TestConfig;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GoodsSteps {

    private final BasicGoodsApi goodsApi;
    private final ApiAssert apiAssert;

    public GoodsSteps(BasicGoodsApi goodsApi, ApiAssert apiAssert) {
        this.goodsApi = goodsApi;
        this.apiAssert = apiAssert;
    }

    @Step("Создать товар через API и получить его id")
    public long createProduct(String name, int price) {
        Response response = goodsApi.addGood(name, price);
        apiAssert.statusCodeIs(response, TestConfig.HTTP_OK);
        return apiAssert.createdProductId(response);
    }
}
