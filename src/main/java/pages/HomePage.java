package pages;

import driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import report.Report;
import support.Verifications;
import org.openqa.selenium.By;

@Log4j2
public class HomePage extends DriverManager{

    private By lblBemVindo = By.xpath("//div[contains(text(),'Bem vindo')]");

    public boolean isPresentHomePage(){
        Report.TakeScreenShot();
        return Verifications.verifyElementIsVisible(lblBemVindo);
    }
}
