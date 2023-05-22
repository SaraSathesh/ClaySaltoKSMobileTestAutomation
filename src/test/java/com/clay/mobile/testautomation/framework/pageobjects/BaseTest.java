package com.clay.mobile.testautomation.framework.pageobjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {
	
	public static AndroidDriver<AndroidElement> driver;
	public static AndroidDriver<AndroidElement> BaseTest() throws Exception {
		System.out.println("ENTER - GSDFordPassAppTest > testEnvironmentSetUp");
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream("src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    String platformName = (String) prop.get("platformname");
	    //System.out.println (platformName);
	    
	    String automationname = (String) prop.get("automationname");
	    String appPackagename = (String) prop.get("appPackagename");
	    String appActivityname = (String) prop.get("appActivityname");
	    
	    		
	    capabilities.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
	    capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
	    //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		
	    capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,automationname);
	    capabilities.setCapability("appPackage", appPackagename);
	    capabilities.setCapability("appActivity", appActivityname);
	    capabilities.setCapability("noSign", true);
	   // driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
	    //wait = new WebDriverWait(driver, 5);
	    System.out.println("EXIT - GSDFordPassAppTest > testEnvironmentSetUp");
		driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	public static void getScreenshot(String s) throws IOException
	{
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile,new File(System.getProperty("user.dir")+"\\resources\\"+s+".png"));
		
	}

}


