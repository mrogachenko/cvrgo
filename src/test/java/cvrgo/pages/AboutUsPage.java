package cvrgo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static cvrgo.constants.MainConstants.DEFAULT_LOAD_PAGE_TIMEOUT_MS;

public class AboutUsPage extends BasePage {
    By pageMarker = By.xpath("//*[@id='__layout']/..//h2[text()='The no-code platform transforming  the insurance industry.']");
    By getInTouchElement = By.xpath("//*[@id='__layout']/..//div[normalize-space()='Get in touch']");

    public AboutUsPage(WebDriver driver) {
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
