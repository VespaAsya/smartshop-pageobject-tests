package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import config.TestConfig;
import io.qameta.allure.Step;

import static com.codeborne.selenide.ClickOptions.usingJavaScript;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    public SelenideElement cartButton = $("#open-cart-btn");
    public SelenideElement cartModal = $("#cartModal");
    public SelenideElement cartItems = $("#cart-items");
    public SelenideElement totalPrice = $("#total-price");
    public SelenideElement orderButton = $("#makeOrder");
    public SelenideElement toast = $(".toast");
    public SelenideElement adminLink = $("a[href='/admin']");
    public ElementsCollection productCards = $$(".product-card");

    @Step("Открыть главную страницу магазина")
    public void openPage() {
        open(TestConfig.BASE_URL + "/");
    }

    @Step("Установить количество товара '{name}': {count}")
    public void setProductCount(String name, String count) {
        $(".product-card[data-name='" + name + "'] .qty-input")
                .setValue(count);
    }

    @Step("Добавить товар '{name}' в корзину")
    public void addProduct(String name) {
        $(".product-card[data-name='" + name + "'] " +
                "button[data-action='add-to-cart']")
                .click(usingJavaScript());
    }

    @Step("Открыть корзину")
    public void openCart() {
        cartButton.click();
    }

    @Step("Оформить заказ")
    public void makeOrder() {
        orderButton.click();
    }

    @Step("Закрыть корзину")
    public void closeCart() {
        $("#close-modal").click();
    }

    @Step("Открыть панель администратора")
    public void openAdmin() {
        adminLink.click();
    }
}
