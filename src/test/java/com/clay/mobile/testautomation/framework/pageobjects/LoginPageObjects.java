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

public class LoginPageObjects {
	/**To call the driver object from testcase to PageObject file
	 * 
	 * @param driver
	 */
	
	// Concatenate driver
	//WebDriverWait wait;
	public LoginPageObjects(AndroidDriver<AndroidElement> driver)
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		//wait = new WebDriverWait(driver, 60);
	}
	
		
	@AndroidFindBy(id="nl.moboa.myclay:id/login_button")
	public WebElement login_button;
	
	public WebElement getSalToKSLoginButton()
	{
		return login_button;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/input")
	public WebElement input;
	
	public WebElement getemailid()
	{
		return input;
	}
	
	@AndroidFindBy(id="nl.moboa.myclay:id/button")
	public WebElement button;
	
	public WebElement getContinueButton()
	{
		return button;
	}
	
	@AndroidFindBy(id="Password")
	public WebElement Password;
	
	public WebElement getPassword()
	{
		return Password;
	}
	
	@AndroidFindBy(id="SubmitButton")
	public WebElement SubmitButton;
	
	public WebElement getSubmitButton()
	{
		return SubmitButton;
	}
	
	@FindBy(xpath="//android.view.ViewGroup[@content-desc='site_1']")
	public List<WebElement> site;
	
	public List<WebElement> getSite()
	{
		return site;
	}
	
	@FindBy(xpath="//android.view.ViewGroup[@content-desc='site_1']")
	public WebElement site1;
	
	public WebElement getSite1()
	{
		return site1;
	}
	@AndroidFindBy(id="nl.moboa.myclay:id/skip_button")
	public WebElement skip_button;
	
	public WebElement getskipButton()
	{
		return skip_button;
	}
	
}
