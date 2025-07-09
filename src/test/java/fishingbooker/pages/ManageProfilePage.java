package fishingbooker.pages;

import fishingbooker.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Map;

public class ManageProfilePage extends BasePage {

    public ManageProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators

    // Upload profile image
    @FindBy(css = "input[type='file']")
    public WebElement profileImageInput;

    // Phone number
    @FindBy(id = "phone")
    public WebElement phoneNumberField;

    @FindBy(css = "div.alert.alert-danger[role='alert']") // Alert after including chars or removing plus sign from the phone number
    public WebElement phoneNumberFormatError;

    @FindBy(id = "phone-error") // Error after entering less than 8 and more than 15 digits into the phone number field
    public WebElement phoneNumberLengthError;

    // Success label
    @FindBy(css = "div.alert.alert-success[role='alert']")
    public WebElement successAlert;

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

    // Methods

    // Validation - Success Message

    public boolean isSuccessMessageVisible() {
        return isElementDisplayed(successAlert);
    }

    public void waitForSuccessAlertVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOf(successAlert));
    }

    // Profile Image

    public void uploadProfileImage(String imagePath) {
        profileImageInput.sendKeys(imagePath);
    }

    // Phone Number

    public void enterPhoneNumber(String phone) {
        phoneNumberField.clear();
        type(phoneNumberField, phone);
    }

    public void enterPhoneNumberRaw(String phone) {
        ((JavascriptExecutor) driver).executeScript("document.getElementById('phone').value = arguments[0];", phone);
    }

    public String getPhoneNumberFormatError() {
        return getText(phoneNumberFormatError);
    }

    public boolean isPhoneNumberFormatErrorPresent() {
        return isElementDisplayed(phoneNumberFormatError);
    }

    public void waitForPhoneNumberFormatErrorVisible() {
        wait.until(ExpectedConditions.visibilityOf(phoneNumberFormatError));
    }

    public String getPhoneNumberLengthError() {
        return getText(phoneNumberLengthError);
    }

    public boolean isPhoneNumberLengthErrorPresent() {
        return isElementDisplayed(phoneNumberLengthError);
    }

    public void waitForPhoneNumberLengthErrorVisible() {
        wait.until(ExpectedConditions.visibilityOf(phoneNumberLengthError));
    }

    // Save Changes

    public void clickSaveChanges() {
        click(saveChangesButton);
    }

    public void saveChangesAndScrollToTop() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Trying to click Save Changes...");
        clickSaveChanges();
        System.out.println("Now scrolling to top...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scrollToTop();
    }

    // Scrolling

    public void scrollToSaveChangesButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveChangesButton);
    }

    public void scrollToTop() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo({ top: 0, behavior: 'smooth' });");
    }

    // Date

    public Map<String, Integer> getMonthToDaysMap() {
        return monthToDaysMap;
    }

    private final Map<String, Integer> monthToDaysMap = Map.ofEntries(
            Map.entry("January", 31),
            Map.entry("February", 28),
            Map.entry("March", 31),
            Map.entry("April", 30),
            Map.entry("May", 31),
            Map.entry("June", 30),
            Map.entry("July", 31),
            Map.entry("August", 31),
            Map.entry("September", 30),
            Map.entry("October", 31),
            Map.entry("November", 30),
            Map.entry("December", 31)
    );

    public void selectMonth(String month) {
        new Select(monthDropdown).selectByVisibleText(month);
    }

    public int getNumberOfDayOptions() {
        return new Select(dayDropdown).getOptions().size() - 1;
    }

    public void selectBirthday(String day, String month, String year) {
        new Select(monthDropdown).selectByVisibleText(month);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Select(dayDropdown).selectByVisibleText(day);
        new Select(yearDropdown).selectByVisibleText(year);
    }




}
