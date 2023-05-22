package com.clay.mobile.testautomation.framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

//import com.stg.gsd.fordpass.poc.GSDFordPassTestDataFromExcel;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All the Objects in Login Page will be defined in this Class

public class LockPageObjects {
	/**To call the driver object from testcase to PageObject file
	 * 
	 * @param driver
	 */
	
	// Concatenate driver
	//WebDriverWait wait;
	public LockPageObjects(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		//wait = new WebDriverWait(driver, 60);
	}
	
		
	
	
	@AndroidFindBy(id="nl.moboa.myclay:id/lock_image")
	public WebElement lock_image;
	
	public WebElement getlockRemoteButton()
	{
		return lock_image;
	}
	
	@FindBy(xpath="//*[@class='android.widget.Button']")
	public static List<WebElement> Widget_Button;
	
	public List<WebElement> getsettingButton()
	{
		
		return Widget_Button;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/eom_setting_view")
	public WebElement eom_setting_view;
	
	public WebElement getEomSetting()
	{
		return eom_setting_view;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/eom_switch")
	public WebElement eom_switch;
	
	public WebElement geteomswitch()
	{
		return eom_switch;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/always_cb")
	public WebElement always_cb;
	
	public WebElement getAlwaysButton()
	{
		
		return always_cb;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/positive_button")
	public WebElement positive_button;
	
	public WebElement getSaveButton()
	{
		
		return positive_button;
	}
	@AndroidFindBy(id="nl.moboa.myclay:id/status_label")
	public WebElement status_label;
	
	public WebElement getEOMLabelStatus()
	{
		
		return status_label;
	}
	
	
	
	@AndroidFindBy(id="nl.moboa.myclay:id/email_et")
	public WebElement email_et;
	
	public WebElement getEmailId()
	{
		
		return email_et;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/user_fullname")
	public List<AndroidElement> user_fullname;
	
	public List<AndroidElement> getFullName()
	{
		
		return user_fullname;
	}
}
