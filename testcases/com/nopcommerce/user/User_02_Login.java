package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopcommerce.HomePageObject;
import pageObjects.nopcommerce.LoginPageObject;
import pageObjects.nopcommerce.PageGeneratorManager;
import pageObjects.nopcommerce.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_02_Login extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		validEmail = "automationTest" + getRandomNumber() + "@gmail.com";
		password = "123456";
		noAccountEmail = "emailtesting" + getRandomNumber() + "@gmail.com";
		
		homePage = PageGeneratorManager.getHomePage(driver);
		registerPage = homePage.clickToRegisterLink();

		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("Testing");
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		loginPage = homePage.clickToLoginLink();

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void Login_02_Email_NotValid() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTexbox("emailtesting63947#^@mila%c");
		loginPage.inputToPasswordTextbox(password);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");
	}

	@Test
	public void Login_03_No_Customer_Account_Found() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTexbox(noAccountEmail);
		loginPage.inputToPasswordTextbox(password);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_No_Input_Password() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTexbox(validEmail);
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Password() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTexbox(validEmail);
		loginPage.inputToPasswordTextbox("789456");

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Login_Successful() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTexbox(validEmail);
		loginPage.inputToPasswordTextbox(password);
		
		loginPage.clickToLoginButton();
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private WebDriver driver;
	private String validEmail, noAccountEmail, password;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
}
