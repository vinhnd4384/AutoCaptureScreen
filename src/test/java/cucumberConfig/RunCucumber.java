package cucumberConfig;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/features", 
		//features="Features",
		glue = "stepDefinitions",
		monochrome = true, 
		plugin = { "pretty", "html:target/site/cucumber-reports",
		"json:target/site/cucumber.json" }, 
		snippets = SnippetType.CAMELCASE,
		tags = {"@screenshot"}
)

public class RunCucumber {

}
