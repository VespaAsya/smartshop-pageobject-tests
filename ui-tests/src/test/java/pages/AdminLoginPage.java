package pages;

import config.TestConfig;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AdminLoginPage {

    @Step("Войти в панель администратора под пользователем {login}")
    public void login(String login, String password) {
        open(TestConfig.BASE_URL + TestConfig.ADMIN_PATH);
        $("#username").setValue(login);
        $("#password").setValue(password);
        $("button[type='submit']").click();
    }
}
