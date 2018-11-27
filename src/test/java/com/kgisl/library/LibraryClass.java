package com.kgisl.library;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.kgisl.baseclass.BaseClass;
import com.kgisl.pageElements.HomePage;
import com.kgisl.pageElements.LoginPage;

import cucumber.api.java.en.Given;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import org.openqa.selenium.interactions.Actions;

public class LibraryClass extends BaseClass {

	// public static final boolean isreturnGridValueinboolean = false;
	public String givenValueInGrid;
	public static int row;
	public WebElement rownumber;
	public WebElement columnNumber;
	public By loadingImg = By.cssSelector("div[id='page-blocker'][style='display: block;']");
	public By preloader = By.id("preloader");
	public By gridClient = By.cssSelector("div[ui-grid='motorSearchGrid']");
	public By gridClient1 = By.cssSelector("div[id='ACC_CODE_SEARCH_WINDOW']");
	public By gridJpj = By.cssSelector("div[class='modal-dialog']");
	public By flexiGuideOptionPopup = By.cssSelector("div[id='FLXOPTION'] div[class='modal-dialog modal-lg']");
	public By ok_Popup = By.xpath(".//*[@id='popup_ok']");
	public By ok_PopupMessage = By.id("popup_message");
	LoginPage loginPage = LoginPage.getInstance();
	HomePage homePage = HomePage.getInstance();
	public int noOfRow;
	public static String expectedValue;

	public void agentLogin() throws Exception {
		waitForInVisibilityOfElement(driver, loadingImg, 30);

		try {
			waitForVisibilityOfElement(driver, homePage.popUpOk_ButtonElement,10);
			if (driver.findElement(homePage.popUpOk_ButtonElement).isDisplayed())
				driver.findElement(homePage.popUpOk_ButtonElement).click();
		} catch (Exception e) {
		}
		
		waitForInVisibilityOfElement(driver, loadingImg, 30);
		WebElement userNameEditBox = driver.findElement(loginPage.userName_TextBoxElement);
		// userNameEditBox.sendKeys("50193"); for first time
		userNameEditBox.sendKeys(agentUserName);
		//userNameEditBox.sendKeys("AUTOAMTION_TEST");

		WebElement passwordEditBox = driver.findElement(loginPage.password_TextBoxElement);
		passwordEditBox.sendKeys(agentPassword);

		Thread.sleep(500);

		WebElement signInButton = driver.findElement(loginPage.signIn_ButtonElement);
		signInButton.click();

		waitForInVisibilityOfElement(driver, loginPage.pageBlocker, 40);
	}

	/*
	 * Admin login
	 */
	public void adminLogin() throws Exception {
		waitForInVisibilityOfElement(driver, loadingImg, 30);
		Thread.sleep(500);
		try {
			if (driver.findElement(homePage.popUpOk_ButtonElement).isDisplayed())
				driver.findElement(homePage.popUpOk_ButtonElement).click();
		} catch (Exception e) {
		}

		waitForInVisibilityOfElement(driver, loadingImg, 30);

		WebElement userNameEditBox = driver.findElement(loginPage.userName_TextBoxElement);
		// userNameEditBox.sendKeys("S001"); for new envi
		userNameEditBox.sendKeys(adminUserName);

		WebElement passwordEditBox = driver.findElement(loginPage.password_TextBoxElement);
		// passwordEditBox.sendKeys("Bausqa@123"); for new envi
		passwordEditBox.sendKeys(adminPassword);

		Thread.sleep(500);
		WebElement signInButton = driver.findElement(loginPage.signInAdmin_ButtonElement);
		signInButton.click();

		waitForInVisibilityOfElement(driver, loadingImg, 30);
		
		
		try {
			if (driver.findElement(homePage.popUpCancel_ButtonElement).isDisplayed())
				driver.findElement(homePage.popUpCancel_ButtonElement).click();
		} catch (Exception e) {
		}

	}

