package support;

import driver.DriverManager;
import localization.MessageParser;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author Rubens Lobo
 * @since 13/05/2018
 */
@Log4j2
public class Action extends DriverManager {

    private static MessageParser parser = new MessageParser();


    /**
     * Returns an existing element from the screen
     *
     * @param by Type of "By"
     * @return Returns an existing element from the screen
     * @author Rubens Lobo
     */
    public static WebElement getExistingElement(By by) {
        log.info(parser.parse("action.get.element", new Object[]{by.toString()}));
        return new WebDriverWait(getDriver(), configuration.timeout())
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }


    /**
     * Returns a visible element from the screen
     *
     * @param by Type of "By"
     * @return Returns an visible element from the screen
     * @author Rubens Lobo
     */
    public static WebElement getVisibleElement(By by) {
        log.info(parser.parse("action.get.element", new Object[]{by.toString()}));
        return new WebDriverWait(getDriver(), configuration.timeout())
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Returns a clickable element from the screen
     *
     * @param by Type of "By"
     * @return Returns a clickable element from the screen
     * @author Rubens Lobo
     */
    public static WebElement getClickableElement(By by) {
        log.info(parser.parse("action.get.element", new Object[]{by.toString()}));
        return new WebDriverWait(getDriver(), configuration.timeout())
                .until(ExpectedConditions.elementToBeClickable(by));
    }


    /**
     * Clears the Text Field of a WebElement
     *
     * @param by Type of "By"
     * @author Rubens Lobo
     */
    public static void clearField(By by) {
        WebElement element = getClickableElement(by);
        log.info(parser.parse("action.clear.field", new Object[]{by.toString()}));
        element.clear();
    }

    /**
     * Enters text in a WebElement Text Field
     *
     * @param by   Type of "By"
     * @param text set a text to web element
     * @author Rubens Lobo
     */
    public static void setText(By by, Object text) {
        WebElement element = getClickableElement(by);
        log.info(parser.parse("action.set.text", new Object[]{by.toString()}));
        element.click();
        element.clear();
        element.sendKeys((CharSequence) text);
    }

    /**
     * Gets text from a WebElement Text Field
     *
     * @param by Type of "By"
     * @author Rubens Lobo
     */
    public static String getText(By by) {
        String textExtracted = null;
        WebElement element = Action.getVisibleElement(by);
        log.info(parser.parse("action.get.text", new Object[]{by.toString()}));
        if (element != null) {
            textExtracted = element.getText().trim();
        } else {
            log.error(parser.parse("action.get.text.error"));
        }
        return textExtracted;
    }


    /**
     * Gets text by attribute in a WebElement Text Field
     *
     * @param by        Type of "By"
     * @param attribute get text value thought of the tag name
     * @author Rubens Lobo
     */
    public static String getTextAttribute(By by, String attribute) {
        String textExtracted = null;
        WebElement element = Action.getVisibleElement(by);
        log.info(parser.parse("action.get.text.attribute", new Object[]{by.toString()}));
        if (element != null) {
            textExtracted = element.getAttribute(attribute).trim();
        } else {
            log.error(parser.parse("action.get.text.attribute.error"));
        }
        return textExtracted;
    }


    /**
     * Clicks in the web element
     *
     * @param by Type of "By"
     * @author Rubens Lobo
     */
    public static void clickOnElement(By by) {
        //if the object is not enabled or visible, this line finalizes the test, but if the object exists the method returns a AppWeb Element object
        WebElement element = getClickableElement(by);
        log.info(parser.parse("action.click.element", new Object[]{by.toString()}));
        //Clicks at the element requested
        element.click();
    }

}
