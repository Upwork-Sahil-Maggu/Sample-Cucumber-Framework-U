package com.sahil.mtours.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sahil.mtours.Utilities.Selenium_Functions;


public class LoginPage
	{
		//LOCATORS
		public static final By USERNAME_LOCATOR = By.name("userName");
		public static final By PASSWORD_LOCATOR = By.name("password");
		public static final By LOGIN_BUTTON_LOCATOR = By.name("login");
		public static final By SIGN_OFF_LOCATOR = By.xpath("//*[@href='mercurysignoff.php']");

	
		//METHODS
	
		public static void typeUserName(WebDriver driver,String text)
		{
			Selenium_Functions.enterText(driver, USERNAME_LOCATOR, text);
		}
		
		public static void typePassword(WebDriver driver,String text)
		{
			Selenium_Functions.enterText(driver, PASSWORD_LOCATOR, text);
		}
		
		public static void clickLoginButton(WebDriver driver)
		{
			Selenium_Functions.clickElement(driver, LOGIN_BUTTON_LOCATOR);
		}
		
	
	}
