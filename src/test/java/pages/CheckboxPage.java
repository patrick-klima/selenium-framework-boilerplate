package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.DriverUtils;

public class CheckboxPage {

    public WebElement getCheckbox(int index) {
        var checkboxForm = DriverUtils.findElement("checkboxes/checkboxes-form");
        return checkboxForm.findElements(By.xpath("//input")).get(index);
    }
}
