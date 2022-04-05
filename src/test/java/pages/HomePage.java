package pages;

import utils.DriverUtils;

public class HomePage {

    public LoginPage goToLogin() {
        DriverUtils.findElement("login-link").click();
        return new LoginPage();
    }

    public CheckboxPage goToCheckboxes() {
        DriverUtils.findElement("checkboxes-link").click();
        return new CheckboxPage();
    }

    public AdPage goToAd() {
        DriverUtils.findElement("ad-link").click();
        return new AdPage();
    }
}
