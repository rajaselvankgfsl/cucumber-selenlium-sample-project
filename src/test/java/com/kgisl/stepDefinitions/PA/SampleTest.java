package com.kgisl.stepDefinitions.PA;

import java.io.File;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.kgisl.baseclass.BaseClass;
import com.kgisl.library.LibraryClass;
import com.kgisl.library.MessageRepositoryClass;
import com.kgisl.pageElements.LoginPage;
import com.kgisl.pageElements.SamplePages.SamplePage;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SampleTest extends BaseClass {

	LoginPage loginPage = LoginPage.getInstance();
	SamplePage paGenericPage = SamplePage.getInstance();
	LibraryClass libraryClass = new LibraryClass();
	MessageRepositoryClass msgRep = new MessageRepositoryClass();
	/*LoginTest loginTest = new LoginTest();
	MotorPage motorPage = new MotorPage();
	ClientProfilePage clientProfilePage = ClientProfilePage.getInstance();*/
	HashMap<Integer, String> tabledatavalue = new HashMap<Integer, String>();

	
	@Given("^I want to write a step with precondition$")
	public void i_want_to_write_a_step_with_precondition() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^some other precondition$")
	public void some_other_precondition() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I complete action$")
	public void i_complete_action() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^some other action$")
	public void some_other_action() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^yet another action$")
	public void yet_another_action() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I validate the outcomes$")
	public void i_validate_the_outcomes() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^check more outcomes$")
	public void check_more_outcomes() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


	
}
