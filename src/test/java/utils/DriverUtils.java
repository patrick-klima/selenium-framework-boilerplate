package utils;

import bases.DriverManager;
import org.openqa.selenium.WebElement;
import repository.ObjectRepository;

import java.util.List;


/**
 * Extends WebDriver with additional functionality.
 * All functions declared in this class are static and can be used within every test class
 */
public class DriverUtils {

    /**
     * Get the Locator declared in the yaml file and find the element.
     * @param path Path to locator defined in the yaml file
     * @return WebElement
     */
    public static WebElement findElement(String path) {
        return DriverManager.getDriver().findElement(ObjectRepository.getLocator(path));
    }

    /**
     * Get the Locator declared in the yaml file and find the elements.
     * @param path Path to locator defined in the yaml file
     * @return List<WebElement>
     */
    public static List<WebElement> findElements(String path) {
        return DriverManager.getDriver().findElements(ObjectRepository.getLocator(path));
    }


    /**
     * Check if WebElement exist.
     * @param path Path to locator defined in the yaml file
     * @return boolean
     */
    public static boolean checkElementExist(String path) {
        return findElements(path).size() > 0;
    }

}
