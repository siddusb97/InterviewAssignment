package stepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/Features/",
        glue= {"stepDefinitions"},
        plugin = { "usage","html:target/cucumber-reports/crypto-pretty",
        "json:target/cucumber-reports/json-reports/crypto-post.json",
        "junit:target/cucumber-reports/crypto-post.xml"
},
        monochrome = true)


public class CryptoTestRunner {
}
