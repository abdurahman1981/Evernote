package cucumber;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import utility.BasePage;

public class GlobalHooks extends BasePage {

    @BeforeClass
    public static void setUp(){
        setUpBrowser();
    }
    @AfterClass
    public static void tearDown(){
        closeBrowser();
    }
}
