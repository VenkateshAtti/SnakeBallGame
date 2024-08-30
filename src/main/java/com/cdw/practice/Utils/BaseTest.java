package com.cdw.practice.Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.cdw.practice.Reuseables.GoogleActions;

public class BaseTest {
	protected WebDriver driver;
	public GoogleActions googleAct;
	
    @BeforeTest
    public void init() {
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.get("https://www.google.com/");
    	Reporter.log("Google Search Page");
    	googleAct = PageFactory.initElements(driver, GoogleActions.class);
    }
    @AfterTest
    public void tearDown() {
    	driver.quit();
    }
}
