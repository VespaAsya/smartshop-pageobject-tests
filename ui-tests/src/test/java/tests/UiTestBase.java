package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class UiTestBase {

    private static final String ALLURE_LISTENER = "AllureSelenide";

    @BeforeAll
    static void addAllureSelenideListener() {
        SelenideLogger.addListener(
                ALLURE_LISTENER,
                new AllureSelenide()
                        .screenshots(true)
                        .savePageSource(true)
        );
    }

    @AfterAll
    static void removeAllureSelenideListener() {
        SelenideLogger.removeListener(ALLURE_LISTENER);
    }
}
