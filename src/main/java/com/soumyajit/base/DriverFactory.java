package com.soumyajit.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * DriverFactory class handles WebDriver initialization and cleanup
 * Implements Singleton pattern to ensure only one driver per thread
 */
public class DriverFactory {
    private static WebDriver driver;

    /**
     * Initializes ChromeDriver if not already created
     * Maximizes the browser window
     * Stores driver in ThreadLocal for thread-safety
     */
    public static void getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();          // Creates new ChromeDriver
            driver.manage().window().maximize();  // Maximizes browser window
            BasePage.setDriver(driver);           // Stores in ThreadLocal
        }
    }

    /**
     * Quits the browser and cleans up resources
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();              // Closes all browser windows
            driver = null;              // Nullifies the reference
            BasePage.closeDriver();     // Removes from ThreadLocal
        }
    }
}