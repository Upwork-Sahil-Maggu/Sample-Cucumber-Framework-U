package com.sahil.mtours.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sahil.mtours.Utilities.Selenium_Functions;


public class DashboardPage
	{
		//LOCATORS
		public static final By REGISTER_LOCATOR = By.xpath("//*[text()='REGISTER']");
		public static final By SUPPORT_LOCATOR = By.xpath("//*[text()='SUPPORT']");
		public static final By CONTACT_LOCATOR = By.xpath("//*[text()='CONTACT']");

	
		//METHODS
	
		public static void checkLinks(WebDriver driver)
		{
			Selenium_Functions.fetchTextAndCompareEquals(driver, REGISTER_LOCATOR, "REGISTER");
			Selenium_Functions.fetchTextAndCompareEquals(driver, SUPPORT_LOCATOR, "SUPPORT");
			Selenium_Functions.fetchTextAndCompareEquals(driver, CONTACT_LOCATOR, "CONTACT");
			
		}
		
		
		
	
	}
