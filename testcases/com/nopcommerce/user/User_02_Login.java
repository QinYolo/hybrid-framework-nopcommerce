package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_02_Login extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		validEmail = "automationTest" + getRandomNumber() + "@gmail.com";
		password = "123456";
		noAccountEmail = "emailtesting" + getRandomNumber() + "@gmail.com";
		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.inputToFirstNameTextbox("Automation");
		userRegisterPage.inputToLastNameTextbox("Testing");
		userRegisterPage.inputToEmailTextbox(validEmail);
		userRegisterPage.inputToPasswordTextbox(password);
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		userRegisterPage.clickToRegisterButton();

		verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
		
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		userLoginPage = userHomePage.clickToLoginLink();

		userLoginPage.clickToLoginButton();

		verifyEquals(userLoginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void Login_02_Email_NotValid() {
		userLoginPage = userHomePage.clickToLoginLink();

		userLoginPage.inputToEmailTexbox("emailtesting63947#^@mila%c");
		userLoginPage.inputToPasswordTextbox(password);

		userLoginPage.clickToLoginButton();

		verifyEquals(userLoginPage.getErrorMessageAtEmailTextBox(), "Wrong email");
	}

	@Test
	public void Login_03_No_Customer_Account_Found() {
		userLoginPage = userHomePage.clickToLoginLink();

		userLoginPage.inputToEmailTexbox(noAccountEmail);
		userLoginPage.inputToPasswordTextbox(password);

		userLoginPage.clickToLoginButton();

		verifyEquals(userLoginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_No_Input_Password() {
		userLoginPage = userHomePage.clickToLoginLink();

		userLoginPage.inputToEmailTexbox(validEmail);
		userLoginPage.clickToLoginButton();

		verifyEquals(userLoginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Password() {
		userLoginPage = userHomePage.clickToLoginLink();

		userLoginPage.inputToEmailTexbox(validEmail);
		userLoginPage.inputToPasswordTextbox("789456");

		userLoginPage.clickToLoginButton();

		verifyEquals(userLoginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Login_Successful() {
		userLoginPage = userHomePage.clickToLoginLink();

		userLoginPage.inputToEmailTexbox(validEmail);
		userLoginPage.inputToPasswordTextbox(password);
		
		userLoginPage.clickToLoginButton();
		
		verifyTrue(userHomePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private WebDriver driver;
	private String validEmail, noAccountEmail, password;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserRegisterPageObject userRegisterPage;
}
