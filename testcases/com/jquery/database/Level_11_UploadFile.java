package com.jquery.database;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jquery.upload.HomePageObject;
import pageObjects.jquery.upload.PageGeneratorManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Level_11_UploadFile extends BaseTest {
    String cityFileName = "city.jpg";
    String flowerFileName = "flower.jpg";
    String galaxyFileName = "galaxy.jpg";
    String lightFileName = "light.jpg";
    String[] multipleFileNames = {cityFileName, flowerFileName, galaxyFileName, lightFileName};

    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String urljQuery) {
        driver = getBrowserDriver(browserName, urljQuery);
        homePage = PageGeneratorManager.getHomePage(driver);
    }

    @Test
    public void Upload_01_One_File_Per_Time() {
        homePage.uploadMultipleFiles(driver, cityFileName);
        verifyTrue(homePage.isFileLoadedByName(cityFileName));
        homePage.clickToStartButton();
        verifyTrue(homePage.isFileLinkUploadedByName(cityFileName));
        verifyTrue(homePage.isFileImageUploadedByName(cityFileName));
    }

    @Test
    public void Upload_02_Multiple_File_Per_Time() {
        homePage.refreshCurrentPage(driver);
        homePage.uploadMultipleFiles(driver, multipleFileNames);
        verifyTrue(homePage.isFileLoadedByName(cityFileName));
        verifyTrue(homePage.isFileLoadedByName(flowerFileName));
        verifyTrue(homePage.isFileLoadedByName(galaxyFileName));
        verifyTrue(homePage.isFileLoadedByName(lightFileName));

        homePage.clickToStartButton();

        verifyTrue(homePage.isFileLinkUploadedByName(cityFileName));
        verifyTrue(homePage.isFileLinkUploadedByName(flowerFileName));
        verifyFalse(homePage.isFileLinkUploadedByName(galaxyFileName));
        verifyTrue(homePage.isFileLinkUploadedByName(lightFileName));

        verifyTrue(homePage.isFileImageUploadedByName(cityFileName));
        verifyFalse(homePage.isFileImageUploadedByName(flowerFileName));
        verifyFalse(homePage.isFileImageUploadedByName(galaxyFileName));
        verifyTrue(homePage.isFileImageUploadedByName(lightFileName));
    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePageObject homePage;
}
