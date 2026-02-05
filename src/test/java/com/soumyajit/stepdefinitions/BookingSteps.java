package com.soumyajit.stepdefinitions;

import com.soumyajit.pages.HomePage;
import com.soumyajit.pages.ResultPage;
import com.soumyajit.utils.ConfigReader;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;

public class BookingSteps {

    HomePage homePage = new HomePage();
    ResultPage resultPage = new ResultPage();

    @Given("the user launches the RedBus application")
    public void the_user_launches_the_red_bus_application() {
        homePage.openSite(ConfigReader.get("baseUrl"));
    }

    @And("the user is on the RedBus home page")
    public void the_user_is_on_the_red_bus_home_page() {
        Assert.assertTrue(homePage.isHomePageLoaded());
    }

    @When("the user enters the source city as {string}")
    public void the_user_enters_the_source_city_as(String fromCity) {
        homePage.enterFromCityInput(fromCity);
    }

    @And("the user enters the destination city as {string}")
    public void the_user_enters_the_destination_city_as(String toCity) {
        homePage.enterToCityInput(toCity);
    }

    @And("the user selects the journey date as {string}")
    public void the_user_selects_the_journey_date_as(String date) throws InterruptedException {
        homePage.selectDate(date);
    }

    @And("the user clicks on the search buses button")
    public void the_user_clicks_on_the_search_buses_button() {
        homePage.searchBuses();
    }

    @Then("the list of available buses should be displayed")
    public void the_list_of_available_buses_should_be_displayed() {
        Assert.assertTrue(resultPage.isResultsDisplayed());
    }

    @When("the user selects a bus from the search results")
    public void the_user_selects_a_bus_from_the_search_results() {
        resultPage.selectBus(1);
    }

    @Then("the seat layout should be displayed")
    public void the_seat_layout_should_be_displayed() {
        Assert.assertTrue(resultPage.isSeatLayoutDisplayed());

        // NEW: Get and display all available seats
        List<String> availableSeats = resultPage.getAvailableSeats();
        System.out.println("\nAvailable seats for booking: " + availableSeats);
    }

    @When("the user selects an available seat")
    public void the_user_selects_an_available_seat() {
        // IMPROVED: Uses new method
        resultPage.selectFirstAvailableSeat();
    }

    @Then("the seat should be marked as selected.")
    public void the_seat_should_be_marked_as_selected() {
        Assert.assertTrue(resultPage.isFirstSeatSelected(),
                "Expected at least one seat to be selected");
    }
}