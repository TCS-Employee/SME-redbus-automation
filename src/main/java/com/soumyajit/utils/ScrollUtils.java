package com.soumyajit.utils;

import com.soumyajit.base.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * ScrollUtils class provides scrolling functionality using JavaScript
 */
public class ScrollUtils {

    /**
     * Scrolls element into view using smooth scrolling
     * Centers element in the middle of viewport
     * @param element - WebElement to scroll to
     */
    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) BasePage.getDriver();
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }
}