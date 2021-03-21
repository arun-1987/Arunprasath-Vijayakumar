package com.selenium.ui.tests;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.global.dataprovider.JsonDataProvider;
import com.selenium.ui.pageobjects.Income;
import com.selenium.ui.testbase.TestBase;

public class MonefyIT extends TestBase {

	@Test(dataProvider = "TestData", dataProviderClass = JsonDataProvider.class)
	public void BalanceValidation(String rowID, String description, JSONObject data) throws Exception {
		new Income().addIncome().addExpense().verifyBalance(data.get("calculatedvalue").toString());
	}
	}