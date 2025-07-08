package fishingbooker.utils;

public class TestData {

    // Valid Data

    public static final String VALID_FIRST_NAME = "Ognjen";
    public static final String VALID_LAST_NAME = "FB";
    public static final String VALID_PASSWORD = "OgnjenFB123!";
    public static final String EXISTING_EMAIL = "fb_test@gmail.com";
    public static final String VALID_PHONE_NUMBER = "+381601234567";

    // Invalid Email

    public static final String INVALID_EMAIL = "fb_test@gmail";

    // Invalid Passwords

    public static final String PASSWORD_WITHOUT_ENOUGH_CHARS = "@Og123";
    public static final String PASSWORD_WITHOUT_NUMBER_VALUES = "OgnjenFishingBooker!";
    public static final String PASSWORD_WITHOUT_SPECIAL_CHARS = "OgnjenFB12345";
    public static final String PASSWORD_WITHOUT_UPPERCASE_CHARS = "ognjenfb12345!";

    // Invalid Phone Numbers

    public static final String PHONE_WITH_CHAR_VALUES = "+3816012345FB";
    public static final String PHONE_WITHOUT_PLUS_SIGN = "3816014423";
    public static final String PHONE_WITH_TOO_FEW_DIGITS = "+3816012";
    public static final String PHONE_WITH_TOO_MANY_DIGITS = "+381601234567890123456789";

    // Login Data

    public static final String LOGIN_EMAIL = "ognjen@gmail.com";
    public static final String LOGIN_PASSWORD = "Ognjen12345!";

}
