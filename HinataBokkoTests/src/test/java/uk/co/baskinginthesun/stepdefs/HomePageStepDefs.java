package uk.co.baskinginthesun.stepdefs;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Dimension;
import uk.co.baskinginthesun.util.BrowserDriver;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static uk.co.baskinginthesun.util.BrowserDriver.*;
import static uk.co.baskinginthesun.util.Config.urlWithPath;

public class HomePageStepDefs {
    private static final String POP_UP_MENU_SELECTOR = "nav ul li ul";
    private static final String NAV_MENU_SELECTOR = "nav ul";

    @Given("^I am browsing on a computer$")
    public void Browser_window_is_large() {
        BrowserDriver.getCurrentDriver().manage().window().setSize(new Dimension(1280, 1024));
    }

    @Given("^I am browsing on a phone$")
    public void I_am_browsing_on_a_phone() {
        BrowserDriver.getCurrentDriver().manage().window().setSize(new Dimension(640, 1136));
    }

    @Given("^I navigate to the home page$")
    public void I_navigate_to_the_home_page() {
        loadPage("");
    }

    @When("^I (?:then )?click (?:again )?on the link that says (.+)$")
    public void I_click_on_the_link_that_says_link_text(final String linkText) {
        user().moveToElement(findLinkByText(linkText)).click().perform();
    }

    @Then("^I am taken to the (.+) page$")
    public void I_am_taken_to_the_target_page(final String path) {
        final String realPath = "<home>".equals(path) ? "" : path;
        pause().until(urlToBe(urlWithPath(realPath)));
    }

    @When("^I hover over the link that says (.+)$")
    public void I_hover_over_the_link_that_says_レッスン(final String linkText) {
        user().moveToElement(findLinkByText(linkText)).build().perform();
    }

    @Then("^a submenu pops up$")
    public void a_submenu_pops_up_with_the_following_options() {
        pause().until(visibilityOfElementLocated(cssSelector(POP_UP_MENU_SELECTOR)));
    }

    @And("^it contains the following options$")
    public void it_contains_the_following_options(final DataTable options) {
        options.asList(String.class).forEach(BrowserDriver::findLinkByText);
    }

    @When("^I move away from the link$")
    public void I_move_away_from_the_link() {
        user().moveByOffset(-100, -100).build().perform();
    }

    @Then("^the submenu disappears$")
    public void the_submenu_disappears() {
        pause().until(invisibilityOfElementLocated(cssSelector(POP_UP_MENU_SELECTOR)));
    }

    @Then("^the navigation menu expands$")
    public void the_navigation_menu_expands() {
        pause().until(visibilityOfElementLocated(cssSelector(NAV_MENU_SELECTOR)));
    }

    @Then("^the navigation menu hides$")
    public void the_navigation_menu_hides() {
        pause().until(invisibilityOfElementLocated(cssSelector(NAV_MENU_SELECTOR)));
    }
}
