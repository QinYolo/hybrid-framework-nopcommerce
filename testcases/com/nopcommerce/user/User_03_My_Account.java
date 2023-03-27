package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.nopcommerce.user.UserCustomerInforPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_03_My_Account extends BaseTest {
	
	@Parameters({"browser", "environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		homePage = new UserHomePageObject(driver);
		
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
		
		homePage.clickToRegisterLink();

		registerPage = new UserRegisterPageObject(driver);
		registerPage.inputToFirstNameTextbox(firstName1st);
		registerPage.inputToLastNameTextbox(lastName1st);
		registerPage.inputToEmailTextbox(emailAdress);
		registerPage.inputToPasswordTextbox(pwd1st);
		registerPage.inputToConfirmPasswordTextbox(pwd1st);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = new UserHomePageObject(driver);
		homePage.clickToLoginLink();
		
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTexbox(emailAdress);
		loginPage.inputToPasswordTextbox(pwd1st);
		
		loginPage.clickToLoginButton();
		
		homePage = new UserHomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void My_Account_01_Update_Customer_Infor() {
		System.out.println("Home Page - Step 01: Click to My Account Link");
		homePage.clickToMyAccountLink();
//		waitForElementClickable(driver, "//a[@class='ico-account']");
//		clickToElement(driver, "//a[@class='ico-account']");
		
		System.out.println("My Account Page - Step 02: Update to Required fields");
		customerInforPage = new UserCustomerInforPageObject(driver);
		customerInforPage.clickToGenderRadioButton();
		customerInforPage.inputToFirstNameTextbox(firstName2st);
		customerInforPage.inputToLastNameTextbox(lastName2st);
		customerInforPage.selectDayDropdown("1");
		customerInforPage.selectMonthDropdown("January");
		customerInforPage.selectYearDropdown("1999");
		customerInforPage.inputToEmailTextbox(emailAdress2);
		customerInforPage.inputToCompanyNameTextbox(company2st);
//		clickToElement(driver, "//input[@id='gender-female']");
//		sendKeyToElement(driver, "//input[@id='FirstName']", firstName2st);
//		sendKeyToElement(driver, "//input[@id='LastName']", lastName2st);
//		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthDay']", "1");
//		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthMonth']", "January");
//		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthYear']", "1999");
//		sendKeyToElement(driver, "//input[@id='Email']", emailAdress2);
//		sendKeyToElement(driver, "//input[@id='Company']", company2st);
		
		System.out.println("My Account Page - Step 03: Click to Save Button");
		customerInforPage.clickToSaveCustomerInfoButton();
//		clickToElement(driver, "//button[@id='save-info-button']");
		
		System.out.println("My Account Page - Step 04: Verify update information Succesfully");
//		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "The customer info has been updated successfully.");
		Assert.assertEquals(customerInforPage.getSuccessMessageAtBarNotification(), "The customer info has been updated successfully.");
		
		System.out.println("My Account Page - Step 05: Close Bar Notification Message");
//		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		customerInforPage.clickCloseButtonAtBarNotification();
	}

	@Test
	public void My_Account_02_Add_New_Addressess() {
		System.out.println("Home Page - Step 01: Click to My Account Link");
		homePage.clickToMyAccountLink();
//		waitForElementClickable(driver, "//a[@class='ico-account']");
//		clickToElement(driver, "//a[@class='ico-account']");
		
		System.out.println("My Account Page - Step 02: Click to Addresses Link");
		customerInforPage = new UserCustomerInforPageObject(driver);
//		clickToElement(driver, "//li[starts-with(@class,'customer-addresses')]/a");
		customerInforPage.clickToAddressesNavigationLink();
		
		System.out.println("My Account Page - Step 03: Click to Add New Addresses Button");
//		waitForElementClickable(driver, "//button[contains(@class,'add-address-button')]");
//		clickToElement(driver, "//button[contains(@class,'add-address-button')]");
		customerInforPage.clickToAddNewButtonAddress();
		
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
		customerInforPage.inputToFirstNameTextboxAddress(firstName2st);
		customerInforPage.inputToLastNameTextboxAddress(lastName2st);
		customerInforPage.inputToEmailTextboxAddress(emailAdress2);
		customerInforPage.inputToCompanyTextboxAddress(company2st);
		customerInforPage.selectCountryDropdownListAddress("Viet Nam");
		customerInforPage.selectStateProvinceDropdownListAddress("Other");
		customerInforPage.inputToCityTextboxAddress("Da Nang");
		customerInforPage.inputToAddress1TextboxAddress("123/4 Le Lai");
		customerInforPage.inputToAddress2TextboxAddress("234/5 Hai Phong");
		customerInforPage.inputToZipPostalCodeTextboxAddress("550000");
		customerInforPage.inputToPhoneNumberTextboxAddress("0123456789");
		customerInforPage.inputToFaxNumberTextboxAddress("0987654321");
		
		System.out.println("My Account Page - Step 05: Click to Save Address Button");
		// clickToElement(driver, "//button[contains(@class,'save-address-button')]");
		customerInforPage.clickToSaveButtonAddress();
		
		System.out.println("My Account Page - Step 06: Verify successful message displayed");
//		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "The new address has been added successfully.");
		Assert.assertEquals(customerInforPage.getSuccessMessageAtBarNotification(), "The new address has been added successfully.");
		
		System.out.println("My Account Page - Step 07: Close Bar Notification Message");
//		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		customerInforPage.clickCloseButtonAtBarNotification();
		
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
		Assert.assertEquals(customerInforPage.getNameAddressList(), firstName2st + " " + lastName2st);
		Assert.assertEquals(customerInforPage.getEmailAddressList(), "Email: " + emailAdress2);
		Assert.assertEquals(customerInforPage.getPhoneAddressList(), "Phone number: " + "0123456789");
		Assert.assertEquals(customerInforPage.getFaxNumberAddressList(), "Fax number: " + "0987654321");
		Assert.assertEquals(customerInforPage.getCompanyNameAddressList(), company2st);
		Assert.assertEquals(customerInforPage.getAddress1AddressList(), "123/4 Le Lai");
		Assert.assertEquals(customerInforPage.getAddress2AddressList(), "234/5 Hai Phong");
		Assert.assertEquals(customerInforPage.getCityStateZipAddressList(), "Da Nang" + ", " + "550000");
		Assert.assertEquals(customerInforPage.getCountryNameAddressList(), "Viet Nam");	
	}

	@Test
	public void My_Account_03_Change_Password() {
		System.out.println("Home Page - Step 01: Click to My Account Link");
		homePage.clickToMyAccountLink();
//		waitForElementClickable(driver, "//a[@class='ico-account']");
//		clickToElement(driver, "//a[@class='ico-account']");
		
		System.out.println("My Account Page - Step 02: Click to Change Password Link");
		customerInforPage = new UserCustomerInforPageObject(driver);
//		clickToElement(driver, "//li[starts-with(@class,'change-password')]/a");
		customerInforPage.clickToChangePasswordNavigationLink();
		
		System.out.println("My Account Page - Step 03: Input to required fields");
//		sendKeyToElement(driver, "//input[@id='OldPassword']", pwd1st);
//		sendKeyToElement(driver, "//input[@id='NewPassword']", pwd2st);
//		sendKeyToElement(driver, "//input[@id='ConfirmNewPassword']", pwd2st);
		customerInforPage.inputToOldPasswordTextbox(pwd1st);
		customerInforPage.inputToNewPasswordTextbox(pwd2st);
		customerInforPage.inputToConfirmNewPasswordTextbox(pwd2st);
		
		System.out.println("My Account Page - Step 04: Click to Change Password Button");
//		clickToElement(driver, "//button[contains(@class,'change-password-button')]");
		customerInforPage.clickToChangePasswordButton();
		
		System.out.println("My Account Page - Step 05: Verify successful message displayed");
//		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "Password was changed");
		Assert.assertEquals(customerInforPage.getSuccessMessageAtBarNotification(), "Password was changed");
		
		System.out.println("My Account Page - Step 06: Close Bar Notification Message");
//		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		customerInforPage.clickCloseButtonAtBarNotification();
		
		System.out.println("Home Page - Step 07: Click to Log out Link");
		homePage = new UserHomePageObject(driver);
		homePage.clickToLogOutLinkAtUserPage(driver);
		
		System.out.println("Home Page - Step 08: Click to Log in Link");
		homePage.clickToLoginLink();
		
		System.out.println("Login Page - Step 09: Input to Email and Password textbox");
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTexbox(emailAdress2);
		loginPage.inputToPasswordTextbox(pwd1st);
		
		System.out.println("Login Page - Step 10: Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login Page - Step 11: Verify error message displayed");
		Assert.assertEquals(loginPage.getLoginErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
		System.out.println("Home Page - Step 12: Click to Login Button");
		homePage = new UserHomePageObject(driver);
		
		System.out.println("Login Page - Step 13: Input to Email and Password textbox");
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTexbox(emailAdress2);
		loginPage.inputToPasswordTextbox(pwd2st);
		
		System.out.println("Login Page - Step 14: Click to Login Button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login Page - Step 15: Verify My Account Link Displayed");
		homePage = new UserHomePageObject(driver);
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@Test
	public void My_Account_04_My_Product_Reviews() {
		System.out.println("Home Page - Step 01: Click to Desktop Link Menu");
//		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[text()='Computers ']");
//		clickToElement(driver, "//ul[@class='top-menu notmobile']//a[text()='Desktops ']");
		
//		waitForElementClickable(driver, "//div[@class='center-2']//a[text()='" + productToReview + "']");
//		clickToElement(driver, "//div[@class='center-2']//a[text()='Build your own computer']");
//		waitForElementClickable(driver, "//a[text()='Add your review']");
//		clickToElement(driver, "//a[text()='Add your review']");
//		
//		sleepInSecond(1);
//		sendKeyToElement(driver, "//input[@id='AddProductReview_Title']", reviewTitle);
//		sendKeyToElement(driver, "//textarea[@id='AddProductReview_ReviewText']", "This is a sample review, the product is good!");
//		clickToElement(driver, "//input[@id='addproductrating_4']");
//		clickToElement(driver, "//button[@name='add-review']");
//		
//		waitForElementVisible(driver, "//div[@class='result']");
//		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Product review is successfully added.");
//		
//		clickToElement(driver, "//a[@class='ico-account']");
//		waitForElementClickable(driver, "//a[text()='My product reviews']");
//		clickToElement(driver, "//a[text()='My product reviews']");
//		
//		Assert.assertEquals(getElementText(driver, "//div[@class='review-title']/strong"), reviewTitle);
//		Assert.assertEquals(getElementText(driver, "//div[@class='review-text']"), "This is a sample review, the product is good!");
//		Assert.assertEquals(getElementText(driver, "//label[text()='Product review for:']/following-sibling::a"), productToReview);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

	private WebDriver driver;
	private String emailAdress, emailAdress2, productToReview, reviewTitle;
	private String firstName1st, lastName1st, pwd1st, firstName2st, lastName2st, company2st, pwd2st;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
}
