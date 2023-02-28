package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_03_My_Account extends BasePage {
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		homePage = new HomePageObject(driver);
		driver.get("https://demo.nopcommerce.com/");
		
		firstName1st = "Automation"; 
		lastName1st = "Testing"; 
		pwd1st = "123456";
		pwd2st = "123456789";
		firstName2st = "Automation"; 
		lastName2st = "FC"; 
		company2st = "Automation FC"; 
		emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";
		emailAdress2 = "automationfc.vn" + getRandomNumber() + "@gmail.com";
		productToReview = "Build your own computer";
		reviewTitle = "Demo Review " + getRandomNumber();
		
		System.out.println("Home Page - Step 01: Click to Register Link");
		homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to Required fields");
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName1st);
		registerPage.inputToLastNameTextbox(lastName1st);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(pwd1st);
		registerPage.inputToConfirmPasswordTextbox(pwd1st);

		System.out.println("Register Page - Step 03: Click to Register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify successful message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("Home Page - Step 05: Click to Login Link");
		homePage = new HomePageObject(driver);
		homePage.clickToLoginLink();
		
		System.out.println("Login Page - Step 02: Input to Email and Password textbox");
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(emailAdress);
		loginPage.inputToPasswordTextbox(pwd1st);
		
		System.out.println("Login Page - Step 03: Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login Page - Step 04: Verify My Account Link Displayed");
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void My_Account_01_Update_Customer_Infor() {
		System.out.println("Home Page - Step 01: Click to My Account Link");
		homePage.clickToMyAccountLink();
//		waitForElementClickable(driver, "//a[@class='ico-account']");
//		clickToElement(driver, "//a[@class='ico-account']");
		
		System.out.println("My Account Page - Step 02: Update to Required fields");
		myAccountPage = new MyAccountPageObject(driver);
		myAccountPage.clickToGenderRadioButton();
		myAccountPage.inputToFirstNameTextbox(firstName2st);
		myAccountPage.inputToLastNameTextbox(lastName2st);
		myAccountPage.selectDayDropdown("1");
		myAccountPage.selectMonthDropdown("January");
		myAccountPage.selectYearDropdown("1999");
		myAccountPage.inputToEmailTextbox(emailAdress2);
		myAccountPage.inputToCompanyNameTextbox(company2st);
//		clickToElement(driver, "//input[@id='gender-female']");
//		sendKeyToElement(driver, "//input[@id='FirstName']", firstName2st);
//		sendKeyToElement(driver, "//input[@id='LastName']", lastName2st);
//		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthDay']", "1");
//		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthMonth']", "January");
//		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthYear']", "1999");
//		sendKeyToElement(driver, "//input[@id='Email']", emailAdress2);
//		sendKeyToElement(driver, "//input[@id='Company']", company2st);
		
		System.out.println("My Account Page - Step 03: Click to Save Button");
		myAccountPage.clickToSaveCustomerInfoButton();
//		clickToElement(driver, "//button[@id='save-info-button']");
		
		System.out.println("My Account Page - Step 04: Verify update information Succesfully");
//		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "The customer info has been updated successfully.");
		Assert.assertEquals(myAccountPage.getSuccessMessageAtBarNotification(), "The customer info has been updated successfully.");
		
		System.out.println("My Account Page - Step 05: Close Bar Notification Message");
//		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		myAccountPage.clickCloseButtonAtBarNotification();
	}

	@Test
	public void My_Account_02_Add_New_Addressess() {
		System.out.println("Home Page - Step 01: Click to My Account Link");
		homePage.clickToMyAccountLink();
//		waitForElementClickable(driver, "//a[@class='ico-account']");
//		clickToElement(driver, "//a[@class='ico-account']");
		
		System.out.println("My Account Page - Step 02: Click to Addresses Link");
		myAccountPage = new MyAccountPageObject(driver);
//		clickToElement(driver, "//li[starts-with(@class,'customer-addresses')]/a");
		myAccountPage.clickToAddressesNavigationLink();
		
		System.out.println("My Account Page - Step 03: Click to Add New Addresses Button");
//		waitForElementClickable(driver, "//button[contains(@class,'add-address-button')]");
//		clickToElement(driver, "//button[contains(@class,'add-address-button')]");
		myAccountPage.clickToAddNewButtonAddress();
		
		System.out.println("My Account Page - Step 04: Input to Required fields");
//		sendKeyToElement(driver, "//input[@id='Address_FirstName']", firstName2st);
//		sendKeyToElement(driver, "//input[@id='Address_LastName']", lastName2st);
//		sendKeyToElement(driver, "//input[@id='Address_Email']", emailAdress2);
//		sendKeyToElement(driver, "//input[@id='Address_Company']", company2st);
//		selectItemInDefaultDropDown(driver, "//select[@id='Address_CountryId']", "Viet Nam");
//		sendKeyToElement(driver, "//input[@id='Address_City']", "Da Nang");
//		sendKeyToElement(driver, "//input[@id='Address_Address1']", "123/4 Le Lai");
//		sendKeyToElement(driver, "//input[@id='Address_Address2']", "234/5 Hai Phong");
//		sendKeyToElement(driver, "//input[@id='Address_ZipPostalCode']", "550000");
//		sendKeyToElement(driver, "//input[@id='Address_PhoneNumber']", "0123456789");
//		sendKeyToElement(driver, "//input[@id='Address_FaxNumber']", "0987654321");
		myAccountPage.inputToFirstNameTextboxAddress(firstName2st);
		myAccountPage.inputToLastNameTextboxAddress(lastName2st);
		myAccountPage.inputToEmailTextboxAddress(emailAdress2);
		myAccountPage.inputToCompanyTextboxAddress(company2st);
		myAccountPage.selectCountryDropdownListAddress("Viet Nam");
		myAccountPage.selectStateProvinceDropdownListAddress("Other");
		myAccountPage.inputToCityTextboxAddress("Da Nang");
		myAccountPage.inputToAddress1TextboxAddress("123/4 Le Lai");
		myAccountPage.inputToAddress2TextboxAddress("234/5 Hai Phong");
		myAccountPage.inputToZipPostalCodeTextboxAddress("550000");
		myAccountPage.inputToPhoneNumberTextboxAddress("0123456789");
		myAccountPage.inputToFaxNumberTextboxAddress("0987654321");
		
		System.out.println("My Account Page - Step 05: Click to Save Address Button");
		clickToElement(driver, "//button[contains(@class,'save-address-button')]");
		myAccountPage.clickToSaveButtonAddress();
		
		System.out.println("My Account Page - Step 06: Verify successful message displayed");
//		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "The new address has been added successfully.");
		Assert.assertEquals(myAccountPage.getSuccessMessageAtBarNotification(), "The new address has been added successfully.");
		
		System.out.println("My Account Page - Step 07: Close Bar Notification Message");
//		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		myAccountPage.clickCloseButtonAtBarNotification();
		
		System.out.println("My Account Page - Step 08: Verify add New Address Succesfully");
//		Assert.assertEquals(getElementText(driver, "//li[@class='name']"), firstName2st + " " + lastName2st);
//		Assert.assertEquals(getElementText(driver, "//li[@class='email']"), "Email: " + emailAdress2);
//		Assert.assertEquals(getElementText(driver, "//li[@class='phone']"), "Phone number: " + "0123456789");
//		Assert.assertEquals(getElementText(driver, "//li[@class='fax']"), "Fax number: " + "0987654321");
//		Assert.assertEquals(getElementText(driver, "//li[@class='company']"), company2st);
//		Assert.assertEquals(getElementText(driver, "//li[@class='address1']"), "123/4 Le Lai");
//		Assert.assertEquals(getElementText(driver, "//li[@class='address2']"), "234/5 Hai Phong");
//		Assert.assertEquals(getElementText(driver, "//li[@class='city-state-zip']"), "Da Nang" + ", " + "550000");
//		Assert.assertEquals(getElementText(driver, "//li[@class='country']"), "Viet Nam");
		Assert.assertEquals(myAccountPage.getNameAddressList(), firstName2st + " " + lastName2st);
		Assert.assertEquals(myAccountPage.getEmailAddressList(), "Email: " + emailAdress2);
		Assert.assertEquals(myAccountPage.getPhoneAddressList(), "Phone number: " + "0123456789");
		Assert.assertEquals(myAccountPage.getFaxNumberAddressList(), "Fax number: " + "0987654321");
		Assert.assertEquals(myAccountPage.getCompanyNameAddressList(), company2st);
		Assert.assertEquals(myAccountPage.getAddress1AddressList(), "123/4 Le Lai");
		Assert.assertEquals(myAccountPage.getAddress2AddressList(), "234/5 Hai Phong");
		Assert.assertEquals(myAccountPage.getCityStateZipAddressList(), "Da Nang" + ", " + "550000");
		Assert.assertEquals(myAccountPage.getCountryNameAddressList(), "Viet Nam");	
	}

	@Test
	public void My_Account_03_Change_Password() {
		System.out.println("Home Page - Step 01: Click to My Account Link");
		homePage.clickToMyAccountLink();
//		waitForElementClickable(driver, "//a[@class='ico-account']");
//		clickToElement(driver, "//a[@class='ico-account']");
		
		System.out.println("My Account Page - Step 02: Click to Change Password Link");
		myAccountPage = new MyAccountPageObject(driver);
//		clickToElement(driver, "//li[starts-with(@class,'change-password')]/a");
		myAccountPage.clickToChangePasswordNavigationLink();
		
		System.out.println("My Account Page - Step 03: Input to required fields");
//		sendKeyToElement(driver, "//input[@id='OldPassword']", pwd1st);
//		sendKeyToElement(driver, "//input[@id='NewPassword']", pwd2st);
//		sendKeyToElement(driver, "//input[@id='ConfirmNewPassword']", pwd2st);
		myAccountPage.inputToOldPasswordTextbox(pwd1st);
		myAccountPage.inputToNewPasswordTextbox(pwd2st);
		myAccountPage.inputToConfirmNewPasswordTextbox(pwd2st);
		
		System.out.println("My Account Page - Step 04: Click to Change Password Button");
//		clickToElement(driver, "//button[contains(@class,'change-password-button')]");
		myAccountPage.clickToChangePasswordButton();
		
		System.out.println("My Account Page - Step 05: Verify successful message displayed");
//		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "Password was changed");
		Assert.assertEquals(myAccountPage.getSuccessMessageAtBarNotification(), "Password was changed");
		
		System.out.println("My Account Page - Step 06: Close Bar Notification Message");
//		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		myAccountPage.clickCloseButtonAtBarNotification();
		
		System.out.println("Home Page - Step 07: Click to Log out Link");
		homePage = new HomePageObject(driver);
		homePage.clickToLogOutLink();
		
		System.out.println("Home Page - Step 08: Click to Log in Link");
		homePage.clickToLoginLink();
		
		System.out.println("Login Page - Step 09: Input to Email and Password textbox");
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(emailAdress2);
		loginPage.inputToPasswordTextbox(pwd1st);
		
		System.out.println("Login Page - Step 10: Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login Page - Step 11: Verify error message displayed");
		Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
		System.out.println("Home Page - Step 12: Click to Login Button");
		homePage = new HomePageObject(driver);
		
		System.out.println("Login Page - Step 13: Input to Email and Password textbox");
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(emailAdress2);
		loginPage.inputToPasswordTextbox(pwd2st);
		
		System.out.println("Login Page - Step 14: Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login Page - Step 15: Verify My Account Link Displayed");
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void My_Account_04_My_Product_Reviews() {
		System.out.println("Home Page - Step 01: Click to Desktop Link Menu");
//		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[text()='Computers ']");
//		clickToElement(driver, "//ul[@class='top-menu notmobile']//a[text()='Desktops ']");
		
		waitForElementClickable(driver, "//div[@class='center-2']//a[text()='" + productToReview + "']");
		clickToElement(driver, "//div[@class='center-2']//a[text()='Build your own computer']");
		waitForElementClickable(driver, "//a[text()='Add your review']");
		clickToElement(driver, "//a[text()='Add your review']");
		
		sleepInSecond(1);
		sendKeyToElement(driver, "//input[@id='AddProductReview_Title']", reviewTitle);
		sendKeyToElement(driver, "//textarea[@id='AddProductReview_ReviewText']", "This is a sample review, the product is good!");
		clickToElement(driver, "//input[@id='addproductrating_4']");
		clickToElement(driver, "//button[@name='add-review']");
		
		waitForElementVisible(driver, "//div[@class='result']");
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Product review is successfully added.");
		
		clickToElement(driver, "//a[@class='ico-account']");
		waitForElementClickable(driver, "//a[text()='My product reviews']");
		clickToElement(driver, "//a[text()='My product reviews']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='review-title']/strong"), reviewTitle);
		Assert.assertEquals(getElementText(driver, "//div[@class='review-text']"), "This is a sample review, the product is good!");
		Assert.assertEquals(getElementText(driver, "//label[text()='Product review for:']/following-sibling::a"), productToReview);
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
	private String projectPath = System.getProperty("user.dir");
	private String homepageURL, emailAdress, emailAdress2, productToReview, reviewTitle;
	private String firstName1st, lastName1st, pwd1st, firstName2st, lastName2st, company2st, pwd2st;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;
	private MyAccountPageObject myAccountPage;
}
