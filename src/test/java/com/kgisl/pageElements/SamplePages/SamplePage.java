package com.kgisl.pageElements.SamplePages;

import org.openqa.selenium.By;

public class SamplePage {
	
	private static SamplePage samplePage;
	static{
		samplePage = new SamplePage();
	}

	private SamplePage(){
	}

	public static SamplePage getInstance(){
		return (samplePage == null) ? new SamplePage(): samplePage;
	}

	
	public By imagePA = By.xpath("//p[contains(text(),'Personal Accident')]");
	}

