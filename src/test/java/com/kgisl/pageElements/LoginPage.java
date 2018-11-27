package com.kgisl.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	
	private static LoginPage loginPage;
	static {
		loginPage = new LoginPage();
	}

	private LoginPage() {
	}

	public static LoginPage getInstance() {
		return (loginPage == null) ? new LoginPage() : loginPage;
	}

	/*
	 * Login page elements
	 */
	
	public By userName_TextBoxElement = By.id("login-form-username");
	public By password_TextBoxElement = By.id("login-form-password");
	public By signIn_ButtonElement = By.id("login");
	public By signInAdmin_ButtonElement = By.xpath(".//*[@id='agentLogin']/div[4]/input");
	public By signInAgent_ButtonElement = By.cssSelector(".btn.btn-warning.btn-block.btn-sm");
	public By changeLaterPassword_ButtonElement = By.id("popup_cancel");
	//public By pageBlocker = By.id("page-blocker");
	public By pageBlocker = By.cssSelector("div[id='page-blocker'][style='display: block;']");
	
	//public By userNameTextBoxElement = By.xpath(".//*[@id='fm-login-id']");

	/*@FindBy (xpath = "//input[@id='passwordId']")
	public WebElement password_TextBoxElement;*/
}
