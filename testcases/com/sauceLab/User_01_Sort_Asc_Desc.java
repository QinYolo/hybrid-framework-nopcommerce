package com.sauceLab;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.sauceLab.LoginPageObject;
import pageObjects.sauceLab.PageGeneratorManager;
import pageObjects.sauceLab.ProductPageObject;

public class User_01_Sort_Asc_Desc extends BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urljQuery) {
		driver = getBrowserDriver(browserName, urljQuery);
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Test
	public void Sort_01_By_Name() {
		loginPage.inputToUserNameTextbox("standard_user");
		loginPage.inputToPasswordTextbox("secret_sauce");
		productPage = loginPage.clickToLoginButton();
		productPage.selectItemInProductDropdown("Name (A to Z)");
		verifyTrue(productPage.isProductNameSortByAscending());
		productPage.selectItemInProductDropdown("Name (Z to A)");
		verifyTrue(productPage.isProductNameSortByDescending());
	}
	
	@Test
	public void Sort_02_By_Price() {
		productPage.selectItemInProductDropdown("Price (low to high)");
		verifyTrue(productPage.isProductPriceSortByAscending());
		productPage.selectItemInProductDropdown("Price (high to low)");
		verifyTrue(productPage.isProductPriceSortByDescending());
	}

	@AfterClass
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private LoginPageObject loginPage;
	private ProductPageObject productPage;
}
