package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/features", glue = { "stepDefinition" }, 
                plugin = { "pretty",
	                       "json:target/jsonReports/cucumber.json",
                		   "rerun:target/failed_scenarios.txt"
                		   

},
//                		  tags = "@Deleteplace",
		monochrome = true)
public class TestRunner {

}
