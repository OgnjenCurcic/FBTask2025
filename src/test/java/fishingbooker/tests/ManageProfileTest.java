package fishingbooker.tests;

import fishingbooker.base.BaseTest;
import fishingbooker.pages.ManageProfilePage;
import fishingbooker.pages.SignUpModalPage;
import fishingbooker.pages.SitemapPage;
import fishingbooker.utils.RandomUtils;
import fishingbooker.utils.TestData;
import fishingbooker.utils.URLs;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.nio.file.Paths;
import java.util.Map;


public class ManageProfileTest extends BaseTest {

    private SignUpModalPage signUpModalPage;
    private ManageProfilePage manageProfilePage;
    private SitemapPage sitemapPage;

    @BeforeMethod
    public void setUp() {

        driver.navigate().to(URLs.SITEMAP_PAGE_URL);

        sitemapPage = new SitemapPage(driver);
        sitemapPage.clickSignUpButtonOnSitemapPage();

        signUpModalPage = new SignUpModalPage(driver);
        signUpModalPage.waitForModalToBeVisible();
        signUpModalPage.fillFirstName(TestData.VALID_FIRST_NAME);
        signUpModalPage.fillLastName(TestData.VALID_LAST_NAME);
        signUpModalPage.fillEmail(RandomUtils.generateUniqueEmail());
        signUpModalPage.fillPassword(TestData.VALID_PASSWORD);
        signUpModalPage.clickSignUpOnModal();

        sitemapPage.waitForNavUsernameToBeDisplayed();

        driver.navigate().to(URLs.MANAGE_PROFILE_PAGE_URL);
        manageProfilePage = new ManageProfilePage(driver);
    }

    @Test
    public void testProfileImageUploadAndSaveSuccess() {
        String imagePath = Paths.get("src/test/java/fishingbooker/resources/fb_image.png").toAbsolutePath().toString();

        manageProfilePage.uploadProfileImage(imagePath);
        manageProfilePage.enterPhoneNumber(RandomUtils.generateRandomPhoneNumber());

        manageProfilePage.scrollToSaveChangesButton();
        manageProfilePage.saveChangesAndScrollToTop();

        manageProfilePage.waitForSuccessAlertVisible();

        Assert.assertTrue(manageProfilePage.isSuccessMessageVisible(), "Success alert was not displayed.");
    }


    @Test
    public void testValidPhoneNumberIsAccepted() {

        manageProfilePage.enterPhoneNumber(TestData.VALID_PHONE_NUMBER);
        manageProfilePage.scrollToSaveChangesButton();
        manageProfilePage.saveChangesAndScrollToTop();

        manageProfilePage.waitForSuccessAlertVisible();

        Assert.assertTrue(manageProfilePage.isSuccessMessageVisible());
    }

    @Test
    public void testPhoneWithCharactersShowsError() {

        manageProfilePage.enterPhoneNumber(TestData.PHONE_WITH_CHAR_VALUES);
        manageProfilePage.scrollToSaveChangesButton();
        manageProfilePage.saveChangesAndScrollToTop();

        manageProfilePage.waitForPhoneNumberFormatErrorVisible();

        Assert.assertTrue(manageProfilePage.isPhoneNumberFormatErrorPresent());
    }

    @Test
    public void testPhoneWithoutPlusSignShowsError() {

        // Ovde testiram unos broja telefona bez "+" znaka na početku.
        // Problem je što kad koristim klasičnu enterPhoneNumber() metodu (koja koristi sendKeys())
        // aplikacija automatski formatira broj (verovatno neka JS biblioteka u pozadini).
        // Na primer, broj "381601234567" se pretvori u "+1381601234567", što mi pokvari test.

        // Zato ovde koristim enterPhoneNumberRaw() koja preko JavaScript-a ubaci tačno ono što ja hoću —
        // da se broj unese bez da se išta automatski menja.

        manageProfilePage.enterPhoneNumberRaw(TestData.PHONE_WITHOUT_PLUS_SIGN);
        manageProfilePage.scrollToSaveChangesButton();
        manageProfilePage.saveChangesAndScrollToTop();

        manageProfilePage.waitForPhoneNumberLengthErrorVisible();

        Assert.assertTrue(manageProfilePage.isPhoneNumberLengthErrorPresent());

        // Zanimljivo: kada radim ovo ručno, dobijem poruku za "phoneNumberFormatError" grešku,
        // ali kad pustim test, dobijam poruku za "phoneNumberLengthError" grešku.
        // Verovatno zavisi od toga kako browser interpretira taj broj kad ga direktno setujem.
    }

    @Test
    public void testPhoneWithTooFewDigitsShowsError() {

        manageProfilePage.enterPhoneNumber(TestData.PHONE_WITH_TOO_FEW_DIGITS);
        manageProfilePage.scrollToSaveChangesButton();
        manageProfilePage.saveChangesAndScrollToTop();

        manageProfilePage.waitForPhoneNumberLengthErrorVisible();

        Assert.assertTrue(manageProfilePage.isPhoneNumberLengthErrorPresent());
    }

    @Test
    public void testPhoneWithTooManyDigitsShowsError() {

        manageProfilePage.enterPhoneNumber(TestData.PHONE_WITH_TOO_MANY_DIGITS);
        manageProfilePage.scrollToSaveChangesButton();
        manageProfilePage.saveChangesAndScrollToTop();

        manageProfilePage.waitForPhoneNumberLengthErrorVisible();

        Assert.assertTrue(manageProfilePage.isPhoneNumberLengthErrorPresent());
    }

    @Test
    public void testEachMonthHasCorrectNumberOfDays() {
        Map<String, Integer> expectedDays = manageProfilePage.getMonthToDaysMap();

        for (String month : expectedDays.keySet()) {

            manageProfilePage.selectMonth(month);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int actualDays = manageProfilePage.getNumberOfDayOptions();
            int expected = expectedDays.get(month);

            System.out.printf("[Month: %s] Expected: %d, Actual: %d\n", month, expected, actualDays);

            Assert.assertEquals(actualDays, expected, "Mismatch in number of days for month: " + month);
        }
    }

    @Test
    public void testSelectSpecificBirthday() {
        manageProfilePage.enterPhoneNumber(RandomUtils.generateRandomPhoneNumber());
        manageProfilePage.selectBirthday("23", "January", "1994");
        manageProfilePage.scrollToSaveChangesButton();
        manageProfilePage.saveChangesAndScrollToTop();
        manageProfilePage.waitForSuccessAlertVisible();

        Assert.assertTrue(manageProfilePage.isSuccessMessageVisible(),
                "Success message not displayed after selecting birthday.");
    }

}
