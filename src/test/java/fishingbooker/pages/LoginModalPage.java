package fishingbooker.pages;

import fishingbooker.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginModalPage extends BasePage {

    public LoginModalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators

    @FindBy(css = "a[data-testid='desktop-open-login-modal']")
    public WebElement loginButtonOnSitemap;

    @FindBy(id = "username")
    public WebElement emailField;

    @FindBy(css = "button[data-testid='auth-submit-button']")
    public WebElement continueWithEmailButton;

    @FindBy(id = "password")
    public WebElement passwordField;

    @FindBy(css = "button[data-testid='auth-password-submit-button']")
    public WebElement finalLoginButton;

    // Methods

    public void openLoginModal() {
        loginButtonOnSitemap.click();
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
        continueWithEmailButton.click();
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
        finalLoginButton.click();
    }

}
