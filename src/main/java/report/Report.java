package report;

import driver.DriverManager;
import io.qameta.allure.Attachment;
import localization.MessageParser;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@Log4j2
public class Report extends DriverManager {

    private static MessageParser parser = new MessageParser();

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] takeScreenShot() {
        log.info(parser.parse("report.screenshot"));
        String target = System.getProperty("report").toUpperCase();
        switch (target) {
            case "CUCUMBER":
                getScenario().get().embed(((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES),"image/png");
                break;
            case "ALLURE":
                return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
            default:
                throw new IllegalStateException(parser.parse("report.unexpected", new Object[]{target}));
        }

        return null;
    }

}
