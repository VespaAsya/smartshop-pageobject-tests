package config;

public final class TestConfig {

    public static final String BASE_URL = text("baseUrl", "http://localhost:8080");
    public static final String ADMIN_LOGIN = text("adminLogin", "admin");
    public static final String ADMIN_PASSWORD = text("adminPassword", "secret123");

    public static final String SHOP_PATH = text("shopPath", "/");
    public static final String ADMIN_PATH = text("adminPath", "/admin");
    public static final String GOODS_LIST_PATH = text("goodsListPath", "/goods/list");
    public static final String GOODS_ADD_PATH = text("goodsAddPath", "/goods/add");
    public static final String GOODS_ITEM_PATH = text("goodsItemPath", "/goods/{id}");
    public static final String PRODUCT_NAME_FIELD = text("productNameField", "name");
    public static final String PRODUCT_PRICE_FIELD = text("productPriceField", "price");
    public static final String CREATED_ID_JSON_PATH = text("createdIdJsonPath", "data.id");

    public static final int HTTP_OK = number("httpOk", 200);
    public static final int HTTP_BAD_REQUEST = number("httpBadRequest", 400);
    public static final int HTTP_NOT_FOUND = number("httpNotFound", 404);

    public static final String FIRST_PRODUCT_NAME = text("firstProductName", "стакан");
    public static final int FIRST_PRODUCT_PRICE = number("firstProductPrice", 13);
    public static final int FIRST_PRODUCT_COUNT = number("firstProductCount", 3);
    public static final String SECOND_PRODUCT_NAME = text("secondProductName", "благовония");
    public static final int SECOND_PRODUCT_PRICE = number("secondProductPrice", 25);

    public static final String API_PRODUCT_PREFIX = text("apiProductPrefix", "API товар");
    public static final String API_PRODUCT_BEFORE_UPDATE_PREFIX = text(
            "apiProductBeforeUpdatePrefix", "Товар до обновления"
    );
    public static final String API_UPDATED_PRODUCT_PREFIX = text(
            "apiUpdatedProductPrefix", "Обновлённый API товар"
    );
    public static final String API_DELETE_PRODUCT_PREFIX = text(
            "apiDeleteProductPrefix", "Товар для удаления"
    );
    public static final int API_CREATE_PRICE = number("apiCreatePrice", 120);
    public static final int API_BEFORE_UPDATE_PRICE = number("apiBeforeUpdatePrice", 130);
    public static final int API_UPDATED_PRICE = number("apiUpdatedPrice", 140);
    public static final int API_DELETE_PRICE = number("apiDeletePrice", 150);
    public static final int API_INVALID_PRICE = number("apiInvalidPrice", 100);

    public static final String UI_CREATED_PRODUCT_PREFIX = text(
            "uiCreatedProductPrefix", "PageObject товар"
    );
    public static final String UI_OLD_PRODUCT_PREFIX = text("uiOldProductPrefix", "старый товар");
    public static final String UI_NEW_PRODUCT_PREFIX = text("uiNewProductPrefix", "новый товар");
    public static final int UI_CREATED_PRODUCT_PRICE = number("uiCreatedProductPrice", 70);
    public static final int UI_OLD_PRODUCT_PRICE = number("uiOldProductPrice", 80);
    public static final int UI_NEW_PRODUCT_PRICE = number("uiNewProductPrice", 90);

    private TestConfig() {
    }

    private static String text(String name, String defaultValue) {
        return System.getProperty("smartshop." + name, defaultValue);
    }

    private static int number(String name, int defaultValue) {
        return Integer.parseInt(text(name, String.valueOf(defaultValue)));
    }
}
