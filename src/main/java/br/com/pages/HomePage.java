package br.com.pages;

import br.com.config.Configuration;
import br.com.driver.DriverManager;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private By lblBemVindo = By.xpath("//div[contains(text(),'Bem vindo')]");

    private WebDriverWait wait;

    public void validaAcesso(){
        Configuration configuration = ConfigCache.getOrCreate(Configuration.class);
        wait = new WebDriverWait(DriverManager.getDriver(),Integer.parseInt(configuration.timeout()));
        wait.until(ExpectedConditions.elementToBeClickable(lblBemVindo));
    }

}
