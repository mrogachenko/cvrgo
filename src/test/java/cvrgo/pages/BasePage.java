package cvrgo.pages;

import cvrgo.constants.MainConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static cvrgo.constants.MainConstants.VIDEO_CONTAINER_WAIT_TIMEOUT_MS;

public abstract class BasePage {
    protected WebDriver driver;
    protected By lastObservedElement = null;
    By topMenuList = By.xpath("//*[@id='__layout']/..//ul/li");
    By mainHeaderElement = By.xpath("//*[@id='__layout']/..//h1");
    By videoContainer = By.xpath("//*[@id='player']");
    By logo = By.xpath("//*[@id=\"__layout\"]/div/div[1]/nav/div/div[1]/a[1]/img");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isTopMenuItemsListDisplayed() {
        lastObservedElement = topMenuList;
        return isLastObservedElementDisplayed();
    }

    public boolean isMainHeaderElementDisplayed() {
        lastObservedElement = mainHeaderElement;
        return isLastObservedElementDisplayed();
    }

    protected boolean isLastObservedElementDisplayed() {
        return driver.findElement(lastObservedElement).isDisplayed();
    }

    public List<String> getLastObservedElementListItems() {
        ArrayList<String> result = new ArrayList<>();
        List<WebElement> elements = driver.findElements(lastObservedElement);
        for (WebElement e : elements) {
            result.add(e.getText());
        }
        return result;
    }

    public String getLastObservedElementText() {
        return driver.findElement(lastObservedElement).getText();
    }

    public WebElement getTopMenuItemElement(String menuItemName) {
        String xpath = String.format("//*[@id='__layout']/..//ul/li//span[text()='%s'] | //*[@id='__layout']/..//ul/li//div[text()='%s']", menuItemName, menuItemName);
        return driver.findElement(By.xpath(xpath));
    }

    public boolean isPagePathEndsWithProvided(String expectedPath) {
        return driver.getCurrentUrl().endsWith(expectedPath);
    }

    public boolean isLastObservedItemInUppercase() {
        String text = driver.findElement(lastObservedElement).getText();
        return text.equals(text.toUpperCase());
    }

    /** Returned false by default since not every page
     * will have 'Get In Touch' element, but some of them
     * have it. In the pages when this element is present
     * there will be override method
     *
     * @return true if Get In Touch element is displayed
     */
    public boolean isGetInTouchElementDisplayed() {
        return false;
    }

    public boolean isThereVideoPlayer() {
        // Video player is located in separate frame
        try {
            driver.switchTo().frame(0);
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofMillis(VIDEO_CONTAINER_WAIT_TIMEOUT_MS));
            wait.until(d -> driver.findElement(videoContainer).isDisplayed());
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void clickAtLogo() {
        driver.findElement(logo).click();
    }
}