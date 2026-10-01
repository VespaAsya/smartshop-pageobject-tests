package tests;

import asserts.AdminLoginPageAssert;
import asserts.AdminPageAssert;
import asserts.MainPageAssert;
import pages.AdminLoginPage;
import pages.AdminPage;
import pages.MainPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class PageObjectTest {

    MainPage mainPage;
    MainPageAssert mainPageAssert;

    AdminLoginPage loginPage;
    AdminLoginPageAssert loginPageAssert;

    AdminPage adminPage;
    AdminPageAssert adminPageAssert;

    @BeforeEach
    void setUp() {
        mainPage = new MainPage();
        mainPageAssert = new MainPageAssert(mainPage);

        loginPage = new AdminLoginPage();
        loginPageAssert = new AdminLoginPageAssert(loginPage);

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

        loginPage.setLogin("admin");
        loginPage.setPassword("secret123");

        loginPageAssert.loginIsFilled();
        loginPageAssert.passwordIsFilled();

        loginPage.clickLogin();

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

        loginPage.setLogin("admin");
        loginPage.setPassword("secret123");
        loginPage.clickLogin();

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
