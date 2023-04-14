package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;

public class User_04_Search_Advanced_Search extends BasePage {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		openPageURL(driver, "https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Empty_Data() {
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
		sendKeyToElement(driver, "//input[@id='Password']", "123456");
		clickToElement(driver, "//button[contains(@class,'login-button')]");
		Assert.assertEquals(getPageURL(driver), "https://demo.nopcommerce.com/");

		waitForElementClickable(driver, "//a[text()='Search']");
		clickToElement(driver, "//a[text()='Search']");

		waitForElementClickable(driver, "//button[contains(@class,'search-button')]");
		clickToElement(driver, "//button[contains(@class,'search-button')]");

		Assert.assertEquals(getElementText(driver, "//div[@class='warning']"),
				"Search term minimum length is 3 characters");
	}

	@Test
	public void TC_02_Data_Does_Not_Exist() {
		refreshCurrentPage(driver);

		sendKeyToElement(driver, "//input[starts-with(@class,'search-text')]", "Macbook Pro 2050");
		clickToElement(driver, "//button[contains(@class,'search-button')]");
		sleepInSecond(1);

		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"),
				"No products were found that matched your criteria.");
	}

	@Test
	public void TC_03_Implicit_Product_Name() {
		refreshCurrentPage(driver);

		sendKeyToElement(driver, "//input[starts-with(@class,'search-text')]", "Lenovo");
		clickToElement(driver, "//button[contains(@class,'search-button')]");
		sleepInSecond(1);

		Assert.assertEquals(getElementsSize(driver, "//h2[@class='product-title']/a"), 2);
		Assert.assertTrue(isKeywordDisplayInSearchResult(driver, "//h2[@class='product-title']/a", "Lenovo"));
	}

	@Test
	public void TC_04_Explicit_Product_Name() {
		refreshCurrentPage(driver);

		sendKeyToElement(driver, "//input[starts-with(@class,'search-text')]", "ThinkPad X1 Carbon");
		clickToElement(driver, "//button[contains(@class,'search-button')]");
		sleepInSecond(1);

		Assert.assertEquals(getElementsSize(driver, "//h2[@class='product-title']/a"), 1);
		Assert.assertEquals(getElementText(driver, "//h2[@class='product-title']/a"),
				"Lenovo Thinkpad X1 Carbon Laptop");
	}

	@Test
	public void TC_05_Advanced_Search_With_Parent_Categories() {
		refreshCurrentPage(driver);

		sendKeyToElement(driver, "//input[starts-with(@class,'search-text')]", "Apple Macbook Pro");
		
		checkToDefautCheckboxOrRadio(driver, "//input[@id='advs']");
		waitForAllElementsVisible(driver, "//select[@id='cid']");
		selectItemInDefaultDropDown(driver, "//select[@id='cid']", "Computers");
		
		clickToElement(driver, "//button[contains(@class,'search-button')]");
		sleepInSecond(1);

		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"),
				"No products were found that matched your criteria.");
	}

	@Test
	public void TC_06_Advanced_Search_With_Sub_Categories() {
		refreshCurrentPage(driver);

		sendKeyToElement(driver, "//input[starts-with(@class,'search-text')]", "Apple Macbook Pro");
		
		checkToDefautCheckboxOrRadio(driver, "//input[@id='advs']");
		waitForAllElementsVisible(driver, "//select[@id='cid']");
		selectItemInDefaultDropDown(driver, "//select[@id='cid']", "Computers");
		checkToDefautCheckboxOrRadio(driver, "//input[@id='isc']");
		
		clickToElement(driver, "//button[contains(@class,'search-button')]");
		sleepInSecond(1);
		
		Assert.assertEquals(getElementsSize(driver, "//h2[@class='product-title']/a"), 1);
		Assert.assertEquals(getElementText(driver, "//h2[@class='product-title']/a"),
				"Apple MacBook Pro 13-inch");
	}

	@Test
	public void TC_07_Advanced_Search_With_Incorrect_Manufacturer() {
		refreshCurrentPage(driver);

		sendKeyToElement(driver, "//input[starts-with(@class,'search-text')]", "Apple Macbook Pro");
		
		checkToDefautCheckboxOrRadio(driver, "//input[@id='advs']");
		waitForAllElementsVisible(driver, "//select[@id='cid']");
		selectItemInDefaultDropDown(driver, "//select[@id='cid']", "Computers");
		checkToDefautCheckboxOrRadio(driver, "//input[@id='isc']");
		waitForAllElementsVisible(driver, "//select[@id='mid']");
		selectItemInDefaultDropDown(driver, "//select[@id='mid']", "HP");
		
		clickToElement(driver, "//button[contains(@class,'search-button')]");
		sleepInSecond(1);
		
		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"),
				"No products were found that matched your criteria.");
	}

	@Test
	public void TC_08_Advanced_Search_With_Correct_Manufacturer() {
		refreshCurrentPage(driver);

		sendKeyToElement(driver, "//input[starts-with(@class,'search-text')]", "Apple Macbook Pro");
		
		checkToDefautCheckboxOrRadio(driver, "//input[@id='advs']");
		waitForAllElementsVisible(driver, "//select[@id='cid']");
		selectItemInDefaultDropDown(driver, "//select[@id='cid']", "Computers");
		checkToDefautCheckboxOrRadio(driver, "//input[@id='isc']");
		waitForAllElementsVisible(driver, "//select[@id='mid']");
		selectItemInDefaultDropDown(driver, "//select[@id='mid']", "Apple");
		
		clickToElement(driver, "//button[contains(@class,'search-button')]");
		sleepInSecond(1);
		
		Assert.assertEquals(getElementsSize(driver, "//h2[@class='product-title']/a"), 1);
		Assert.assertEquals(getElementText(driver, "//h2[@class='product-title']/a"),
				"Apple MacBook Pro 13-inch");
	}

	@AfterClass
	public void afterClass() {
		quitPageURL(driver);
	}
	
	private int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
