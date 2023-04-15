package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.liveguru.AdminPanelPageObject;
import pageObjects.liveguru.HomePageObject;
import pageObjects.liveguru.LoginPageAdminObject;
import pageObjects.liveguru.LoginPageObject;
import pageObjects.liveguru.MyDashboardPageObject;
import pageObjects.liveguru.PageGeneratorManager;
import pageObjects.liveguru.RegisterPageObject;

public class User_02_Data_Grid extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		driver.get("http://live.techpanda.org/");
		emailAddress = "automationTest" + getRandomNumber() + "@gmail.com";
		password = "123456";
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void TC_01_Create_Account() {
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
	public void TC_02_Verify_Account_At_Admin_Page() {
		homePage.openPageURL(driver, "http://live.techpanda.org/index.php/backendlogin/customer/");
		loginPageAdmin = PageGeneratorManager.getLoginPageAdmin(driver);
		loginPageAdmin.inputToUserNameTextbox("user01");
		loginPageAdmin.inputToPasswordTextbox("guru99com");
		adminPanelPage = loginPageAdmin.clickToLoginButton();
		adminPanelPage.clickToCloseButtonPopup();
		adminPanelPage.inputToTextboxByColumnNameAndRowNumber("Email", "2", emailAddress);
		adminPanelPage.clickToButtonAboveTable("Search");
		Assert.assertTrue(adminPanelPage.isAccountDisplayed("Automation" + " " + "Testing", emailAddress));
	}
	
	@Test
	public void TC_03_Deleted_Account() {
		adminPanelPage.clickToAccountCheckboxByEmail(emailAddress);
		adminPanelPage.selectActionDropDownByName("Delete");
		adminPanelPage.clickSubmitButton();
		adminPanelPage.clickToAcceptAlert();
		Assert.assertEquals(adminPanelPage.isSuccessMessageDisplayed(), "Total of 1 record(s) were deleted.");
		adminPanelPage.openPageURL(driver, "http://live.techpanda.org/");
		homePage = PageGeneratorManager.getHomePage(driver);
		loginPage = homePage.clickToMyAccountLink();
		loginPage.inputToEmailAddressTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.isErrorMessageDisplayed(), "Invalid login or password.");
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
	private LoginPageAdminObject loginPageAdmin;
	private AdminPanelPageObject adminPanelPage;
}
