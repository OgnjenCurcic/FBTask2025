package fishingbooker.tests;

import fishingbooker.base.BaseTest;
import fishingbooker.pages.LoginModalPage;
import fishingbooker.pages.ManageProfilePage;
import fishingbooker.pages.SitemapPage;
import fishingbooker.utils.TestData;
import fishingbooker.utils.URLs;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class ManageProfileTest extends BaseTest {

    private LoginModalPage loginModalPage;
    private ManageProfilePage manageProfilePage;

    @BeforeMethod
    public void setUp() {

        driver.navigate().to(URLs.HOME_PAGE_URL);

        loginModalPage = new LoginModalPage(driver);

        loginModalPage.openLoginModal();
        loginModalPage.enterEmail(TestData.LOGIN_EMAIL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loginModalPage.passwordField));

        loginModalPage.enterPassword(TestData.LOGIN_PASSWORD);

        driver.navigate().to(URLs.MANAGE_PROFILE_PAGE_URL);
    }

}
