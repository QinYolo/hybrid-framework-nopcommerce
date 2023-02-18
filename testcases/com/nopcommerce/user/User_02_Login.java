package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_02_Login extends BasePage {

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		openPageURL(driver, "https://demo.nopcommerce.com/");

		emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";
		password = "123456";
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		loginPage = new LoginPageObject(driver);
	}

	@Test
	public void TC_01_Login_Empty_Data() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
	}

	@Test
	public void TC_02_Login_Wrong_Email() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage.inputToEmailTexbox("emailtesting63947#^@mila%c");
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");
	}

	@Test
	public void TC_03_Login_No_Customer_Account_Found() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage.inputToEmailTexbox("emailtesting" + getRandomNumber() + "@gmail.com");
		loginPage.inputToPasswordTextbox(password);

		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_04_Login_No_Input_Pwd() {
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to Required fields");
		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("Testing");
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify successful message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Home Page - Step 05: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 06: Input to Email textbox");
		loginPage.inputToEmailTexbox(emailAdress);

		System.out.println("Login Page - Step 07: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 08: Verify error message displayed");
		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_05_Login_Wrong_Pwd() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage.inputToEmailTexbox(emailAdress);
		loginPage.inputToPasswordTextbox("789456");

		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();

		System.out.println("Login Page - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPage.getLoginErrorMessage(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_Successful() {
		System.out.println("Home Page - Step 01: Click to Login Link");
		homePage.clickToLoginLink();

		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage.inputToEmailTexbox(emailAdress);
		loginPage.inputToPasswordTextbox(password);
		
		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login Page - Step 04: Verify URL Page Loading Successful");
		Assert.assertEquals(homePage.getHomePageURL(), "https://demo.nopcommerce.com/");
	}

	@AfterClass
	public void afterClass() {
		quitPageURL(driver);
	}

	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String emailAdress, password;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
}
