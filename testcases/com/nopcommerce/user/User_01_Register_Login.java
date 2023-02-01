package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class User_01_Register_Login {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String emailAdress = "automationTest" + getRandomNumber() + "@gmail.com";
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void TC_01_Register_Empty_Data() {
	  driver.get("https://demo.nopcommerce.com/");
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  Assert.assertEquals(driver.findElement(By.cssSelector("#FirstName-error")).getText(), "First name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("#LastName-error")).getText(), "Last name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Email is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "Password is required.");
  }
  
  @Test
  public void TC_02_Register_Wrong_Email() {
	  driver.get("https://demo.nopcommerce.com/");
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("#LastName")).sendKeys("Testing");
	  driver.findElement(By.cssSelector("#Email")).sendKeys("randdddd678@yu.67#!");
	  driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("#Email-error")).getText(), "Wrong email");
  }
  
  @Test
  public void TC_03_Register_Successful() {
	  driver.get("https://demo.nopcommerce.com/");
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("#LastName")).sendKeys("Testing");
	  driver.findElement(By.cssSelector("#Email")).sendKeys(emailAdress);
	  driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
  }
  
  @Test
  public void TC_04_Register_Email_Already_Exists() {
	  driver.get("https://demo.nopcommerce.com/");
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("#LastName")).sendKeys("Testing");
	  driver.findElement(By.cssSelector("#Email")).sendKeys(emailAdress);
	  driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123456");
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
  }
  
  @Test
  public void TC_05_Register_Pwd_Less_Than_6_Chars() {
	  driver.get("https://demo.nopcommerce.com/");
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("#LastName")).sendKeys("Testing");
	  driver.findElement(By.cssSelector("#Email")).sendKeys(emailAdress);
	  driver.findElement(By.cssSelector("#Password")).sendKeys("123");
	  driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123");
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
  }
  
  @Test
  public void TC_06_Register_Pwd_Confirmation_Does_Not_Match() {
	  driver.get("https://demo.nopcommerce.com/");
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("#FirstName")).sendKeys("Automation");
	  driver.findElement(By.cssSelector("#LastName")).sendKeys("Testing");
	  driver.findElement(By.cssSelector("#Email")).sendKeys(emailAdress);
	  driver.findElement(By.cssSelector("#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("#ConfirmPassword")).sendKeys("123");
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

  public int getRandomNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
	  
  }
}
