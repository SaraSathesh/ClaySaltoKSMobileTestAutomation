package com.clay.mobile.testautomation.testsuite;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Helper extends BaseTest {
	
	public static String captureScreenshot(AndroidDriver<AndroidElement> driver,String s) throws Exception
	{
		// AndroidDriver<AndroidElement> driver = BaseTest();
		 File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 
		 String screenshotPath = System.getProperty("user.dir")+"\\resources\\Report\\Screenshots\\"+s+ Helper.getCurrentDateTime()+".png";
		 FileHandler.copy(src, new File(screenshotPath));
		return screenshotPath;
	}

	public static String getCurrentDateTime()
	{
		DateFormat customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}
}
