package com.soumyajit.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.soumyajit"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html"
        }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    // FIXED: Removed parallel=true to prevent browser crashes
    @Override
    @DataProvider(parallel = false)  // Changed to false
    public Object[][] scenarios() {
        return super.scenarios();
    }
}