package com.selenium.ui.pageobjects;



import static org.junit.Assert.assertThat;

import java.util.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.connectors.JsonConnector;
import com.selenium.ui.helper.ExceptionHelper;
import com.selenium.ui.reporter.ExtentListener;
import com.selenium.ui.reporter.ThreadSafeExtent;
import com.selenium.ui.testbase.TestBase;


public class Income extends BasePage{

	
	
    public Income() throws Exception {
		super();
	}

    
   private  By Income = By.id("income_button_title");
   private  By Five = By.id("buttonKeyboard5");
   private  By Three = By.id("buttonKeyboard3");
   private  By Zero = By.id("buttonKeyboard0");
   private  By ChooseCategory = By.id("keyboard_action_button");
   private By Salary = By.xpath("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.ImageView");
   private By Balance = By.id("balance_amount");
   private By Expense = By.id("expense_button_title");
   private By ExpenseCategories = By.xpath("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView");
   private By SnackBar = By.id("snackbar_text");
	
   private String totalincome = null;
   private String totalexpense = null;
   
  public Income addIncome() throws ExceptionHelper {
	  performClick(Income, "Click income button");
	  performClick(Five,"Entering the value 5000");
	  for(int i=0;i<3;i++) {
		  performClick(Zero,"");
	  }
	  performClick(ChooseCategory,"Clicking the choose category button");
	  performClick(Salary,"Choosing the category Salary");
	  totalincome = getText(Balance);
	  return this;
  }
  
  public Income addExpense() throws ExceptionHelper{
	  waitUntilElementIsNotVisibleAndClick(SnackBar,Expense,"Waiting for the snakbar to be invisible and click on Expense");
	  performClick(Three,"Entering the value 3000");
	  for(int i=0;i<3;i++) {
		  performClick(Zero,"");
	  }
	  performClick(ChooseCategory,"Clicking the choose category button");
	  performClick(ExpenseCategories,"Choosing the category Car");
	  return this;
  }
  
    public void verifyBalance(String expdata)throws ExceptionHelper {
    	totalexpense = getText(Balance);
    	System.out.println(totalexpense +"          "+ expdata);
    	Assert.assertTrue(totalexpense.contains(expdata));
    	Reporter.log("Expected value is :"+expdata+ " Actual value is :"+totalexpense);
    }
    
}