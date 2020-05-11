
package driver;

import config.Configuration;
import localization.MessageParser;
import report.Report;
import cucumber.api.Scenario;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    private static MessageParser parser = new MessageParser();

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<Scenario> scenario = new ThreadLocal<>();
    public static final Configuration configuration = ConfigCache.getOrCreate(Configuration.class);

    public static WebDriver getDriver() {
        return  driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static ThreadLocal<Scenario> getScenario() {
        return scenario;

    }

    public static void setScenario(Scenario scenario) {
        DriverManager.scenario.set(scenario);
    }

    public static void quit(Scenario scenario) {
        if (scenario.isFailed()) {
            Report.takeScreenShot();
        }
        DriverManager.driver.get().quit();
    }

    public static String getInfo() {
        Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
        String browserName = cap.getBrowserName();
        String platform = cap.getPlatform().toString();
        String version = cap.getVersion();
        return parser.parse("driver.manager.info", new Object[]{browserName, version, platform});
    }
}
