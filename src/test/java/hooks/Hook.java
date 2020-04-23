package hooks;

import driver.DriverFactory;
import driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import report.Report;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;


@Log4j2
public class Hook  {

    @Before
    public void init(Scenario scenario) {
        log.info("TESTE INICIADO: " + scenario.getName());
        String enviroment = null;
        String env = System.getProperty("env");
        ConfigFactory.setProperty("env", env == null ? enviroment : env);
        WebDriver driver = DriverFactory.createInstance(System.getProperty("browser"));
        driver.manage().window().maximize();
        DriverManager.setDriver(driver);
    }

    @After
    public void end(Scenario scenario){
        log.info("TESTE FINALIZADO: " + scenario.getName());
        DriverManager.quit(scenario);
    }

}









