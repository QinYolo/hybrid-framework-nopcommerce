package com.nopcommerce.common;

import commons.BaseTest;
import pageObjects.nopcommerce.user.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Common_01_Register_End_User extends BaseTest {

	@Parameters({ "envName", "severName", "browserName", "browserVersion", "osName", "osVersion", "ipAddress", "portNumber" })
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String severName,
			@Optional("chrome") String browserName, @Optional("latest") String browserVersion,
			@Optional("Windows 10") String osName, @Optional("10") String osVersion, @Optional("192") String ipAddress,
			@Optional("4444") String portNumber) {
		driver = getBrowserDriver(envName, severName, browserName, browserVersion, osName, osVersion, ipAddress,
				portNumber);
        userHomePage = PageGeneratorManager.getUserHomePage(driver);

        firstName = "Automation";
        lastName = "Testing";
        password = "123456";
        emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";

        userRegisterPage = userHomePage.clickToRegisterLink();

        userRegisterPage.inputToFirstNameTextbox(firstName);
        userRegisterPage.inputToLastNameTextbox(lastName);
        userRegisterPage.inputToEmailTextbox(emailAdress);
        userRegisterPage.inputToPasswordTextbox(password);
        userRegisterPage.inputToConfirmPasswordTextbox(password);

        userRegisterPage.clickToRegisterButton();
        verifyEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

        userLoginPage = userHomePage.clickToLoginLink();

        userLoginPage.inputToEmailTexbox(emailAdress);
        userLoginPage.inputToPasswordTextbox(password);

        userLoginPage.clickToLoginButton();

        verifyTrue(userHomePage.isMyAccountLinkDisplayed());

        driver.quit();
    }

    private WebDriver driver;
    private String firstName, lastName;
    public static String emailAdress, password;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
}
