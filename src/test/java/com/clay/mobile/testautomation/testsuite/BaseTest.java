package com.clay.mobile.testautomation.testsuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseTest implements IReporter {

	public static AndroidDriver<AndroidElement> driver;
	
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentHtmlReporter htmlReporter;
	public static AndroidDriver<AndroidElement> BaseTest()  throws Exception {
		System.out.println("ENTER - ClaySaltoKSMobileAutomation > testEnvironmentSetUp");
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
	
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,automationname);
		capabilities.setCapability("appPackage", appPackagename);
		capabilities.setCapability("appActivity", appActivityname);
		capabilities.setCapability("chromedriverExecutable","C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver.exe");
		
		capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
	
		System.out.println("EXIT - ClaySaltoKSMobileAutomation > testEnvironmentSetUp");
		driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"),capabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	

	@BeforeSuite
	public void setUpReport()
	{
		 htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\resources\\Report\\ExtentReport_"+Helper.getCurrentDateTime()+".html");
		report=new ExtentReports();
		report.attachReporter(htmlReporter);
		
		htmlReporter.config().setDocumentTitle("Clay-SaltoKS Mobile Automation Test Report");
		htmlReporter.config().setReportName("Clay-SaltoKS Mobile Automation Test Results");

		report.setSystemInfo("Environment", "SaltoKS Prod-Test App");
		report.setSystemInfo("OS","Android");
		report.setSystemInfo("Report Name","Clay-SaltoKS Mobile Automation Test Results");




	}
	
	
	
	public String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];

			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}



	@AfterMethod
	public void Completion(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			
			logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver, "Test")).build());
			
			
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger.pass(MarkupHelper.createLabel(result.getName()+" - Passed", ExtentColor.GREEN));
		}
		

	}
	
	@AfterClass
	public void Teardown()
	{
		
		driver.quit();
	}
	
	@AfterSuite
	public void closeReport()
	{
		report.flush();
	}
	

	
}


