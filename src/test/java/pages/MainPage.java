package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

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

    public void openPage() {
        open("http://localhost:8080/");
    }

    public void setProductCount(String name, String count) {
        $(".product-card[data-name='" + name + "'] .qty-input")
                .setValue(count);
    }

    public void addProduct(String name) {
        $(".product-card[data-name='" + name + "'] " +
                "button[data-action='add-to-cart']")
                .click();
    }

    public void openCart() {
        cartButton.click();
    }

    public void makeOrder() {
        orderButton.click();
    }

    public void closeCart() {
        $("#close-modal").click();
    }

    public void openAdmin() {
        adminLink.click();
    }
}
