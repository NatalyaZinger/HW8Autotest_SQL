package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class AuthorizationTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void teardown() {
        cleanDatabase();
    }

    @Test
    @DisplayName("Successfully login with test data")
    void shouldSuccessfullyLogin() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibility();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    @DisplayName("Wrong login")
    void shouldReturnErrorWithInvalidLogin() {
        new LoginPage().invalidLogin(generateRandomUser());
    }

    @Test
    @DisplayName("Wrong password")
    void shouldReturnErrorWithInvalidPassword() {
        new LoginPage().invalidPassword(generateRandomUser());
    }

    @Test
    @DisplayName("Empty login")
    void shouldReturnErrorWithEmptyLogin() {
        new LoginPage().emptyLoginOrPass(getEmptyLogin());
    }

    @Test
    @DisplayName("Empty password")
    void shouldReturnErrorWithEmptyPassword() {
        new LoginPage().emptyLoginOrPass(getEmptyPassword());
    }

    @Test
    @DisplayName("Empty login and password")
    void shouldReturnErrorWithEmptyLoginAndPass() {
        new LoginPage().emptyLoginOrPass(getEmptyAuthInfo());
    }

    @Test
    @DisplayName("Wrong verification code")
    void shouldReturnErrorWithInvalidCode() {
        new LoginPage().validLogin(getAuthInfoWithTestData()).wrongCode();
    }

    @Test
    @DisplayName("Empty code")
    void shouldReturnErrorWithEmptyCode() {
        new LoginPage().validLogin(getAuthInfoWithTestData()).emptyCode();
    }

    @Test
    @DisplayName("Block after three times wrong password")
    void shouldBlockAfterThreeTimes() {
        new LoginPage().invalidPasswordTripleEntry(generateRandomUser());
    }


}




