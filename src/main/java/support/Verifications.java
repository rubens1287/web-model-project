package support;

import driver.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Fail.fail;
import static org.junit.Assert.assertTrue;

@Log4j2
public class Verifications extends DriverManager {


    private Verifications() {
        throw new IllegalStateException();
    }

    /**
     * Highlights the requested part of a component on screen
     *
     * @author Rubens Lobo
     */
    public static void highlightElement(WebElement element) {
        try {
            if (!getDriver().toString().contains("Windows") && !getDriver().toString().contains("appPackage")
                    && !getDriver().toString().contains("WiniumDriver") && getDriver() instanceof JavascriptExecutor) {
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='2px dashed red'", element);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border='1,5'", element);
                wait(1);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border=''", element);
                ((JavascriptExecutor) getDriver()).executeScript("arguments[0].style.border=''", element);
                log.info(String.format("Highlight no elemento web via locator %s ", element.toString()));
            }
        } catch (Exception e) {
            log.error(String.format("O elemento não esta visivel para o Highlight: %s", e));
        }
    }

    /**
     * Waits for a defined period
     *
     * @param seconds the number of seconds the function will wait for
     * @author Rubens Lobo
     */
    public static void wait(int seconds) {
        log.info(String.format("Aguardando %s segundos ", seconds));
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Verifies an existing element on the screen
     *
     * @param by      Type of "By"
     * @author Rubens Lobo
     */
    public static void verifyElementExists(By by) {
        log.info(String.format("Verificando se o elemento via locator %s existe na tela", by.toString()));
        WebElement element = Action.getExistingElement(by);
        highlightElement(element);
    }

    /**
     * Verifies if the text requested is part of an attribute of an element on screen
     *
     * @param by           Type of "By"
     * @param attribute    set attribute of the web element
     * @param expectedText is the text expected to be matched on screen
     * @author Rubens Lobo
     */
    public static void verifyElementAttributeContains(By by, String attribute, String expectedText) {
        log.info(String.format("Verificando se o texto esperado faz parte do valor do atributo do elemento via locator %s ", by.toString()));
        WebElement element = Action.getClickableElement(by);
        assertTrue(element.getAttribute(attribute).contains(expectedText));
        highlightElement(element);
    }

    /**
     * Verifies if a clickable element contains the expected text on screen
     *
     * @param by           Type of "By"
     * @param expectedText is the text expected to be matched on screen
     * @author Rubens Lobo
     */
    public static void verifyTextsElementClickable(By by, String expectedText) {
        log.info(String.format("Verificando se o elemento via locator %s e clicavel e contem o texto esperado", by.toString()));
        WebElement element = Action.getClickableElement(by);
        int timeout = 0;
        while (!(element.getText().trim().equals(expectedText)) && (timeout <= Integer.parseInt(configuration.timeout()))) {
            Verifications.wait(1);
            if (timeout == Integer.parseInt(configuration.timeout())) {
                log.error("Elemento via locator " + by.toString() + " nao encontrado na pagina atual!");
                Assert.fail();
            }
            timeout++;
        }
        highlightElement(element);
    }


    /**
     * Verifies if an existing element contains the expected text on screen. It can be a disabled element
     *
     * @param by           Type of "By"
     * @param expectedText is the text to be entered expected to be matched on screen
     * @author Rubens Lobo
     */
    public static void verifyTextsExistingElement(By by, String expectedText) {
        log.info(String.format("Verificando se o elemento via locator %s existe e se contem o texto esperado", by.toString()));
        WebElement element = Action.getExistingElement(by);
        int timeout = 0;
        while (!(element.getText().trim().equals(expectedText)) && (timeout <= Integer.parseInt(configuration.timeout()))) {
            Verifications.wait(1);
            if (timeout == Integer.parseInt(configuration.timeout())) {
                log.error("Elemento via locator " + by.toString() + " nao encontrado na pagina atual!");
                Assert.fail();
            }
            timeout++;
        }
        highlightElement(element);
    }

    /**
     * Verifies if an element is visible and Clickable
     *
     * @param by      Type of "By"
     * @param seconds Waits for the defined time set as parameter
     * @return checking an element is visible and can be clicked
     * @author Rubens Lobo
     */
    public static void verifyElementIsClickable(WebDriver driver, By by, int seconds) {
        log.info(String.format("Verificando se o elemento via locator %s e visivel e clicavel", by.toString()));
        WebElement element = Action.getClickableElement(by);
        highlightElement(element);
    }

    /**
     * Verifies if the given element is visible.
     *
     * @param by      Type of "By"
     * @author Rubens Lobo
     */
    public static void verifyElementIsVisible(By by) {
        log.info(String.format("Verificando o elemento via locator %s e visivel", by.toString()));
        WebElement element = Action.getVisibleElement(by);
        highlightElement(element);
    }

    /**
     * Verifies if an element is not on screen
     *
     * @param by      Type of "By"
     * @author Rubens Lobo
     */
    public static void verifyElementDoesNotExist(By by) {
        log.info(String.format("Verificando se o elemento via locator %s nao esta visivel na tela", by.toString()));
        WebDriverWait wait = new WebDriverWait(getDriver(), Integer.parseInt(configuration.timeout()));
        ExpectedCondition elementIsDisplayed = (ExpectedCondition<Boolean>) webDriver -> {
            try {
                getDriver().findElement(by).isDisplayed();
                return false;
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                return true;
            }
        };
        wait.until(elementIsDisplayed);
    }
}
