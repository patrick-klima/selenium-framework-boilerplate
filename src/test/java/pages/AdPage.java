package pages;

import bases.DriverManager;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.DriverUtils;

public class AdPage {

    @SneakyThrows
    public boolean adExist() {
        Thread.sleep(5000);
        WebElement adFrame = DriverUtils.findElement("entry-ad/ad-frame");
        var displayCss = adFrame.getCssValue("display");
        return displayCss.equals("block");
    }
}
