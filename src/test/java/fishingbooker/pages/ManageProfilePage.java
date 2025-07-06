package fishingbooker.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ManageProfilePage {

    WebDriver driver;

    public ManageProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Upload profile image (input[type='file']) *****************

    @FindBy(css = "input[type='file']")
    public WebElement profileImageInput;

    // Phone number

    @FindBy(id = "phone")
    public WebElement phoneInput;

    @FindBy(id = "phone-error")
    public WebElement phoneErrorMessage;

    @FindBy(css = "small.help-block")
    public WebElement phoneInfoMessage;

    // Date dropdowns

    @FindBy(xpath = "//select[contains(@class, 'date-field-margin')][1]")
    public WebElement monthDropdown;

    @FindBy(xpath = "//select[contains(@class, 'date-field-margin')][2]")
    public WebElement dayDropdown;

    @FindBy(xpath = "//select[contains(@class, 'date-field-width')][not(contains(@class,'date-field-margin'))]")
    public WebElement yearDropdown;

    // Save changes

    @FindBy(xpath = "//button[contains(text(),'Save changes')]")
    public WebElement saveChangesButton;

    // Success label ***********************

    @FindBy(css = ".label.label-success")
    public WebElement successMessage;

    public void uploadProfileImage(String imagePath) {
        profileImageInput.sendKeys(imagePath);
    }

    public void enterPhoneNumber(String phone) {
        phoneInput.clear();
        phoneInput.sendKeys(phone);
    }

    public void selectBirthday(String day, String month, String year) {
        new Select(monthDropdown).selectByVisibleText(month);
        new Select(dayDropdown).selectByVisibleText(day);
        new Select(yearDropdown).selectByVisibleText(year);
    }

    public void clickSaveChanges() {
        saveChangesButton.click();
    }

    public boolean isSuccessMessageVisible() {
        try {
            return successMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
