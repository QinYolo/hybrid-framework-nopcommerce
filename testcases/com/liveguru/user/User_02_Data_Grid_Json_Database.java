package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.liveguru.data.RegisterAccount;
import com.liveguru.data.UserDataMapper;

import commons.BaseTest;
import pageObjects.liveguru.AdminPanelPageObject;
import pageObjects.liveguru.HomePageObject;
import pageObjects.liveguru.LoginPageAdminObject;
import pageObjects.liveguru.LoginPageObject;
import pageObjects.liveguru.MyDashboardPageObject;
import pageObjects.liveguru.PageGeneratorManager;
import pageObjects.liveguru.RegisterPageObject;

public class User_02_Data_Grid_Json_Database extends BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlLiveGuru) {
		driver = getBrowserDriver(browserName, urlLiveGuru);
		userData = UserDataMapper.getUserData();
		emailAddress = userData.getEmail();
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test
	public void TC_01_Create_Account() {
		loginPage = homePage.clickToMyAccountLink();
		registerPage = loginPage.clickToCreateAnAccountButton();

		registerPage.inputToFirstNameTextbox(userData.getFirst_name());
		registerPage.inputToLastNameTextbox(userData.getLast_name());
		registerPage.inputToEmailAddressTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(userData.getPassword());
		registerPage.inputToConfirmPasswordTextbox(userData.getPassword());
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
		loginPageAdmin.inputToUserNameTextbox(RegisterAccount.AdminAccount.USERNAME);
		loginPageAdmin.inputToPasswordTextbox(RegisterAccount.AdminAccount.PASSWORD);
		adminPanelPage = loginPageAdmin.clickToLoginButton();
		adminPanelPage.clickToCloseButtonPopup();
		adminPanelPage.inputToTextboxByColumnNameAndRowNumber("Email", "2", emailAddress);
		adminPanelPage.clickToButtonAboveTable("Search");
		Assert.assertTrue(adminPanelPage.isAccountDisplayed(
				userData.getFirst_name() + " " + userData.getLast_name(), emailAddress));
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
		loginPage.inputToPasswordTextbox(userData.getPassword());
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.isErrorMessageDisplayed(), "Invalid login or password.");
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String emailAddress;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private MyDashboardPageObject myDashboardPage;
	private LoginPageAdminObject loginPageAdmin;
	private AdminPanelPageObject adminPanelPage;
	private UserDataMapper userData;
}
