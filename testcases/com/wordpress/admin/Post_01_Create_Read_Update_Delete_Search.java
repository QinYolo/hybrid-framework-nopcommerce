package com.wordpress.admin;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import pageObjects.wordpress.admin.AdminDashboardPO;
import pageObjects.wordpress.admin.AdminLoginPO;
import pageObjects.wordpress.admin.AdminPostAddNewPO;
import pageObjects.wordpress.admin.AdminPostSearchPO;
import pageObjects.wordpress.admin.PageGeneratorManager;
import reportConfig.ExtentTestManager;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {

	@Parameters({"browser", "urlAdmin"})
	@BeforeClass
	public void beforeClass(String browserName, String urlBrowser) {
		adminUsername = "qingbao";
		adminPassword = "onlyyou764101";
		randomNumber = getRandomNumber();
		postTitleValue = "Selenium Auto" + randomNumber;
		postBodyValue = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod" + randomNumber;
		
		log.info("Pre-Condition - Step 01: Open browser by Admin URL");
		driver = getBrowserDriver(browserName, urlBrowser);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		log.info("Pre-Condition - Step 02: Input username in textbox with value: " + adminUsername);
		adminLoginPage.inputToUsernameTextbox(adminUsername);
		
		log.info("Pre-Condition - Step 03: Input password in textbox with value: " + adminPassword);
		adminLoginPage.inputToPasswordTextbox(adminPassword);
		
		log.info("Pre-Condition - Step 04: Click 'Log in' button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
	}

	@Test
	public void Post_01_Create_a_New_Post(Method method) {
		ExtentTestManager.startTest(method.getName(), "Create a new post");
		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 01: Click to 'Post' menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 02: Get 'Search Post' Page URL");
		searchPostPageURL = adminPostSearchPage.getPageURL(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 03: Click 'Add New' button");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();
		
//		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 04: Close Tutorial Pop-up");
//		adminPostAddNewPage.clickToCloseTutorialPopup();
		
		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 04: Input text to textbox title");
		adminPostAddNewPage.inputToTitleTextbox(postTitleValue);
		
		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 05: Input text to textbox body");
		adminPostAddNewPage.inputToBodyTextbox(postBodyValue);
		
		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 06: Click to 'Publish' button");
		adminPostAddNewPage.clickToPublishButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 07: Click to 'Pre-Publish' button");
		adminPostAddNewPage.clickToRePublishButton();
		
		ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 08: Verify 'Post published' message is displayed");
		verifyTrue(adminPostAddNewPage.isPublishMessageDisplayed("Post published."));
	}
	
	@Test
	public void Post_02_Search_Post(Method method) {
		
	}
	
	@Test
	public void Post_03_View_Post(Method method) {
		
	}
	
	@Test
	public void Post_04_Edit_Post(Method method) {
		
	}
	
	@Test
	public void Post_04_Delete_Post(Method method) {

	}

	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

	private WebDriver driver;
	private String adminUsername, adminPassword, searchPostPageURL, postTitleValue, postBodyValue;
	private int randomNumber;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostAddNewPO adminPostAddNewPage;
	private AdminPostSearchPO adminPostSearchPage;
}