	/*
	 * navigate to admin home page
	 */
	public void navigateToAdminHomePage() throws Exception {
		driver.navigate().to(adminHomePageUrl);
		waitForInVisibilityOfElement(driver, loginPage.pageBlocker, 30);
	}

	/*
	 * navigate to agent home page
	 */
	public void navigateToAgentHomePage1() throws Exception {
		driver.navigate().to(agentHomePageUrl);
		waitForInVisibilityOfElement(driver, loginPage.pageBlocker, 30);
	}

	/*
	 * navigate to admin client profile page
	 */
	public void navigateToClientProfilePage() throws Exception {
		driver.navigate().to(adminClientProfileUrl);
	}

	/*
	 * navigate to admin client profile page
	 */
	public void navigateToAgentClientProfilePage() throws Exception {
		driver.navigate().to(agentClientProfileUrl);
	}

	public String todaysDate() {
		Date todays = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter.format(todays);
		String todaysDate = date;
		return todaysDate;

	}

	public void verifyValueingrid(WebDriver driver, int mintblcolum, WebElement gridID,
			HashMap<Integer, String> givenTableValue) {

		boolean matchFound = false;

		waitForInVisibilityOfElement(driver, loadingImg);

		WebElement table = gridID;
		List<WebElement> tblRows = table.findElements(By.cssSelector(
				"div[class='ui-grid-render-container ng-isolate-scope ui-grid-render-container-body'] div[class='ui-grid-row ng-scope']"));
		int tblRowscount = tblRows.size();
		/*System.out.println("row size " +tblRowscount);*/

		for (row = 0; row < tblRowscount; row++) {

			List<WebElement> actualTblData = tblRows.get(row)
					.findElements(By.cssSelector("div[class='ui-grid-cell-contents ng-binding ng-scope']"));

			int tblDataCount = actualTblData.size();

			if (tblDataCount <= mintblcolum) {
				continue;
			}
			matchFound = true;
			Set<Integer> keys = givenTableValue.keySet();
			for (Integer key : keys) {

			/*	System.out.println("TableValue of " + key + " is: " + actualTblData.get(key).getText());*/

				givenValueInGrid = givenTableValue.get(key);

			/*	System.out.println("Given of " + key + " is: " + givenValueInGrid);*/

				if (actualTblData.get(key) != null
						&& !givenTableValue.get(key).equals(actualTblData.get(key).getText())) {
					matchFound = false;
					break;
				}
			} // For Key Loop End

			if (matchFound == true) {
				Assert.assertTrue("The records have shown properly", matchFound);
				noOfRow = row;
				rownumber = tblRows.get(row);

				columnNumber = actualTblData.get(0);
				break;
			}

		} // Main For End
		if (matchFound == false) {
			givenTableValue.clear();
			Assert.fail("Not able to find the value in the grid " + givenValueInGrid);
			table.click();
		}
		givenTableValue.clear();

	}

	public void verifyValueInReferRiskApprovalGrid(WebDriver driver, int mintblcolum, WebElement gridID,
			HashMap<Integer, String> givenTableValue) {

		boolean matchFound = false;

		waitForInVisibilityOfElement(driver, loadingImg);

		WebElement table = gridID;
		List<WebElement> tblRows = table.findElements(By.cssSelector(
				"div[class='ui-grid-render-container ng-isolate-scope ui-grid-render-container-body'] div[class='ui-grid-row ng-scope']"));
		int tblRowscount = tblRows.size();

		for (row = 0; row < tblRowscount; row++) {

			List<WebElement> actualTblData = tblRows.get(row)
					.findElements(By.cssSelector("div[class='ui-grid-cell-contents ng-binding ng-scope']"));

			int tblDataCount = actualTblData.size();

			if (tblDataCount <= mintblcolum) {
				continue;
			}
			matchFound = true;
			Set<Integer> keys = givenTableValue.keySet();
			for (Integer key : keys) {

				givenValueInGrid = givenTableValue.get(key);

				if (actualTblData.get(key) != null
						&& !givenTableValue.get(key).equals(actualTblData.get(key).getText())) {
					matchFound = false;
					break;
				}
			} // For Key Loop End

			if (matchFound == true) {
				Assert.assertTrue("The records have shown properly", matchFound);
				noOfRow = row;
				rownumber = tblRows.get(row);

				columnNumber = actualTblData.get(0);
				break;
			}

		} // Main For End
		if (matchFound == false) {
			givenTableValue.clear();
			Assert.fail("Not able to find the value in the grid " + givenValueInGrid);
			table.click();
		}
		givenTableValue.clear();

	}

