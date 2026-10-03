package asserts;

import io.qameta.allure.Step;
import pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPageAssert {

    MainPage page;

    public MainPageAssert(MainPage page) {
        this.page = page;
    }

    @Step("Проверить видимость кнопки корзины")
    public void cartButtonIsVisible() {
        page.cartButton.shouldBe(visible);
    }

    @Step("Проверить видимость корзины")
    public void cartIsVisible() {
        page.cartModal.shouldBe(visible);
    }

    @Step("Проверить наличие товара '{name}' в корзине")
    public void cartContainsProduct(String name) {
        page.cartItems.shouldBe(visible).shouldHave(text(name));
    }

    @Step("Проверить итоговую стоимость: {price}")
    public void totalPriceIs(String price) {
        page.totalPrice.shouldHave(text(price));
    }

    @Step("Проверить видимость уведомления об оформлении заказа")
    public void orderNotificationIsVisible() {
        page.toast.shouldBe(visible);
    }

    @Step("Проверить видимость ссылки на панель администратора")
    public void adminLinkIsVisible() {
        page.adminLink.shouldBe(visible);
    }

    @Step("Проверить видимость списка товаров")
    public void productsAreVisible() {
        page.productCards.first().shouldBe(visible);
    }

    @Step("Проверить видимость товара '{name}'")
    public void productIsVisible(String name) {
        $(".product-card[data-name='" + name + "']")
                .shouldBe(visible);
    }

    @Step("Проверить цену товара '{name}': {price}")
    public void productHasPrice(String name, String price) {
        $(".product-card[data-name='" + name + "']")
                .shouldHave(text(price));
    }
}
