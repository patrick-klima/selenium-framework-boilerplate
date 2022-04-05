package bases;

import factories.DriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import repository.ObjectRepository;

import java.nio.file.Paths;
import java.util.List;

public abstract class BaseTest {

    private final String BASE_URL = "https://the-internet.herokuapp.com/";

    @BeforeMethod
    public void beforeTest(ITestContext ctx) {
        String baseUrl = ctx.getCurrentXmlTest().getSuite().getParameter("baseUrl");
        String browserName = ctx.getCurrentXmlTest().getSuite().getParameter("browserName");
        String browserVersion = ctx.getCurrentXmlTest().getSuite().getParameter("browserVersion");
        String objectRepository = ctx.getCurrentXmlTest().getSuite().getParameter("objectRepository");

        browserName = browserName == null ? "chrome" : browserName;
        browserVersion = browserVersion == null ? "99" : browserVersion;

        DriverFactory.createWebDriver(browserName, browserVersion);
        ObjectRepository.initialize(objectRepository);
        DriverManager.getDriver().navigate().to(baseUrl);
    }

    @AfterMethod
    public void afterTest() {
        DriverManager.getDriver().close();
    }
}
