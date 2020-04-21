package pages;

import driver.DriverManager;
import report.Report;
import support.Verifications;
import org.openqa.selenium.By;

public class HomePage extends DriverManager{

    private By lblBemVindo = By.xpath("//div[contains(text(),'Bem vindo')]");

    public void validaAcesso(){
        Report.TakeScreenShot(getDriver());
        Verifications.verifyElementIsVisible(getDriver(),lblBemVindo,Integer.parseInt(configuration.timeout()));
    }
}
