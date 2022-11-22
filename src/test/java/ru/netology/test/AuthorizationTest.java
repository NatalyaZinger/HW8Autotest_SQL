package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;
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
    @DisplayName("Wrong login or password")
    void shouldReturnErrorWithInvalidLogin() {
        //var loginPage = new LoginPage();
        //loginPage.invalidLogin(generateRandomUser());
        new LoginPage().invalidLoginOrPassword(generateRandomUser());
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
    @DisplayName("Wrong code")
    void shouldReturnErrorWithInvalidCode() {
    }

    @Test
    @DisplayName("Empty code")
    void shouldReturnErrorWithEmptyCode() {
    }

    @Test
    @DisplayName("Block after three time wrong password")
    void shouldBlockAfterTripleEntry() {
    }

}




