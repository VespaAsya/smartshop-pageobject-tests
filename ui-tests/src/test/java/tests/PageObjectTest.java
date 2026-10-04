package tests;

import asserts.AdminPageAssert;
import asserts.MainPageAssert;
import config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.AdminLoginPage;
import pages.AdminPage;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;

@Tag("ui")
public class PageObjectTest extends UiTestBase {

    private MainPage mainPage;
    private MainPageAssert mainPageAssert;
    private AdminLoginPage loginPage;
    private AdminPage adminPage;
    private AdminPageAssert adminPageAssert;

    @BeforeAll
    static void prepareProducts() {
        AdminLoginPage loginPage = new AdminLoginPage();
        AdminPage adminPage = new AdminPage();

        loginPage.login(TestConfig.ADMIN_LOGIN, TestConfig.ADMIN_PASSWORD);
        adminPage.ensureProductExists(
                TestConfig.FIRST_PRODUCT_NAME,
                String.valueOf(TestConfig.FIRST_PRODUCT_PRICE)
        );
        adminPage.ensureProductExists(
                TestConfig.SECOND_PRODUCT_NAME,
                String.valueOf(TestConfig.SECOND_PRODUCT_PRICE)
        );
        closeWebDriver();
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
    @Tag("ui-smoke")
    void buyThreeProductsTest() {
        mainPage.openPage();

        mainPage.setProductCount(
                TestConfig.FIRST_PRODUCT_NAME,
                String.valueOf(TestConfig.FIRST_PRODUCT_COUNT)
        );
        mainPage.addProduct(TestConfig.FIRST_PRODUCT_NAME);
        mainPage.openCart();

        mainPageAssert.cartIsVisible();
        mainPageAssert.cartContainsProduct(TestConfig.FIRST_PRODUCT_NAME);
        mainPageAssert.totalPriceIs(String.valueOf(
                TestConfig.FIRST_PRODUCT_PRICE * TestConfig.FIRST_PRODUCT_COUNT
        ));

        mainPage.makeOrder();
        mainPageAssert.orderNotificationIsVisible();
    }

    @Test
    void checkTotalPriceTest() {
        mainPage.openPage();

        mainPage.addProduct(TestConfig.FIRST_PRODUCT_NAME);
        mainPage.addProduct(TestConfig.SECOND_PRODUCT_NAME);
        mainPage.openCart();

        mainPageAssert.cartContainsProduct(TestConfig.FIRST_PRODUCT_NAME);
        mainPageAssert.cartContainsProduct(TestConfig.SECOND_PRODUCT_NAME);
        mainPageAssert.totalPriceIs(String.valueOf(
                TestConfig.FIRST_PRODUCT_PRICE + TestConfig.SECOND_PRODUCT_PRICE
        ));
    }

    @Test
    @Tag("ui-smoke")
    void addProductThroughAdminTest() {
        String productName = uniqueName(TestConfig.UI_CREATED_PRODUCT_PREFIX);
        String productPrice = String.valueOf(TestConfig.UI_CREATED_PRODUCT_PRICE);

        loginPage.login(TestConfig.ADMIN_LOGIN, TestConfig.ADMIN_PASSWORD);

        adminPageAssert.nameFieldIsVisible();
        adminPageAssert.priceFieldIsVisible();
        adminPageAssert.addButtonIsVisible();

        adminPage.addProduct(productName, productPrice);
        adminPageAssert.notificationIsVisible();
        adminPage.goToShop();

        mainPageAssert.productIsVisible(productName);
        mainPageAssert.productHasPrice(productName, productPrice);
    }

    @Test
    void editProductTest() {
        String oldName = uniqueName(TestConfig.UI_OLD_PRODUCT_PREFIX);
        String newName = uniqueName(TestConfig.UI_NEW_PRODUCT_PREFIX);
        String oldPrice = String.valueOf(TestConfig.UI_OLD_PRODUCT_PRICE);
        String newPrice = String.valueOf(TestConfig.UI_NEW_PRODUCT_PRICE);

        loginPage.login(TestConfig.ADMIN_LOGIN, TestConfig.ADMIN_PASSWORD);

        adminPage.addProduct(oldName, oldPrice);
        adminPageAssert.notificationIsVisible();
        adminPage.editProduct(oldName, newName, newPrice);
        adminPageAssert.notificationIsVisible();
        adminPage.goToShop();

        mainPageAssert.productIsVisible(newName);
        mainPageAssert.productHasPrice(newName, newPrice);
    }

    private static String uniqueName(String prefix) {
        return prefix + " " + System.currentTimeMillis();
    }
}
