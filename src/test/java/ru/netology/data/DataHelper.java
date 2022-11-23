package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getAuthInfoWithTestData() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static String generateRandomLogin() {
        return faker.name().username();
    }

    private static String generateRandomPassword() {
        return faker.internet().password();
    }

    public static AuthInfo generateRandomUser() {
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    public static AuthInfo getEmptyAuthInfo() {
        return new AuthInfo("", "");
    }

    public static AuthInfo getEmptyLogin() {
        return new AuthInfo("", faker.internet().password());
    }

    public static AuthInfo getEmptyPassword() {
        return new AuthInfo(faker.name().username(), "");
    }

    public static VerificationCode generateRandomVerificationCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;
    }


}
