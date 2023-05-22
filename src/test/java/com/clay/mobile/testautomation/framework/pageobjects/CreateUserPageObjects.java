package com.clay.mobile.testautomation.framework.pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;

//import com.stg.gsd.fordpass.poc.GSDFordPassTestDataFromExcel;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

// All the Objects in Login Page will be defined in this Class

public class CreateUserPageObjects {
	/**To call the driver object from testcase to PageObject file
	 * 
	 * @param driver
	 */
	
	// Concatenate driver
	//WebDriverWait wait;
	public CreateUserPageObjects(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		//wait = new WebDriverWait(driver, 60);
	}
	
		

	
	
	
	
		
	
	@AndroidFindBy(id="nl.moboa.myclay:id/tab_people")
	public WebElement tab_people;
	
	public WebElement getPeopleTab()
	{
		return tab_people;
	}
	
	
	@AndroidFindBy(id="nl.moboa.myclay:id/add_and_invite_tv")
	public WebElement add_and_invite_tv;
	
	public WebElement getAddAndInvite()
	{
		return add_and_invite_tv;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/subtitle_tv")
	public WebElement subtitle_tv;
	
	public WebElement getCreateUser()
	{
		return subtitle_tv;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/first_name_et")
	public WebElement first_name_et;
	
	public WebElement getFirstName()
	{
		
		return first_name_et;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/last_name_et")
	public WebElement last_name_et;
	
	public WebElement getLastName()
	{
		
		return last_name_et;
	}
	@AndroidFindBy(id="nl.moboa.myclay:id/alias_et")
	public WebElement alias_et;
	
	public WebElement getAliasName()
	{
		
		return alias_et;
	}
	
	
	@AndroidFindBy(id="nl.moboa.myclay:id/positive_button")
	public WebElement positive_button;
	
	public WebElement getNextButton()
	{
		
		return positive_button;
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
