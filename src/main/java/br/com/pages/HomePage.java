package br.com.pages;

import br.com.driver.DriverManager;
import br.com.report.Report;
import br.com.support.Verifications;
import org.openqa.selenium.By;

public class HomePage extends DriverManager{

    private By lblBemVindo = By.xpath("//div[contains(text(),'Bem vindo')]");

    public void validaAcesso(){
        Report.TakeScreenShot(getDriver());
        Verifications.verifyElementIsVisible(getDriver(),lblBemVindo,Integer.parseInt(configuration.timeout()));
    }
}
