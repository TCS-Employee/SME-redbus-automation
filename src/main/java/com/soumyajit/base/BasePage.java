package com.soumyajit.base;

import org.openqa.selenium.WebDriver;

/**
 * BasePage class manages WebDriver instance using ThreadLocal
 * This ensures thread-safety for parallel test execution
 */
public class BasePage {
    // ThreadLocal ensures each thread gets its own WebDriver instance
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Sets the WebDriver instance for current thread
     */
    public static void setDriver(WebDriver driver) {
        BasePage.driver.set(driver);
    }

    /**
     * Gets the WebDriver instance for current thread
     */
    public static WebDriver getDriver() {
        return BasePage.driver.get();
    }

    /**
     * Removes WebDriver instance from current thread
     */
    public static void closeDriver() {
        BasePage.driver.remove();
    }
}