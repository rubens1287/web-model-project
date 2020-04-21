package br.com.pages;

import br.com.driver.DriverManager;
import br.com.report.Report;
import br.com.support.Verifications;
import org.openqa.selenium.By;



import java.util.Map;

public class LoginPage extends DriverManager {

    private By txtUsuario = By.name("email");
    private By txtSenha = By.name("senha1");
    private By btnEntrar = By.xpath("//button");


    public void acessaAplicacao(){

       getDriver().get(configuration.url());
        Verifications.verifyElementIsClickable(getDriver(),txtUsuario,
                Integer.parseInt(configuration.timeout()));
        Report.TakeScreenShot(getDriver());
    }

    public void executaLogin(Map data){
        getDriver().findElement(txtUsuario).sendKeys((CharSequence) data.get("usuario"));
        getDriver().findElement(txtSenha).sendKeys((CharSequence) data.get("senha"));
        Report.TakeScreenShot(getDriver());
        getDriver().findElement(btnEntrar).click();
    }


}
