package pages;

import driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import report.Report;
import support.Action;
import support.Verifications;
import org.openqa.selenium.By;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Log4j2
public class LoginPage extends DriverManager implements BaseValidationPage {

    private By txtUsuario = By.name("email");
    private By txtSenha = By.name("senha");
    private By btnEntrar = By.xpath("//button");
    private By abaLogin = By.xpath("//a[contains(text(),'Login')]");

    @Override
    public boolean isPresent() {
        return  Verifications.verifyTextsElementClickable(abaLogin,"Login")
                && Verifications.verifyElementIsClickable(txtUsuario);
    }

    public void acessaAplicacao(){
        getDriver().get(configuration.url());
        Report.TakeScreenShot();
        log.info("Acesso a aplicacao efetuado com sucesso");
    }

    public void executaLogin(HashMap data){
        Action.setText(txtUsuario,data.get("usuario"));
        getDriver().findElement(txtSenha).sendKeys((CharSequence) data.get("senha"));
        Report.TakeScreenShot();
        Action.clickOnElement(btnEntrar);
        log.info("Login na aplicacao efetuado com sucesso");
    }

}
