package fishingbooker.tests;

import fishingbooker.base.BaseTest;
import fishingbooker.utils.RandomUtils;
import fishingbooker.utils.TestData;
import fishingbooker.pages.SignUpModalPage;
import fishingbooker.pages.SitemapPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest extends BaseTest {

    private SitemapPage sitemapPage;
    private SignUpModalPage signUpModalPage;

    @BeforeMethod
    public void openSignUpModal() {

        // PopUp Window problem fix
        // HTTP Basic Auth
        // https://<username>:<password>@<URL> format is used
        driver.get("https://fishingbooker:QAFBTest@qahiring.dev.fishingbooker.com/sitemap");

        sitemapPage = new SitemapPage(driver);
        sitemapPage.clickSignUpButtonOnSitemapPage();

        signUpModalPage = new SignUpModalPage(driver);
        signUpModalPage.waitForModalToBeVisible(); // ensures modal is ready
    }

    // Test clicking the SignUp Button on Sitemap Page and displaying SignUp Modal

    @Test
    public void clickSignUpButtonOnSitemapPage() {

        // Here we assert if the SignUp Modal is displayed
        SignUpModalPage modal = new SignUpModalPage(driver);
        Assert.assertTrue(modal.isModalVisible(), "Sign up modal should be visible");

        // Debug output
        System.out.println("Modal title text: >>>" + modal.getModalTitle() + "<<<");

        // Assert just the first line or trimmed value
        String firstLine = modal.getModalTitle().split("\n")[0].trim();
        Assert.assertEquals(firstLine, "Sign up");
    }

    // Test with Positive / Valid Credentials

    @Test
    public void signUpWithValidCredentials() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail());
        signUpModalPage.fillPassword(TestData.VALID_PASSWORD);

        signUpModalPage.clickSignUpOnModal();
    }

    // Test with Negative / Invalid Credentials

    @Test
    public void signUpWithEmptyFields() {

        signUpModalPage.clickSignUpOnModal();
        Assert.assertTrue(signUpModalPage.isFirstNameErrorVisible());
        Assert.assertTrue(signUpModalPage.isLastNameErrorVisible());
        Assert.assertTrue(signUpModalPage.isEmailErrorVisible());
        Assert.assertTrue(signUpModalPage.isPasswordRequiredErrorVisible());
    }

    @Test
    public void signUpWithoutFirstName() {

        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME); // Missing First Name
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail());
        signUpModalPage.fillPassword(TestData.VALID_PASSWORD);
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isFirstNameErrorVisible());
    }

    @Test
    public void signUpWithoutLastName() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail()); // Missing Last Name
        signUpModalPage.fillPassword(TestData.VALID_PASSWORD);
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isLastNameErrorVisible());
    }

    @Test
    public void signUpWithoutEmail() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillPassword(TestData.VALID_PASSWORD); // Missing Email
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isEmailErrorVisible());
    }

    @Test
    public void signUpWithInvalidEmail() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(TestData.INVALID_EMAIL); // Invalid email
        signUpModalPage.fillPassword(TestData.VALID_PASSWORD);
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isEmailErrorVisible());
    }

    @Test
    public void signUpWithoutPassword() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail());
        signUpModalPage.clickSignUpOnModal(); // Missing Password

        Assert.assertTrue(signUpModalPage.isPasswordRequiredErrorVisible());
    }

    @Test
    public void signUpPasswordMissingCharacters() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail());
        signUpModalPage.fillPassword(TestData.PASSWORD_WITHOUT_ENOUGH_CHARS); // Not enough characters (* less than 12)
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isPasswordLengthErrorVisible());
    }

    @Test
    public void signUpPasswordMissingNumber() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail());
        signUpModalPage.fillPassword(TestData.PASSWORD_WITHOUT_NUMBER_VALUES); // No number values
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isPasswordNumberErrorVisible());
    }

    @Test
    public void signUpPasswordMissingSpecialCharacter() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail());
        signUpModalPage.fillPassword(TestData.PASSWORD_WITHOUT_SPECIAL_CHARS); // No Special Characters
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isPasswordSpecialCharErrorVisible());
    }

    @Test
    public void signUpPasswordMissingUppercaseLetter() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail());
        signUpModalPage.fillPassword(TestData.PASSWORD_WITHOUT_UPPERCASE_CHARS); // No UpperCase Lette
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isPasswordUppercaseErrorVisible());

    }

    @Test
    public void signUpWithAlreadyRegisteredEmail() {

        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(TestData.EXISTING_EMAIL); // Already registered email
        signUpModalPage.fillPassword(TestData.VALID_PASSWORD);
        signUpModalPage.clickSignUpOnModal();

        Assert.assertTrue(signUpModalPage.isEmailAlreadyRegisteredErrorVisible());
    }

}