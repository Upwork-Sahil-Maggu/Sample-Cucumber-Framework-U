package com.sahil.mtours.Test;
import org.junit.runner.RunWith;

import cucumber.api.junit.*;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@RunWith(Cucumber.class)
@Cucumber.Options(

features = "src/main/java/com/sahil/mtours/Features",

format = { "pretty", "html:target/cucumber-htmlreport", "json-pretty:target/cucumber-json-report.json", "junit:target/cucumber-junit-report.xml" },
tags = {"@MTOURS-1,@MTOURS-2"}

)

public class RunCukesTest_CH extends AbstractTestNGCucumberTests
	{

	}
