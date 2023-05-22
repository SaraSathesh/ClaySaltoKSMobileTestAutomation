package com.clay.mobile.testautomation.testsuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.Set;
import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.clay.mobile.testautomation.framework.pageobjects.CreateUserPageObjects;
import com.clay.mobile.testautomation.framework.pageobjects.LoginPageObjects;
import com.clay.mobile.testautomation.framework.pageobjects.LogoutPageObjects;


public class SaltoKSCreateUser extends BaseTest {
	private String sTestCaseName;

	private int iTestCaseRow;

	public ExtentTest test;
	ExtentHtmlReporter htmlReporter;
	private SoftAssert softAssert = new SoftAssert();

	/**
	 * DataProvider method to set up the Test Data from Excel file for Login Test Case
	 * 
	 * @return
	 * @throws Exception
	 */

	@DataProvider(name = "appLogin")

	public Object[][] Authentication() throws Exception {

		// Setting up the Test Data Excel file

		ClaySaltoKSTestDataFromExcel.setExcelFile("src//test//resources//TestData.xlsx", "Credentials");

		sTestCaseName = this.toString();

		// From above method we get long test case name including package and class name
		// etc.

		// The below method will refine your test case name, exactly the name use have
		// used

		sTestCaseName = ClaySaltoKSTestDataFromExcel.getTestCaseName(this.toString());

		// Fetching the Test Case row number from the Test Data Sheet

		// Getting the Test Case name to get the TestCase row from the Test Data Excel
		// sheet

		iTestCaseRow = ClaySaltoKSTestDataFromExcel.getRowContains(sTestCaseName, 0);

		Object[][] testObjArray = ClaySaltoKSTestDataFromExcel.getTableArray("src//test//resources//TestData.xlsx",
				"Credentials", iTestCaseRow);

		return (testObjArray);

	}

