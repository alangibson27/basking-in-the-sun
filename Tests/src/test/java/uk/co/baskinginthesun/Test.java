package uk.co.baskinginthesun;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
                 format = {"pretty", "json:target/integration_cucumber.json"})
public class Test {
}
