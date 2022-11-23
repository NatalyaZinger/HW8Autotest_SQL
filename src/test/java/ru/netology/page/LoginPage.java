package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.util.Objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    SelenideElement login = $("[data-test-id='login'] input");
    SelenideElement password = $("[data-test-id='password'] input");
    SelenideElement loginButton = $("[data-test-id='action-login']");
    SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    SelenideElement errorEmptyLogin = $("[data-test-id='login'] .input__sub");
    SelenideElement errorEmptyPassword = $("[data-test-id='password'] .input__sub");


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

    public void invalidLoginOrPassword(DataHelper.AuthInfo info) {
        //clearInput();
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    public void emptyLoginOrPass(DataHelper.AuthInfo info) {
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        if (login.getValue().isEmpty()) {
            errorEmptyLogin.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
        }
        if (password.getValue().isEmpty()) {
            errorEmptyPassword.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
        }
    }

    public void invalidPasswordTripleEntry(DataHelper.AuthInfo info) {
        for (int i = 0; i < 3; i++) {
            invalidLoginOrPassword(info);
            if (i < 2) {
                errorNotification.getText().equals("Ошибка! Неверно указан логин или пароль");
            } else {
                errorNotification.getText().equals("Ошибка! Превышено количество попыток входа. Пользователь заблокирован");
            }
        }
    }


}