	@Test(priority = 1, dataProvider = "appLogin", enabled = true)
	public void loginTest(String username, String password) throws Exception {
		System.out.println("ENTER - Application");
		AndroidDriver<AndroidElement> driver = BaseTest();
		logger = report.createTest("Login to SaltoKS Mobile Application");

		LoginPageObjects login = new LoginPageObjects(driver);
		WebElement loginButton = null;
		WebElement emailAddressField = null;
		WebElement continueButton = null;
		WebElement passwordTextField = null;
		WebElement submitButton = null;
		List<WebElement> selectSite = null;
		WebElement site1 = null;
		WebElement skipButton = null;
		WebDriverWait wait = new WebDriverWait(driver, 40);
		
		//Verify if Login Button is available in the App Launch Page and Click the button
		
		
		logger.info("loginPageUIElementTest: Verified the existence of Continue Button in the SaltoKS Launch Page");
		Thread.sleep(10);
	
		loginButton = login.getSalToKSLoginButton();
		    if (loginButton != null)
	    {
	        if(!loginButton.isDisplayed())            {
	        	softAssert.assertTrue(false,"The 'Login Button' is not displayed in the Launch Page");
	            logger.fail("The 'Login Button' is not displayed in the Launch Page");
	            
	        }
	        
	        loginButton.click();
	        
	        
	        logger.pass("loginTest: Login Button is successfully clicked");
	    }
	    else
	    {
	        System.out.println("Login Button Does Not Exist on SaltoKS LaunchPage");
	        Assert.assertTrue(false);
	    }
	    
	  //Verify if Email Address field  is available enter the email address to login
		    
	    emailAddressField = login.getemailid();
	    if (emailAddressField != null)
	    {
	        if(!emailAddressField.isDisplayed())            {
	        	softAssert.assertTrue(false,"The 'emailAddressField' is not displayed");
	            logger.fail("The 'emailAddressField' is not displayed");
	            
	        }
	        
	        emailAddressField.sendKeys(username);
	        
	        
	        logger.pass("loginTest: email address is successfully entered");
	    }
	    else
	    {
	        System.out.println(" email address field Does Not Exist");
	        Assert.assertTrue(false);
	    }
	    
	    //Verify if Continue Button  is available click on the Continue Button
	    
	    continueButton = login.getContinueButton();
	    if (continueButton != null)
	    {
	        if(!continueButton.isDisplayed())            {
	        	softAssert.assertTrue(false,"The 'Continue Button' is not displayed");
	            logger.fail("The 'continueButton' is not displayed");
	            
	        }
	        
	        continueButton.click();
	        
	        
	        logger.pass("loginTest: Continue Button is successfully clicked");
	    }
	    else
	    {
	        System.out.println("Continue Button Does Not Exist");
	        Assert.assertTrue(false);
	    }
	    
	    //Verify if Access Site page is launched directly and proceed with access site selection
	    
	    selectSite = login.getSite();
		if (selectSite.size() > 0==true) {
			
			selectSite.get(0).click();
			

			logger.pass("Login Test: The 'Site Access' is successfully clicked");
			
			
			skipButton = login.getskipButton();
		    wait.until(ExpectedConditions.visibilityOf(skipButton));
		   
		    if (skipButton != null)
		    {
		        if(!skipButton.isDisplayed())            {
		        	softAssert.assertTrue(false,"The 'Skip Button' is not displayed");
		            logger.fail("The 'Skip Button' is not displayed");
		            
		        }
		        
		        skipButton.click();
		        
		        
		        logger.pass("Create Test: The 'Skip Button' is successfully clicked");
		    }
		    else
		    {
		        System.out.println("The 'Skip Button' Does Not Exist");
		        Assert.assertTrue(false);
		    }
			
		
		} 
		else {
			
		/** Check if page gets redirected to Webview and change the context to Webview
		 * 
		 */
		Set<String> contexts = driver.getContextHandles();
		Thread.sleep(5);

		for (String contextName : contexts) {
			if (contextName.contains("WEBVIEW")) {
				System.out.println("Context Name is " + contextName);
				
				driver.context(contextName);
				break;
			}

		}
		 //Verify if Password field  is available enter the Password to login
		
		passwordTextField = login.getPassword();
	    if (passwordTextField != null)
	    {
	        if(!passwordTextField.isDisplayed())            {
	        	softAssert.assertTrue(false,"The 'passwordTextField' is not displayed");
	            logger.fail("The 'passwordTextField' is not displayed");
	            
	        }
	        
	        passwordTextField.sendKeys(password);
	        
	        
	        logger.pass("loginTest: The 'Password' is successfully entered");
	    }
	    else
	    {
	        System.out.println(" The 'Password'field Does Not Exist");
	        Assert.assertTrue(false);
	    }
		
	  //Verify if Submit Button  is available  and click on the Submit Button
	    
	    submitButton = login.getSubmitButton();
	    if (submitButton != null)
	    {
	        if(!submitButton.isDisplayed())            {
	        	softAssert.assertTrue(false,"The 'Submit Button' is not displayed");
	            logger.fail("The 'Submit Button' is not displayed");
	            
	        }
	        
	        submitButton.click();
	        
	        
	        logger.pass("loginTest: The 'Submit Button' is successfully clicked");
	    }
	    else
	    {
	        System.out.println("The 'Submit Button' Does Not Exist");
	        Assert.assertTrue(false);
	    }
	    
	    
	    Thread.sleep(7);
	    //Switch back to Native App Context after entering credentials
	    driver.context("NATIVE_APP");
	    
	    
	    //Verify if Site Access Page is launched and click on the available site
	    site1=login.getSite1();
	   
	    if (site1 != null)
	    {
	        if(!site1.isDisplayed())            {
	        	softAssert.assertTrue(false,"The 'Site Access' is not displayed");
	            logger.fail("The 'Site Access' is not displayed");
	            
	        }
	        
	        site1.click();
	        
	        
	        logger.pass("Create Test: The 'Site Access' is successfully clicked");
	    }
	    else
	    {
	        System.out.println("The 'Site Access' Does Not Exist");
	        Assert.assertTrue(false);
	    }
	
	    //Verify if skip button  is available and click on skip button
	    skipButton = login.getskipButton();
	    wait.until(ExpectedConditions.visibilityOf(skipButton));
	   
	    if (skipButton != null)
	    {
	        if(!skipButton.isDisplayed())            {
	        	softAssert.assertTrue(false,"The 'Skip Button' is not displayed");
	            logger.fail("The 'Skip Button' is not displayed");
	            
	        }
	        
	        skipButton.click();
	        
	        
	        logger.pass("Create Test: The 'Skip Button' is successfully clicked");
	    }
	    else
	    {
	        System.out.println("The 'Skip Button' Does Not Exist");
	        Assert.assertTrue(false);
	    }
		
	}

	}
	
	/**
	 * DataProvider method to set up the Test Data from Excel file for Create User Test Case
	 * 
	 * @return
	 * @throws Exception
	 */