	public void gridDoubleClick() {
		Actions action = new Actions(driver);
		waitForClickableElement(driver, rownumber);

		action.moveToElement(rownumber).click();

		action.doubleClick(rownumber).build().perform();

		try {
			action.moveToElement(rownumber).click();
			rownumber.click();
			rownumber.click();
		} catch (Exception e) {

		}
		try {
			action.doubleClick(rownumber).build().perform();
		} catch (Exception e) {

		}

	}

	// for delete

	public void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {

			return;
		}

		// This loop will rotate for 25 times to check If page Is ready after
		// every 1 second.
		// You can replace your value with 25 If you wants to Increase or
		// decrease wait time.
		for (int i = 0; i < 15; i++) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public void verifyRemovedRowInGrid(WebDriver driver, int mintblcolum, WebElement gridID,
			HashMap<Integer, String> givenTableValue) throws InterruptedException {

		try {
			boolean matchFound = false;

			waitForInVisibilityOfElement(driver, loadingImg);

			WebElement table = gridID;
			List<WebElement> tblRows = table.findElements(By.cssSelector(
					"div[class='ui-grid-render-container ng-isolate-scope ui-grid-render-container-body'] div[class='ui-grid-row ng-scope']"));
			int tblRowscount = tblRows.size();

			for (row = 0; row < tblRowscount; row++) {

				List<WebElement> actualTblData = tblRows.get(row)
						.findElements(By.cssSelector("div[class='ui-grid-cell-contents ng-binding ng-scope']"));

				int tblDataCount = actualTblData.size();
				if (tblDataCount <= mintblcolum) {
					continue;
				}
				matchFound = true;
				Set<Integer> keys = givenTableValue.keySet();
				for (Integer key : keys) {

					givenValueInGrid = givenTableValue.get(key);

					if (actualTblData.get(key) != null
							&& !givenTableValue.get(key).equals(actualTblData.get(key).getText())) {
						matchFound = false;
						break;
					}
				} // For Key Loop End

				if (matchFound == true) {
					/*
					 * Assert.assertTrue("The records have shown properly",
					 * matchFound); rownumber = tblRows.get(row); columnNumber =
					 * actualTblData.get(0); break;
					 */

					Assert.fail("Admin " + "(" + givenValueInGrid + ")"
							+ " is able to find in the grid even after removing");
				}

			} // Main For End
			/*
			 * if (matchFound == false) {
			 * 
			 * givenTableValue.clear(); Assert.fail(
			 * "Not able to find the value in the grid" + givenValueInGrid);
			 * table.click(); }
			 */
			givenTableValue.clear();
		}

		catch (Exception e) {
			/*WebElement viewAgentClose_Btn = driver.findElement(FlexiGuide.viewAgentClose_Btn);
			viewAgentClose_Btn.click();*/
		}
	}

