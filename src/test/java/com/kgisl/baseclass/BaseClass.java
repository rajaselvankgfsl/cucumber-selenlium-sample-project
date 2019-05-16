package com.kgisl.baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.kgisl.library.LibraryClass;

public class BaseClass {

	protected static WebDriver driver = null;
	protected static Connection con;
	public static Statement st;
	public static String schemaName = "";
	public static String userRole, browser, environemt, jenkinsuserName, jenkinspassword;
	
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
	public static String insuredAge = null;

	// wave2
	public static String adminHomePageUrlWave2 = "https://10.100.7.83:9443/nsure/admin/#/";
	public static String agentHomePageUrlWave2 = "https://10.100.7.83:9443/nsure/agent/#/";

	public static String clientProfileUrlWave2 = "https://10.100.7.83:9443/nsure/admin/#/explorerclientprofile";
	public static String agentClientProfileUrlWave2 = "https://10.100.7.83:9443/nsure/agent/#/explorerclientprofile";


	
	@BeforeClass
	public static void launchBrowser() throws Exception {
		LibraryClass libraryClass = new LibraryClass();

		// Local Setting
		 userRole="Agent";
		 browser="chrome";
		 environemt ="Ilive";
		 
		/*// Jenkin Setting
		userRole = System.getProperty("userRole");
		System.out.println("user role :" +userRole);
		
		environemt = System.getProperty("environemt");
		System.out.println("environment:" +environemt);

		browser = System.getProperty("browser");
		System.out.println("browser: " +browser);
		
		jenkinsuserName = System.getProperty("jenkinsuserName");
		//System.out.println("username: "+jenkinsuserName);
		
		jenkinspassword = System.getProperty("jenkinspassword");
		//System.out.println("password: "+jenkinspassword);
*/		
		
		
	       
		// String Browser = getConfigData("Browser");
		// System.out.println("browser name is "+ Browser);

		 insuredAge = getConfigData("maxInsuredAgeLimit");
		 
		 if(environemt.equalsIgnoreCase("ILive"))
			{
				agentUrl = getConfigData("AgentILive");
				adminUrl = getConfigData("AdminILive");
				adminHomePageUrl = adminUrl+"#/";
				agentHomePageUrl = agentUrl+"#/";
				agentClientProfileUrl = agentUrl+"#/explorerclientprofile";
				adminClientProfileUrl = adminUrl+"#/explorerclientprofile";
				schemaName = getConfigData("SchemaNameILive");
				if (jenkinsuserName !=null && jenkinspassword != null){
					agentUserName = jenkinsuserName;
					System.out.println("username:" + jenkinsuserName);
					agentPassword = jenkinspassword;
					System.out.println("password :" + jenkinspassword);
					adminUserName = getConfigData("adminUserNameIlive");
					adminPassword = getConfigData("adminPasswordIlive");
				}
				else {
				
				agentUserName = getConfigData("agentUserNameIlive");
				System.out.println(agentUserName);
				agentPassword = getConfigData("agentPasswordIlive");
				System.out.println(agentPassword);
				adminUserName = getConfigData("adminUserNameIlive");
				System.out.println(adminUserName);
				adminPassword = getConfigData("adminPasswordIlive");
				System.out.println(adminPassword);
				}
				
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
				if (jenkinsuserName !=null && jenkinspassword != null){
					agentUserName = jenkinsuserName;
					adminPassword = jenkinspassword;
					adminUserName = getConfigData("adminUserNameSQA");
					adminPassword = getConfigData("adminPasswordSQA");
				}else{
				agentUserName = getConfigData("agentUserNameSQA");
				agentPassword = getConfigData("agentPasswordSQA");
				adminUserName = getConfigData("adminUserNameSQA");
				adminPassword = getConfigData("adminPasswordSQA");
				}
				
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
				
				if (jenkinsuserName !=null && jenkinspassword != null){
					agentUserName = jenkinsuserName;
					adminPassword = jenkinspassword;
					adminUserName = getConfigData("adminUserNameIUAT");
					adminPassword = getConfigData("adminPasswordIUAT");
				} else {
					agentUserName = getConfigData("agentUserNameIUAT");
					agentPassword = getConfigData("agentPasswordIUAT");
					adminUserName = getConfigData("adminUserNameIUAT");
					adminPassword = getConfigData("adminPasswordIUAT");					
				}
				
				
			}
		 
		 
		try {
			System.out.println(agentUrl);

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
			ChromeOptions chromeOptions = new ChromeOptions();
			
			/*chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("window-size=1366,768");*/
			chromeOptions.addArguments("--no-sandbox");
	        DesiredCapabilities capab = DesiredCapabilities.chrome();
	        capab.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capab);
			 Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
			    String browserName = cap.getBrowserName().toLowerCase();
			    System.out.println(browserName);
			    String os = cap.getPlatform().toString();
			    System.out.println(os);
			    String version = cap.getVersion().toString();
			    System.out.println(version);

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
			ChromeOptions chromeOptions = new ChromeOptions();
			//chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("window-size=1366,768");
		        DesiredCapabilities capab = DesiredCapabilities.chrome();
		        capab.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capab);
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		    String browserName = cap.getBrowserName().toLowerCase();
		    System.out.println(browserName);
		    String os = cap.getPlatform().toString();
		    System.out.println(os);
		    String version = cap.getVersion().toString();
		    System.out.println(version);
			
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

	public void relaunchingBrowser(String roles,String userName,String password) throws Exception {

		LibraryClass libraryClass = new LibraryClass();
		if (browser.equalsIgnoreCase("Firefox")) {
			// System.setProperty("webdriver.firefox.driver",
			// "D:/Selenium/Geckodriver/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

			if (roles.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin(userName,password);

			}

			else if (roles.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin(userName,password);

			} else if (roles.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin(userName,password);

			}

		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			//chromeOptions.addArguments("--headless");
			chromeOptions.addArguments("window-size=1366,768");
		        DesiredCapabilities capab = DesiredCapabilities.chrome();
		        capab.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new ChromeDriver(capab);
			Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		    String browserName = cap.getBrowserName().toLowerCase();
		    System.out.println(browserName);
		    String os = cap.getPlatform().toString();
		    System.out.println(os);
		    String version = cap.getVersion().toString();
		    System.out.println(version);
			driver.manage().window().maximize();

			if (roles.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin(userName,password);

			}

			else if (roles.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin(userName,password);

			} else if (roles.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin(userName,password);

			}

		} else if (browser.equalsIgnoreCase("ie")) {
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

			System.setProperty("webdriver.ie.driver", "Drivers//IEDriverServer.exe");

			WebDriver driver = new InternetExplorerDriver(capabilities);

			driver.manage().window().maximize();

			if (roles.equalsIgnoreCase("Admin")) {
				driver.get(adminUrl);
				libraryClass.adminLogin(userName,password);
			}

			else if (roles.equalsIgnoreCase("Agent")) {
				driver.get(agentUrl);
				libraryClass.agentLogin(userName,password);

			} else if (roles.equalsIgnoreCase("Both")) {
				driver.get(agentUrl);
				libraryClass.agentLogin(userName,password);

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

	@AfterClass
	public static void aftereSuite() throws SQLException {
		System.out.println("This is afterSuite");
		if (con != null) {
			con.close();
		}
		driver.quit();
	}

	
}
