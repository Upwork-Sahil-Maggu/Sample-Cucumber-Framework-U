package com.sahil.mtours.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sahil.mtours.Utilities.GetXmlData;
import com.sahil.mtours.Utilities.Selenium_Functions;

public class EnvironmentConfig
	{
		public static final int DEFAULT_TIMEOUT = 300;
		public static final int VISIBILITY_TIMEOUT = 45;
		public static final int VISIBILITY_TIMEOUT_IE = 90;
		public static String CHROME_DRIVER_PATH = null,IE_DRIVER_PATH = null,REMOTE_DRIVER_ENABLED = null;
		public static final String GLOBAL_TESTDATA_FILENAME = "/Test Data/Test Data.xls";
		public static String testBrowser, testEnv;
		
		public static String hubUrl, baseUrlTescoItp,MercuryToursPage; 
		public static String validUserId,validPassword,userName,validUserId2,validPassword2;
		public static WebDriverWait wait;
		
		public static String firefoxNode = "Firefox";
		public static String ie8Node = "IE8";
		public static String ie9Node = "IE9";
		public static String ie10Node = "IE10";
		public static String ie11Node = "IE11";
		public static String browserNode;
		public static String envNode,env;
		
		
		static Logger log = Logger.getLogger(EnvironmentConfig.class.getName());
		public static String favoriteflag,holdflag;
		

		
		public static void PerformBeforeRunCukesTest() throws InterruptedException, IOException
			{
				
				testBrowser = TestMainControllerTest.Browser;
				testEnv = TestMainControllerTest.ENVIRONMENT;
				log.info(testBrowser);
				log.info(testEnv);
				EnvironmentConfig.getEnvDataFromTestDataxml();
				EnvironmentConfig.getFuncDataFromTestDataSheet();
				EnvironmentConfig.getConfigDataFromPropertiesFile();

				log.info("Test browser in Environment config is  " + EnvironmentConfig.testBrowser);
			}

		public static void getFuncDataFromTestDataSheet()
		{
			try
				{
					validUserId = GetXmlData.getTagValue("//FunctionalData/", "validUserId");
					validPassword = GetXmlData.getTagValue("//FunctionalData/", "validPassword");
				} catch (Exception e)
				{
					log.error(e);
					log.info("Unable to fetch functional data from test data sheet for " + testBrowser + " execution");
				}
		}

		public static void setEnv()
		{
			if (testEnv.equalsIgnoreCase("E2E"))
				{
					env = "E2E";
				} else if (testEnv.equalsIgnoreCase("PERF"))
				{
					env = "PERF";
				} else if (testEnv.equalsIgnoreCase("PROD"))
				{
					env = "PR0D";
				} 

		}

		
		public static void getEnvDataFromTestDataxml()
		{
			try
				{

				setEnv();

					MercuryToursPage = GetXmlData.getTagValue("//Env/" + env, "MercuryToursPage");

				} catch (Exception e)
				{
					log.error(e);
					log.info("some exception occured while fetching environment data from data sheet");
				}
		}
		


		public static void getConfigDataFromPropertiesFile() throws IOException
			{

				Properties prop2 = new Properties();

				InputStream EnvConfig = null;
				EnvConfig = new FileInputStream(new File("Test Data/EnvConfig.properties"));
				prop2.load(EnvConfig);

				CHROME_DRIVER_PATH = prop2.getProperty("CHROME_DRIVER_PATH_REMOTE");
				IE_DRIVER_PATH = prop2.getProperty("IE_DRIVER_PATH_REMOTE");
				hubUrl = prop2.getProperty("Hub_URL");
				REMOTE_DRIVER_ENABLED = prop2.getProperty("REMOTE_DRIVER_ENABLED");

			}

		public static void deleteFolder(File file)
			{

				if (file.isDirectory())
					{
						if (file.list().length == 0)
							{
								log.info("Directory is not deleted : " + file.getAbsolutePath());
							} else
							{
								String files[] = file.list();
								for (String temp : files)
									{
										File fileDelete = new File(file, temp);
										deleteFolder(fileDelete);
									}
							}
					} else
					{
						file.delete();
						log.info("File is deleted : " + file.getAbsolutePath());
					}
			}

		public static void deleteBrowserCookie(WebDriver driver) throws InterruptedException
			{
				driver.manage().deleteAllCookies();
				Selenium_Functions.sleepCode("5000");
				driver.navigate().refresh();
			}
		
		   
		
		
		
		
	}
