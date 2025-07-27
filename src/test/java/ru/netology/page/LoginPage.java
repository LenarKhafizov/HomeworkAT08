package ru.netology.page;
import ru.netology.data.DataHelper;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement buttonField = $$("button").findBy((text("Продолжить")));
    private final SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    public void login(DataHelper.AuthInfo info){
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        buttonField.click();
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login(info);
        return new VerificationPage();
    }

    public void checkErrorMessage(String errorText) {
        errorMessage.shouldHave(exactText(errorText)).shouldBe(visible);
    }
}
