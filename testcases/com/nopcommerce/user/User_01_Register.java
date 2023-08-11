package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManager;
import utilities.DataHelper;
import utilities.Environments;

public class User_01_Register extends BaseTest {

    @Parameters({"browser", "browserURL"})
    @BeforeClass
    public void beforeClass(String browserName, String envName) {
    	ConfigFactory.setProperty("env", envName);
    	environment = ConfigFactory.create(Environments.class);
        driver = getBrowserDriver(browserName, environment.getAppURL());
        dataFaker = DataHelper.getData();

        firstName = dataFaker.getFirstName();
        lastName = dataFaker.getLastName();
        password = dataFaker.getPassword();
        emailAdress = dataFaker.getEmail();

        userHomePage = PageGeneratorManager.getUserHomePage(driver);
    }

    @Test
    public void Register_01_Empty_Data(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_01_Empty_Data");
        ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Empty Data - Step 01: Click to register link from HomePage");
        userRegisterPage = userHomePage.clickToRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Empty Data - Step 02: Click to register button with out any action");
        userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Empty Data - Step 03: Verify Error Message is displayed at First Name Textbox");
        verifyEquals(userRegisterPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");

        ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Empty Data - Step 04: Verify Error Message is displayed at Last Name Textbox");
        verifyEquals(userRegisterPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");

        ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Empty Data - Step 05: Verify Error Message is displayed at Email Textbox");
        verifyEquals(userRegisterPage.getErrorMessageAtEmailTextbox(), "Email is required.");

        ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Empty Data - Step 06: Verify Error Message is displayed at Password Textbox");
        verifyEquals(userRegisterPage.getErrorMessageAtPasswordTextbox(), "Password is required.");

        ExtentTestManager.getTest().log(Status.INFO, "Register 01 - Empty Data - Step 07: Verify Error Message is displayed at Confirm Password Textbox");
        verifyEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
    }

    @Test
    public void Register_02_Email_NotValid(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_02_Email_NotValid");
        ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Email Not Valid - Step 01: Click to register link from HomePage");
        userRegisterPage = userHomePage.clickToRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Email Not Valid - Step 02: Input '" + firstName + "' to first name textbox");
        userRegisterPage.inputToTextboxByID(driver, "FirstName", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Email Not Valid - Step 03: Input '" + lastName + "' to last name textbox");
        userRegisterPage.inputToTextboxByID(driver, "LastName", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Email Not Valid - Step 04: Input '" + "randdddd678@yu.67#!" + "' to email textbox");
        userRegisterPage.inputToTextboxByID(driver, "Email", "randdddd678@yu.67#!");

        ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Email Not Valid - Step 05: Input '" + password + "' to password textbox");
        userRegisterPage.inputToTextboxByID(driver, "Password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Email Not Valid - Step 06: Input '" + password + "' to confirm password textbox");
        userRegisterPage.inputToTextboxByID(driver, "ConfirmPassword", password);

        ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Email Not Valid - Step 07: Click to register button");
        userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register 02 - Email Not Valid - Step 08: Verify error message display at Email textbox");
        verifyEquals(userRegisterPage.getErrorMessageAtEmailTextbox(), "Wrong email");
    }

    @Test
    public void Register_03_Register_Successful(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_03_Register_Successful");
        ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Register Successful - Step 01: Click to register link from HomePage");
        userRegisterPage = userHomePage.clickToRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Register Successful - Step 02: Input '" + firstName + "' to first name textbox");
        userRegisterPage.inputToTextboxByID(driver, "FirstName", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Register Successful - Step 03: Input '" + lastName + "' to last name textbox");
        userRegisterPage.inputToTextboxByID(driver, "LastName", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Register Successful - Step 04: Input '" + emailAdress + "' to email textbox");
        userRegisterPage.inputToTextboxByID(driver, "Email", emailAdress);

        ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Register Successful - Step 05: Input '" + password + "' to password textbox");
        userRegisterPage.inputToTextboxByID(driver, "Password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Register Successful - Step 06: Input '" + password + "' to confirm password textbox");
        userRegisterPage.inputToTextboxByID(driver, "ConfirmPassword", password);

        ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Register Successful - Step 07: Click to register button");
        userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register 03 - Register Successful - Step 08: Verify successful message displayed");
        verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
    }

    @Test
    public void Register_04_Email_Already_Exists(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_04_Email_Already_Exists");
        ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Email already exits - Step 01: Click to register link from HomePage");
        userRegisterPage = userHomePage.clickToRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Email already exits - Step 02: Input '" + firstName + "' to first name textbox");
        userRegisterPage.inputToTextboxByID(driver, "FirstName", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Email already exits - Step 03: Input '" + lastName + "' to last name textbox");
        userRegisterPage.inputToTextboxByID(driver, "LastName", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Email already exits - Step 04: Input '" + emailAdress + "' to email textbox");
        userRegisterPage.inputToTextboxByID(driver, "Email", emailAdress);

        ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Email already exits - Step 05: Input '" + password + "' to password textbox");
        userRegisterPage.inputToTextboxByID(driver, "Password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Email already exits - Step 06: Input '" + password + "' to confirm password textbox");
        userRegisterPage.inputToTextboxByID(driver, "ConfirmPassword", password);

        ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Email already exits - Step 07: Click to register button");
        userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register 04 - Email already exits - Step 08: Verify email error message displayed");
        verifyEquals(userRegisterPage.getErrorEmailExistingMessage(), "The specified email already exists");
    }

    @Test
    public void Register_05_Password_Less_Than_6_Chars(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_05_Password_Less_Than_6_Chars");
        ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Password less than 6 chars - Step 01: Click to register link from HomePage");
        userRegisterPage = userHomePage.clickToRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Password less than 6 chars - Step 02: Input '" + firstName + "' to first name textbox");
        userRegisterPage.inputToTextboxByID(driver, "FirstName", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Password less than 6 chars - Step 03: Input '" + lastName + "' to last name textbox");
        userRegisterPage.inputToTextboxByID(driver, "LastName", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Password less than 6 chars - Step 04: Input '" + emailAdress + "' to email textbox");
        userRegisterPage.inputToTextboxByID(driver, "Email", emailAdress);

        ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Password less than 6 chars - Step 05: Input '" + "123" + "' to password textbox");
        userRegisterPage.inputToTextboxByID(driver, "Password", "123");

        ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Password less than 6 chars - Step 06: Input '" + "123" + "' to confirm password textbox");
        userRegisterPage.inputToTextboxByID(driver, "ConfirmPassword", "123");

        ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Password less than 6 chars - Step 07: Click to register button");
        userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register 05 - Password less than 6 chars - Step 08: Verify error message displayed at password textbox");
        verifyEquals(userRegisterPage.getErrorMessageAtPasswordTextbox(),
                "Password must meet the following rules:\nmust have at least 6 characters");
    }

    @Test
    public void Register_06_Password_Confirmation_Does_Not_Match(Method method) {
        ExtentTestManager.startTest(method.getName(), "Register_06_Password_Confirmation_Does_Not_Match");
        ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Password confirmation does not match - Step 01: Click to register link from HomePage");
        userRegisterPage = userHomePage.clickToRegisterLink();

        ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Password confirmation does not match - Step 02: Input '" + firstName + "' to first name textbox");
        userRegisterPage.inputToTextboxByID(driver, "FirstName", firstName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Password confirmation does not match - Step 03: Input '" + lastName + "' to last name textbox");
        userRegisterPage.inputToTextboxByID(driver, "LastName", lastName);

        ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Password confirmation does not match - Step 04: Input '" + emailAdress + "' to email textbox");
        userRegisterPage.inputToTextboxByID(driver, "Email", emailAdress);

        ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Password confirmation does not match - Step 05: Input '" + password + "' to password textbox");
        userRegisterPage.inputToPasswordTextbox(password);
        userRegisterPage.inputToTextboxByID(driver, "Password", password);

        ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Password confirmation does not match - Step 06: Input '" + "123" + "' to confirm password textbox");
        userRegisterPage.inputToTextboxByID(driver, "ConfirmPassword", "123");

        ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Password confirmation does not match - Step 07: Click to register button");
        userRegisterPage.clickToRegisterButton();

        ExtentTestManager.getTest().log(Status.INFO, "Register 06 - Password confirmation does not match - Step 08: Verify error message displayed at confirm password textbox");
        verifyEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String firstName, lastName, emailAdress, password;
    private DataHelper dataFaker;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private Environments environment;
}
