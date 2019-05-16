package com.kgisl.runner;

import org.junit.runner.RunWith;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.kgisl.baseclass.BaseClass;
import com.kgisl.library.LibraryClass;

import cucumber.api.CucumberOptions;
	
@RunWith(ExtendedCucumber.class)
@ExtendedCucumberOptions(
		jsonReport = "target/cucumber.json",
		detailedAggregatedReport = true,
        overviewReport = true,
        overviewChartsReport = false,
        toPDF=true,
        screenShotLocation = "Screenshots",
        outputFolder = "target/ExtendedReport",
        formats = {"png"},
        retryCount = 0)
	@CucumberOptions(
			features =  ".",
	        glue = "com.kgisl.stepDefinitions",
	        //tags = "@Environment2,@Environment1,@Environment4",
	    	 tags = {"@Agent12","@Regression77","@Motor",},

	        monochrome = true,
	        plugin = {"pretty","html:target/html","json:target/cucumber.json", "rerun:rerun.txt"})

public class RunTest extends BaseClass{
		
	    	    	    
		LibraryClass lib=new LibraryClass();
	/*    @Test(groups = "examples-testng", description = "Example of using TestNGCucumberRunner to invoke Cucumber")
	    public void runCukes() 
	    {
	        new TestNGCucumberRunner(getClass()).runCukes();
	    }*/
	    
	   	
	}
