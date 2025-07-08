package fishingbooker.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class RandomUtils {

    public static String generateUniqueEmail() {
        // System.currentTimeMillis() -> Used to always generate a random email
        return "fb_test+" + System.currentTimeMillis() + "@gmail.com";
    }

    public static String generateRandomPhoneNumber() {
        return "+1 201-" + (int)(Math.random() * 900 + 100) + "-" + (int)(Math.random() * 9000 + 1000);
    }

}
