package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import driver.DriverFactory;
import driver.DriverManager;
import localization.MessageParser;
import lombok.extern.log4j.Log4j2;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;


@Log4j2
public class Hook {

    private static MessageParser parser = new MessageParser();

    @Before
    public void init(Scenario scenario) {
        DriverManager.setScenario(scenario);
        log.info(parser.parse("hook.test.started", new Object[]{scenario.getName()}));

        ConfigFactory.setProperty("env", System.getProperty("env"));


        WebDriver driver = DriverFactory.createInstance(System.getProperty("browser"));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
    }

    @After
    public void end(Scenario scenario) {
        DriverManager.quit(scenario);
        log.info(parser.parse("hook.test.ended", new Object[]{scenario.getName()}));
        log.info(parser.parse("hook.test.status", new Object[]{scenario.getStatus()}));
    }

}