	@DataProvider(name = "CreateUser")

	public Object[][] UserCreation() throws Exception {

		// Setting up the Test Data Excel file

		ClaySaltoKSTestDataFromExcel.setExcelFile("src//test//resources//TestData.xlsx", "UserDetails");

		sTestCaseName = this.toString();

		// From above method we get long test case name including package and class name
		// etc.

		// The below method will refine your test case name, exactly the name use have
		// used

		sTestCaseName = ClaySaltoKSTestDataFromExcel.getTestCaseName(this.toString());

		// Fetching the Test Case row number from the Test Data Sheet

		// Getting the Test Case name to get the TestCase row from the Test Data Excel
		// sheet

		iTestCaseRow = ClaySaltoKSTestDataFromExcel.getRowContains(sTestCaseName, 0);

		Object[][] testObjArray = ClaySaltoKSTestDataFromExcel.getTableArray("src//test//resources//TestData.xlsx",
				"UserDetails", iTestCaseRow);

		return (testObjArray);

	}

	@Test(priority = 2, dataProvider = "CreateUser", enabled = false)
	public void CreateUser(String fname, String lname) throws InterruptedException {
		System.out.println("Create/Invite User");
		logger = report.createTest("SaltoKS - Create User/Invite");
		logger.info("Validation Steps to Create New User");
		CreateUserPageObjects createUserObj = new CreateUserPageObjects(driver);
		
		WebElement peopleButton = null;
		WebElement addInvite = null;
		WebElement createUser = null;
		WebElement firstname = null;
		WebElement lastname = null;
		WebElement aliasname = null;
		WebElement nextButton = null;
	
		List<AndroidElement> userfullname = null;

		WebDriverWait wait = new WebDriverWait(driver, 50);

		 //Verify if People Button Icon is available  and click on the People Button Icon 
		
		peopleButton = createUserObj.getPeopleTab();
		wait.until(ExpectedConditions.visibilityOf(peopleButton));
		if (peopleButton != null) {
			if (!peopleButton.isDisplayed()) {
				softAssert.assertTrue(false, "The 'People Tab' is not displayed");
				logger.fail("The 'People Tab' is not displayed");

			}

			peopleButton.click();

			logger.pass("Create User Test: The 'People Tab' is successfully clicked");
		} else {
			System.out.println("The 'People Tab' Does Not Exist");
			Assert.assertTrue(false);
		}

		 //Verify if Add/Invite User button is available  and click on the Add/Invite User button
		
		addInvite = createUserObj.getAddAndInvite();
		wait.until(ExpectedConditions.visibilityOf(addInvite));
		if (addInvite != null) {
			if (!addInvite.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Add/Invite User' is not displayed");
				logger.fail("The 'Add/Invite User' is not displayed");

			}

			addInvite.click();

			logger.pass("Create User Test:: The 'Add/Invite User' slab is successfully clicked");
		} else {
			System.out.println("The 'Add/Invite User' Does Not Exist");
			Assert.assertTrue(false);
		}

		 //Verify if Create User is available  and click on the Create User
		
		createUser = createUserObj.getCreateUser();
		
		if (createUser != null) {
			if (!createUser.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Create User' button is not displayed");
				logger.fail("The 'Create User'button is not displayed");

			}

			createUser.click();

			logger.pass("Create User Test:: The 'Create User'button is successfully clicked");
		} else {
			System.out.println("The 'Create User'button Does Not Exist");
			Assert.assertTrue(false);
		}
		
		 //Verify if First name field  is available  and enter first name in the field
		
		firstname = createUserObj.getFirstName();
		if (firstname != null) {
			if (!firstname.isDisplayed()) {
				softAssert.assertTrue(false, "The 'First Name' field is not displayed");
				logger.fail("The 'First Name' field  is not displayed");

			}

			firstname.sendKeys(fname);

			logger.pass("Create User Test:: The 'First Name'   is successfully entered");
		} else {
			System.out.println("The 'First Name' field  Does Not Exist");
			Assert.assertTrue(false);
		}

		 //Verify if Last name field  is available  and enter Last name in the field
		
		lastname = createUserObj.getLastName();
		if (lastname != null) {
			if (!lastname.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Last Name' field is not displayed");
				logger.fail("The 'Last Name' field  is not displayed");

			}

			lastname.sendKeys(lname);

			logger.pass("Create User Test:: The 'Last Name'  is successfully entered");
		} else {
			System.out.println("The 'Last Name' field  Does Not Exist");
			Assert.assertTrue(false);
		}

		 //Verify if Alias name field  is available  and enter Alias name in the field
		
		aliasname = createUserObj.getAliasName();
		if (aliasname != null) {
			if (!aliasname.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Alias Name' field is not displayed");
				logger.fail("The 'Alias Name' field  is not displayed");

			}

			aliasname.sendKeys("ClayUser");

			logger.pass("Create User Test:: The 'Alias Name'  is successfully entered");
		} else {
			System.out.println("The 'Alias Name' field  Does Not Exist");
			Assert.assertTrue(false);
		}

		 //Verify if 'Next'  is available  and Click on the 'Next' button
		nextButton = createUserObj.getNextButton();

		if (nextButton != null) {
			if (!nextButton.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Next' button is not displayed");
				logger.fail("The 'Next'button is not displayed");

			}

			nextButton.click();

			logger.pass("Create User Test: The 'Next'button is successfully clicked");
		} else {
			System.out.println("The 'Next'button Does Not Exist");
			Assert.assertTrue(false);
		}

		nextButton = createUserObj.getNextButton();

		if (nextButton != null) {
			if (!nextButton.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Next' button is not displayed");
				logger.fail("The 'Next'button is not displayed");

			}

			nextButton.click();

			logger.pass("Create User Test: The 'Next'button is successfully clicked");
		} else {
			System.out.println("The 'Next'button Does Not Exist");
			Assert.assertTrue(false);
		}

		nextButton = createUserObj.getNextButton();

		if (nextButton != null) {
			if (!nextButton.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Next' button is not displayed");
				logger.fail("The 'Next'button is not displayed");

			}

			nextButton.click();
			Thread.sleep(5);
			nextButton.click();
			logger.pass("Create User Test: The 'Next'button is successfully clicked");
		} else {
			System.out.println("The 'Next'button Does Not Exist");
			Assert.assertTrue(false);
		}

		// Verify if the created username is available in the existing user list
		
		userfullname = createUserObj.getFullName();
        String expectedUserName = fname+ ' ' +lname;
		for (AndroidElement uname : userfullname) {
			if (uname.getText().contains(fname)) {

				
				
				String displayedName=uname.getText();
				Assert.assertEquals(displayedName, expectedUserName );
				logger.pass("Create User Test: New User is successfully created");
				break;
			}
			

		}
		
		

	}

