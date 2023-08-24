package cvrgo.definitions;

import cvrgo.config.WebDriverFactory;
import cvrgo.constants.MainConstants;
import cvrgo.constants.PageNamesConstants;
import cvrgo.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

public class BrowserStepDefinition {

    private WebDriver driver;
    private BasePage currentPage;

    @Given("I am on the CoverGo homepage")
    public void iAmOnTheCoverGoHomepage() {
        driver = WebDriverFactory.createWebDriver();
        driver.get(MainConstants.COVERGO_MAIN_URL);
        currentPage = new HomePage(driver);
    }

    @When("I search for {string}")
    public void search_for(String query) {
        WebElement element = driver.findElement(By.name("q"));
        // Enter something to search for
        element.sendKeys(query);
        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();
    }

    @Then("the page title should be equal to {string}")
    public void checkTitle(String expectedTitle) {
        Assertions.assertThat(currentPage.getPageTitle()).as("Page title validation").isEqualTo(expectedTitle);
    }

    @When("I looked at the {string} element")
    public void iLookedAtTheElement(String elementName) {
        boolean visibilityResult = false;
        switch (elementName) {
            case "top menu items list" -> visibilityResult = currentPage.isTopMenuItemsListDisplayed();
            case "top header" -> visibilityResult = currentPage.isMainHeaderElementDisplayed();
            case "Get In Touch" -> visibilityResult = currentPage.isGetInTouchElementDisplayed();
            default -> throw new IllegalStateException("Unknown element name to proceed: \"" + elementName + "\"");
        }
        Assertions.assertThat(visibilityResult)
                .as("\"" + elementName + "\" visibility")
                .isTrue();
    }

    @Then("I see {string} text")
    public void iSeeText(String expectedText) {
        Assertions.assertThat(currentPage.getLastObservedElementText())
                .as("Element text value verification")
                .isEqualTo(expectedText);
    }

    @When("I looked at the top menu items list")
    public void iLookedAtTheTopMenuItemsList() {

    }

    @Then("It consists of the following valuable items:")
    public void itContainsTheFollowingItems(List<String> items) {
        List<String> actualFilteredItems = currentPage.getLastObservedElementListItems();
        actualFilteredItems.removeAll(Arrays.asList("", null));
        Assertions.assertThat(actualFilteredItems)
                .as("Items list elements content verification")
                .hasSameElementsAs(items);
    }

    @When("I clicked at {string} menu item element")
    public void iClickedAtMenuItemElement(String menuItem) {
        currentPage.getTopMenuItemElement(menuItem).click();
    }

    @Then("I see {string} page is opened")
    public void iSeePageIsOpened(String pageName) throws InterruptedException {
        switch (pageName) {
            case PageNamesConstants.CONTACT_US_PAGE -> currentPage = new ContactUsPage(driver);
            case PageNamesConstants.OUR_CLIENTS_PAGE -> currentPage = new OurClientsPage(driver);
            case PageNamesConstants.ABOUT_US_PAGE -> currentPage = new AboutUsPage(driver);
            case PageNamesConstants.CASE_STUDIES_PAGE -> currentPage = new CaseStudiesPage(driver);
            case PageNamesConstants.HOME_PAGE_PAGE -> currentPage = new HomePage(driver);

            default -> throw new IllegalStateException("Unsupported page name value: " + pageName);
        }
    }

    @And("Page Url Path ends with {string}")
    public void pageUrlPathEndsWith(String urlEnding) {
        Assertions.assertThat(currentPage.isPagePathEndsWithProvided(urlEnding))
                .as("Validation ending of URL")
                .isTrue();
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

    @And("It has text in uppercase")
    public void itHasTextInUppercase() {
        Assertions.assertThat(currentPage.isLastObservedItemInUppercase())
                .as("Check if text is in uppercase")
                .isTrue();
    }

    @And("There is a video on the page")
    public void thereIsAVideoOnThePage() {
        Assertions.assertThat(currentPage.isThereVideoPlayer())
                .as("Check if there is video player")
                .isTrue();
    }

    @When("I clicked at logo")
    public void iClickedAtLogo() {
        currentPage.clickAtLogo();
    }
}