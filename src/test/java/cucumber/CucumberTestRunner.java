package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/evernote-cucumber", "json:target/cucumber.json"},
        tags = "@EvernoteTest",
        features = {"/Users/apple/Documents/Evernote/src/test/resources/features/EvernoteUItesting.feature"})

public class CucumberTestRunner extends GlobalHooks {
}
