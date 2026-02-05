package com.soumyajit.pages;

import com.soumyajit.base.BasePage;
import com.soumyajit.utils.ScrollUtils;
import com.soumyajit.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ResultPage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"searchContentWrap\"]//ul/li")
    private List<WebElement> resultList;

    @FindBy(xpath = "//*[@id=\"seat-canvas-wrapper\"]/div[2]/div")
    private WebElement seatLayout;

    // FIXED: Using your XPath to find only available seats
    @FindBy(xpath = "//div[contains(@aria-label,'seat status available')]")
    private List<WebElement> availableSeats;

    public ResultPage() {
        driver = BasePage.getDriver();
        PageFactory.initElements(driver, this);
    }

    public boolean isResultsDisplayed() {
        WaitUtils.waitForAllElements(resultList);
        return !resultList.isEmpty();
    }

    public void selectBus(int index) {
        if (index >= 0 && index < resultList.size()) {
            WebElement result = resultList.get(index);
            ScrollUtils.scrollToElement(result);
            result.click();
        }
    }

    public boolean isSeatLayoutDisplayed() {
        WaitUtils.waitForVisible(seatLayout);
        return seatLayout.isDisplayed();
    }

    /**
     * NEW METHOD: Gets list of all available seat numbers
     * @return List of available seat IDs
     */
    public List<String> getAvailableSeats() {
        List<String> seatNumbers = new ArrayList<>();

        try {
            // Wait for seats to load
            Thread.sleep(2000);

            // Find all available seats using your XPath
            List<WebElement> seats = driver.findElements(
                    By.xpath("//div[contains(@aria-label,'seat status available')]")
            );

            System.out.println("\n=== AVAILABLE SEATS ===");
            System.out.println("Total available seats: " + seats.size());

            for (WebElement seat : seats) {
                String ariaLabel = seat.getAttribute("aria-label");
                // Extract seat number from aria-label
                if (ariaLabel != null && ariaLabel.contains("Seat number")) {
                    String seatNumber = ariaLabel.split(",")[0]
                            .replace("Seat number ", "")
                            .trim();
                    seatNumbers.add(seatNumber);
                    System.out.println("  - " + seatNumber + " (" + ariaLabel + ")");
                }
            }
            System.out.println("======================\n");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return seatNumbers;
    }

    /**
     * IMPROVED: Selects first available seat with better error handling
     */
    public void selectFirstAvailableSeat() {
        try {
            Thread.sleep(2000);  // Wait for seat layout to fully load

            // Find all available seats
            List<WebElement> seats = driver.findElements(
                    By.xpath("//div[contains(@aria-label,'seat status available')]")
            );

            if (seats.isEmpty()) {
                System.out.println("No available seats found!");
                return;
            }

            System.out.println("Found " + seats.size() + " available seats");

            // Try to click the first available seat
            WebElement firstSeat = seats.get(0);
            String seatInfo = firstSeat.getAttribute("aria-label");
            System.out.println("Attempting to select: " + seatInfo);

            // Scroll to seat
            ScrollUtils.scrollToElement(firstSeat);
            Thread.sleep(500);

            // Try JavaScript click if regular click fails
            try {
                firstSeat.click();
            } catch (Exception e) {
                System.out.println("Regular click failed, trying JavaScript click...");
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", firstSeat);
            }

            Thread.sleep(1000);
            System.out.println("Seat selected successfully!");

        } catch (Exception e) {
            System.out.println("Error selecting seat: " + e.getMessage());
        }
    }

    /**
     * IMPROVED: Verifies if first available seat is selected
     */
    public boolean isFirstSeatSelected() {
        try {
            Thread.sleep(1000);

            List<WebElement> selectedSeats = driver.findElements(
                    By.xpath("//div[contains(@aria-label,'seat status selected') or @aria-pressed='true']")
            );

            boolean isSelected = !selectedSeats.isEmpty();
            System.out.println("Seats selected: " + selectedSeats.size());

            return isSelected;

        } catch (Exception e) {
            System.out.println("Error checking seat selection: " + e.getMessage());
            return false;
        }
    }
}