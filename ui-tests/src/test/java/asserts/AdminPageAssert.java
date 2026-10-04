package asserts;

import io.qameta.allure.Step;
import pages.AdminPage;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;

public class AdminPageAssert {

    private final AdminPage page;

    public AdminPageAssert(AdminPage page) {
        this.page = page;
    }

    @Step("Проверить видимость поля названия товара")
    public void nameFieldIsVisible() {
        page.nameInput.shouldBe(visible);
    }

    @Step("Проверить видимость поля цены товара")
    public void priceFieldIsVisible() {
        page.priceInput.shouldBe(visible);
    }

    @Step("Проверить видимость кнопки добавления товара")
    public void addButtonIsVisible() {
        page.addButton.shouldBe(visible);
    }

    @Step("Проверить название товара: {text}")
    public void nameFieldHasText(String text) {
        page.nameInput.shouldHave(value(text));
    }

    @Step("Проверить цену товара: {text}")
    public void priceFieldHasText(String text) {
        page.priceInput.shouldHave(value(text));
    }

    @Step("Проверить видимость уведомления администратора")
    public void notificationIsVisible() {
        page.toast.shouldBe(visible);
    }
}
