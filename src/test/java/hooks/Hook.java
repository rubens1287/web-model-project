package hooks;

import driver.DriverFactory;
import driver.DriverManager;
import report.Report;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;


public class Hook  {

    @Before
    public void init() {
        String enviroment = null;
        String env = System.getProperty("env");
        ConfigFactory.setProperty("env", env == null ? enviroment : env);
        WebDriver driver = DriverFactory.createInstance(System.getProperty("browser"));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
    }

    @After
    public void end(Scenario scenario){
        DriverManager.quit(scenario);
    }

}









