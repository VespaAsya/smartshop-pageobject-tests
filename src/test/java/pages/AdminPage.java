package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class AdminPage {

    public SelenideElement nameInput = $("#n-name");
    public SelenideElement priceInput = $("#n-price");
    public SelenideElement addButton = $("#add-btn");
    public SelenideElement toast = $(".toast");
    public SelenideElement backToShop = $("a[href='/']");
    @Step("Добавить товар через панель администратора: {name}, цена {price}")
    public void addProduct(String name, String price) {
        nameInput.setValue(name);
        priceInput.setValue(price);
        addButton.click();
    }

    @Step("Изменить товар '{oldName}' на '{newName}', цена {newPrice}")
    public void editProduct(String oldName, String newName, String newPrice) {
        refresh();
        SelenideElement row = $("input[value='" + oldName + "']").closest("tr");

        row.$("input[type='text']").setValue(newName);
        row.$("input[type='number']").setValue(newPrice);
        row.$("button[data-action='update']").click();
    }

    @Step("Перейти из панели администратора в магазин")
    public void goToShop() {
        backToShop.click();
    }
}
