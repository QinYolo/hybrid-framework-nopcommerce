package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_02_Login extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_07_Login_Empty_Data() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		waitForElementClickable(driver, "//button[contains(@class,'login-button')]");
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Please enter your email");
	}

	@Test
	public void TC_08_Login_Wrong_Email() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		sendKeyToElement(driver, "//input[@id='Email']", "emailtesting63947#^@mila%c");
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_09_Login_No_Customer_Account_Found() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		sendKeyToElement(driver, "//input[@id='Email']", "emailtesting" + getRandomNumber() + "@gmail.com");
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'message-error')]"),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void TC_10_Login_No_Input_Pwd() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'message-error')]"),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_11_Login_Wrong_Pwd() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendKeyToElement(driver, "//input[@id='Password']", "789456");
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getElementText(driver, "//div[starts-with(@class,'message-error')]"),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void TC_12_Login_Successful() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-login']");
		clickToElement(driver, "//a[@class='ico-login']");

		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getPageURL(driver), "https://demo.nopcommerce.com/");
	}

	@AfterClass
	public void afterClass() {
		quitPageURL(driver);
	}

}
