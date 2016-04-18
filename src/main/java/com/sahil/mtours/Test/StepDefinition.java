package com.sahil.mtours.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;

import com.sahil.mtours.PageObjects.DashboardPage;
import com.sahil.mtours.PageObjects.LoginPage;
import com.sahil.mtours.Utilities.JsonUtils;
import com.sahil.mtours.Utilities.Selenium_Functions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SuppressWarnings("deprecation")
public class StepDefinition {
	public static WebDriver driver;
	public static WebDriver driver2;
	public static WebDriver driverRemote;
	static Logger log = Logger.getLogger(StepDefinition.class.getName());
	public static String dataProduct;
	public static List<String> dataProductList = null;
	public static String proposedPrice;
	public String ManmadeQuery = "";
	public static String UIproposedprice, UIimpactPrice;

	@Given("The user is on Tesco ITP Login page")
	public void The_user_is_on_Tesco_ITP_Login_page() {
		
		
		
		log.info(" Browser: " + EnvironmentConfig.testBrowser);
		driver = Selenium_Functions.Launch_Driver(driver,
				EnvironmentConfig.testBrowser);
		//Selenium_Functions.Launch_Website(/*EnvironmentConfig.baseUrlTescoItp*/"http://ukird294.dev.global.tesco.org");
		driver.navigate().to("http://ukird294.dev.global.tesco.org");
		log.info("The user is on Tesco ITP Login page"
				+ EnvironmentConfig.baseUrlTescoItp);
		System.out.println("The user is on Tesco ITP Login page");
	}
	
	@Given("^The user is on Mercury Tours Login page$")
	public void The_user_is_on_Mercury_Tours_Login_page() {
		
		log.info(" Browser: " + EnvironmentConfig.testBrowser);
		driver = Selenium_Functions.Launch_Driver(driver,
				EnvironmentConfig.testBrowser);
		Selenium_Functions.Launch_Website(EnvironmentConfig.MercuryToursPage);
		log.info("The user is on Mercury Tours Login page"
				+ EnvironmentConfig.MercuryToursPage);
		log.info("The user is on Mercury Tours Login page");
	}
	

	@And("^user enters valid userid as '(.+)' on the Login page$")
	public void user_enters_valid_user_id_on_the_Login_page(String text) {

		LoginPage.typeUserName(driver, EnvironmentConfig.validUserId);
		log.info("user has entered a valid user id"
				+ EnvironmentConfig.validUserId);
	}


	@Given("^open new browser and go to \"([^\"]*)\"$")
	public void open_new_browser_and_go_to_url(String url) {

		/*
		 * log.info(" Browser: " + EnvironmentConfig.testBrowser);
		 * Selenium_Functions.Launch_Driver(driver2,
		 * EnvironmentConfig.testBrowser);
		 * Selenium_Functions.Launch_WebsiteonDriver2
		 * (EnvironmentConfig.baseUrlTescoItp, driver2);
		 * log.info("The user is on Tesco ITP Login page" +
		 * EnvironmentConfig.baseUrlTescoItp);
		 * System.out.println("The user is on Tesco ITP Login page");
		 */
		Selenium_Functions.openNewBrowserAndgotoNewUrl(driver,
				EnvironmentConfig.baseUrlTescoItp);
		log.info("The user is on Tesco ITP Login page"
				+ EnvironmentConfig.baseUrlTescoItp);
		System.out.println("The user is on Tesco ITP Login page");

	}

	@And("^user enters valid password as \"([^\"]*)\" on the Login page$")
	public void user_enters_valid_password_on_the_Login_page(String text) {
		LoginPage.typePassword(driver, EnvironmentConfig.validPassword);
		log.info("user has entered a valid password" + text);
	}

	@And("user clicks on Login button on the Login page")
	public void user_clicks_on_Login_button_on_the_Login_page() {
		LoginPage.clickLoginButton(driver);
		log.info("user has clicked on Login button");
	}


	

	

	@Then("verify that user is on the welcome page")
	public void verify_that_user_is_on_the_welcome_page() {
		Selenium_Functions.verifyElement(driver,
				LoginPage.SIGN_OFF_LOCATOR);
		log.info("user is on the welcome page");
	}


	

	

	public enum productKeywords {
		favorite, favoriteheld, favoriteinverseheld;
	}

	
	

	
	


	

	@Then("^ the total index impact will update to show the overall impact for \"([^\"]*)\"$")
	public void the_total_index_impact_will_update_to_show_the_overall_impact_for_Currys(
			String IndexCompetitorSelection) {

	}
	@Then("^All Link is there on Home page$")
	public void Registerlink_is_there_on_Home_page() {
		DashboardPage.checkLinks(driver);
	}

	@After
	public void tearDown(Scenario scenario) {
		log.info("***** Tear Down *****");
		if (scenario.isFailed()) {
			try {
				if (!driver.toString().contains("null")) {
					driverRemote = driver;
				}
			} catch (Exception e) {
				log.info("Main Driver Closed!!!");
			}

			if (EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("Yes")) {
				WebDriver augmentedDriver = new Augmenter()
						.augment(driverRemote);
				try {
					final byte[] screenshot = ((TakesScreenshot) augmentedDriver)
							.getScreenshotAs(OutputType.BYTES);
					scenario.embed(screenshot, "image/png");
				} catch (Exception e) {
					log.error("Exception while taking screenshot" + e);
				}
			} else {
				try {
					final byte[] screenshot = ((TakesScreenshot) driverRemote)
							.getScreenshotAs(OutputType.BYTES);
					scenario.embed(screenshot, "image/png");
				} catch (Exception e) {
					log.error("Exception while taking screenshot" + e);
				}
			}
		}
		Selenium_Functions.deleteAllCookies(driver);
		try {
			if (driver != null) {
				driver.quit();
			}
		} catch (Exception e) {
			log.info("Main Driver Closed!!!");
		}
//		if (dataProductList.size() > 0) {
//			dataProductList.clear();
//		}
	}
}