package com.clay.mobile.testautomation.testsuite;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

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

import com.clay.mobile.testautomation.framework.pageobjects.LoginPageObjects;


import com.clay.mobile.testautomation.framework.pageobjects.LogoutPageObjects;
import com.clay.mobile.testautomation.framework.pageobjects.LockPageObjects;


public class SaltoKSLockEnableEOM extends BaseTest {
	private String sTestCaseName;

	private int iTestCaseRow;

	public ExtentTest test;
	ExtentHtmlReporter htmlReporter;
	private SoftAssert softAssert = new SoftAssert();

	/**
	 * DataProvider method to set up the Test Data from Excel file for Login Test
	 * Case
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

		// Verify if Login Button is available in the App Launch Page and Click the
		// button

		logger.info("loginPageUIElementTest: Verified the existence of Continue Button in the SaltoKS Launch Page");
		Thread.sleep(10);

		loginButton = login.getSalToKSLoginButton();
		if (loginButton != null) {
			if (!loginButton.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Login Button' is not displayed in the Launch Page");
				logger.fail("The 'Login Button' is not displayed in the Launch Page");

			}

			loginButton.click();

			logger.pass("loginTest: Login Button is successfully clicked");
		} else {
			System.out.println("Login Button Does Not Exist on SaltoKS LaunchPage");
			Assert.assertTrue(false);
		}

		// Verify if Email Address field is available enter the email address to login

		emailAddressField = login.getemailid();
		if (emailAddressField != null) {
			if (!emailAddressField.isDisplayed()) {
				softAssert.assertTrue(false, "The 'emailAddressField' is not displayed");
				logger.fail("The 'emailAddressField' is not displayed");

			}

			emailAddressField.sendKeys(username);

			logger.pass("loginTest: email address is successfully entered");
		} else {
			System.out.println(" email address field Does Not Exist");
			Assert.assertTrue(false);
		}

		// Verify if Continue Button is available click on the Continue Button

		continueButton = login.getContinueButton();
		if (continueButton != null) {
			if (!continueButton.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Continue Button' is not displayed");
				logger.fail("The 'continueButton' is not displayed");

			}

			continueButton.click();

			logger.pass("loginTest: Continue Button is successfully clicked");
		} else {
			System.out.println("Continue Button Does Not Exist");
			Assert.assertTrue(false);
		}
		// Verify if Access Site page is launched directly and proceed with access site
		// selection

		selectSite = login.getSite();
		if (selectSite.size() > 0 == true) {

			selectSite.get(0).click();

			logger.pass("Login Test: The 'Site Access' is successfully clicked");

			skipButton = login.getskipButton();
			wait.until(ExpectedConditions.visibilityOf(skipButton));

			if (skipButton != null) {
				if (!skipButton.isDisplayed()) {
					softAssert.assertTrue(false, "The 'Skip Button' is not displayed");
					logger.fail("The 'Skip Button' is not displayed");

				}

				skipButton.click();

				logger.pass("Create Test: The 'Skip Button' is successfully clicked");
			} else {
				System.out.println("The 'Skip Button' Does Not Exist");
				Assert.assertTrue(false);
			}

		} else {

			/**
			 * Check if page gets redirected to Webview and change the context to Webview
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
			// Verify if Password field is available enter the Password to login

			passwordTextField = login.getPassword();
			if (passwordTextField != null) {
				if (!passwordTextField.isDisplayed()) {
					softAssert.assertTrue(false, "The 'passwordTextField' is not displayed");
					logger.fail("The 'passwordTextField' is not displayed");

				}

				passwordTextField.sendKeys(password);

				logger.pass("loginTest: The 'Password' is successfully entered");
			} else {
				System.out.println(" The 'Password'field Does Not Exist");
				Assert.assertTrue(false);
			}

			// Verify if Submit Button is available and click on the Submit Button

			submitButton = login.getSubmitButton();
			if (submitButton != null) {
				if (!submitButton.isDisplayed()) {
					softAssert.assertTrue(false, "The 'Submit Button' is not displayed");
					logger.fail("The 'Submit Button' is not displayed");

				}

				submitButton.click();

				logger.pass("loginTest: The 'Submit Button' is successfully clicked");
			} else {
				System.out.println("The 'Submit Button' Does Not Exist");
				Assert.assertTrue(false);
			}

			Thread.sleep(7);
			// Switch back to Native App Context after entering credentials
			driver.context("NATIVE_APP");

			// Verify if Site Access Page is launched and click on the available site
			site1 = login.getSite1();

			if (site1 != null) {
				if (!site1.isDisplayed()) {
					softAssert.assertTrue(false, "The 'Site Access' is not displayed");
					logger.fail("The 'Site Access' is not displayed");

				}

				site1.click();

				logger.pass("Create Test: The 'Site Access' is successfully clicked");
			} else {
				System.out.println("The 'Site Access' Does Not Exist");
				Assert.assertTrue(false);
			}

			// Verify if skip button is available and click on skip button
			skipButton = login.getskipButton();
			wait.until(ExpectedConditions.visibilityOf(skipButton));

			if (skipButton != null) {
				if (!skipButton.isDisplayed()) {
					softAssert.assertTrue(false, "The 'Skip Button' is not displayed");
					logger.fail("The 'Skip Button' is not displayed");

				}

				skipButton.click();

				logger.pass("Create Test: The 'Skip Button' is successfully clicked");
			} else {
				System.out.println("The 'Skip Button' Does Not Exist");
				Assert.assertTrue(false);
			}

		}

	}

	@Test(priority = 2, enabled = true)
	public void enableLockEOM() throws InterruptedException {

		logger = report.createTest("SaltoKS - Enable Easy Office Mode(EOM) for Available Lock");
		logger.info("Validation Steps to Enable Easy Office Mode(EOM) for Available Lock");
		LockPageObjects lockObj = new LockPageObjects(driver);

		WebElement lockRemoteButton = null;
		List<WebElement> settingButton = null;
		WebElement eomSettingButton = null;
		WebElement eomSwitchButton = null;
		WebElement alwaysButton = null;
		WebElement saveButton = null;
		WebElement eomLabelStatus = null;
		String eomLabelStatusUpdated = null;

		// Verify if Remote Lock field is available and click on the Lock

		lockRemoteButton = lockObj.getlockRemoteButton();

		if (lockRemoteButton != null) {
			if (!lockRemoteButton.isDisplayed()) {
				softAssert.assertTrue(false, "The 'Available Lock ' is not displayed");
				logger.fail("The available lock is not displayed");

			}

			lockRemoteButton.click();

			logger.pass("Enable Office Mode Test: The 'Available Lock'  is successfully clicked");
		} else {
			System.out.println("No Lock Exist under Locks Section ");
			Assert.assertTrue(false);
		}

		// Verify if Lock Setting icon button is available and click on the Settings
		// Icon button

		settingButton = lockObj.getsettingButton();

		if (settingButton != null) {

			settingButton.get(1).click();

			logger.pass("Enable Office Mode Test: The 'Setting' Icon Button is successfully clicked");
		} else {
			System.out.println("The 'Setting'Icon Button Does Not Exist");
			Assert.assertTrue(false);
			logger.fail("The 'EOM Setting' Icon button is not displayed");
		}

		// Verify if Status of the Easy Office Mode is already Enabled

		eomLabelStatus = lockObj.getEOMLabelStatus();
		String EOMStatus = eomLabelStatus.getText();
		if (EOMStatus.equalsIgnoreCase("ENABLED")) {

			logger.info("The 'EOM' status is already Enabled");

			// Traverse back to Logout

			driver.pressKey(new KeyEvent(AndroidKey.BACK));
			driver.pressKey(new KeyEvent(AndroidKey.BACK));

		}

		else {

			// Proceed with EOM Status Enable Steps

			eomSettingButton = lockObj.getEomSetting();

			if (eomSettingButton != null) {
				if (!eomSettingButton.isDisplayed()) {
					softAssert.assertTrue(false, "The 'EOM Setting' button is not displayed");
					logger.fail("The 'EOM Setting'button is not displayed");

				}

				eomSettingButton.click();

				logger.pass("Enable Office Mode Test: The 'EOM Setting'button is successfully clicked");
			} else {
				System.out.println("The 'Create User'button Does Not Exist");
				Assert.assertTrue(false);
			}

			// Verify if Easy Office Mode switch button is available and turn the switch to
			// ON status

			eomSwitchButton = lockObj.geteomswitch();
			if (eomSwitchButton != null) {
				if (!eomSwitchButton.isDisplayed()) {
					softAssert.assertTrue(false, "The 'EOM Switch' is not displayed");
					logger.fail("The 'EOM Switch'  is not displayed");

				}

				eomSwitchButton.click();

				logger.pass("Enable Office Mode Test: The 'EOM Switch' is successfully turned On");
			} else {
				System.out.println("The 'EOM Switch' field  Does Not Exist");
				Assert.assertTrue(false);
			}

			// Verify if Always Option is available and select the Always Option

			alwaysButton = lockObj.getAlwaysButton();
			if (alwaysButton != null) {
				if (!alwaysButton.isDisplayed()) {
					softAssert.assertTrue(false, "The 'Always' option is not displayed");
					logger.fail("The 'Always' option  is not displayed");

				}

				alwaysButton.click();

				logger.pass("Enable Office Mode Test: The 'Always' option is successfully selected");
			} else {
				System.out.println("The 'Always' option Does Not Exist");
				Assert.assertTrue(false);
			}

			// Verify if Save Button is available and click on Save Button

			saveButton = lockObj.getSaveButton();
			if (saveButton != null) {
				if (!saveButton.isDisplayed()) {
					softAssert.assertTrue(false, "The 'Save' button is not displayed");
					logger.fail("The 'Save' button is not displayed");

				}

				saveButton.click();

				logger.pass("Enable Office Mode Test: The 'Save' button is successfully clicked");
			} else {
				System.out.println("The The 'Save' button  Does Not Exist");
				Assert.assertTrue(false);
			}

			// Verify if Status of the Easy Office Mode is updated as Enabled

			eomLabelStatusUpdated = lockObj.getEOMLabelStatus().getText();

			if (eomLabelStatusUpdated != null) {

				softAssert.assertTrue(false, "The 'EOM' status label is displayed");
				logger.pass("The 'EOM' status label is not displayed");

				Assert.assertEquals(eomLabelStatusUpdated, "ENABLED");
				logger.pass("Enable Office Mode Test: The 'EOM' status label is successfully updated as Enabled");
			} else {
				System.out.println("The 'EOM' status label Does Not Exist");
				Assert.assertTrue(false);
			}

			// Traverse back to Logout

			driver.pressKey(new KeyEvent(AndroidKey.BACK));
			driver.pressKey(new KeyEvent(AndroidKey.BACK));
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

		// Verify if Settings Button Icon is available and click on the Settings Button
		// Icon

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

		// Scroll down till 'Log Out' option is visible and click on 'Log Out' option

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

		// Verify if the application is successfully logged out

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
