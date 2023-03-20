package base.testcase;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstant;
import pageObjects.nopcommerce.user.UserAddressesPageObject;
import pageObjects.nopcommerce.user.UserChangePasswordPageObject;
import pageObjects.nopcommerce.user.UserCustomerInforPageObject;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserOrdersPageObject;
import pageObjects.nopcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopcommerce.admin.AdminLoginPageObject;
import pageObjects.nopcommerce.user.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_01_Switch_Page extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		firstName = "Automation";
		lastName = "Testing";
		userPassword = "123456";
		userEmailAdress = "automationTest" + getRandomNumber() + "@gmail.com";
		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void User_01_Register() {
		userRegisterPage = userHomePage.clickToRegisterLink();

		userRegisterPage.inputToFirstNameTextbox(firstName);
		userRegisterPage.inputToLastNameTextbox(lastName);
		userRegisterPage.inputToEmailTextbox(userEmailAdress);
		userRegisterPage.inputToPasswordTextbox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextbox(userPassword);
		userRegisterPage.clickToRegisterButton();
		
		System.out.println(userEmailAdress);
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");
	}
	
	@Test
	public void User_02_Login() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userLoginPage = userHomePage.clickToLoginLink();
		
		userLoginPage.inputToEmailTexbox(userEmailAdress);
		userLoginPage.inputToPasswordTextbox(userPassword);
		
		userHomePage = userLoginPage.clickToLoginButton();
	}
	
	@Test
	public void User_03_My_Account() {
		userCustomerInforPage = userHomePage.clickToMyAccountLink();
		Assert.assertTrue(userCustomerInforPage.isCustomerInforPageTitleDisplayed());
	}
	
	@Test
	public void User_04_Switch_Page() {
		userAddressPage = (UserAddressesPageObject) userCustomerInforPage.openPageAtMyAccountByName(driver, "Addresses");
		userOrdersPage = (UserOrdersPageObject) userAddressPage.openPageAtMyAccountByName(driver, "Orders");
		userChangePasswordPage = (UserChangePasswordPageObject) userOrdersPage.openPageAtMyAccountByName(driver, "Change password");
		userChangePasswordPage.openPageAtMyAccountByName(driver, "My product reviews");
	}

	@Test
	public void User_05_Switch_Role_User_To_Admin() {
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userHomePage.clickToLogOutLinkAtUserPage(driver);
		userHomePage.openPageURL(driver, GlobalConstant.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isAdminDashboardHeaderDisplayed());
		adminLoginPage = adminDashboardPage.clickToLogOutLinkAtAdminPage(driver);
	}
	
	@Test
	public void User_06_Switch_Role_Admin_To_User() {
		adminLoginPage.openPageURL(driver, GlobalConstant.USER_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userLoginPage = userHomePage.clickToLoginLink();
		userHomePage = userLoginPage.loginAsUser(userEmailAdress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private WebDriver driver;
	private String firstName, lastName, userEmailAdress, userPassword, adminEmailAddress, adminPassword;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInforPageObject userCustomerInforPage;
	private UserAddressesPageObject userAddressPage;
	private UserOrdersPageObject userOrdersPage;
	private UserChangePasswordPageObject userChangePasswordPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
}
