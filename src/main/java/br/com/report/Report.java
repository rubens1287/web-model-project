package br.com.report;

import br.com.driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Report extends DriverManager {

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] TakeScreenShot(WebDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

}
