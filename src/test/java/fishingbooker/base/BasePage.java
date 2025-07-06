package fishingbooker.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click on an element
    protected void click(WebElement element) {
        try {
            wait.until(driver -> element.isDisplayed() && element.isEnabled());
            element.click();
        } catch (ElementClickInterceptedException | TimeoutException e) {
            System.out.println("WARNING: Could not click element. It may be disabled or hidden.");
        }
    }

    // Type text into a field
    protected void type(WebElement element, String text) {
        element.clear(); // Clear existing text
        element.sendKeys(text); // Type new text
    }

    // Get visible text from an element
    protected String getText(WebElement element) {
        return element.getText();
    }

    // Check if element is visible on screen
    protected boolean isElementDisplayed(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
