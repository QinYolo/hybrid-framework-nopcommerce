package com.nopcommerce.user;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

public class User_01_Register_Allure extends BaseTest {

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

	@Description("Register to system without data")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Register_01_Empty_Data( ) {
		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.clickToRegisterButton();

		verifyEquals(userRegisterPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		verifyEquals(userRegisterPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		verifyEquals(userRegisterPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		verifyEquals(userRegisterPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		verifyEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Description("Register to system with email is not valid")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Register_02_Email_NotValid( ) {
		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox("randdddd678@yu.67#!");
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		userRegisterPage.clickToRegisterButton();
		// error
		verifyEquals(userRegisterPage.getErrorMessageAtEmailTextbox(), "Wrong email____");
	}

	@Description("Register to system is successful")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void Register_03_Register_Successful( ) {
		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(emailAdress);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(password);
		
		userRegisterPage.clickToRegisterButton();

		verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Description("Register to system with email is already exists")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void Register_04_Email_Already_Exists( ) {
		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(emailAdress);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		userRegisterPage.clickToRegisterButton();
		// error
		verifyEquals(userRegisterPage.getErrorEmailExistingMessage(), "The specified email already exists____");
	}

	@Description("Register to system with password is less than 6 characters")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void  Register_05_Password_Less_Than_6_Chars( ) {
		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(emailAdress);
		userRegisterPage.inputToPasswordTextbox("123");
		userRegisterPage.inputToConfirmPasswordTextbox("123");
		userRegisterPage.clickToRegisterButton();

		verifyEquals(userRegisterPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Description("Register to system with password confirmation does not match")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void  Register_06_Password_Confirmation_Does_Not_Match( ) {
		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(emailAdress);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox("123");

		userRegisterPage.clickToRegisterButton();
		// error
		verifyEquals(userRegisterPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.___");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String firstName, lastName, emailAdress, password;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
}
