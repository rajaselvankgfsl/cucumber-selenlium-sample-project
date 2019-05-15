package com.kgisl.runner;

import org.testng.annotations.Test;

import com.kgisl.baseclass.BaseClass;
import com.kgisl.library.LibraryClass;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
	
	@CucumberOptions(
			features =  ".",
	        glue = "com.kgisl.stepDefinitions",
	        //tags = "@Environment2,@Environment1,@Environment4",
	    	 tags = {"@Agent12","@Regression777","@Motor",},

	        monochrome = true,
	        plugin = {"pretty","html:target/html","json:target/cucumber.json", "rerun:rerun.txt"})

public class RunTest extends BaseClass{
		
	    	    	    
		LibraryClass lib=new LibraryClass();
	    @Test(groups = "examples-testng", description = "Example of using TestNGCucumberRunner to invoke Cucumber")
	    public void runCukes() 
	    {
	        new TestNGCucumberRunner(getClass()).runCukes();
	    }
	    
	   	
	}