	public void waitForInVisibilityOfElement(WebDriver driver, By invisbleElement) {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(invisbleElement));

	}

	public void waitForInVisibilityOfElement(WebDriver driver, By invisbleElement, int secondsToWait) {

		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);

		wait.until(ExpectedConditions.invisibilityOfElementLocated(invisbleElement));

	}

	public void waitForClickableElement(WebDriver driver, By clickableElement, int secondsToWait) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(clickableElement));

	}

	public void waitForVisibilityOfElement(WebDriver driver, By visbleElement, int secondsToWait) {
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
		wait.until(ExpectedConditions.visibilityOfElementLocated(visbleElement));

	}

	public void waitForVisibilityOfElement(WebDriver driver, By visbleElement) {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		wait.until(ExpectedConditions.visibilityOfElementLocated(visbleElement));

	}

	public void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForWindowSizeEqualsInput(WebDriver driver, int windowSize) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.numberOfWindowsToBe(windowSize));

	}
	public void waitForWindowSizeEqualsInput(WebDriver driver, int windowSize, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.numberOfWindowsToBe(windowSize));

	}

	public void waitForClickableElement(WebDriver driver, By clickableElement) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(clickableElement));

	}

	public void waitForClickableElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void waitForAlertPresentException(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());

	}

	public void dropDownSelection(WebElement dropDownWebElement, String optionToBeSelected) {

		Select select = new Select(dropDownWebElement);
		select.selectByVisibleText(optionToBeSelected);

	}

	public void acceptPopUp() {
		waitForClickableElement(driver, homePage.popUpOk_ButtonElement);
		driver.findElement(homePage.popUpOk_ButtonElement).click();

	}

	public String DBVerification(String sqlQuery, String ColumnToGet) throws SQLException
	{
		expectedValue = null;
		ResultSet resultSet = null;
		resultSet = st.executeQuery(sqlQuery);
		
		while (resultSet.next()) 
		{
			    expectedValue = resultSet.getString(ColumnToGet);	
		}
		return expectedValue;
		
	}
	
	/*
	 * Generate random number
	 */
	public String generateRandomNumbers(int count)
	{
		 SecureRandom random = new SecureRandom();
	     int num = random.nextInt(count);
	     String number = String.format("%05d", num);
	     return number;
	}

	private void handleMultipleWindows(String currentURL) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			driver.switchTo().window(window);
			if (driver.getCurrentUrl().contains(currentURL)) {
				return;
			}
		}
	}

	private static void disableSslVerification() {
		try {
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				public void checkClientTrusted(X509Certificate[] certs, String authType) {
				}

				public void checkServerTrusted(X509Certificate[] certs, String authType) {
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

			// Create all-trusting host name verifier
			HostnameVerifier allHostsValid = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// Install the all-trusting host verifier
			HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
	}

	public boolean isButtonVisible(WebElement VisibleButton) {
		try {

			if (VisibleButton.isEnabled()) {

				return true;

			} else {

				return false;
			}

		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	// verifyValueingrid1---common
	public boolean isreturnGridValueinboolean(WebDriver driver, int mintblcolum, WebElement gridID,
			HashMap<Integer, String> givenTableValue) {

		boolean matchFound = false;

		waitForInVisibilityOfElement(driver, loadingImg);

		WebElement table = gridID;
		List<WebElement> tblRows = table.findElements(By.cssSelector(
				"div[class='ui-grid-render-container ng-isolate-scope ui-grid-render-container-body'] div[class='ui-grid-row ng-scope']"));
		int tblRowscount = tblRows.size();

		for (row = 0; row < tblRowscount; row++) {

			List<WebElement> actualTblData = tblRows.get(row)
					.findElements(By.cssSelector("div[class='ui-grid-cell-contents ng-binding ng-scope']"));

			int tblDataCount = actualTblData.size();

			if (tblDataCount <= mintblcolum) {
				continue;
			}
			matchFound = true;
			Set<Integer> keys = givenTableValue.keySet();
			for (Integer key : keys) {

				givenValueInGrid = givenTableValue.get(key);

				// System.out.println(givenValueInGrid);
				// System.out.println(actualTblData.get(key).getText());

				if (actualTblData.get(key) != null
						&& !givenTableValue.get(key).equals(actualTblData.get(key).getText())) {
					matchFound = false;
					break;
				}
			} // For Key Loop End

			if (matchFound == true) {
				Assert.assertTrue("The records have shown properly", matchFound);
				noOfRow = row;
				rownumber = tblRows.get(row);

				columnNumber = actualTblData.get(0);
				break;
			}

		} // Main For End
		/*
		 * if (matchFound == false) { givenTableValue.clear(); Assert.fail(
		 * "Not able to find the value in the grid " + givenValueInGrid);
		 * table.click(); }
		 */
		givenTableValue.clear();

		return matchFound;
	}

	//
	public String incrementADateByOneday(int daysIncreaseCount) throws ParseException {

		String sourceDate = todaysDate(); // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(sourceDate)); // parsed date and setting to
													// calendar
		calendar.add(Calendar.DATE, daysIncreaseCount); // number of days to add
		String destDate = sdf.format(calendar.getTime());

		return destDate;

	}
	
	
	public String incrementADateByOneday(int daysIncreaseCount,String givenDate) throws ParseException {

		String sourceDate = givenDate; // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(sourceDate)); // parsed date and setting to
													// calendar
		calendar.add(Calendar.DATE, daysIncreaseCount); // number of days to add
		String destDate = sdf.format(calendar.getTime());

		return destDate;

	}
	
	public static double convertToDoubleAndRoundOff(String toConvert) throws ParseException {
		NumberFormat nf1 = NumberFormat.getInstance();
		DecimalFormat df = new DecimalFormat("0.00");
		Number numberValue = nf1.parse(toConvert);
		Double doubleValue = numberValue.doubleValue();
		double roundOff = (double) Math.round(doubleValue * 100.00) / 100.00;

		String formate = df.format(roundOff);
		// double finalValue = (Double)df.parse(formate) ;
		double finalValue = Double.parseDouble(formate);
		return finalValue;
	}

	/*
	 * Get first selected option in dropdown
	 */
	public String getFirstSelectedOptionInDropDown(WebElement element)
	{
		Select select = new Select(element);
		String firstSelectedOption = select.getFirstSelectedOption().getText();
		return firstSelectedOption;
	}
	
	/*
	 * Calculate days between two dates
	 */
	 public static long daysBetween(Date one, Date two) {
	        long difference =  (one.getTime()-two.getTime())/86400000;
	        return Math.abs(difference+1);
	    }

	
	public void verifyValueingrid(WebDriver driver, int mintblcolum, WebElement gridID,
			HashMap<Integer, String> givenTableValue,int colNumber) {

		boolean matchFound = false;

		waitForInVisibilityOfElement(driver, loadingImg);

		WebElement table = gridID;
		List<WebElement> tblRows = table.findElements(By.cssSelector(
				"div[class='ui-grid-render-container ng-isolate-scope ui-grid-render-container-body'] div[class='ui-grid-row ng-scope']"));
		int tblRowscount = tblRows.size();
		/*System.out.println("row size " +tblRowscount);*/

		for (row = 0; row < tblRowscount; row++) {

			List<WebElement> actualTblData = tblRows.get(row)
					.findElements(By.cssSelector("div[class='ui-grid-cell-contents ng-binding ng-scope']"));

			int tblDataCount = actualTblData.size();

			if (tblDataCount <= mintblcolum) {
				continue;
			}
			matchFound = true;
			Set<Integer> keys = givenTableValue.keySet();
			for (Integer key : keys) {

			/*	System.out.println("TableValue of " + key + " is: " + actualTblData.get(key).getText());*/

				givenValueInGrid = givenTableValue.get(key);

			/*	System.out.println("Given of " + key + " is: " + givenValueInGrid);*/

				if (actualTblData.get(key) != null
						&& !givenTableValue.get(key).equals(actualTblData.get(key).getText())) {
					matchFound = false;
					break;
				}
			} // For Key Loop End

			if (matchFound == true) {
				Assert.assertTrue("The records have shown properly", matchFound);
				noOfRow = row;
				rownumber = tblRows.get(row);

				columnNumber = actualTblData.get(colNumber);
				String col = columnNumber.getText();
				break;
			}

		} // Main For End
		if (matchFound == false) {
			givenTableValue.clear();
			Assert.fail("Not able to find the value in the grid " + givenValueInGrid);
			table.click();
		}
		givenTableValue.clear();

	}

	
	
	
}
