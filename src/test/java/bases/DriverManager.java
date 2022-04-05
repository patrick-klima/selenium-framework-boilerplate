package bases;

import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {
    public static ThreadLocal<RemoteWebDriver> driverThreadLocal = new ThreadLocal<>();

    public static RemoteWebDriver getDriver() { return driverThreadLocal.get(); }

    public static void setDriverThreadLocal(RemoteWebDriver driver) { driverThreadLocal.set(driver); }
}
