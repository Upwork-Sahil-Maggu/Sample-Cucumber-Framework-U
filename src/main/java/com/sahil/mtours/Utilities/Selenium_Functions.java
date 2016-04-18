package com.sahil.mtours.Utilities;

import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sahil.mtours.Test.*;
//import com.thoughtworks.selenium.webdriven.JavascriptLibrary;

public class Selenium_Functions
	{

		static Logger log = Logger.getLogger(Selenium_Functions.class.getName());
	
		public static WebDriver Launch_Driver(WebDriver driver, String Selected_Browser)

			{
				try
					{
						if (StepDefinition.driver != null)
							{
								StepDefinition.driver.close();
								StepDefinition.driver.quit();
							}
					} catch (Exception e)
					{
						log.error("Exception while quitting driver" + e);
					}
				try
					{
						if (Selected_Browser.equalsIgnoreCase("windowsfirefox"))

							{
								
								FirefoxProfile profile = new FirefoxProfile();
	
		                        profile.setPreference("network.proxy.type",ProxyType.AUTODETECT.ordinal());
	
		                        profile.setPreference("network.proxy.http", "localhost");
	
		                        profile.setPreference("network.proxy.http_port", 8080);
		                        //excel download prefereence
		                        profile.setPreference("browser.download.folderList", 2);     

		                        profile.setPreference("browser.download.dir","D:\\Downloads_sel");
		                       // profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword,application/x-rar-compressed,application/octet-stream,application/csv,text/csv");
		                        profile.setPreference("browser.helperApps.neverAsksaveToDisk", "application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
		                        
		                        
		                        
		                        
		                       
		                        profile.setPreference("browser.download.alertOnEXEOpen", false);
		                        profile.setPreference("browser.helperApps.neverAsksaveToDisk", "application/x-msexcel,application/excel,application/x-excel,application/excel,application/x-excel,application/excel,application/vnd.ms-excel,application/x-excel,application/x-msexcel");
		                        profile.setPreference("browser.download.manager.showWhenStarting", false);
		                        profile.setPreference("browser.download.manager.focusWhenStarting", false);
		                        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
		                        profile.setPreference("browser.download.manager.alertOnEXEOpen", false);
		                        profile.setPreference("browser.download.manager.closeWhenDone", false);
		                        profile.setPreference("browser.download.manager.showAlertOnComplete", false);
		                        profile.setPreference("browser.download.manager.useWindow", false);
		                        profile.setPreference("browser.download.manager.showWhenStarting", false);
		                        profile.setPreference("services.sync.prefs.sync.browser.download.manager.showWhenStarting", false);
		                        profile.setPreference("pdfjs.disabled", true);
	
		                       /*logger.info("firefox browser is launched");
	
		                        driver = new FirefoxDriver(profile);
	
		                        logger.info("Execution initiated");
	
		                        try {
	
		                                this.launchSite(driver);
	
		                        } catch (Exception e) {
	
		                                logger.error(ERROR + e.getMessage());
	
		                        }
*/
		                        
								DesiredCapabilities ffcapability = DesiredCapabilities.firefox();
								ffcapability.setBrowserName("firefox");
								ffcapability.setPlatform(Platform.WINDOWS);
								ffcapability.setJavascriptEnabled(true);
								ffcapability.setCapability("acceptSslCerts", true);
								ffcapability.setCapability("unexpectedAlertBehaviour", "dismiss");
								ffcapability.setCapability(FirefoxDriver.PROFILE, profile);

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), ffcapability);
									}
								else
									{
										driver = new FirefoxDriver(ffcapability);
									}
								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().window().maximize();
								log.info("FireFox Driver launched");
							}

						if (Selected_Browser.equalsIgnoreCase("MACfirefox"))

							{
								DesiredCapabilities ffcapability = DesiredCapabilities.firefox();
								ffcapability.setBrowserName("firefox");
								ffcapability.setPlatform(Platform.MAC);
								ffcapability.setJavascriptEnabled(true);
								ffcapability.setCapability("acceptSslCerts", true);
								ffcapability.setCapability("unexpectedAlertBehaviour", "dismiss");

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), ffcapability);
									}
								else
									{
										driver = new FirefoxDriver(ffcapability);
									}
								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().window().maximize();
								log.info("FireFox Driver launched");
							}

						if (Selected_Browser.equalsIgnoreCase("chrome"))
							{
							System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+EnvironmentConfig.CHROME_DRIVER_PATH);
								DesiredCapabilities ChromeCapability = DesiredCapabilities.chrome();
								ChromeCapability.setBrowserName("chrome");
								ChromeCapability.setPlatform(Platform.WINDOWS);
								ChromeCapability.setCapability("acceptSslCerts", true);
								ChromeCapability.setJavascriptEnabled(true);
								ChromeCapability.setCapability("unexpectedAlertBehaviour", "dismiss");

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), ChromeCapability);
									}
								else
									{
										driver = new ChromeDriver(ChromeCapability);
									}
								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().window().maximize();
								log.info("Driver chrome launch ok");
							}

						if (Selected_Browser.equalsIgnoreCase("MACchrome"))
							{
								System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+EnvironmentConfig.CHROME_DRIVER_PATH);
								DesiredCapabilities ChromeCapability = DesiredCapabilities.chrome();
								ChromeCapability.setBrowserName("chrome");
								ChromeCapability.setPlatform(Platform.MAC);
								ChromeCapability.setCapability("acceptSslCerts", true);
								ChromeCapability.setJavascriptEnabled(true);
								ChromeCapability.setCapability("unexpectedAlertBehaviour", "dismiss");

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), ChromeCapability);
									}
								else
									{
										driver = new ChromeDriver(ChromeCapability);
									}
								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().window().maximize();
								log.info("Driver chrome launch ok");
							}

						if (Selected_Browser.equalsIgnoreCase("ie8"))
							{
								System.setProperty("webdriver.ie.driver", EnvironmentConfig.IE_DRIVER_PATH);
								DesiredCapabilities IECapability = DesiredCapabilities.internetExplorer();
								IECapability.setBrowserName("internet explorer");
								IECapability.setVersion("8");
								IECapability.setCapability("acceptSslCerts", true);
								IECapability.setJavascriptEnabled(true);
								IECapability.setCapability("nativeEvents", false);
								IECapability.setCapability("unexpectedAlertBehaviour", "dismiss");
								IECapability.setCapability("ie.ensureCleanSession", true);
								IECapability.setCapability("requireWindowFocus", false);
								IECapability.setCapability("ignoreZoomSetting", true);

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), IECapability);
									}
								else
									{
										driver = new InternetExplorerDriver(IECapability);
									}
								
								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT_IE, TimeUnit.SECONDS);
								driver.manage().window().maximize();
								log.info("IE8 Driver launched");
							}
						if (Selected_Browser.equalsIgnoreCase("ie9"))
							{
								System.setProperty("webdriver.ie.driver", EnvironmentConfig.IE_DRIVER_PATH);
								DesiredCapabilities IECapability = DesiredCapabilities.internetExplorer();
								IECapability.setBrowserName("internet explorer");
								IECapability.setVersion("9");
								IECapability.setCapability("acceptSslCerts", true);
								IECapability.setJavascriptEnabled(true);
								IECapability.setCapability("unexpectedAlertBehaviour", "dismiss");
								IECapability.setCapability("ie.ensureCleanSession", true);
								IECapability.setCapability("requireWindowFocus", false);
								IECapability.setCapability("ignoreZoomSetting", true);

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), IECapability);
									}
								else
									{
										driver = new InternetExplorerDriver(IECapability);
									}

								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT_IE, TimeUnit.SECONDS);
								driver.manage().window().maximize();
								log.info("IE9 Driver launched");
							}
						if (Selected_Browser.equalsIgnoreCase("ie10"))
							{
								System.setProperty("webdriver.ie.driver", EnvironmentConfig.IE_DRIVER_PATH);
								DesiredCapabilities IECapability = DesiredCapabilities.internetExplorer();
								IECapability.setBrowserName("internet explorer");
								IECapability.setVersion("10");
								IECapability.setCapability("acceptSslCerts", true);
								IECapability.setJavascriptEnabled(true);
								IECapability.setCapability("unexpectedAlertBehaviour", "dismiss");
								IECapability.setCapability("ie.ensureCleanSession", true);
								IECapability.setCapability("requireWindowFocus", false);
								IECapability.setCapability("ignoreZoomSetting", true);

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), IECapability);
									}
								else
									{
										driver = new InternetExplorerDriver(IECapability);
									}

								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT_IE, TimeUnit.SECONDS);
								driver.manage().window().maximize();
								log.info("IE10 Driver launched");
							}

						if (Selected_Browser.equalsIgnoreCase("ie11"))
							{
								System.setProperty("webdriver.ie.driver", EnvironmentConfig.IE_DRIVER_PATH);
								DesiredCapabilities IECapability = DesiredCapabilities.internetExplorer();
								IECapability.setBrowserName("internet explorer");
								IECapability.setVersion("11");
								IECapability.setCapability("acceptSslCerts", true);
								IECapability.setJavascriptEnabled(true);
								IECapability.setCapability("unexpectedAlertBehaviour", "dismiss");
								IECapability.setCapability("ie.ensureCleanSession", true);
								IECapability.setCapability("requireWindowFocus", false);
								IECapability.setCapability("ignoreZoomSetting", true);

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), IECapability);
									}
								else
									{
										driver = new InternetExplorerDriver(IECapability);
									}

								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT_IE, TimeUnit.SECONDS);
								driver.manage().window().maximize();
								log.info("IE11 Driver launched");
							}

						if (Selected_Browser.equalsIgnoreCase("MACSafari"))
							{
								System.setProperty("webdriver.safari.noinstall", "true");
								DesiredCapabilities macsafaricap = DesiredCapabilities.safari();
								macsafaricap.setBrowserName("safari");
								macsafaricap.setJavascriptEnabled(true);
								macsafaricap.setCapability("takesScreenshot", true);
								macsafaricap.setCapability("acceptSslCerts", true);
								macsafaricap.setCapability("ensureCleanSession", true);
								macsafaricap.setCapability("unexpectedAlertBehaviour", "dismiss");
								macsafaricap.setCapability("requireWindowFocus", false);

								driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), macsafaricap);

								if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
									{
										driver = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), macsafaricap);
									}
								else
									{
										driver = new InternetExplorerDriver(macsafaricap);
									}
								
								driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
								driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT, TimeUnit.SECONDS);

								log.info("Safari Driver launched");
							}
					} catch (Exception e)
					{
						log.error(e);
						System.out.println(e);
					}
				return driver;
			}

		public static void Launch_Website(String testURL)
			{
				if (!(testURL == null))
					{
						try
							{
								if (EnvironmentConfig.testBrowser.equalsIgnoreCase("ie8") || EnvironmentConfig.testBrowser.equalsIgnoreCase("ie9") || EnvironmentConfig.testBrowser.equalsIgnoreCase("ie10"))
									{
										StepDefinition.driver.get(testURL);
										StepDefinition.driver.navigate().to("javascript:document.getElementById('overridelink').click()");
									}
								StepDefinition.driver.get(testURL);
							} catch (Exception e)
							{
								System.out.println(e + "Unable to launch website");
								log.error(e);
							}
						String PageTitle = StepDefinition.driver.getTitle();
						if (!(PageTitle.contains("404 Not Found") || PageTitle.contains("is not available") || PageTitle.contains("Problem loading page")))
							{
								log.info("Website launched successfully");
							} else
							{
								log.error("The website is not launched");
								throw new IllegalStateException("The website is not launched");
							}
					} else
					{
						log.error("The url passed is null");
						throw new IllegalStateException("The url passed is null");
					}
			}
		public static void Launch_WebsiteonDriver2(String testURL,WebDriver driver)
		{
			if (!(testURL == null))
				{
					try
						{
							if (EnvironmentConfig.testBrowser.equalsIgnoreCase("ie8") || EnvironmentConfig.testBrowser.equalsIgnoreCase("ie9") || EnvironmentConfig.testBrowser.equalsIgnoreCase("ie10"))
								{
								driver.get(testURL);
								driver.navigate().to("javascript:document.getElementById('overridelink').click()");
								}
							driver.get(testURL);
						} catch (Exception e)
						{
							System.out.println(e + "Unable to launch website");
							log.error(e);
						}
					String PageTitle = StepDefinition.driver.getTitle();
					if (!(PageTitle.contains("404 Not Found") || PageTitle.contains("is not available") || PageTitle.contains("Problem loading page")))
						{
							log.info("Website launched successfully");
						} else
						{
							log.error("The website is not launched");
							throw new IllegalStateException("The website is not launched");
						}
				} else
				{
					log.error("The url passed is null");
					throw new IllegalStateException("The url passed is null");
				}
		}
		public static void clickElement(WebDriver driver, By elemLocator)
			{
				if (isElementPresent(driver, elemLocator))
					{
						if (EnvironmentConfig.testBrowser.equalsIgnoreCase("ie8") || EnvironmentConfig.testBrowser.equalsIgnoreCase("ie9") || EnvironmentConfig.testBrowser.equalsIgnoreCase("ie10"))
							{
								System.out.println(driver.toString());
								if (driver.toString().contains("Firefox"))
									{
										driver.findElement(elemLocator).click();

									} else
									{
										driver.findElement(elemLocator).sendKeys(Keys.ENTER);
									}
							} else
							{
								driver.findElement(elemLocator).click();
							}

						if (TestMainControllerTest.Browser.equalsIgnoreCase("MACSafari"))
							{
								Selenium_Functions.sleepCode("5000");
							}

					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void click(WebDriver driver, By elemLocator)
			{
				if (isElementPresent(driver, elemLocator))
					{
						driver.findElement(elemLocator).click();
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void clickElementAt(WebDriver driver, By elemLocator)
			{
				if (isElementPresent(driver, elemLocator))
					{

						if (TestMainControllerTest.Browser.equalsIgnoreCase("MACSafari"))
							{
								if (driver.toString().contains("firefox"))
									{
										Actions builder = new Actions(driver);
										WebElement tagElement = driver.findElement(elemLocator);
										builder.moveToElement(tagElement).click().perform();

									} else
									{
										WebElement element = driver.findElement(elemLocator);
										JavascriptExecutor executor = (JavascriptExecutor) driver;
										executor.executeScript("arguments[0].click();", element);
										Selenium_Functions.sleepCode("1000");
									}
							} else
							{
								Actions builder = new Actions(driver);
								WebElement tagElement = driver.findElement(elemLocator);
								builder.moveToElement(tagElement).click().perform();
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void doubleclickElement(WebDriver driver, By elemLocator)
			{
				if (isElementPresent(driver, elemLocator))
					{
						Actions builder = new Actions(driver);
						WebElement tagElement = driver.findElement(elemLocator);
						builder.moveToElement(tagElement).doubleClick().perform();
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void verifyElement(WebDriver driver, By elemLocator)
			{
				if (isElementPresent(driver, elemLocator))
					{
						log.info("The object " + elemLocator + " is available on current page");
					} else
					{
						log.error("The object " + elemLocator + " is not available on current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on current page");
					}
			}

		public static void verifyElementNotPresent(WebDriver driver, By elemLocator)
			{
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
				if (!isElementPresent(driver, elemLocator))
					{
						driver.manage().timeouts().implicitlyWait(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
						log.info("The object " + elemLocator + " is not available on current page");
					} else
					{
						log.error("The object " + elemLocator + " is available on current page");
						throw new IllegalStateException("The object " + elemLocator + " is available on current page");
					}
			}

		public static void enterText(WebDriver driver, By elemLocator, String text)
			{
				if (isElementPresent(driver, elemLocator))
					{
						driver.findElement(elemLocator).clear();
						Selenium_Functions.sleepCode("100");
						driver.findElement(elemLocator).sendKeys(text);
						log.info("The text entered is: " + text);
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void enterTextWithoutClear(WebDriver driver, By elemLocator, String text)
			{
				if (isElementPresent(driver, elemLocator))
					{
						driver.findElement(elemLocator).sendKeys(text);
						log.info("The text entered is: " + text);
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void enterBlankText(WebDriver driver, By elemLocator, String text)
			{
				if (isElementPresent(driver, elemLocator))
					{
						text = "";
						driver.findElement(elemLocator).clear();
						String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
						driver.findElement(elemLocator).sendKeys(Keys.HOME, del);
						driver.findElement(elemLocator).sendKeys(text);
						driver.findElement(elemLocator).sendKeys(Keys.HOME, del);
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void select(WebDriver driver, By elemLocator, String visibleText)
			{
				if (isElementPresent(driver, elemLocator))
					{
						new Select(driver.findElement(elemLocator)).selectByVisibleText(visibleText);
						log.info("The selected text is: " + visibleText);
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void selectbyIndex(WebDriver driver, By elemLocator, int index)
			{
				if (isElementPresent(driver, elemLocator))
					{
						new Select(driver.findElement(elemLocator)).selectByIndex(index);
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void getCSSpropertyValue(WebDriver driver, By elemLocator, String cssProperty)
			{
				if (isElementPresent(driver, elemLocator))
					{
						String areaText = driver.findElement(elemLocator).getCssValue(cssProperty);
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void sleepCode(String slTime)
			{
				try
					{
						long L = Long.parseLong(slTime);
						Thread.sleep(L);
					} catch (Exception e)
					{
						log.error(e);
					}
			}

		public static void deleteAllCookies(WebDriver driver)
			{
				try
					{
						if (StepDefinition.driver != null)
							{
								try
									{
										StepDefinition.driver.manage().deleteAllCookies();
									} catch (Exception e)
									{
										log.error(e);
									}
							}
					} catch (Exception e)
					{
						log.error(e);
					}
			}

		public static void fetchTextAndCompareContains(WebDriver driver, By elemLocator, String compareData)
			{
				String areaText;
				if (isElementPresent(driver, elemLocator))
					{
						areaText = driver.findElement(elemLocator).getText();
						if (areaText.contains(compareData))
							{
								log.info("The object " + elemLocator + " is available and " + compareData + " is present in object's text " + areaText);
							} else
							{
								log.error("The object " + elemLocator + " is available but " + compareData + " is not present in object's text " + areaText);
								throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not present in object's text " + areaText);
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void fetchTextAndCompareEquals(WebDriver driver, By elemLocator, String compareData)
			{
				String areaText;
				if (isElementPresent(driver, elemLocator))
					{
						areaText = driver.findElement(elemLocator).getText();
						if (areaText.equalsIgnoreCase(compareData))
							{
								log.info("The object " + elemLocator + " is available and " + compareData + " is present in object's text " + areaText);
							} else
							{
								log.error("The object " + elemLocator + " is available but " + compareData + " is not equal to object's text");
								throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not equal to object's text");
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}
		
		public static void getSelectedStatusAndCompare(WebDriver driver, By elemLocator, boolean compareData)
			{
				if (isElementPresent(driver, elemLocator))
					{
						boolean value = driver.findElement(elemLocator).isSelected();
						if (value == compareData)
							{
								log.info("The object " + elemLocator + " is available and " + compareData + " is equal to object's value3 " + value);
							} else
							{
								log.error("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value3 " + value);
								throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value3 " + value);
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void getToggleStatusAndCompare(WebDriver driver, By elemLocator, String xpath, boolean compareData)
			{
				if (isElementPresent(driver, elemLocator))
					{
						JavascriptExecutor js = (JavascriptExecutor) driver;
						Object checkedValue = js.executeScript("return document.getElementById('sms-toggle-button').checked");
						boolean value = driver.findElement(elemLocator).isSelected();
						driver.findElement(elemLocator).getAttribute("checked");
						driver.findElement(elemLocator).isEnabled();
						if (js.executeScript("return document.getElementById('sms-toggle-button').checked").equals(compareData))
							{
								log.info("The object " + elemLocator + " is available and " + compareData + " is equal to object's value " + value + " and java script checked value  value is " + checkedValue);
							} else
							{
								log.error("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value " + value + " and java script checked value  value is " + checkedValue);
								throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value " + value + " and java script checked value  value is " + checkedValue);
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void fetchTwoAreaTextEquals(WebDriver driver, By elemLocator1, By elemLocator2)
			{
				String tempObject1 = elemLocator1.toString();
				String tempObject2 = elemLocator2.toString();
				String area1Text, area2Text;
				if (isElementPresent(driver, elemLocator1))
					{
						area1Text = driver.findElement(elemLocator1).getText();
						if (isElementPresent(driver, elemLocator2))
							{
								area2Text = driver.findElement(elemLocator2).getText();
								if (area1Text.equalsIgnoreCase(area2Text))
									{
										log.info("The objects Object 1 " + tempObject1 + "Object 2 " + tempObject2 + " are available and their texts are equal");
									} else
									{
										log.error("The objects Object 1 " + tempObject1 + "Object 2 " + tempObject2 + " are available but their text is not equal");
										throw new IllegalStateException("The objects Object 1 " + tempObject1 + "Object 2 " + tempObject2 + " are available but their text is not equal");
									}
							} else
							{
								log.error("Object2 is not available on page");
								throw new IllegalStateException("Object2 is not available on page");
							}
					} else
					{
						log.error("Object1 is not available on page");
						throw new IllegalStateException("Object1 is not available on page");
					}
			}

		public static void compareTwoStrings(String str1, String str2)
			{
				if (str1.equals(str2))
					{
						log.info("Two strings are equal");
					} else
					{
						log.error("Two strings are not equal");
						throw new IllegalStateException("Two strings are not equal");
					}
			}

		public static void fetchValueAndCompareEquals(WebDriver driver, By elemLocator, String compareData)
			{
				String value;
				if (isElementPresent(driver, elemLocator))
					{
						value = driver.findElement(elemLocator).getAttribute("value");

						if (value.equalsIgnoreCase(compareData))
							{
								log.info("The object " + elemLocator + " is available and " + compareData + " is equal to object's value" + value);
							} else
							{
								log.error("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value" + value);
								throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value" + value);
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}

			}
		public static void fetchClassAndCompareEquals(WebDriver driver, By elemLocator, String compareData)
		{
			String value;
			if (isElementPresent(driver, elemLocator))
				{
					value = driver.findElement(elemLocator).getAttribute("class");

					if (value.equalsIgnoreCase(compareData))
						{
							log.info("The object " + elemLocator + " is available and " + compareData + " is equal to object's value" + value);
						} else
						{
							log.error("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value" + value);
							throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value" + value);
						}
				} else
				{
					log.error("The object " + elemLocator + " is not available on the current page");
					throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
				}

		}
		public static void fetchValueAndCompareContains(WebDriver driver, By elemLocator, String compareData)
			{
				String value;
				if (isElementPresent(driver, elemLocator))
					{
						value = driver.findElement(elemLocator).getAttribute("value");
						value = value.replaceAll("[\\s\\-()]", "");

						if (value.contains(compareData))
							{
								log.info("The object " + elemLocator + " is available but " + compareData + " is equal to object's value\"" + value + "\"");
							} else
							{
								log.error("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value\"" + value + "\"");
								throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value\"" + value + "\"");
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}

			}

		public static void fetchValueAndCompareContains_NonClickableElement(WebDriver driver, By elemLocator, String compareData)
			{
				String value;
				if (isNonClickableElementPresent(driver, elemLocator))
					{
						value = driver.findElement(elemLocator).getAttribute("value");
						value = value.replaceAll("[\\s\\-()]", "");
						if (value.contains(compareData))
							{
								log.info("The object " + elemLocator + " is available but " + compareData + " is equal to object's value\"" + value + "\"");
							} else
							{
								log.error("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value\"" + value + "\"");
								throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not equal to object's value\"" + value + "\"");
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static String getValue(WebDriver driver, By elemLocator)
			{
				String value;
				if (isElementPresent(driver, elemLocator))
					{
						value = driver.findElement(elemLocator).getAttribute("value");
						return value;
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void switchToFrame(WebDriver driver, By frameLocator)
			{
				String tempObject = frameLocator.toString();
				if (isElementPresent(driver, frameLocator))
					{
						WebElement element = driver.findElement(frameLocator);
						driver.switchTo().frame(element);
						log.info("The object " + tempObject + " is available");
					} else
					{
						log.error("The object " + tempObject + " is not available");
						throw new IllegalStateException("The object " + tempObject + " is not available");
					}
			}

		public static void switchToDefaultContent(WebDriver driver)
			{
				try
					{
						driver.switchTo().defaultContent();
					} catch (Exception e)
					{
						log.error("driver has not been able to switch back to default content");
						throw new IllegalStateException("driver has not been able to switch back to default content");
					}
			}

		public static void switchToWindow(WebDriver driver, String windowHandle)
			{
				driver.switchTo().window(windowHandle);
			}
		
		public static void openNewBrowserAndgotoNewUrl(WebDriver driver, String url)
		{
			try
			{
			FirefoxProfile profile = new FirefoxProfile();
			
            profile.setPreference("network.proxy.type",ProxyType.AUTODETECT.ordinal());

            profile.setPreference("network.proxy.http", "localhost");

            profile.setPreference("network.proxy.http_port", 8080);

          
            
			DesiredCapabilities ffcapability = DesiredCapabilities.firefox();
			ffcapability.setBrowserName("firefox");
			ffcapability.setPlatform(Platform.WINDOWS);
			ffcapability.setJavascriptEnabled(true);
			ffcapability.setCapability("acceptSslCerts", true);
			ffcapability.setCapability("unexpectedAlertBehaviour", "dismiss");
			ffcapability.setCapability(FirefoxDriver.PROFILE, profile);

			if(EnvironmentConfig.REMOTE_DRIVER_ENABLED.equalsIgnoreCase("yes"))
				{
				StepDefinition.driver2 = new RemoteWebDriver(new URL(EnvironmentConfig.hubUrl), ffcapability);
					StepDefinition.driver2.navigate().to(url);
				}
			else
				{
				StepDefinition.driver2 = new FirefoxDriver(ffcapability);
				StepDefinition.driver2.navigate().to(url);
				}
			driver.manage().timeouts().pageLoadTimeout(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			log.info("FireFox Driver launched");
			} catch (Exception e)
			{
				log.error("driver has not been launched");
				throw new IllegalStateException("driver has not been launched");
			}
			
			
		}

		public static void openWindow(WebDriver driver)
			{
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.open()");
			}

		public static String getRandomAlphabeticString(int count)
			{
				String random = RandomStringUtils.randomAlphabetic(count);
				log.info("Randow Alphabetic string is: " + random);
				return random;
			}

		public static void fireEventBlur(WebDriver driver, By Locator)
			{
				try
					{
						WebElement element = driver.findElement(Locator);
						JavascriptLibrary javascript = new JavascriptLibrary();
						javascript.callEmbeddedSelenium(driver, "triggerEvent", element, "blur");
						Selenium_Functions.sleepCode("2000");
					} catch (Exception e)
					{
						log.error(e);
					}
			}

		public void WaitUntillElementAvailable(WebDriver driver, By locator)
			{
				WebElement element = driver.findElement(locator);
				Selenium_Functions.sleepCode("5000");
				WaitTillVisible(driver, locator);
				WaitTillClickReady(driver, locator);
				WaitTillPresencelocated(driver, locator);
				flash(element, driver);
			}

		public static void flash(WebElement element, WebDriver driver)
			{
				JavascriptExecutor js = ((JavascriptExecutor) driver);
				String bgcolor = element.getCssValue("backgroundColor");
				for (int i = 0; i < 3; i++)
					{
						changeColor("rgb(0,200,0)", element, js);
						changeColor(bgcolor, element, js);
					}
			}

		public static void changeColor(String color, WebElement element, JavascriptExecutor js)
			{
				js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
				Selenium_Functions.sleepCode("5");
			}

		public static void WaitTillVisible(WebDriver driver, By locator)
			{
				nullifyImplicitWait(driver);
				WebDriverWait wait = new WebDriverWait(driver, EnvironmentConfig.VISIBILITY_TIMEOUT);
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
				Selenium_Functions.sleepCode("5");
				setImplicitWait(driver);
			}

		public static void WaitTillClickReady(WebDriver driver, By locator)
			{
				nullifyImplicitWait(driver);
				WebDriverWait wait = new WebDriverWait(driver, EnvironmentConfig.VISIBILITY_TIMEOUT);
				wait.until(ExpectedConditions.elementToBeClickable(locator));
				Selenium_Functions.sleepCode("5");
				setImplicitWait(driver);
			}

		public static void WaitTillPresencelocated(WebDriver driver, By locator)
			{
				nullifyImplicitWait(driver);
				WebDriverWait wait = new WebDriverWait(driver, EnvironmentConfig.VISIBILITY_TIMEOUT);
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				Selenium_Functions.sleepCode("5");
				setImplicitWait(driver);
			}

		public void waitForPageLoaded(WebDriver driver, String timeout)
			{
				ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>()
					{
						public Boolean apply(WebDriver driver)
							{
								return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
							}
					};
				long L;
				L = Long.parseLong(timeout);
				nullifyImplicitWait(driver);
				WebDriverWait wait = new WebDriverWait(driver, L);
				wait.until(expectation);
				setImplicitWait(driver);
			}

		public static boolean isElementPresentDOM(WebDriver driver, By locator)
			{
				try
					{
						driver.findElement(locator);
						Selenium_Functions.sleepCode("2000");
						return true;
					} catch (org.openqa.selenium.NoSuchElementException e)
					{
						log.error(e);
						return false;
					}

			}

		public static boolean isElementPresent(WebDriver driver, By elemLocator)
			{
				try
					{
						if (TestMainControllerTest.Browser.equalsIgnoreCase("ie8") || TestMainControllerTest.Browser.equalsIgnoreCase("ie9") || TestMainControllerTest.Browser.equalsIgnoreCase("ie10") || TestMainControllerTest.Browser.equalsIgnoreCase("ie11") || TestMainControllerTest.Browser.equalsIgnoreCase("macsafari"))
							{
								Selenium_Functions.sleepCode("3000");
							}
						driver.findElement(elemLocator);
						Selenium_Functions.sleepCode("3000");
						WaitTillVisible(driver, elemLocator);
						WaitTillPresencelocated(driver, elemLocator);
						WaitTillClickReady(driver, elemLocator);
						Thread.sleep(5);
						return true;
					} catch (Exception e)
					{
						log.error(e.getMessage());
						return false;
					}

			}

		public static boolean isNonClickableElementPresent(WebDriver driver, By elemLocator)
			{
				try
					{
						driver.findElement(elemLocator);
						try
							{
								Thread.sleep(5);
							} catch (Exception e)
							{
								log.error(e);
							}
						WaitTillVisible(driver, elemLocator);
						WaitTillPresencelocated(driver, elemLocator);
						return true;
					} catch (Exception e)
					{
						log.error(e);
						return false;
					}
			}

		public static void reSizeWindow(WebDriver driver, int x, int y)
			{
				driver.manage().window().setPosition(new Point(0, 0));
				driver.manage().window().setSize(new Dimension(x, y));
			}

		public static void isAlertPresent(WebDriver driver, By elemLocator)
			{
				String tempObject = driver.findElement(elemLocator).getText();
				try
					{
						driver.switchTo().alert();
					} catch (NoAlertPresentException e)
					{
						log.error("The object " + tempObject + " is not available");
						throw new IllegalStateException("The object " + tempObject + " is not available");
					}
			}

		public void SLEEP_CODE_TILL_NEXT_OBJ_FOUND_FN(WebDriver driver, By locator, String sleep_time)
			{
				try
					{
						driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
						int tempsltime;
						int maxsltime = Integer.parseInt(sleep_time);
						for (tempsltime = 0; tempsltime < maxsltime; tempsltime = tempsltime + 1000)
							{
								if (isElementPresentDOM(driver, locator) == true)
									{
										driver.manage().timeouts().implicitlyWait(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
										break;
									}
								Thread.sleep(1000);
							}
						driver.manage().timeouts().implicitlyWait(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
					} catch (Exception e)
					{
						driver.manage().timeouts().implicitlyWait(EnvironmentConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
						log.error(e);
					}
			}

		public static boolean isExpiryDatePatternMatch(WebDriver driver, By elemLocator)
			{
				String datePattern = "\\d{1,2}/\\d{4}";
				if (isElementPresent(driver, elemLocator))
					{
						String expirydate = Selenium_Functions.getValue(driver, elemLocator);
						if (expirydate.matches(datePattern))
							{
								return true;
							} else
							{
								return false;
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void getXpathCountAndCompareEquals(WebDriver driver, By elemLocator, int count)
			{
				if (isElementPresent(driver, elemLocator))
					{
						int size = driver.findElements(elemLocator).size();
						if (size == count)
							{
								log.info("The object " + elemLocator + " is available and " + count + " is equal to object's xpath count" + size);
							} else
							{
								log.error("The object " + elemLocator + " is available but " + count + " is not equal to object's xpath count" + size);
								throw new IllegalStateException("The object " + elemLocator + " is available but " + count + " is not equal to object's xpath count" + size);
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void pressEnterKey(WebDriver driver, By elemLocator)
			{
				driver.findElement(elemLocator).sendKeys(Keys.ENTER);
			}

		public static String getRandomnumeric(int count)
			{
				String random = RandomStringUtils.randomNumeric(count);
				log.info(random);
				return random;
			}

		public static long calculatedatedifference(Date date1, Date date2)
			{
				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance();
				calendar1.setTime(date1);
				calendar2.setTime(date2);
				long milliseconds1 = calendar1.getTimeInMillis();
				long milliseconds2 = calendar2.getTimeInMillis();
				long diff = milliseconds2 - milliseconds1;
				long diffDays = diff / (24 * 60 * 60 * 1000);
				return diffDays;
			}

		public static void fetchTextAndCompareEqualsForNonClickableElement(WebDriver driver, By elemLocator, String compareData)
			{
				String areaText;
				if (isNonClickableElementPresent(driver, elemLocator))
					{
						areaText = driver.findElement(elemLocator).getText();
						if (areaText.equalsIgnoreCase(compareData))
							{
								log.info("The object " + elemLocator + " is available and " + compareData + " is equal to object's text");
							} else
							{
								log.error("The object " + elemLocator + " is available but " + compareData + " is not equal to object's text");
								throw new IllegalStateException("The object " + elemLocator + " is available but " + compareData + " is not equal to object's text");
							}
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void pressTabKey(WebDriver driver, By elemLocator)
			{
				driver.findElement(elemLocator).sendKeys(Keys.TAB);
			}

		public static void getURLandcontainscompare(WebDriver driver, String compareData)
			{
				String url = driver.getCurrentUrl();
				if (url.contains(compareData))
					{
						log.info("User is on this url " + url + " page which contains " + compareData);
					} else
					{
						log.error("User is on this url " + url + " page which do not contains " + compareData);
						throw new IllegalStateException("User is on this url " + url + " page which do not contains " + compareData);
					}
			}

		public static boolean fetchLinkAndCompareContains(WebDriver driver, By elemLocator, String compareData)
			{
				String areaText;
				if (isElementPresent(driver, elemLocator))
					{
						areaText = driver.findElement(elemLocator).getAttribute("href");
						if (areaText.contains(compareData))
							{
								return true;
							}
					}
				return false;
			}

		public static String getText(WebDriver driver, By elemLocator)
			{
				if (driver.findElement(elemLocator).isDisplayed())
					{
						return driver.findElement(elemLocator).getText().toString();
					} else
					{
						log.info("Element Not Found. " + elemLocator.toString());
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void selectSize(WebDriver driver, By elemLocator, String selectText)
			{
				if (driver.findElement(elemLocator).isDisplayed())
					{
						driver.findElement(elemLocator).click();
					}
			}

		public static void switches_to_last_window(WebDriver driver)
			{
				Set handleSet = driver.getWindowHandles();
				int s = handleSet.size();
				log.info(s + "no. of handles");
				for (String winHandle : driver.getWindowHandles())
					{
						Selenium_Functions.switchToWindow(driver, winHandle);
					}
				Selenium_Functions.sleepCode("7000");
			}

		public static void switches_to_parent_window(WebDriver driver)
			{
				Set handleSet = driver.getWindowHandles();
				Iterator ite = handleSet.iterator();
				String parentWindowHandle = ite.next().toString();
				Selenium_Functions.switchToWindow(driver, parentWindowHandle);
			}

		public static void switches_to_previous_window(WebDriver driver)
			{
				Set handleSet = driver.getWindowHandles();
				Iterator ite = handleSet.iterator();
				String parentWindowHandle1 = ite.next().toString();
				String parentWindowHandle2 = ite.next().toString();
				Selenium_Functions.switchToWindow(driver, parentWindowHandle2);
			}

		public static void right_click(WebDriver driver, By elemLocator)
			{
				if (isElementPresent(driver, elemLocator))
					{
						WebElement wer = driver.findElement(elemLocator);
						Actions right = new Actions(driver);
						right.moveToElement(wer);
						right.contextClick(wer).build().perform();
					} else
					{
						log.error("The object " + elemLocator + " is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void mouse_hover_and_click(WebDriver driver, By elemLocator, By click_elemLocator)
			{
				if (isElementPresent(driver, elemLocator))
					{
						Actions action = new Actions(driver);
						WebElement we = driver.findElement(elemLocator);
						action.moveToElement(we).build().perform();
						try
							{
								Thread.sleep(2000);
							} catch (InterruptedException e)
							{
								log.error(e);
								e.printStackTrace();
							}
						action.moveToElement(driver.findElement(click_elemLocator)).click().build().perform();
						try
							{
								Thread.sleep(2000);
							} catch (InterruptedException e)
							{
								log.error(e);
								e.printStackTrace();
							}
					} else
					{
						log.error("The object " + elemLocator + "is not available on the current page");
						throw new IllegalStateException("The object " + elemLocator + " is not available on the current page");
					}
			}

		public static void accept_Alert(WebDriver driver)
			{
				driver.switchTo().alert().accept();
			}

		public static void contains_string_true(String src, String dest)
			{
				try
					{
						if (src.contains(dest))
							{
								log.info(src + " contains " + dest);
							} else
							{
								log.error("The object " + dest + " is not available on the current page in " + src);
								throw new IllegalStateException("The object " + dest + " is not available on the current page in " + src);
							}
					} catch (Exception e)
					{
						log.error("The object " + dest + " is not available on the current page");
						throw new IllegalStateException("The object " + dest + " is not available on the current page");
					}
			}

		public static void contains_string_false(String src, String dest)
			{
				try
					{
						if (src.contains(dest))
							{
								throw new IllegalStateException("The object " + dest + " is present in " + src);
							} else
							{
								log.info(src + " is not contains " + dest);
							}
					} catch (Exception e)
					{
						log.error("The object " + dest + " is present in " + src);
						throw new IllegalStateException("The object " + dest + " is present in " + src);
					}
			}

		public static List<WebElement> getElements(WebDriver driver, By elementlocator)
			{
				return (driver.findElements(elementlocator));
			}

		public static void click_Element_JS(WebDriver driver, By elemLocator)
			{
				if (isElementPresentDOM(driver, elemLocator))
					{
						WebElement element = driver.findElement(elemLocator);
						JavascriptExecutor executor = (JavascriptExecutor) driver;
						executor.executeScript("arguments[0].click();", element);
					}
			}

		public static void nullifyImplicitWait(WebDriver driver)
			{
				if (!TestMainControllerTest.Browser.equalsIgnoreCase("phantumjs"))
					{
						driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify
																						// implicitlyWait()
					}
			}

		public static void setImplicitWait(WebDriver driver)
			{
				if (!TestMainControllerTest.Browser.equalsIgnoreCase("phantumjs"))
					{
						driver.manage().timeouts().implicitlyWait(EnvironmentConfig.VISIBILITY_TIMEOUT, TimeUnit.SECONDS);
					}
			}

	}
