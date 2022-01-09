package utility;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class BasePage {

    public static WebDriver  driver;

    public static void setUpBrowser()
    {
        String osName = System.getProperty("os.name").toLowerCase().substring(0, 3);
        System.out.println(osName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //define a WebDriver
        if (osName.equals("win")) {
            System.setProperty("webdriver.chrome.driver", "c:\\webdriver\\chromedriver.exe");
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        } else if (osName.equals("mac")) {
            System.setProperty("webdriver.chrome.driver", "/Users/apple/Documents/Java/WebDrivers/chromedriver");
            driver=new ChromeDriver(chromeOptions);
            driver.manage().window().maximize();
            Log.info("Chrome Browser has been opened");
        } else {
            System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
            chromeOptions.addArguments(Arrays.asList("--headless", "--dosan-gpu"));
            chromeOptions.addArguments("window-size=1200,1100");
            driver.manage().window().maximize();
        }

    }
    public static void closeBrowser()
    {
        driver.close();
        driver.quit();
        Log.info("Chrome Browser has been closed");

    }
}
