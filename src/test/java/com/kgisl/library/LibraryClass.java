package com.kgisl.library;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
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
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.kgisl.baseclass.BaseClass;
import com.kgisl.pageElements.HomePage;
import com.kgisl.pageElements.LoginPage;


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
	private static final String CHAR_LIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int RANDOM_STRING_LENGTH = 3;
	boolean flag;

	public void agentLogin() throws Exception {
		waitForInVisibilityOfElement(driver, loadingImg, 30);

		try {
			// waitForVisibilityOfElement(driver,
			// homePage.popUpOk_ButtonElement,10);
			if (driver.findElement(homePage.popUpOk_ButtonElement).isDisplayed())
				driver.findElement(homePage.popUpOk_ButtonElement).click();
		} catch (Exception e) {
		}

		waitForInVisibilityOfElement(driver, loadingImg, 30);
		WebElement userNameEditBox = driver.findElement(loginPage.userName_TextBoxElement);
		// userNameEditBox.sendKeys("50193"); for first time
		userNameEditBox.sendKeys(agentUserName);
		// userNameEditBox.sendKeys("AUTOAMTION_TEST");

		WebElement passwordEditBox = driver.findElement(loginPage.password_TextBoxElement);
		passwordEditBox.sendKeys(agentPassword);

		Thread.sleep(500);
		waitForVisibilityOfElement(driver, loginPage.signInAgent_ButtonElement, 10);
		WebElement signInButton = driver.findElement(loginPage.signInAgent_ButtonElement);
		signInButton.click();

		waitForInVisibilityOfElement(driver, loginPage.pageBlocker, 40);
	}

	public void agentLogin(String agentUserName, String agentPassword) throws Exception {
		waitForInVisibilityOfElement(driver, loadingImg, 30);

		try {
			// waitForVisibilityOfElement(driver,
			// homePage.popUpOk_ButtonElement,10);
			if (driver.findElement(homePage.popUpOk_ButtonElement).isDisplayed())
				driver.findElement(homePage.popUpOk_ButtonElement).click();
		} catch (Exception e) {
		}

		waitForInVisibilityOfElement(driver, loadingImg, 30);
		WebElement userNameEditBox = driver.findElement(loginPage.userName_TextBoxElement);
		// userNameEditBox.sendKeys("50193"); for first time
		userNameEditBox.sendKeys(agentUserName);
		// userNameEditBox.sendKeys("AUTOAMTION_TEST");

		WebElement passwordEditBox = driver.findElement(loginPage.password_TextBoxElement);
		passwordEditBox.sendKeys(agentPassword);

		Thread.sleep(500);

		WebElement signInButton = driver.findElement(loginPage.signInAgent_ButtonElement);
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

		waitForInVisibilityOfElement(driver, loadingImg, 60);

		try {
			if (driver.findElement(homePage.popUpCancel_ButtonElement).isDisplayed())
				driver.findElement(homePage.popUpCancel_ButtonElement).click();
		} catch (Exception e) {
		}

	}

	public void adminLogin(String adminUserID, String adminPassword) throws Exception {
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
		userNameEditBox.sendKeys(adminUserID);

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
		/* System.out.println("row size " +tblRowscount); */

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

				// System.out.println("TableValue of " + key + " is: " +
				// actualTblData.get(key).getText());

				givenValueInGrid = givenTableValue.get(key);

				// System.out.println("Given of " + key + " is: " +
				// givenValueInGrid);

				if (actualTblData.get(key) != null
						&& !givenTableValue.get(key).equals(actualTblData.get(key).getText())) {
					matchFound = false;
					break;
				}
			} // For Key Loop End

			if (matchFound == true) {
				waitForInVisibilityOfElement(driver, loginPage.pageBlocker, 60);
				Assert.assertTrue("The records have shown properly", matchFound);
				noOfRow = row;
				rownumber = tblRows.get(row);

				columnNumber = actualTblData.get(0);
				break;
			}

		} // Main For End
		if (matchFound == false) {
			givenTableValue.clear();
			Assert.fail("Not able to find the value in the grid " + givenTableValue);
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
			System.out.println(e);
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
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
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

	public void waitForElementToBeSelected(WebDriver driver, By visbleElement) {
		WebDriverWait wait = new WebDriverWait(driver, 35);
		wait.until(ExpectedConditions.elementToBeSelected(visbleElement));
	}

	public void waitForVisibilityOfElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public void waitForWindowSizeEqualsInput(WebDriver driver, int windowSize) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.numberOfWindowsToBe(windowSize));

	}

	public void waitForWindowSizeEqualsInput(WebDriver driver, int windowSize, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.numberOfWindowsToBe(windowSize));

	}

	public void waitForPresenceOfElementLocated(WebDriver driver, By clickableElement) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(clickableElement));

	}

	public void waitForClickableElement(WebDriver driver, By clickableElement) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(clickableElement));

	}

	public void waitForClickableElement(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));

	}

	public void waitForAlertPresentException(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.alertIsPresent());

	}

	public void dropDownSelection(WebElement dropDownWebElement, String optionToBeSelected) {
		waitForInVisibilityOfElement(driver, By.id("page-blocker"), 300);
		Select select = new Select(dropDownWebElement);
		select.selectByVisibleText(optionToBeSelected);
	}

	public void acceptPopUp() {
		try {
			waitForClickableElement(driver, homePage.popUpOk_ButtonElement);
			driver.findElement(homePage.popUpOk_ButtonElement).click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(homePage.popUpOk_ButtonElement));
		}
	}

	public String DBVerification(String sqlQuery, String ColumnToGet) throws SQLException {
		expectedValue = null;
		ResultSet resultSet = null;
		// System.out.println(sqlQuery);
		resultSet = st.executeQuery(sqlQuery);

		while (resultSet.next()) {
			expectedValue = resultSet.getString(ColumnToGet);
		}
		return expectedValue;

	}

	/*
	 * Generate random number
	 */
	public String generateRandomNumbers(int count) {
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

	public boolean calculateleapyear() {

		boolean chkleapyear;
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		cal.add(Calendar.YEAR, 1);
		String nextyear = sdf.format(cal.getTime());

		// System.out.println(nextyear);
		if ((Integer.parseInt(nextyear) / 4) == 0) {

			chkleapyear = false;
		} else {
			chkleapyear = true;
		}

		return chkleapyear;

	}

	public String incrementADateByOneday(String daysIncreaseCunt) throws ParseException {

		int daysIncreaseCount = Integer.parseInt(daysIncreaseCunt);
		String sourceDate = todaysDate(); // Start date
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(sourceDate)); // parsed date and setting to
													// calendar
		calendar.add(Calendar.DATE, daysIncreaseCount); // number of days to add
		String destDate = sdf.format(calendar.getTime());

		return destDate;

	}

	public String incrementADateByOneday(int daysIncreaseCount, String givenDate) throws ParseException {

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
		if (toConvert == null) {
			toConvert = "0.00";
		}
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

	public static BigDecimal convertToDoubleAndRoundOff1(String toConvert) throws ParseException {

		NumberFormat nf1 = NumberFormat.getInstance();
		DecimalFormat df = new DecimalFormat("0.00");
		Number numberValue = nf1.parse(toConvert);
		Double doubleValue = numberValue.doubleValue();
		double roundOff = (double) Math.round(doubleValue * 100.00) / 100.00;

		String formate = df.format(roundOff);

		BigDecimal bd = new BigDecimal(formate);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		// double finalValue = (Double)df.parse(formate) ;
		double finalValue = Double.parseDouble(formate);
		return bd;
	}

	public int covertToInteger(String convertionString) {

		int convertIntValue = Integer.parseInt(convertionString);
		return convertIntValue;
	}

	/*
	 * Get first selected option in dropdown
	 */
	public String getFirstSelectedOptionInDropDown(WebElement element) {
		Select select = new Select(element);
		String firstSelectedOption = select.getFirstSelectedOption().getText();
		return firstSelectedOption;
	}

	/*
	 * Calculate days between two dates
	 */
	public static long daysBetween(Date one, Date two) {
		long difference = (one.getTime() - two.getTime()) / 86400000;
		return Math.abs(difference + 1);
	}

	public void verifyValueingrid(WebDriver driver, int mintblcolum, WebElement gridID,
			HashMap<Integer, String> givenTableValue, int colNumber) {

		boolean matchFound = false;

		waitForInVisibilityOfElement(driver, loadingImg);

		WebElement table = gridID;
		List<WebElement> tblRows = table.findElements(By.cssSelector(
				"div[class='ui-grid-render-container ng-isolate-scope ui-grid-render-container-body'] div[class='ui-grid-row ng-scope']"));
		int tblRowscount = tblRows.size();
		/* System.out.println("row size " +tblRowscount); */

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

				/*
				 * System.out.println("TableValue of " + key + " is: " +
				 * actualTblData.get(key).getText());
				 */

				givenValueInGrid = givenTableValue.get(key);

				/*
				 * System.out.println("Given of " + key + " is: " +
				 * givenValueInGrid);
				 */

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

	public String generateRandomString() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public boolean retryingFindClick(WebDriver driver, By by) {
		boolean result = false;
		int attempts = 0;
		while (attempts < 2) {
			try {
				driver.findElement(by).click();
				result = true;
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
		return result;
	}

	public void verifyValueingrid(WebDriver driver, int mintblcolum, WebElement gridID,
			HashMap<Integer, String> givenTableValue, String msg, String parameterData) {

		boolean matchFound = false;

		waitForInVisibilityOfElement(driver, loadingImg);

		WebElement table = gridID;
		List<WebElement> tblRows = table.findElements(By.cssSelector(
				"div[class='ui-grid-render-container ng-isolate-scope ui-grid-render-container-body'] div[class='ui-grid-row ng-scope']"));
		int tblRowscount = tblRows.size();
		/* System.out.println("row size " +tblRowscount); */

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

				// System.out.println("TableValue of " + key + " is: " +
				// actualTblData.get(key).getText());

				givenValueInGrid = givenTableValue.get(key);

				// System.out.println("Given of " + key + " is: " +
				// givenValueInGrid);

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
			Assert.fail(msg + ": " + parameterData);
			table.click();
		}
		givenTableValue.clear();

	}

	public void click(By element) {
		boolean blSuccessFlag = true;
		int loopCounter = 0;
		do {
			try {
				driver.findElement(element).click();
				System.out.println("Before element click" + element + loopCounter);
				blSuccessFlag = false;
			}
			catch (Exception e) {
				if (e.toString().contains("org.openqa.selenium.WebDriverException: unknown error:")
						|| e.toString().contains("is not clickable at point")) {
					blSuccessFlag = true;
					loopCounter++;
					System.out.println("After element click" + element + loopCounter);
				}
			}
		} while (blSuccessFlag && loopCounter < 500);
	}

	public boolean verifyTextfromOpenedPDFFileInBrowser(String[] pdfSearchString) {
		String[] reqTextInPDF = pdfSearchString;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;
		int i;
		try {
			flag = false;
			Properties systemSettings = System.getProperties();
			systemSettings.put("proxySet", "true");
			systemSettings.put("https.proxyHost", "10.100.7.81");
			systemSettings.put("https.proxyPort", "9443");
			// URL url = new URL(driver.getCurrentUrl());
			URL url = new URL("https", "10.100.7.81", 9443, "/nsure/letters/printpdf");
			url.openConnection();
			BufferedInputStream file = new BufferedInputStream(url.openStream(), 100);
			PDFParser parser = new PDFParser(file);
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(1);

			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed " + e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}

		/*
		 * System.out.println("+++++++++++++++++");
		 * System.out.println(parsedText);
		 * System.out.println("+++++++++++++++++");
		 */
		for (i = 0; i < reqTextInPDF.length; i++) {
			if (parsedText.contains(reqTextInPDF[i])) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag == false) {
			Assert.fail("Unable to find text in PDF document" + reqTextInPDF[i]);
		}
		return flag;

	}

	public boolean verifyTextfromSavedPDFFile(String[] pdfSearchString, String filePath) {
		String[] reqTextInPDF = pdfSearchString;
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		String parsedText = null;
		int i;
		try {
			flag = false;
			File file = new File(filePath);
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdfStripper.setStartPage(1);
			pdfStripper.setEndPage(1);

			pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);
		} catch (MalformedURLException e2) {
			System.err.println("URL string could not be parsed " + e2.getMessage());
		} catch (IOException e) {
			System.err.println("Unable to open PDF Parser. " + e.getMessage());
			try {
				if (cosDoc != null)
					cosDoc.close();
				if (pdDoc != null)
					pdDoc.close();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		}

		/*
		 * System.out.println("+++++++++++++++++");
		 * System.out.println(parsedText);
		 * System.out.println("+++++++++++++++++");
		 */
		for (i = 0; i < reqTextInPDF.length; i++) {
			if (parsedText.contains(reqTextInPDF[i])) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}

		if (flag == false) {
			Assert.fail("Unable to find text in PDF document" + reqTextInPDF[i]);
		}
		return flag;

	}

}
