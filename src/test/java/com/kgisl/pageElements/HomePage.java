package com.kgisl.pageElements;

import org.openqa.selenium.By;

public class HomePage {
	
	private static HomePage homePage;
	static {
		homePage = new HomePage();
	}

	private HomePage() {
	}

	public static HomePage getInstance() {
		return (homePage == null) ? new HomePage() : homePage;
	}


	public By createClient_ButtonElement = By.xpath("//*[contains(text(),'Create Client')]");
	public By administration_ButtonElement = By.xpath(".//*[@id='stickyMenuElement']/li[3]/a");
	public By motor_ButtonElement = By.cssSelector("i.fa.fa-car");
	//public By motor_ButtonElement = By.cssSelector("#agentMainTiles > section > form > div:nth-child(1) > div:nth-child(2) > a > div > div.icon > i");
	public By userName_ButtonElement = By.xpath(".//*[@id='MAIN_APP_SCOPE']/header/nav/div/ul/li[6]/a/span");
	public By logout_ButtonElement = By.xpath("//h2[contains(text(),'Log out')]");
	public By navTab_ButtonElement = By.id("nav-tab");
	public By clientProfile_ButtonElement = By.xpath(".//*[@id='nav-content']/li[2]/a");
	
	public By adminName_ButtonElement = By.xpath(".//*[@id='MAIN_APP_SCOPE']/header/nav/div/ul/li[6]/a");
	public By systemSettings_ButtonElement = By.xpath("//span[contains(text(),'System Settings')]");
	public By updateJPJ_ButtonElement = By.xpath("//h3[contains(text(), 'Update JPJ')]");
	public By popUpOk_ButtonElement = By.id("popup_ok");
	public By popUpCancel_ButtonElement = By.id("popup_cancel");

	public By popUpMessage_LabelElement = By.cssSelector("#popup_message");
	


	public By cancelPending_ButtonElement = By.xpath(".//h3[contains(text(), 'Cancel Pending')]");
	//payment
	//public By payment_menu = By.xpath("//p[contains(text(), 'Payment')]");
	public By payment_menu = By.cssSelector("i[class='fa']");
	//public By payment_menu = By.cssSelector("#agentMainTiles > section > form > div:nth-child(3) > div:nth-child(4) > a > div > div.icon > i");
    public By payment_menuAdmin = By.cssSelector("#agentMainTiles > section > form > div:nth-child(3) > div.col-lg-4.col-xs-7.dashboard-padding-r.ng-scope > a > div > div.inner");
    
    public By closeGrid_ButtonElement = By.xpath("//button[@class='close']");
    public By closeGrid2_ButtonElement = By.cssSelector("input.btn.btn-icon-left.btn-sm[value='Close']");
    public By userManagement_ButtonElement = By.xpath("//*[contains(text(), 'User Management')]");
    public By cbcAndFloatSetup_ButtonElement=By.xpath("//span[text()='CBC & Float Setup']");

    public By underWriting_ButtonElement = By.xpath("//*[contains(text(), 'UnderWriting')]");
	public By intermediaryProfile_ButtonElement = By.xpath("//h3[contains(text(),'Intermediary Profile')]");
  
    //admin ISM
    public By ISM_ButtonElement = By.xpath(".//*[@class='ng-scope'] [contains(text(), 'ISM')]");
    //cbc float set up
 

	
}
