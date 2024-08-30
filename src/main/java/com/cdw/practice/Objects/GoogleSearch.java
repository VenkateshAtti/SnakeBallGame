package com.cdw.practice.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cdw.practice.Utils.BaseTest;

public class GoogleSearch extends BaseTest {

	public GoogleSearch(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(name="q")
	protected WebElement googleSearchField;
}
