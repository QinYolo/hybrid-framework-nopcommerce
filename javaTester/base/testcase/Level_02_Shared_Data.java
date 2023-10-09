package base.testcase;

import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Register_Cookie;
import com.nopcommerce.common.Common_01_Register_End_User;

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

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Shared_Data extends BaseTest {

	@Parameters({ "envName", "severName", "browserName", "browserVersion", "osName", "osVersion", "ipAddress", "portNumber" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String severName,
			@Optional("chrome") String browserName, @Optional("latest") String browserVersion,
			@Optional("Windows 10") String osName, @Optional("10") String osVersion, @Optional("192") String ipAddress,
			@Optional("4444") String portNumber) {
		driver = getBrowserDriver(envName, severName, browserName, browserVersion, osName, osVersion, ipAddress,
				portNumber);
        userEmailAdress = Common_01_Register_End_User.emailAdress;
        userPassword = Common_01_Register_End_User.password;
        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
    }

    @Test
    public void User_01_Login() {
        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.setCookies(driver, Common_01_Register_Cookie.loggedCookies);
        userLoginPage.refreshCurrentPage(driver);

//		userLoginPage.inputToEmailTexbox(userEmailAdress);
//		userLoginPage.inputToPasswordTextbox(userPassword);
//		
//		userHomePage = userLoginPage.clickToLoginButton();
    }

    @Test
    public void User_02_My_Account() {
        userCustomerInforPage = userHomePage.clickToMyAccountLink();
        Assert.assertTrue(userCustomerInforPage.isCustomerInforPageTitleDisplayed());
    }

    @Test
    public void User_03_Switch_Page() {
        userAddressPage = (UserAddressesPageObject) userCustomerInforPage.openPageAtMyAccountByName(driver, "Addresses");
        userOrdersPage = (UserOrdersPageObject) userAddressPage.openPageAtMyAccountByName(driver, "Orders");
        userChangePasswordPage = (UserChangePasswordPageObject) userOrdersPage.openPageAtMyAccountByName(driver, "Change password");
        userChangePasswordPage.openPageAtMyAccountByName(driver, "My product reviews");
    }

    @Test
    public void User_04_Switch_Role_User_To_Admin() {
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userHomePage.clickToLogOutLinkAtUserPage(driver);
        userHomePage.openPageURL(driver, GlobalConstant.ADMIN_PAGE_URL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
        Assert.assertTrue(adminDashboardPage.isAdminDashboardHeaderDisplayed());
        adminLoginPage = adminDashboardPage.clickToLogOutLinkAtAdminPage(driver);
    }

    @Test
    public void User_05_Switch_Role_Admin_To_User() {
        adminLoginPage.openPageURL(driver, GlobalConstant.USER_PAGE_URL);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);
        userLoginPage = userHomePage.clickToLoginLink();
        userHomePage = userLoginPage.loginAsUser(userEmailAdress, userPassword);
        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    private WebDriver driver;
    private String userEmailAdress, userPassword, adminEmailAddress, adminPassword;
    private UserHomePageObject userHomePage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInforPageObject userCustomerInforPage;
    private UserAddressesPageObject userAddressPage;
    private UserOrdersPageObject userOrdersPage;
    private UserChangePasswordPageObject userChangePasswordPage;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;
}
