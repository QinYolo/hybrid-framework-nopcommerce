package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

public class User_01_Register extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		firstName = "Automation";
		lastName = "Testing";
		password = "123456";
		emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";
		
		homePage = new HomePageObject(driver);
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Click to Register Button");
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Email_NotValid() {
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to Required fields");
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("randdddd678@yu.67#!");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Register_Successful() {
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to Required fields");
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify successful message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}

	@Test
	public void Register_04_Email_Already_Exists() {
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to Required fields");
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorEmailExistingMessage(), "The specified email already exists");
	}

	@Test
	public void  Register_05_Password_Less_Than_6_Chars() {
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to Required fields");
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.println("Register Page - Step 03: Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void  Register_06_Password_Confirmation_Does_Not_Match() {
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to Required fields");
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.println("Register Page - Step 03: Click to Register Button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
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
	private String firstName, lastName, emailAdress, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
}
