package fishingbooker.pages;

import fishingbooker.base.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpModalPage extends BasePage {

    public SignUpModalPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators for Fields and Buttons

    @FindBy(xpath = "//div[contains(@class,'sc-ehmTmK') and contains(text(),'Sign up')]")
    private WebElement modalTitle;

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "username")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(css = "button[data-testid='auth-submit-button']")
    private WebElement signUpButton;

    // Locators for Error Messages

    @FindBy(xpath = "//div[contains(text(), 'First Name is required.')]")
    private WebElement firstNameError;

    @FindBy(xpath = "//div[contains(text(), 'Last Name is required.')]")
    private WebElement lastNameError;

    @FindBy(xpath = "//div[contains(text(), 'Enter a valid email.')]")
    private WebElement emailError;

    @FindBy(xpath = "//div[contains(text(), 'Password is required')]")
    private WebElement passwordRequiredError;

    @FindBy(xpath = "//div[contains(text(), 'Password must be at least 12 characters long')]")
    private WebElement passwordLengthError;

    @FindBy(xpath = "//div[contains(text(), 'Must contain at least one number')]")
    private WebElement passwordNumberError;

    @FindBy(xpath = "//div[contains(text(), 'Must contain at least one special character')]")
    private WebElement passwordSpecialCharError;

    @FindBy(xpath = "//div[contains(text(), 'Must contain at least one uppercase letter')]")
    private WebElement passwordUppercaseCharError;

    @FindBy(xpath = "//div[contains(text(), 'already registered at FishingBooker')]")
    private WebElement emailAlreadyExistsError;

    public boolean isModalVisible() {
        return isElementDisplayed(modalTitle);
    }

    public void waitForModalToBeVisible() {
        wait.until(ExpectedConditions.visibilityOf(modalTitle));
    }

    public boolean isEmailAlreadyRegisteredErrorVisible() {
        return isElementDisplayed(emailAlreadyExistsError);
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    public void fillFirstName(String firstName) {
        type(firstNameInput, firstName);
    }

    public void fillLastName(String lastName) {
        type(lastNameInput, lastName);
    }

    public void fillEmail(String email) {
        type(emailInput, email);
    }

    public void fillPassword(String password) {
        type(passwordInput, password);
    }

    public void clickSignUpOnModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(signUpButton));
            signUpButton.click();
        } catch (TimeoutException e) {
            if (signUpButton.isDisplayed() && signUpButton.isEnabled()) {
                signUpButton.click();
            } else {
                System.out.println("Sign Up button is not clickable. Likely due to invalid password input.");
            }
        }
    }

    // Error check methods

    public boolean isFirstNameErrorVisible() {
        return isElementDisplayed(firstNameError);
    }

    public boolean isLastNameErrorVisible() {
        return isElementDisplayed(lastNameError);
    }

    public boolean isEmailErrorVisible() {
        return isElementDisplayed(emailError);
    }

    public boolean isPasswordRequiredErrorVisible() {
        return isElementDisplayed(passwordRequiredError);
    }

    public boolean isPasswordLengthErrorVisible() {
        return isElementDisplayed(passwordLengthError);
    }

    public boolean isPasswordNumberErrorVisible() {
        return isElementDisplayed(passwordNumberError);
    }

    public boolean isPasswordSpecialCharErrorVisible() {
        return isElementDisplayed(passwordSpecialCharError);
    }

    public boolean isPasswordUppercaseErrorVisible() {
        return isElementDisplayed(passwordUppercaseCharError);
    }
}
