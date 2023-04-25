package com.facebook;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Level_13_Element_Undisplayed extends BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urljQuery) {
		driver = getBrowserDriver(browserName, urljQuery);
		loginPage = PageGeneratorManager.getLoginPage(driver);
		
	}

	@Test
	public void TC_01_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		verifyTrue(loginPage.isEmailAddressTextBoxDisplayed());
	}
	
	@Test
	public void TC_02_Element_Undisplayed_In_DOM() {
		loginPage.enterToEmailAddressTextBox("email@address.com");
		loginPage.sleepInSecond(1);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());
		loginPage.enterToEmailAddressTextBox("");
		loginPage.sleepInSecond(1);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplay());
	}
	
	@Test
	public void TC_03_Element_Undisplayed_Not_In_DOM() {
		loginPage.sleepInSecond(1);
		loginPage.closeRegisteFormPopup();
		loginPage.sleepInSecond(1);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplay());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private LoginPageObject loginPage;
}
