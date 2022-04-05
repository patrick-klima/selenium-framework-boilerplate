package tests;

import bases.BaseTest;
import org.testng.annotations.Test;
import pages.CheckboxPage;
import pages.HomePage;
import pages.LoginPage;
import utils.DriverUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckboxTests extends BaseTest {

    @Test(testName = "Can check first checkbox", groups = {"smoke"})
    public void canCheckFirstCheckbox() {
        HomePage homePage = new HomePage();
        CheckboxPage checkboxPage = homePage.goToCheckboxes();

        var checkbox = checkboxPage.getCheckbox(0);

        assertThat(checkbox.isSelected()).isFalse();
        checkbox.click();
        assertThat(checkbox.isSelected()).isTrue();
    }

    @Test(testName = "is second checkbox checked", groups = {"smoke"})
    public void isSecondCheckboxChecked() {
        HomePage homePage = new HomePage();
        CheckboxPage checkboxPage = homePage.goToCheckboxes();

        var checkbox = checkboxPage.getCheckbox(1);
        assertThat(checkbox.isSelected()).isTrue();

    }

}
