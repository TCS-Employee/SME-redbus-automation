package com.soumyajit.pages;

import com.soumyajit.base.BasePage;
import com.soumyajit.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "srcinput")
    private WebElement fromCityInput;

    @FindBy(id = "destinput")
    private WebElement toCityInput;

    @FindBy(xpath = "//div[contains(@aria-label,'Select Date of Journey.')]")
    private WebElement dateSelector;

    @FindBy(xpath = "//div[contains(@class,'monthArea')]")
    private WebElement monthYearSelector;

    @FindBy(xpath = "//i[contains(@aria-label,'Next month')]")
    private WebElement nextButton;

    @FindBy(xpath = "//ul[contains(@class,'datesWrap')]")
    private WebElement allDays;

    @FindBy(xpath = "//button[@aria-label='Search buses']")
    private WebElement searchButton;

    private final WebDriver driver;

    public HomePage() {
        driver = BasePage.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void openSite(String url) {
        driver.get(url);
    }

    public boolean isHomePageLoaded() {
        WaitUtils.waitForVisible(fromCityInput);
        return fromCityInput.isDisplayed();
    }

    // FIXED: Uses keyboard navigation
    public void enterFromCityInput(String fromCity) {
        try {
            WaitUtils.waitForVisible(fromCityInput);
            fromCityInput.clear();
            fromCityInput.sendKeys(fromCity);
            Thread.sleep(2000);  // Wait for dropdown
            fromCityInput.sendKeys(Keys.ARROW_DOWN);
            fromCityInput.sendKeys(Keys.ENTER);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // FIXED: Uses keyboard navigation
    public void enterToCityInput(String toCity) {
        try {
            WaitUtils.waitForClickable(toCityInput);
            toCityInput.click();
            toCityInput.clear();
            toCityInput.sendKeys(toCity);
            Thread.sleep(2000);  // Wait for dropdown
            toCityInput.sendKeys(Keys.ARROW_DOWN);
            toCityInput.sendKeys(Keys.ENTER);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // This method is now optional - city selection handles boarding point
    public void selectBoardingPoint(String boardingPoint) {
        System.out.println("Boarding point handled by city selection: " + boardingPoint);
        // No action needed - Arrow Down + Enter selects default
    }

    public void selectDate(String date) throws InterruptedException {
        String[] parts = date.split(" ");
        String day = parts[0];
        String monthAndYear = parts[1] + " " + parts[2];

        WaitUtils.waitForClickable(dateSelector);
        dateSelector.click();

        while (true) {
            WaitUtils.waitForVisible(monthYearSelector);
            String currentText = monthYearSelector.getText();

            if (currentText.contains(monthAndYear)) {
                break;
            }
            nextButton.click();
            Thread.sleep(300);
        }

        WaitUtils.waitForClickable(allDays);
        String dynamicDatePath = String.format(
                "//div[contains(@class,'calendarDate')]//span[text()='%s']", day
        );

        WebElement boardingDate = driver.findElement(By.xpath(dynamicDatePath));
        WaitUtils.waitForClickable(boardingDate);
        boardingDate.click();
    }

    public void searchBuses() {
        WaitUtils.waitForClickable(searchButton);
        searchButton.click();
    }
}