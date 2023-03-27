package com.liveguru.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.liveguru.HomePageObject;
import pageObjects.liveguru.LoginPageObject;
import pageObjects.liveguru.MyDashboardPageObject;
import pageObjects.liveguru.PageGeneratorManager;
import pageObjects.liveguru.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_01_Register_Login extends BaseTest {

	@Parameters({"browser", "environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		driver = getBrowserDriver(browserName, environmentName);
		driver.get("http://live.techpanda.org/index.php/");
		emailAddress = "automationTest" + getRandomNumber() + "@gmail.com";
		password = "123456";
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void User_01_Register_To_System() {
		loginPage = homePage.clickToMyAccountLink();

		registerPage = loginPage.clickToCreateAnAccountButton();

		registerPage.inputToFirstNameTextbox("Automation");
		registerPage.inputToLastNameTextbox("Testing");
		registerPage.inputToEmailAddressTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);
		myDashboardPage = registerPage.clickToRegisterButton();

		Assert.assertEquals(myDashboardPage.getSuccessfulMessageAtMyDashboard(),
				"Thank you for registering with Main Website Store.");
		myDashboardPage.clickToAccountLabel();
		homePage = myDashboardPage.clickToLogOutLink();

		Assert.assertTrue(homePage.isLogOutSuccessMessageDisplayed());
	}

	@Test
	public void User_02_Login_To_System() {
		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailAddressTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		myDashboardPage = loginPage.clickToLoginButton();
		
		Assert.assertTrue(myDashboardPage.isMyDashboardTitleDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private String emailAddress, password;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private MyDashboardPageObject myDashboardPage;
}
