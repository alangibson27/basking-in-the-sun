package uk.co.baskinginthesun;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",
                 format = {"json:target/integration_cucumber.json"})
public class RunTests {
}
