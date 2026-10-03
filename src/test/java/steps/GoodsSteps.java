package steps;

import api.GoodsApi;
import asserts.GoodsApiAssert;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GoodsSteps {

    private final GoodsApi goodsApi;
    private final GoodsApiAssert goodsApiAssert;

    public GoodsSteps(GoodsApi goodsApi, GoodsApiAssert goodsApiAssert) {
        this.goodsApi = goodsApi;
        this.goodsApiAssert = goodsApiAssert;
    }

    @Step("Создать товар через API и получить его id")
    public long createProduct(String name, int price) {
        Response response = goodsApi.addGood(name, price);
        goodsApiAssert.statusCodeIs(response, 200);
        return goodsApiAssert.createdProductId(response);
    }

    @Step("Обеспечить наличие товара '{name}' с ценой {price}")
    public void ensureProductExists(String name, int price) {
        Response listResponse = goodsApi.getGoods();
        goodsApiAssert.statusCodeIs(listResponse, 200);

        if (!listResponse.asString().contains("\"name\":\"" + name + "\"")) {
            Response addResponse = goodsApi.addGood(name, price);
            goodsApiAssert.statusCodeIs(addResponse, 200);
        }
    }
}
