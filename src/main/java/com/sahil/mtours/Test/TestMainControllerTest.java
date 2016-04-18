package com.sahil.mtours.Test;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.apache.log4j.Logger;

import com.sahil.mtours.Test.EnvironmentConfig;

public class TestMainControllerTest
	{

		public static String Browser = null;
		public static String ENVIRONMENT;
		public static String browserCode;
		static Class runCuke;
		final static Logger log = Logger.getLogger(TestMainControllerTest.class.getName());

		public static void main(String[] args) throws Exception
		{

			if (args[0].equalsIgnoreCase("windowsfirefox"))
				{
					Browser = "windowsfirefox";
					browserCode = "FF";
				//	Browser = "ie9";
					//browserCode = "IE";
				
				}
			if (args[0].equalsIgnoreCase("chrome"))
				{
					Browser = "chrome";
					browserCode = "CH";
				}
			if (args[0].equalsIgnoreCase("ie8"))
				{
					Browser = "ie8";
					browserCode = "IE";
				}
			if (args[0].equalsIgnoreCase("ie9"))
				{
					Browser = "ie9";
					browserCode = "IE";
				}
			if (args[0].equalsIgnoreCase("ie10"))
				{
					Browser = "ie10";
					browserCode = "IE";
				}
			if (args[0].equalsIgnoreCase("ie11"))
				{
					Browser = "ie11";
					browserCode = "IE";
				}
			if (args[0].equalsIgnoreCase("Phantomjs"))
				{
					Browser = "phantomjs";
					browserCode = "PH";
				}
			if (args[0].equalsIgnoreCase("MACSafari"))
				{
					Browser = "MACSafari";
					browserCode = "SA";
				}
			if (args[0].equalsIgnoreCase("MACfirefox"))
				{
					Browser = "MACfirefox";
					browserCode = "FF";
				}
			if (args[0].equalsIgnoreCase("MACchrome"))
				{
					Browser = "MACchrome";
					browserCode = "CH";
				}

			log.info("##################################################################################");
			log.info("################################# TEST STARTED ####################################");
			log.info("###################################################################################");

			log.info(Browser);

			// Environment - expected values "QA1" or "QA2 or QA"

			ENVIRONMENT = args[1];
			log.info(ENVIRONMENT);

			EnvironmentConfig.PerformBeforeRunCukesTest();

			JUnitCore core = new JUnitCore();

			String runCukeName = "com.sahil.mtours.Test.RunCukesTest_"+ browserCode;
			System.out.println("Run Cuke Name "+runCukeName);
			
			try
				{
					runCuke = Class.forName(runCukeName.trim());
					System.out.println("run cuke "+runCuke);
					
				}catch (Exception e)
					{ 
						log.info("Exception generated while generating cuke runner class name " + e);
						System.out.println("Exception generated while generating cuke runner class name "+ e);
					}
			Result result = core.run(runCuke);
			if(result.wasSuccessful())
				{
					log.info("Tests ran successfully on browser-------------" + Browser);
					System.out.println("Tests ran successfully on browser-------------" + Browser);
				}
			
			
		}
	/*	@Test	
		public  void mainMethod() throws Exception
		{
			String browser = "MACchrome";
			String environment;
			//String browser = "MACchrome"; 
			System.out.println(System.getProperty("browser").toString());
			if(System.getProperty("browser").toString().equalsIgnoreCase("null")){
				browser = "MACchrome"; 
			}
			//if(System.getProperty("environment").isEmpty()){
				environment="E2E";
		//	}
			// browser=System.getProperty("browser");
			 //environment=(String) System.getProperty("environment");
			if (browser.equalsIgnoreCase("windowsfirefox"))
				{
					Browser = "windowsfirefox";
					browserCode = "FF";
				//	Browser = "ie9";
					//browserCode = "IE";
				
				}
			if (browser.equalsIgnoreCase("chrome"))
				{
					Browser = "chrome";
					browserCode = "CH";
				}
			if (browser.equalsIgnoreCase("ie8"))
				{
					Browser = "ie8";
					browserCode = "IE";
				}
			if (browser.equalsIgnoreCase("ie9"))
				{
					Browser = "ie9";
					browserCode = "IE";
				}
			if (browser.equalsIgnoreCase("ie10"))
				{
					Browser = "ie10";
					browserCode = "IE";
				}
			if (browser.equalsIgnoreCase("ie11"))
				{
					Browser = "ie11";
					browserCode = "IE";
				}
			if (browser.equalsIgnoreCase("Phantomjs"))
				{
					Browser = "phantomjs";
					browserCode = "PH";
				}
			if (browser.equalsIgnoreCase("MACSafari"))
				{
					Browser = "MACSafari";
					browserCode = "SA";
				}
			if (browser.equalsIgnoreCase("MACfirefox"))
				{
					Browser = "MACfirefox";
					browserCode = "FF";
				}
			if (browser.equalsIgnoreCase("MACchrome"))
				{
					Browser = "MACchrome";
					browserCode = "CH";
				}

			log.info("##################################################################################");
			log.info("################################# TEST STARTED ####################################");
			log.info("###################################################################################");

			log.info(Browser);

			// Environment - expected values "QA1" or "QA2 or QA"

			ENVIRONMENT = environment;
			log.info(ENVIRONMENT);

			EnvironmentConfig.PerformBeforeRunCukesTest();

			JUnitCore core = new JUnitCore();

			String runCukeName = "com.sahil.mtours.Test.RunCukesTest_"+ browserCode;
			System.out.println("Run Cuke Name "+runCukeName);
			
			try
				{
					runCuke = Class.forName(runCukeName.trim());
					System.out.println("run cuke "+runCuke);
					
				}catch (Exception e)
					{ 
						log.info("Exception generated while generating cuke runner class name " + e);
						System.out.println("Exception generated while generating cuke runner class name "+ e);
					}
			Result result = core.run(runCuke);
			if(result.wasSuccessful())
				{
					log.info("Tests ran successfully on browser-------------" + Browser);
					System.out.println("Tests ran successfully on browser-------------" + Browser);
				}
			
			
		}*/
	}
