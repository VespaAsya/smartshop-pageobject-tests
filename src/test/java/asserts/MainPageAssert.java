package asserts;

import pages.MainPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPageAssert {

    MainPage page;

    public MainPageAssert(MainPage page) {
        this.page = page;
    }

    public void cartButtonIsVisible() {
        page.cartButton.shouldBe(visible);
    }

    public void cartIsVisible() {
        page.cartModal.shouldBe(visible);
    }

    public void cartContainsProduct(String name) {
        page.cartItems.shouldBe(visible).shouldHave(text(name));
    }

    public void totalPriceIs(String price) {
        page.totalPrice.shouldHave(text(price));
    }

    public void orderNotificationIsVisible() {
        page.toast.shouldBe(visible);
    }

    public void adminLinkIsVisible() {
        page.adminLink.shouldBe(visible);
    }

    public void productsAreVisible() {
        page.productCards.first().shouldBe(visible);
    }

    public void productIsVisible(String name) {
        $(".product-card[data-name='" + name + "']")
                .shouldBe(visible);
    }

    public void productHasPrice(String name, String price) {
        $(".product-card[data-name='" + name + "']")
                .shouldHave(text(price));
    }
}
