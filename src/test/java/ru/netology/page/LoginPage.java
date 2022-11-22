package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    SelenideElement login = $("[data-test-id='login'] input");
    SelenideElement password = $("[data-test-id='password'] input");
    SelenideElement loginButton = $("[data-test-id='action-login']");
    SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    //SelenideElement errorEmptyLogin = $("[data-test-id='login'] .input__sub");
    //SelenideElement errorEmptyPass = $("[data-test-id='password'] .input__sub");


    public void verifyErrorNotificationVisibility() {
        errorNotification.shouldBe(visible);
    }

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
        //return page(VerificationPage.class);
    }

}



