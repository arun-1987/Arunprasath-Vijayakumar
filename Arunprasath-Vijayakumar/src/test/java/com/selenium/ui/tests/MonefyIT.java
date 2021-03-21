package com.selenium.ui.tests;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.global.dataprovider.JsonDataProvider;
import com.selenium.ui.pageobjects.GoogleHomePage;
import com.selenium.ui.testbase.TestBase;

public class MonefyIT extends TestBase {



	@Test(dataProvider = "TestData", dataProviderClass = JsonDataProvider.class)
	public void googleCheeseExample(String rowID, String description, JSONObject data) throws Exception {
		GoogleHomePage googleHomePage = new GoogleHomePage();
		googleHomePage.LaunchApplication();
		googleHomePage.enterSearchTerm(data.get("SearchTerm").toString())
					.submitSearch()
					.verifyTitle("Cheese")
					.verifyElement();
	}
	}