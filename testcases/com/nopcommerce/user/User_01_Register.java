package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import commons.BaseTest;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserRegisterPageObject;
import reportConfig.ExtentManager;

public class User_01_Register extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		firstName = "Automation";
		lastName = "Testing";
		password = "123456";
		emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";
		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void Register_01_Empty_Data(Method method) {
		ExtentManager.startTest(method.getName(), "Register_01_Empty_Data");
		ExtentManager.getTest().log(LogStatus.INFO, "Register 01 - Empty Data - Step 01: Click to register link from HomePage");
		userRegisterPage = userHomePage.clickToRegisterLink();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 01 - Empty Data - Step 02: Click to register button with out any action");
		userRegisterPage.clickToRegisterButton();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 01 - Empty Data - Step 03: Verify Error Message is displayed at First Name Textbox");
		verifyEquals(userRegisterPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 01 - Empty Data - Step 04: Verify Error Message is displayed at Last Name Textbox");
		verifyEquals(userRegisterPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");

		ExtentManager.getTest().log(LogStatus.INFO, "Register 01 - Empty Data - Step 05: Verify Error Message is displayed at Email Textbox");
		verifyEquals(userRegisterPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 01 - Empty Data - Step 06: Verify Error Message is displayed at Password Textbox");
		verifyEquals(userRegisterPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 01 - Empty Data - Step 07: Verify Error Message is displayed at Confirm Password Textbox");
		verifyEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
		ExtentManager.endTest();
	}

	@Test
	public void Register_02_Email_NotValid(Method method) {
		ExtentManager.startTest(method.getName(), "Register_02_Email_NotValid");
		ExtentManager.getTest().log(LogStatus.INFO, "Register 02 - Email Not Valid - Step 01: Click to register link from HomePage");
		userRegisterPage = userHomePage.clickToRegisterLink();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 02 - Email Not Valid - Step 02: Input '" + firstName + "' to first name textbox");
		userRegisterPage.inputToFirstNameTextbox(firstName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 02 - Email Not Valid - Step 03: Input '" + lastName + "' to last name textbox");
		userRegisterPage.inputToLastNameTextbox(lastName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 02 - Email Not Valid - Step 04: Input '" + "randdddd678@yu.67#!" + "' to email textbox");
		userRegisterPage.inputToEmailTextbox("randdddd678@yu.67#!");
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 02 - Email Not Valid - Step 05: Input '" + password + "' to password textbox");
		userRegisterPage.inputToPasswordTextbox(password);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 02 - Email Not Valid - Step 06: Input '" + password + "' to confirm password textbox");
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		ExtentManager.getTest().log(LogStatus.INFO, "Register 02 - Email Not Valid - Step 07: Click to register button");
		userRegisterPage.clickToRegisterButton();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 02 - Email Not Valid - Step 08: Verify error message display at Email textbox");
		// error
		verifyEquals(userRegisterPage.getErrorMessageAtEmailTextbox(), "Wrong email____");
		ExtentManager.endTest();
	}

	@Test
	public void Register_03_Register_Successful(Method method) {
		ExtentManager.startTest(method.getName(), "Register_03_Register_Successful");
		ExtentManager.getTest().log(LogStatus.INFO, "Register 03 - Register Successful - Step 01: Click to register link from HomePage");
		userRegisterPage = userHomePage.clickToRegisterLink();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 03 - Register Successful - Step 02: Input '" + firstName + "' to first name textbox");
		userRegisterPage.inputToFirstNameTextbox(firstName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 03 - Register Successful - Step 03: Input '" + lastName + "' to last name textbox");
		userRegisterPage.inputToLastNameTextbox(lastName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 03 - Register Successful - Step 04: Input '" + emailAdress + "' to email textbox");
		userRegisterPage.inputToEmailTextbox(emailAdress);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 03 - Register Successful - Step 05: Input '" + password + "' to password textbox");
		userRegisterPage.inputToPasswordTextbox(password);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 03 - Register Successful - Step 06: Input '" + password + "' to confirm password textbox");
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		ExtentManager.getTest().log(LogStatus.INFO, "Register 03 - Register Successful - Step 07: Click to register button");
		userRegisterPage.clickToRegisterButton();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 03 - Register Successful - Step 08: Verify successful message displayed");
		verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		ExtentManager.endTest();
	}

	@Test
	public void Register_04_Email_Already_Exists(Method method) {
		ExtentManager.startTest(method.getName(), "Register_04_Email_Already_Exists");
		ExtentManager.getTest().log(LogStatus.INFO, "Register 04 - Email already exits - Step 01: Click to register link from HomePage");
		userRegisterPage = userHomePage.clickToRegisterLink();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 04 - Email already exits - Step 02: Input '" + firstName + "' to first name textbox");
		userRegisterPage.inputToFirstNameTextbox(firstName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 04 - Email already exits - Step 03: Input '" + lastName + "' to last name textbox");
		userRegisterPage.inputToLastNameTextbox(lastName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 04 - Email already exits - Step 04: Input '" + emailAdress + "' to email textbox");
		userRegisterPage.inputToEmailTextbox(emailAdress);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 04 - Email already exits - Step 05: Input '" + password + "' to password textbox");
		userRegisterPage.inputToPasswordTextbox(password);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 04 - Email already exits - Step 06: Input '" + password + "' to confirm password textbox");
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		ExtentManager.getTest().log(LogStatus.INFO, "Register 04 - Email already exits - Step 07: Click to register button");
		userRegisterPage.clickToRegisterButton();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 04 - Email already exits - Step 08: Verify email error message displayed");
		// error
		verifyEquals(userRegisterPage.getErrorEmailExistingMessage(), "The specified email already exists____");
		ExtentManager.endTest();
	}

	@Test
	public void  Register_05_Password_Less_Than_6_Chars(Method method) {
		ExtentManager.startTest(method.getName(), "Register_05_Password_Less_Than_6_Chars");
		ExtentManager.getTest().log(LogStatus.INFO, "Register 05 - Password less than 6 chars - Step 01: Click to register link from HomePage");
		userRegisterPage = userHomePage.clickToRegisterLink();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 05 - Password less than 6 chars - Step 02: Input '" + firstName + "' to first name textbox");
		userRegisterPage.inputToFirstNameTextbox(firstName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 05 - Password less than 6 chars - Step 03: Input '" + lastName + "' to last name textbox");
		userRegisterPage.inputToLastNameTextbox(lastName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 05 - Password less than 6 chars - Step 04: Input '" + emailAdress + "' to email textbox");
		userRegisterPage.inputToEmailTextbox(emailAdress);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 05 - Password less than 6 chars - Step 05: Input '" + "123" + "' to password textbox");
		userRegisterPage.inputToPasswordTextbox("123");
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 05 - Password less than 6 chars - Step 06: Input '" + "123" + "' to confirm password textbox");
		userRegisterPage.inputToConfirmPasswordTextbox("123");

		ExtentManager.getTest().log(LogStatus.INFO, "Register 05 - Password less than 6 chars - Step 07: Click to register button");
		userRegisterPage.clickToRegisterButton();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 05 - Password less than 6 chars - Step 08: Verify error message displayed at password textbox");
		verifyEquals(userRegisterPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");
		ExtentManager.endTest();
	}

	@Test
	public void  Register_06_Password_Confirmation_Does_Not_Match(Method method) {
		ExtentManager.startTest(method.getName(), "Register_06_Password_Confirmation_Does_Not_Match");
		ExtentManager.getTest().log(LogStatus.INFO, "Register 06 - Password confirmation does not match - Step 01: Click to register link from HomePage");
		userRegisterPage = userHomePage.clickToRegisterLink();

		ExtentManager.getTest().log(LogStatus.INFO, "Register 06 - Password confirmation does not match - Step 02: Input '" + firstName + "' to first name textbox");
		userRegisterPage.inputToFirstNameTextbox(firstName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 06 - Password confirmation does not match - Step 03: Input '" + lastName + "' to last name textbox");
		userRegisterPage.inputToLastNameTextbox(lastName);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 06 - Password confirmation does not match - Step 04: Input '" + emailAdress + "' to email textbox");
		userRegisterPage.inputToEmailTextbox(emailAdress);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 06 - Password confirmation does not match - Step 05: Input '" + password + "' to password textbox");
		userRegisterPage.inputToPasswordTextbox(password);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 06 - Password confirmation does not match - Step 06: Input '" + "123" + "' to confirm password textbox");
		userRegisterPage.inputToConfirmPasswordTextbox("123");

		ExtentManager.getTest().log(LogStatus.INFO, "Register 06 - Password confirmation does not match - Step 07: Click to register button");
		userRegisterPage.clickToRegisterButton();
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register 06 - Password confirmation does not match - Step 08: Verify error message displayed at confirm password textbox");
		// error
		verifyEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.___");
		ExtentManager.endTest();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String firstName, lastName, emailAdress, password;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
}
