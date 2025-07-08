package fishingbooker.pages;

import fishingbooker.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SitemapPage extends BasePage {

    public SitemapPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators

    @FindBy(css = "a[data-testid='desktop-open-signup-modal']")
    private WebElement openSignUpModalButton;

    @FindBy(css = "div[data-testid='nav-username']")
    public WebElement navUsername;

    // Methods

    public void clickSignUpButtonOnSitemapPage() {
        click(openSignUpModalButton);
    }

    public void waitForNavUsernameToBeDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(navUsername));
    }


}