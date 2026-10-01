package pages;

import static com.codeborne.selenide.Selenide.open;

public class AdminLoginPage {

    private String login;
    private String password;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void clickLogin() {
        open("http://" + login + ":" + password + "@localhost:8080/admin");
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
