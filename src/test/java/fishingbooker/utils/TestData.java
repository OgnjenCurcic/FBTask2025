package fishingbooker.utils;

public class TestData {

    // Valid

    public static final String VALID_FIRST_NAME = "Ognjen";
    public static final String VALID_LAST_NAME = "FB";
    public static final String VALID_PASSWORD = "OgnjenFB123!";
    public static final String EXISTING_EMAIL = "fb_test@gmail.com";

    // Invalid

    public static final String INVALID_EMAIL = "fb_test@gmail";
    public static final String PASSWORD_WITHOUT_ENOUGH_CHARS = "@Og123";
    public static final String PASSWORD_WITHOUT_NUMBER_VALUES = "OgnjenFishingBooker!";
    public static final String PASSWORD_WITHOUT_SPECIAL_CHARS = "OgnjenFB12345";
    public static final String PASSWORD_WITHOUT_UPPERCASE_CHARS = "ognjenfb12345!";

    // Login Data

    public static final String LOGIN_EMAIL = "ognjen@gmail.com";
    public static final String LOGIN_PASSWORD = "Ognjen12345!";

}
