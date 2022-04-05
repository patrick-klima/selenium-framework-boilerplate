package pages;

import utils.DriverUtils;

public class LoginPage {

    public void login(String user, String pass) {
        DriverUtils.findElement("login/username").sendKeys(user);
        DriverUtils.findElement("login/password").sendKeys(pass);

        DriverUtils.findElement("login/password").submit();
    }
}