	@Test(priority = 3, enabled = true)
	public void logOut() throws InterruptedException {
	
		logger = report.createTest("SaltoKS - Logout Mobile App");
		logger.info("Validation Steps to Log out of the Application");
		LogoutPageObjects logoutObj = new LogoutPageObjects(driver);
		WebElement settingsIcon = null;
		WebElement logoutbutton = null;
		WebElement LogoutConfirmation = null;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		settingsIcon = logoutObj.getSettingsIcon();

		//Verify if Settings Button Icon is available  and click on the Settings Button Icon
		
		if (settingsIcon != null) {
			if (!settingsIcon.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Settings Icon' is not displayed");
				logger.fail("The 'Settings Icon' is not displayed");

			}

			settingsIcon.click();

			logger.pass("LogOut Test: The 'Settings Icon' is successfully clicked");
		} else {
			System.out.println("The 'Settings Icon' Does Not Exist");
			Assert.assertTrue(false);
		}

		//Scroll down till 'Log Out' option is visible and click on 'Log Out' option
		
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"Log out\").instance(0))")
				.click();

		logoutbutton = logoutObj.getLogoutButton();
		if (logoutbutton != null) {
			if (!logoutbutton.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Log Out'button is not displayed");
				logger.fail("The 'Log Out'button is not displayed");

			}

			logoutbutton.click();

			logger.pass("LogOut: The'Log Out'button is successfully clicked");
		} else {
			System.out.println("The 'Log Out'button Does Not Exist");
			Assert.assertTrue(false);
		}
		
		//Verify if the application is successfully logged out
		
		LogoutConfirmation = logoutObj.getLogoutConfirmation();
		wait.until(ExpectedConditions.visibilityOf(LogoutConfirmation));
		String Logout = LogoutConfirmation.getText();
		if (Logout.contains("You may be redirected to login.")) {
			
			logger.pass("LogOut Test: The application is successfully logged out");
		} else {
			logger.fail("LogOut Test: The application is not logged out");
		}
	}
}
