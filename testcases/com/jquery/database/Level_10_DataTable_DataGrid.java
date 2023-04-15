package com.jquery.database;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.database.HomePageObject;
import pageObjects.jquery.database.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_10_DataTable_DataGrid extends BaseTest {
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urljQuery) {
		driver = getBrowserDriver(browserName, urljQuery);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	
	public void TC_01_Paging() {
		homePage.openPagingByPageNumber("10");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("10"));
		homePage.openPagingByPageNumber("4");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("4"));
		homePage.openPagingByPageNumber("2");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActive("2"));
	}
	
	
	public void TC_02_Enter_To_Header() {
		homePage.refreshCurrentPage(driver);
		homePage.enterToHeaderTextboxByLabel("Country","Armenia");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isDataDisplayedByInputLabelTextbox("Country","Armenia"));
		
		homePage.enterToHeaderTextboxByLabel("Females","15999");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isDataDisplayedByInputLabelTextbox("Females","15999"));
		
		homePage.enterToHeaderTextboxByLabel("Males","16456");
		homePage.sleepInSecond(1);
		Assert.assertTrue(homePage.isDataDisplayedByInputLabelTextbox("Males","16456"));
		
		homePage.enterToHeaderTextboxByLabel("Total","32487");
	}
	
	public void TC_03_Get_Data_Colum() {
		homePage.getVallueEachRowAtAllPage();
	}
	
	@Test
	public void TC_04_Enter_To_Textbox_At_Any_Row() {
		homePage.clickToLoadButton();
		homePage.enterToTextboxByColumnNameAtRowNumber("Company","1","Automation FC");
		homePage.enterToTextboxByColumnNameAtRowNumber("Company","2","vfdfdbfbf");
		homePage.enterToTextboxByColumnNameAtRowNumber("Company","3","fbfnbfgng");
	}
	
	@Test
	public void TC_05_Action_At_Any_Row() {
		homePage.selectDefaultDropDownByColumnNameAtRowNumber("Country", "2", "Hong Kong");
		homePage.selectDefaultDropDownByColumnNameAtRowNumber("Country", "1", "Taiwan");
		homePage.selectDefaultDropDownByColumnNameAtRowNumber("Country", "3", "Japan");
		
		homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?","2");
		homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?","3");
		homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?","6");
		homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?","7");
		homePage.checkToCheckBoxByColumnNameAtRowNumber("NPO?","8");
		
		homePage.uncheckToCheckBoxByColumnNameAtRowNumber("NPO?","1");
		homePage.uncheckToCheckBoxByColumnNameAtRowNumber("NPO?","4");
		homePage.uncheckToCheckBoxByColumnNameAtRowNumber("NPO?","5");
		
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		homePage.clickToIconByRowNumber("4", "Remove Current Row");
		homePage.clickToIconByRowNumber("2", "Move Up");
		homePage.clickToIconByRowNumber("1", "Insert Row Above");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
}
