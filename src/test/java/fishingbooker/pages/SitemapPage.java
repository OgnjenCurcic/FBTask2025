package fishingbooker.pages;

import fishingbooker.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SitemapPage extends BasePage {

    public SitemapPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a[data-testid='desktop-open-signup-modal']")
    private WebElement openSignUpModalButton;

    public void clickSignUpButtonOnSitemapPage() {
        click(openSignUpModalButton);
    }

}