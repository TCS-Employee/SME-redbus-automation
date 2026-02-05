package com.soumyajit.hooks;

import com.soumyajit.base.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * Hooks class manages setup and teardown for each test scenario
 * @Before runs before each scenario
 * @After runs after each scenario (even if test fails)
 */
public class Hooks {

    /**
     * Setup method - runs before each scenario
     * Initializes browser and maximizes window
     */
    @Before
    public void setUp() {
        DriverFactory.getDriver();
        System.out.println("Browser launched successfully!");
    }

    /**
     * Teardown method - runs after each scenario
     * Closes browser and cleans up resources
     */
    @After
    public void tearDown() {
        DriverFactory.quitDriver();
        System.out.println("Browser closed successfully!");
    }
}