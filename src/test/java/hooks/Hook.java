package hooks;

import br.com.config.Configuration;
import br.com.driver.DriverFactory;
import br.com.driver.DriverManager;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;


public class Hook  {

    @Before
    public void init() {
        String enviroment = null;
        String env = System.getProperty("env");
        ConfigFactory.setProperty("env", env == null ? enviroment : env);
        WebDriver driver = DriverFactory.createInstance(System.getProperty("browser"));
        DriverManager.setDriver(driver);
    }

    @After
    public void end(){
        DriverManager.quit();
    }
}









