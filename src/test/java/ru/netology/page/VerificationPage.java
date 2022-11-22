package ru.netology.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    SelenideElement codeInput = $("[data-test-id='code'] input");
    SelenideElement verifyButton = $("[data-test-id='action-verify']");
    SelenideElement errorNotification = $("[data-test-id='error-notification'] .notification__content");
    //SelenideElement errorEmptyCode = $("[data-test-id='code'] .input__sub");
    //SelenideElement header = $(".paragraph");

    public void verifyVerificationPageVisibility() {
        codeInput.shouldBe(visible);
    }

    public void verifyErrorNotificationVisibility() {
        errorNotification.shouldBe(visible);
    }

    public void verify(String verificationCode) {
        codeInput.setValue(verificationCode);
        verifyButton.click();
    }

    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }
}
