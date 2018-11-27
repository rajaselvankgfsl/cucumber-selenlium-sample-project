package com.kgisl.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import com.kgisl.library.LibraryClass;

public class BaseClass {

	protected static WebDriver driver = null;
	public static Connection con;
	public static Statement st;
	public static String schemaName = "";
	public static String userRole, browser, environemt;
	
	public static String agentUrl;
	public static String adminUrl;
	public static String adminHomePageUrl;
	public static String agentHomePageUrl;
	public static String adminClientProfileUrl;
	public static String agentClientProfileUrl;
	public static String agentUserName;
	public static String agentPassword;
	public static String adminUserName;
	public static String adminPassword;
	public static String floatused = null;;
	public static String floatBalance = null;;

	// wave2
	public static String adminHomePageUrlWave2 = "https://10.100.7.83:9443/nsure/admin/#/";
	public static String agentHomePageUrlWave2 = "https://10.100.7.83:9443/nsure/agent/#/";

	public static String clientProfileUrlWave2 = "https://10.100.7.83:9443/nsure/admin/#/explorerclientprofile";
	public static String agentClientProfileUrlWave2 = "https://10.100.7.83:9443/nsure/agent/#/explorerclientprofile";

	@BeforeSuite
	public void launchBrowser() throws Exception {
		LibraryClass libraryClass = new LibraryClass();

		// Local Setting
		 userRole="Agent";
		 
		 browser="Chrome";
		 
		 environemt ="Ilive";
		 

		// Jenkin Setting
	/*	userRole = System.getProperty("userRole");
		System.out.println(userRole);
		
		environemt = System.getProperty("environemt");
		System.out.println(environemt);

		browser = System.getProperty("browser");
		System.out.println(browser);
*/		
		
		// String Browser = getConfigData("Browser");
		// System.out.println("browser name is "+ Browser);

		 
		 if(environemt.equalsIgnoreCase("ILive"))
			{
				agentUrl = getConfigData("AgentILive");
				adminUrl = getConfigData("AdminILive");
				adminHomePageUrl = adminUrl+"#/";
				agentHomePageUrl = agentUrl+"#/";
				agentClientProfileUrl = agentUrl+"#/explorerclientprofile";
				adminClientProfileUrl = adminUrl+"#/explorerclientprofile";
				schemaName = getConfigData("SchemaNameILive");
				agentUserName = getConfigData("agentUserNameIlive");
				agentPassword = getConfigData("agentPasswordIlive");
				adminUserName = getConfigData("adminUserNameIlive");
				adminPassword = getConfigData("adminPasswordIlive");
				
			}
		 else if(environemt.equalsIgnoreCase("SQA"))
			{
				agentUrl = getConfigData("AgentSQA");
				adminUrl = getConfigData("AdminSQA");
				adminHomePageUrl = adminUrl+"#/";
				agentHomePageUrl = agentUrl+"#/";
				agentClientProfileUrl = agentUrl+"#/explorerclientprofile";
				adminClientProfileUrl = adminUrl+"#/explorerclientprofile";
				schemaName = getConfigData("SchemaNameSQA");
				agentUserName = getConfigData("agentUserNameSQA");
				agentPassword = getConfigData("agentPasswordSQA");
				adminUserName = getConfigData("adminUserNameSQA");
				adminPassword = getConfigData("adminPasswordSQA");
				
			}
		 
		 else if(environemt.equalsIgnoreCase("IUAT"))
			{
				agentUrl = getConfigData("AgentIUAT");
				adminUrl = getConfigData("AdminIUAT");
				adminHomePageUrl = adminUrl+"#/";
				agentHomePageUrl = agentUrl+"#/";
				agentClientProfileUrl = agentUrl+"#/explorerclientprofile";
				adminClientProfileUrl = adminUrl+"#/explorerclientprofile";
				schemaName = getConfigData("SchemaNameIUAT");
				agentUserName = getConfigData("agentUserNameIUAT");
				agentPassword = getConfigData("agentPasswordIUAT");
				adminUserName = getConfigData("adminUserNameIUAT");
				adminPassword = getConfigData("adminPasswordIUAT");
				
			}
		 
		 System.out.println(agentUrl);
		try {

			if (con == null) {

				String DB_URL = getConfigData("DbURL");
				String dbname = getConfigData("DbName");
				String USER = getConfigData("DbUsername");
				String PASS = getConfigData("DbPassword");
				schemaName = schemaName;

				Class.forName("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(DB_URL + ":" + dbname, USER, PASS);
				st = con.createStatement();
			}
		} catch (SQLException e) {
			System.out.print(e);
		}

		if (browser.equalsIgnoreCase("Firefox") && driver == null) {
			/*
			 * System.setProperty("webdriver.firefox.driver",
			 * "D:\Selenium_Karthick\geckodriver-v0.17.0-win64");
			 */
			System.setProperty("webdriver.gecko.driver", "Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			if (userRole.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin();
			}

			else if (userRole.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			} else if (userRole.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			}

		} else if (browser.equalsIgnoreCase("chrome") && driver == null) {
			System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			
	/*		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		    capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		    System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");
		    ChromeDriver driver = new ChromeDriver();
		    driver.manage().deleteAllCookies();
		    driver.manage().window().maximize();*/

			if (userRole.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin();

			}

			else if (userRole.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			} else if (userRole.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			}

		} else if (browser.equalsIgnoreCase("ie") && driver == null) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

			System.setProperty("webdriver.ie.driver", "Drivers//IEDriverServer.exe");

			WebDriver driver = new InternetExplorerDriver(capabilities);

			driver.manage().window().maximize();

			if (userRole.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin();

			}

			else if (userRole.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();
				
			} 
			else if (userRole.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			}
		}

	}

	public void relaunchingBrowser(String roles) throws Exception {

		LibraryClass libraryClass = new LibraryClass();
		if (browser.equalsIgnoreCase("Firefox")) {
			// System.setProperty("webdriver.firefox.driver",
			// "D:/Selenium/Geckodriver/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

			if (roles.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin();

			}

			else if (roles.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			} else if (roles.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			}

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();

			if (roles.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin();

			}

			else if (roles.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			} else if (roles.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			}

		} else if (browser.equalsIgnoreCase("ie")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

			System.setProperty("webdriver.ie.driver", "Drivers//IEDriverServer.exe");

			WebDriver driver = new InternetExplorerDriver(capabilities);

			driver.manage().window().maximize();

			if (roles.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin();

			}

			else if (roles.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			} else if (roles.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin();

			}
		}

	}

	public static String getConfigData(String propName) throws IOException {
		String propValue = "";

		Properties prop = new Properties();

		File f = new File("config.properties");
		FileInputStream fio = new FileInputStream(f);

		prop.load(fio);
		propValue = prop.getProperty(propName);

		return propValue;

	}

	@AfterSuite(alwaysRun = true)
	public void aftereSuite() throws SQLException {
		System.out.println("This is afterSuite");
		if (con != null) {
			con.close();
		}
		driver.quit();
	}

	
}
