package tests;

import bases.BaseTest;
import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverUtils;

public class FormAuthenticationTests extends BaseTest {

    @Test(testName = "Can login with valid data", groups = {"smoke"})
    public void canLoginWithValidData() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("tomsmith", "SuperSecretPassword!");

        assertThat(DriverUtils.checkElementExist("login/result/result-message")).isTrue();
    }

    @Test(testName = "Cannot login with invalid data", groups = {"smoke", "negative"})
    public void cannotLoginWithInvalidData() {
        HomePage homePage = new HomePage();
        LoginPage loginPage = homePage.goToLogin();
        loginPage.login("tomsmith", "password123!");

        assertThat(DriverUtils.checkElementExist("login/result/result-message")).isFalse();
    }
}
