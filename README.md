# FishingBooker QA Automation Project

![Java](https://img.shields.io/badge/language-Java%2021-blue.svg)
![Maven](https://img.shields.io/badge/build-Maven-green.svg)

Automated UI tests for the FishingBooker platform, built from scratch using Java, Selenium WebDriver, and TestNG.  
This project focuses on user registration, profile management, and clean OOP-based automation principles.

---

## Project Overview

This automation framework covers the **sign-up process** and **profile management features**, including:

- Negative and positive sign-up scenarios
- Profile image upload
- Phone number format validation
- Date dropdown logic and validation
- Full profile update flow including visual confirmation (success alert)

The tests simulate user behavior from sitemap navigation, through registration, to profile update and validation.

---

## Tech Stack & Tools

- Java 21
- Selenium WebDriver 4.33
- TestNG 7.11
- WebDriverManager 6.1
- Apache Maven 3.9.5
- IntelliJ IDEA Community Edition

---

## Project Structure

The project follows a clean **Page Object Model (POM)** architecture with clear separation of concerns:

### `base`
- Contains shared logic such as `BasePage` and `BaseTest`
- Handles common setup, teardown, and reusable helper methods

### `pages`
- Contains one class per UI page (e.g. `SitemapPage`, `SignUpModalPage`, `LoginModalPage` and `ManageProfilePage`)
- Each page class encapsulates its elements and user actions

### `tests`
- Contains all `@Test` classes (e.g. `SignUpTest`, `ManageProfileTest`)

### `utils`
- Contains utility classes like `TestData`, `RandomUtils` and `URLs`
- Helps generate test inputs and organize constants

### `resources`
- Stores static test resources such as images (used for profile image upload)

---

## Installation & Setup

### Prerequisites

- Java 21 installed and configured in PATH
- Maven installed and available via terminal
- Chrome browser installed

### Clone the repository

```
git clone https://github.com/OgnjenCurcic/FishingBookerQA.git
```

```
cd FishingBookerQA
```

### Running Tests

```
mvn clean test
```

```
mvn test -Dtest="SignUpTest"
```

```
mvn clean test -DsuiteXmlFile="testng.xml"
```

## Test Environment Info

**Access credentials used during automation:**

- **Username:** `fishingbooker`
- **Password:** `QAFBTest`

**Main entry page for test flow:**  
[https://qahiring.dev.fishingbooker.com/sitemap](https://qahiring.dev.fishingbooker.com/sitemap)

---

## Test Coverage Summary

### Sign-Up Flow
- Click "Sign up" from sitemap
- Enter invalid inputs (missing email/password, invalid formats)
- Enter valid inputs and register new account

### Manage Profile
- Navigate to `/manage/profile`
- Upload profile image
- Input valid and invalid phone numbers:
    - Without country code prefix
    - Too short / too long values
- Validate dynamic day dropdown updates correctly for each month (excluding leap year)
- Select specific birthdate: `23.01.1994`
- Confirm success label appears after update

---

## Notes

- Tests are designed to be **resilient and independent**, with proper setup and teardown in place
- Project uses the **Page Object Model (POM)** with full separation of concerns
- **Waiting strategies** include both explicit waits and fallback scrolling logic to improve element visibility
- Some temporary `Thread.sleep()` calls are used where needed, but can later be replaced with more robust `WebDriverWait` conditions

---

## Author

**Ognjen Ćurčić**  
Junior QA Automation Engineer

[GitHub: OgnjenCurcic](https://github.com/OgnjenCurcic)



