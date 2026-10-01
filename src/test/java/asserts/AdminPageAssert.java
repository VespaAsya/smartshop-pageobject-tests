package asserts;

import pages.AdminPage;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;

public class AdminPageAssert {

    AdminPage page;

    public AdminPageAssert(AdminPage page) {
        this.page = page;
    }

    public void nameFieldIsVisible() {
        page.nameInput.shouldBe(visible);
    }

    public void priceFieldIsVisible() {
        page.priceInput.shouldBe(visible);
    }

    public void addButtonIsVisible() {
        page.addButton.shouldBe(visible);
    }

    public void nameFieldHasText(String text) {
        page.nameInput.shouldHave(value(text));
    }

    public void priceFieldHasText(String text) {
        page.priceInput.shouldHave(value(text));
    }

    public void notificationIsVisible() {
        page.toast.shouldBe(visible);
    }
}
