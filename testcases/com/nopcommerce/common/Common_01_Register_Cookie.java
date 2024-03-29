package com.nopcommerce.common;

import commons.BaseTest;
import pageObjects.nopcommerce.user.PageGeneratorManager;
import pageObjects.nopcommerce.user.UserHomePageObject;
import pageObjects.nopcommerce.user.UserLoginPageObject;
import pageObjects.nopcommerce.user.UserRegisterPageObject;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Common_01_Register_Cookie extends BaseTest {
	
	@Parameters({ "envName", "severName", "browserName", "browserVersion", "osName", "osVersion", "ipAddress", "portNumber" })
	@BeforeTest(description = "Create new common user for all classes test")
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

        loggedCookies = userHomePage.getAllCookie(driver);

        driver.quit();
    }

    private WebDriver driver;
    private String firstName, lastName;
    private String emailAdress, password;
    private UserHomePageObject userHomePage;
    private UserRegisterPageObject userRegisterPage;
    private UserLoginPageObject userLoginPage;
    public static Set<Cookie> loggedCookies;
}
