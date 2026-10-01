package asserts;

import pages.AdminLoginPage;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AdminLoginPageAssert {

    AdminLoginPage page;

    public AdminLoginPageAssert(AdminLoginPage page) {
        this.page = page;
    }

    public void loginIsFilled() {
        assertFalse(page.getLogin().isEmpty());
    }

    public void passwordIsFilled() {
        assertFalse(page.getPassword().isEmpty());
    }
}
