package br.com.pages;

import br.com.config.Configuration;
import br.com.driver.DriverManager;
import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class LoginPage {

    private By txtUsuario = By.name("email");
    private By txtSenha = By.name("senha");
    private By btnEntrar = By.xpath("//button");
    private WebDriverWait wait;

    public void acessaAplicacao(){
        Configuration configuration = ConfigCache.getOrCreate(Configuration.class);
        DriverManager.getDriver().get(configuration.url());
        wait = new WebDriverWait(DriverManager.getDriver(),Integer.parseInt(configuration.timeout()));
        wait.until(ExpectedConditions.elementToBeClickable(txtUsuario));
    }

    public void executaLogin(Map data){
        DriverManager.getDriver().findElement(txtUsuario).sendKeys(data.get("usuario").toString());
        DriverManager.getDriver().findElement(txtSenha).sendKeys(data.get("senha").toString());
        DriverManager.getDriver().findElement(btnEntrar).click();
    }
}
