package com.clay.mobile.testautomation.framework.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

//import com.stg.gsd.fordpass.poc.GSDFordPassTestDataFromExcel;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All the Objects in Login Page will be defined in this Class

public class LogoutPageObjects {
	/**To call the driver object from testcase to PageObject file
	 * 
	 * @param driver
	 */
	
	// Concatenate driver
	//WebDriverWait wait;
	public LogoutPageObjects(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		//wait = new WebDriverWait(driver, 60);
	}
	
		
	@AndroidFindBy(id="nl.moboa.myclay:id/tab_setting")
	public WebElement tab_setting;
	
	public WebElement getSettingsIcon()
	{
		return tab_setting;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/logout_view")
	public WebElement logout_view;
	
	public WebElement getLogoutButton()
	{
		return logout_view;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/login_info")
	public WebElement login_info;
	
	public WebElement getLogoutConfirmation()
	{
		return login_info;
	}
	
	
	

}
