package fishingbooker.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginModalPage {

    WebDriver driver;

    public LoginModalPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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
