package com.clay.mobile.testautomation.testsuite;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
public class AppScreenshots extends BaseTest {
	
	public static String getScreenshot(String screenshotName) throws IOException
	
	{
		File sourceFile =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String imgPath ="./resources/Report/Screenshots/" + screenshotName +".png";
		File path = new File(imgPath);
		FileUtils.copyFile(sourceFile,path);
		return imgPath;
	}

}
