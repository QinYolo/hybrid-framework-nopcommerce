package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_03_My_Account extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";
	String emailAdress2 = "automationfc.vn" + getRandomNumber() + "@gmail.com";
	String homepageURL = "https://demo.nopcommerce.com/";
	String firstName1st = "Automation", lastName1st = "Testing", company1st = "FNZ", pwd1st = "123456";
	String firstName2st = "Automation", lastName2st = "FC", company2st = "Automation FC", pwd2st = "123456789";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Customer_Infor() {
		openPageURL(driver, homepageURL);

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		clickToElement(driver, "//input[@id='gender-male']");
		sendKeyToElement(driver, "//input[@id='FirstName']", firstName1st);
		sendKeyToElement(driver, "//input[@id='LastName']", lastName1st);
		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthDay']", "3");
		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthMonth']", "January");
		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthYear']", "1917");
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendKeyToElement(driver, "//input[@id='Company']", company1st);
		sendKeyToElement(driver, "//input[@id='Password']", pwd1st);
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", pwd1st);
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendKeyToElement(driver, "//input[@id='Password']", pwd1st);
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getPageURL(driver), homepageURL);
		
		waitForElementClickable(driver, "//a[@class='ico-account']");
		clickToElement(driver, "//a[@class='ico-account']");
		
		Assert.assertTrue(isElementSelected(driver, "//input[@id='gender-male']"));
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='FirstName']", "value"), firstName1st);
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='LastName']", "value"), lastName1st);
		Assert.assertEquals(getSelectedItemDefaultDropDown(driver, "//select[@name='DateOfBirthDay']"), "3");
		Assert.assertEquals(getSelectedItemDefaultDropDown(driver, "//select[@name='DateOfBirthMonth']"), "January");
		Assert.assertEquals(getSelectedItemDefaultDropDown(driver, "//select[@name='DateOfBirthYear']"), "1917");
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='Email']", "value"), emailAdress);
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='Company']", "value"), company1st);
		
		clickToElement(driver, "//input[@id='gender-female']");
		sendKeyToElement(driver, "//input[@id='FirstName']", firstName2st);
		sendKeyToElement(driver, "//input[@id='LastName']", lastName2st);
		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthDay']", "1");
		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthMonth']", "January");
		selectItemInDefaultDropDown(driver, "//select[@name='DateOfBirthYear']", "1999");
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress2);
		sendKeyToElement(driver, "//input[@id='Company']", company2st);
		clickToElement(driver, "//button[@id='save-info-button']");
		clickToElement(driver, "//input[@id='Newsletter']");
		
		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "The customer info has been updated successfully.");
		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		
		sleepInSecond(3);
		clickToElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void TC_02_Addressess() {
		openPageURL(driver, homepageURL);

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress2);
		sendKeyToElement(driver, "//input[@id='Password']", pwd1st);
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		
		waitForElementClickable(driver, "//a[@class='ico-account']");
		clickToElement(driver, "//a[@class='ico-account']");
		
		clickToElement(driver, "//li[starts-with(@class,'customer-addresses')]/a");
		waitForElementClickable(driver, "//button[contains(@class,'add-address-button')]");
		clickToElement(driver, "//button[contains(@class,'add-address-button')]");
		
		sendKeyToElement(driver, "//input[@id='Address_FirstName']", firstName2st);
		sendKeyToElement(driver, "//input[@id='Address_LastName']", lastName2st);
		sendKeyToElement(driver, "//input[@id='Address_Email']", emailAdress2);
		sendKeyToElement(driver, "//input[@id='Address_Company']", company2st);
		selectItemInDefaultDropDown(driver, "//select[@id='Address_CountryId']", "Viet Nam");
		sendKeyToElement(driver, "//input[@id='Address_City']", "Da Nang");
		sendKeyToElement(driver, "//input[@id='Address_Address1']", "123/4 Le Lai");
		sendKeyToElement(driver, "//input[@id='Address_Address2']", "234/5 Hai Phong");
		sendKeyToElement(driver, "//input[@id='Address_ZipPostalCode']", "550000");
		sendKeyToElement(driver, "//input[@id='Address_PhoneNumber']", "0123456789");
		sendKeyToElement(driver, "//input[@id='Address_FaxNumber']", "0987654321");
		clickToElement(driver, "//button[contains(@class,'save-address-button')]");
		
		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "The new address has been added successfully.");
		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		
		Assert.assertEquals(getElementText(driver, "//li[@class='name']"), firstName2st + " " + lastName2st);
		Assert.assertEquals(getElementText(driver, "//li[@class='email']"), "Email: " + emailAdress2);
		Assert.assertEquals(getElementText(driver, "//li[@class='phone']"), "Phone number: " + "0123456789");
		Assert.assertEquals(getElementText(driver, "//li[@class='fax']"), "Fax number: " + "0987654321");
		Assert.assertEquals(getElementText(driver, "//li[@class='company']"), company2st);
		Assert.assertEquals(getElementText(driver, "//li[@class='address1']"), "123/4 Le Lai");
		Assert.assertEquals(getElementText(driver, "//li[@class='address2']"), "234/5 Hai Phong");
		Assert.assertEquals(getElementText(driver, "//li[@class='city-state-zip']"), "Da Nang" + ", " + "550000");
		Assert.assertEquals(getElementText(driver, "//li[@class='country']"), "Viet Nam");
		
		sleepInSecond(3);
		clickToElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void TC_03_Change_Pwd() {
		openPageURL(driver, homepageURL);

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress2);
		sendKeyToElement(driver, "//input[@id='Password']", pwd1st);
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		
		waitForElementClickable(driver, "//a[@class='ico-account']");
		clickToElement(driver, "//a[@class='ico-account']");
		
		clickToElement(driver, "//li[starts-with(@class,'change-password')]/a");
		sendKeyToElement(driver, "//input[@id='OldPassword']", pwd1st);
		sendKeyToElement(driver, "//input[@id='NewPassword']", pwd2st);
		sendKeyToElement(driver, "//input[@id='ConfirmNewPassword']", pwd2st);
		clickToElement(driver, "//button[contains(@class,'change-password-button')]");
		
		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'bar-notification')]/p"), "Password was changed");
		clickToElement(driver, "//div[starts-with(@class,'bar-notification')]//span");
		
		sleepInSecond(3);
		clickToElement(driver, "//a[@class='ico-logout']");
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress2);
		sendKeyToElement(driver, "//input[@id='Password']", pwd1st);
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress2);
		sendKeyToElement(driver, "//input[@id='Password']", pwd2st);
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getPageURL(driver), homepageURL);
		
		waitForElementClickable(driver, "//a[@class='ico-logout']");
		clickToElement(driver, "//a[@class='ico-logout']");
	}

	@Test
	public void TC_04_My_Product_Reviews() {
		openPageURL(driver, homepageURL);
		String productToReview = "Build your own computer";
		String reviewTitle = "Demo Review " + getRandomNumber();
		
		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");
		
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress2);
		sendKeyToElement(driver, "//input[@id='Password']", pwd2st);
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[text()='Computers ']");
		clickToElement(driver, "//ul[@class='top-menu notmobile']//a[text()='Desktops ']");
		
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
		quitPageURL(driver);
	}

}
