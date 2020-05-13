
package driver.local;

import driver.IDriver;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import localization.MessageParser;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class LocalDriverManager implements IDriver {

    private static MessageParser parser = new MessageParser();

    @Override
    public WebDriver createInstance(String browser) {
         WebDriver driver = null;

        try {
            DriverManagerType driverManagerType = DriverManagerType.valueOf(browser.toUpperCase());
            Class<?> driverClass = Class.forName(driverManagerType.browserClass());
            WebDriverManager.getInstance(driverManagerType).setup();
            driver = (WebDriver) driverClass.newInstance();
        } catch (IllegalAccessException | ClassNotFoundException e) {
            log.error(parser.parse("local.driver.manager.class.not.found.exception", new Object[]{e}));
        } catch (InstantiationException e) {
            log.error(parser.parse("local.driver.manager.instantiation.exception", new Object[]{e}));
        }
        return driver;
    }
}
