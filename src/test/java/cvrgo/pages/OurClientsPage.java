package cvrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static cvrgo.constants.MainConstants.DEFAULT_LOAD_PAGE_TIMEOUT_MS;

public class OurClientsPage extends BasePage {
    By pageMarker = By.xpath("//*[@id='__layout']/..//h1[text()='A key partner for all industry players']");
    By getInTouchElement = By.xpath("//*[@id='__layout']/..//div[text()='Get in touch']");

    public OurClientsPage(WebDriver driver) {
        super(driver);
        // Exception if the page will not be loaded
        new WebDriverWait(driver, Duration.ofMillis(DEFAULT_LOAD_PAGE_TIMEOUT_MS))
                .until(d -> d.findElement(pageMarker));
    }

    public boolean isGetInTouchElementDisplayed() {
        lastObservedElement = getInTouchElement;
        return isLastObservedElementDisplayed();
    }
}
