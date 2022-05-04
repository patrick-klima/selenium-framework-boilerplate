package factories;

import bases.DriverManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@Slf4j
public class DriverFactory {

    public static RemoteWebDriver createWebDriver(String browserType, String version) {
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("browserName", browserType);
        cap.setCapability("browserVersion", version);

        cap.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        log.info("Driver created");

        RemoteWebDriver drv = null;
        try {
            drv = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        DriverManager.setDriverThreadLocal(drv);

        return drv;
    }

}
