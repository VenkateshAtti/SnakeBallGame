package com.cdw.practice.Reuseables;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.cdw.practice.Objects.GoogleSearch;

public class GoogleActions extends GoogleSearch{

	public GoogleActions(WebDriver driver) {
		super(driver);
	}
	
	public void searchInGoogle() {
		Reporter.log("Google Search Page loaded Successfully");
		googleSearchField.sendKeys("Venky");
		Reporter.log("Enter Venky in Google Search Page");
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			e.getMessage();
		}
	}
 
}
