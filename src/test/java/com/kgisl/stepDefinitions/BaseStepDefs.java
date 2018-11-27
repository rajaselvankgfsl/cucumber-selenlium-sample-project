package com.kgisl.stepDefinitions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.kgisl.baseclass.BaseClass;
import com.kgisl.library.LibraryClass;
import com.kgisl.pageElements.HomePage;
import com.kgisl.pageElements.LoginPage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class BaseStepDefs extends BaseClass {
	HomePage homePage = HomePage.getInstance();
	LibraryClass libraryClass = new LibraryClass();
	LoginPage loginPage = LoginPage.getInstance();

	@Before("@CloseOpenBrowser")
	public void before(Scenario scenario) throws Exception {
		scenario.getId();
		System.out.println("This is before Scenario: " + scenario.getName().toString());
		
		driver.quit();
		relaunchingBrowser(userRole);

	}

	@After
	public void after(Scenario scenario) throws Exception {
		System.out.println("This is after Scenario: " + scenario.getName().toString());
		
		if (scenario.isFailed()) {
			System.out.println(scenario + " test is failed");

			scenario.write("Failed Page URL is " + driver.getCurrentUrl());
			byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");

			if (userRole.equalsIgnoreCase("Agent")) {
				driver.quit();
				relaunchingBrowser("Agent");
			}

			try {
				driver.findElement(homePage.popUpOk_ButtonElement).click();
				driver.findElement(homePage.closeGrid_ButtonElement).click();

			} catch (Exception e) {

			}

			try {

				driver.findElement(homePage.closeGrid_ButtonElement).click();
				driver.findElement(homePage.closeGrid_ButtonElement).sendKeys(Keys.ENTER);
				driver.findElement(homePage.popUpOk_ButtonElement).click();
			} catch (Exception e) {

			}

		}

		if (userRole.equalsIgnoreCase("Agent") && !driver.getCurrentUrl().equals(agentHomePageUrl) && driver != null) {

			driver.navigate().to(agentHomePageUrl);
			libraryClass.waitForInVisibilityOfElement(driver, loginPage.pageBlocker, 30);
		}

		if (userRole.equalsIgnoreCase("Admin") && !driver.getCurrentUrl().equals(adminHomePageUrl)) {

			driver.navigate().to(adminHomePageUrl);
			libraryClass.waitForInVisibilityOfElement(driver, loginPage.pageBlocker, 30);

		}

		if (userRole.equalsIgnoreCase("Both")) {
			driver.quit();

			System.out.println("Browser Close");

		}

	}

}
