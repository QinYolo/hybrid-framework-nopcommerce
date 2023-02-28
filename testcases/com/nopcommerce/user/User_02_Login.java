package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

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
		
		homePage = new HomePageObject(driver);
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to Required fields");
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("Testing");
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify successful message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = new HomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Click to Login Button");
		loginPage = new LoginPageObject(driver);
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void Login_02_Email_NotValid() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox("emailtesting63947#^@mila%c");
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");
	}

	@Test
	public void Login_03_No_Customer_Account_Found() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(noAccountEmail);
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_No_Input_Password() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email textbox");
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(validEmail);

		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Wrong_Password() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(validEmail);
		loginPage.inputToPasswordTextbox("789456");

		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Login_Successful() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(validEmail);
		loginPage.inputToPasswordTextbox(password);
		
		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login Page - Step 04: Verify My Account Link Displayed");
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	private WebDriver driver;
	private String validEmail, noAccountEmail, password;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
}
