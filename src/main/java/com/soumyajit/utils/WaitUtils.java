package com.soumyajit.utils;

import com.soumyajit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * WaitUtils class provides explicit wait methods for Selenium
 * Prevents NoSuchElementException and ElementNotInteractableException
 */
public class WaitUtils {

    /**
     * Waits for element to be visible on the page
     * @param element - WebElement to wait for
     */
    public static void waitForVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits for element to become invisible
     * @param element - WebElement to wait for
     */
    public static void waitForInvisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    /**
     * Waits for element to be clickable (visible and enabled)
     * @param element - WebElement to wait for
     */
    public static void waitForClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for element to be present in DOM (may not be visible)
     * @param locator - By locator to find element
     */
    public static void waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for child element within parent element
     * @param element - Parent WebElement
     * @param locator - By locator for child element
     * @return WebElement - Found child element
     */
    public static WebElement waitForNestedElement(WebElement element, By locator) {
        WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, locator));
    }

    /**
     * Waits for all elements in list to be visible
     * @param elements - List of WebElements
     */
    public static void waitForAllElements(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(BasePage.getDriver(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}