package ru.netology.page;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {
    SelenideElement login = $("[data-test-id='login'] input");
    SelenideElement password = $("[data-test-id='password'] input");
    SelenideElement loginButton = $("[data-test-id='action-login']");
    SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    SelenideElement errorEmptyLogin = $("[data-test-id='login'] .input__sub");
    SelenideElement errorEmptyPassword = $("[data-test-id='password'] .input__sub");


    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        clearField();
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void invalidLogin(DataHelper.AuthInfo info) {
        clearField();
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    public void invalidPassword(DataHelper.AuthInfo info) {
        clearField();
        login.setValue(info.getLogin());
        password.setValue(info.getPassword());
        loginButton.click();
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    public void emptyLoginOrPass(DataHelper.AuthInfo info) {
        clearField();
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
            invalidPassword(info);
            if (i < 2) {
                errorNotification.getText().equals("Ошибка! Неверно указан логин или пароль");
            } else {
                errorNotification.getText().equals("Ошибка! Превышено количество попыток входа. Пользователь заблокирован");
            }
        }
    }

    private void clearField() {
        login.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        password.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }


}



