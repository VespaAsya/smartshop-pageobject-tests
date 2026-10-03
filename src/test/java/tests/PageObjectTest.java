package tests;

import api.GoodsApi;
import asserts.AdminPageAssert;
import asserts.GoodsApiAssert;
import asserts.MainPageAssert;
import config.TestConfig;
import pages.AdminLoginPage;
import pages.AdminPage;
import pages.MainPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import steps.GoodsSteps;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Tag("ui")
public class PageObjectTest extends UiTestBase {

    MainPage mainPage;
    MainPageAssert mainPageAssert;

    AdminLoginPage loginPage;

    AdminPage adminPage;
    AdminPageAssert adminPageAssert;

    @BeforeAll
    static void prepareProducts() {
        GoodsApi goodsApi = new GoodsApi(
                TestConfig.BASE_URL,
                TestConfig.ADMIN_LOGIN,
                TestConfig.ADMIN_PASSWORD
        );
        GoodsSteps goodsSteps = new GoodsSteps(goodsApi, new GoodsApiAssert());

        goodsSteps.ensureProductExists("стакан", 13);
        goodsSteps.ensureProductExists("благовония", 25);
    }

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        mainPageAssert = new MainPageAssert(mainPage);

        loginPage = new AdminLoginPage();

        adminPage = new AdminPage();
        adminPageAssert = new AdminPageAssert(adminPage);
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @Test
    void buyThreeProductsTest() {
        mainPage.openPage();

        mainPage.setProductCount("стакан", "3");
        mainPage.addProduct("стакан");
        mainPage.openCart();

        mainPageAssert.cartIsVisible();
        mainPageAssert.cartContainsProduct("стакан");
        mainPageAssert.totalPriceIs("39");

        mainPage.makeOrder();

        mainPageAssert.orderNotificationIsVisible();
    }

    @Test
    void checkTotalPriceTest() {
        mainPage.openPage();

        mainPage.addProduct("стакан");
        mainPage.addProduct("благовония");
        mainPage.openCart();

        mainPageAssert.cartContainsProduct("стакан");
        mainPageAssert.cartContainsProduct("благовония");
        mainPageAssert.totalPriceIs("38");
    }

    @Test
    void addProductThroughAdminTest() {
        String productName =
                "PageObject товар " + System.currentTimeMillis();

        loginPage.login(TestConfig.ADMIN_LOGIN, TestConfig.ADMIN_PASSWORD);

        adminPageAssert.nameFieldIsVisible();
        adminPageAssert.priceFieldIsVisible();
        adminPageAssert.addButtonIsVisible();

        adminPage.addProduct(productName, "70");

        adminPageAssert.notificationIsVisible();

        adminPage.goToShop();

        mainPageAssert.productIsVisible(productName);
        mainPageAssert.productHasPrice(productName, "70");
    }

    @Test
    void editProductTest() {
        String oldName =
                "старый товар " + System.currentTimeMillis();

        String newName =
                "новый товар " + System.currentTimeMillis();

        loginPage.login(TestConfig.ADMIN_LOGIN, TestConfig.ADMIN_PASSWORD);

        adminPage.addProduct(oldName, "80");
        adminPageAssert.notificationIsVisible();

        adminPage.editProduct(
                oldName,
                newName,
                "90"
        );

        adminPageAssert.notificationIsVisible();

        adminPage.goToShop();

        mainPageAssert.productIsVisible(newName);
        mainPageAssert.productHasPrice(newName, "90");
    }
}
