package com.wordpress;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import pageObjects.wordpress.AdminDashboardPO;
import pageObjects.wordpress.AdminLoginPO;
import pageObjects.wordpress.AdminPostAddNewPO;
import pageObjects.wordpress.AdminPostSearchPO;
import pageObjects.wordpress.PageGeneratorManager;
import pageObjects.wordpress.UserHomePO;
import pageObjects.wordpress.UserPostDetailPO;
import pageObjects.wordpress.UserPostSearchPO;
import reportConfig.ExtentTestManager;

public class Post_01_Create_Read_Update_Delete_Search extends BaseTest {

    @Parameters({"browser", "urlAdmin", "urlUser"})
    @BeforeClass
    public void beforeClass(String browserName, String adminURL, String userURL) {
        adminUsername = "qingbao";
        adminPassword = "onlyyou764101";
        authorName = "qingbao";
        randomNumber = getRandomNumber();
        currentDay = getCurrentDay();
        postTitle = "Selenium Auto" + randomNumber;
        postBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod " + randomNumber;
        editPostTitle = "Auto Edit Title " + randomNumber;
        editPostBody = "Edit Auto Lorem ipsum dolor sit amet, consectetur adipiscing elit" + randomNumber;

        this.adminURL = adminURL;
        this.userURL = userURL;
        driver = getBrowserDriver(browserName, this.adminURL);
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminLoginPage.inputToUsernameTextbox(adminUsername);
        adminLoginPage.inputToPasswordTextbox(adminPassword);
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

        ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 04: Input text to textbox title");
        adminPostAddNewPage.inputToTitleTextbox(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 05: Input text to textbox body");
        adminPostAddNewPage.inputToBodyTextbox(postBody);

        ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 06: Click to 'Publish' button");
        adminPostAddNewPage.clickToPublishButton("Publish");

        ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 07: Click to 'Pre-Publish' button");
        adminPostAddNewPage.clickToRePublishButton();

        ExtentTestManager.getTest().log(Status.INFO, "Create Post - Step 08: Verify 'Post published' message is displayed");
        verifyTrue(adminPostAddNewPage.isPublishMessageDisplayed("Post published."));
    }

    @Test
    public void Post_02_Search_And_View_Post(Method method) {
        ExtentTestManager.startTest(method.getName(), "Search post");
        ExtentTestManager.getTest().log(Status.INFO, "Search post - Step 01: Open Search Post Page");
        adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostPageURL);

        ExtentTestManager.getTest().log(Status.INFO, "Search post - Step 02: Enter to Search textbox with value: " + postTitle);
        adminPostSearchPage.inputToSearchTextBox(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Search post - Step 03: Click To 'Search Posts' button");
        adminPostSearchPage.clickToSearchPostsButton();

        ExtentTestManager.getTest().log(Status.INFO, "Search post - Step 04: Verify display search result at table: " + postTitle);
        verifyTrue(adminPostSearchPage.isPostSearchTableDisplayedByID("title", postTitle));

        ExtentTestManager.getTest().log(Status.INFO, "Search post - Step 05: Verify display search result at table: " + authorName);
        verifyTrue(adminPostSearchPage.isPostSearchTableDisplayedByID("author", authorName));

        ExtentTestManager.getTest().log(Status.INFO, "View post - Step 06: Open End User site");
        userHomePage = adminPostSearchPage.openEndUserSite(driver, this.userURL);

        ExtentTestManager.getTest().log(Status.INFO, "View post - Step 07: Verify post display at User Home Page");
        verifyTrue(userHomePage.isPostInforDisplayedTitle(postTitle));
        verifyTrue(userHomePage.isPostInforDisplayedAuthor(postTitle, authorName));
        verifyTrue(userHomePage.isPostInforDisplayedTextBody(postTitle, postBody));
        verifyTrue(userHomePage.isPostInforDisplayedDate(postTitle, currentDay));

        ExtentTestManager.getTest().log(Status.INFO, "View post - Step 08: Click to Post Title: " + postTitle);
        userPostDetailPage = userHomePage.clickToPostTitle(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "View post - Step 09: Verify post infor displayed at Post detail page");
        verifyTrue(userPostDetailPage.isPostDetailDisplayedTitle(postTitle));
        verifyTrue(userPostDetailPage.isPostDetailDisplayedAuthor(postTitle, authorName));
        verifyTrue(userPostDetailPage.isPostDetailDisplayedTextBody(postTitle, postBody));
        verifyTrue(userPostDetailPage.isPostDetailDisplayedDate(postTitle, currentDay));
    }

    @Test
    public void Post_03_Edit_Post(Method method) {
        ExtentTestManager.startTest(method.getName(), "Edit Post");
        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 01: Open Admin Page URL");
        adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminURL);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 02: Click to 'Post' menu link");
        adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 03: Enter to Search textbox with value: " + postTitle);
        adminPostSearchPage.inputToSearchTextBox(postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 04: Click To 'Search Posts' button");
        adminPostSearchPage.clickToSearchPostsButton();

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 05: Click to post title link and navigate to edit post page");
        adminPostSearchPage.clickToPostTitleLink("title", postTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 06: Input text to textbox title with edit value: " + editPostTitle);
        adminPostAddNewPage.inputToTitleTextbox(editPostTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 07: Input text to textbox body with edit value: " + editPostBody);
        adminPostAddNewPage.inputToBodyTextbox(editPostBody);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 08: Click to 'Update' button");
        adminPostAddNewPage.clickToPublishButton("Update");

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 09: Verify 'Post updated.' message is displayed");
        verifyTrue(adminPostAddNewPage.isPublishMessageDisplayed("Post updated."));

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 10: Open Search Post Page");
        adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostPageURL);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 11: Enter to Search textbox with value: " + editPostTitle);
        adminPostSearchPage.inputToSearchTextBox(editPostTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 12: Click To 'Search Posts' button");
        adminPostSearchPage.clickToSearchPostsButton();

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 13: Verify display search result at table: " + postTitle);
        verifyTrue(adminPostSearchPage.isPostSearchTableDisplayedByID("title", editPostTitle));

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 14: Verify display search result at table: " + authorName);
        verifyTrue(adminPostSearchPage.isPostSearchTableDisplayedByID("author", authorName));

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 15: Open End User site");
        userHomePage = adminPostSearchPage.openEndUserSite(driver, this.userURL);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 16: Verify post display at User Home Page");
        verifyTrue(userHomePage.isPostInforDisplayedTitle(editPostTitle));
        verifyTrue(userHomePage.isPostInforDisplayedAuthor(editPostTitle, authorName));
        verifyTrue(userHomePage.isPostInforDisplayedTextBody(editPostTitle, editPostBody));
        verifyTrue(userHomePage.isPostInforDisplayedDate(editPostTitle, currentDay));

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 17: Click to Post Title: " + postTitle);
        userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Edit post - Step 18: Verify post infor displayed at Post detail page");
        verifyTrue(userPostDetailPage.isPostDetailDisplayedTitle(editPostTitle));
        verifyTrue(userPostDetailPage.isPostDetailDisplayedAuthor(editPostTitle, authorName));
        verifyTrue(userPostDetailPage.isPostDetailDisplayedTextBody(editPostTitle, editPostBody));
        verifyTrue(userPostDetailPage.isPostDetailDisplayedDate(editPostTitle, currentDay));
    }

    @Test
    public void Post_04_Delete_Post(Method method) {
        ExtentTestManager.startTest(method.getName(), "Delete Post");
        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 01: Open Admin Page URL");
        adminDashboardPage = userPostDetailPage.openAdminSite(driver, this.adminURL);

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 02: Click to 'Post' menu link");
        adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 03: Enter to Search textbox with value: " + editPostTitle);
        adminPostSearchPage.inputToSearchTextBox(editPostTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 04: Click To 'Search Posts' button");
        adminPostSearchPage.clickToSearchPostsButton();

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 05: Click To checkbox with title value: " + editPostTitle);
        adminPostSearchPage.clickToCheckboxByTitle(editPostTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 06: Select 'Move to Trash' at action dropdown list");
        adminPostSearchPage.selectActionNameAtDropdownList("Move to Trash");

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 07: Click to Apply button");
        adminPostSearchPage.clickToApplyButton();

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 08: Verify displayed '1 post moved to the Trash. ' message");
        verifyTrue(adminPostSearchPage.isPostStatusMessageDisplayed("1 post moved to the Trash."));

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 09: Enter to Search textbox with value: " + editPostTitle);
        adminPostSearchPage.inputToSearchTextBox(editPostTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 10: Click To 'Search Posts' button");
        adminPostSearchPage.clickToSearchPostsButton();

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 11: Verify displayed 'No posts found.' message");
        verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed());

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 12: Open End User site");
        userHomePage = adminPostSearchPage.openEndUserSite(driver, this.userURL);

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 13: Verify post title undisplay at User Home Page with value: " + editPostTitle);
        verifyTrue(userHomePage.isPostTitleNoDisplayedAtHomePage(editPostTitle));

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 14: Enter to Search Textbox");
        userHomePage.enterTextToSearchTextbox(editPostTitle);

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 15: Click To Search button");
        userPostSearchPage = userHomePage.clickToSearchButton();

        ExtentTestManager.getTest().log(Status.INFO, "Delete post - Step 16: Verify displayed 'Nothing Found' message");
        verifyTrue(userPostSearchPage.isNothingFoundMessageDisplayed("Nothing Found"));
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String adminUsername, adminPassword, searchPostPageURL, postTitle, postBody,
            authorName, userURL, adminURL, currentDay, editPostTitle, editPostBody;
    private int randomNumber;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private AdminPostAddNewPO adminPostAddNewPage;
    private AdminPostSearchPO adminPostSearchPage;
    private UserHomePO userHomePage;
    private UserPostDetailPO userPostDetailPage;
    private UserPostSearchPO userPostSearchPage;
}
