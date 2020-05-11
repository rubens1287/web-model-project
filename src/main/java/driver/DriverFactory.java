

package driver;

import config.Configuration;
import driver.local.LocalDriverManager;
import driver.remote.RemoteDriverManager;
import localization.MessageParser;
import lombok.extern.log4j.Log4j2;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DriverFactory {

    private static MessageParser parser = new MessageParser();

    public static WebDriver createInstance(String browser) {
        Configuration configuration = ConfigCache.getOrCreate(Configuration.class);
        Target target = Target.valueOf(configuration.target().toUpperCase());
        WebDriver webdriver;

        switch (target) {

            case LOCAL:
                webdriver = new LocalDriverManager().createInstance(browser);
                break;
            case GRID:
                webdriver = new RemoteDriverManager().createInstance(browser);
                break;
            default:
                throw new IllegalStateException(parser.parse("driver.factory.unexpected", new Object[]{target}));
        }
        return webdriver;
    }

    enum Target {
        LOCAL, GRID
    }
}
