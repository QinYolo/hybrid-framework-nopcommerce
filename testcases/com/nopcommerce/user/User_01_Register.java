package com.nopcommerce.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class User_01_Register extends BasePage {
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
	public void TC_01_Register_Empty_Data() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		waitForElementClickable(driver, "//button[@id='register-button']");
		clickToElement(driver, "//button[@id='register-button']");
		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}

	@Test
	public void TC_02_Register_Wrong_Email() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", "randdddd678@yu.67#!");
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Register_Successful() {
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
	}

	@Test
	public void TC_04_Register_Email_Already_Exists() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"),"The specified email already exists");
	}

	@Test
	public void TC_05_Register_Pwd_Less_Than_6_Chars() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendKeyToElement(driver, "//input[@id='Password']", "123");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"),
				"Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_06_Register_Pwd_Confirmation_Does_Not_Match() {
		openPageURL(driver, "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[@class='ico-register']");
		clickToElement(driver, "//a[@class='ico-register']");

		sendKeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendKeyToElement(driver, "//input[@id='LastName']", "Testing");
		sendKeyToElement(driver, "//input[@id='Email']", emailAdress);
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		sendKeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		clickToElement(driver, "//button[@id='register-button']");

		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"),
				"The password and confirmation password do not match.");
	}
	
	@AfterClass
	public void afterClass() {
		quitPageURL(driver);
	}

	
}
