package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class AdminPage {

    public SelenideElement nameInput = $("#n-name");
    public SelenideElement priceInput = $("#n-price");
    public SelenideElement addButton = $("#add-btn");
    public SelenideElement toast = $(".toast");
    public SelenideElement backToShop = $("a[href='/']");
    public ElementsCollection productRows = $$("tbody tr");

    public void addProduct(String name, String price) {
        nameInput.setValue(name);
        priceInput.setValue(price);
        addButton.click();
    }

    public void editProduct(String oldName, String newName, String newPrice) {
        SelenideElement row = productRows.findBy(text(oldName));

        row.$("input[type='text']").setValue(newName);
        row.$("input[type='number']").setValue(newPrice);
        row.$("button[data-action='update']").click();
    }

    public void goToShop() {
        backToShop.click();
    }
}
