package utility;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class UtilityPage extends BasePage{

    int timeout=Integer.valueOf(readConfig("timeout"));

    public UtilityPage() {
    }

    //create read properties file function
    public static String readConfig(String keyValue) {
        File file = new File("config.properties");
        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        //load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String value=prop.getProperty(keyValue);
        return value;
    }

    //wait until element is present function
    public void waitElementPresent(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeout);
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    //wait elements clickable function
    public void waitElementClickabel(WebElement webElement) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    //sleep time function
    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void captureImage(String fileName){

        File imageFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        DateTime dt1 = new DateTime();
        //use formatter to print the date time in yyyyMMdd
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd-HH-mm-ss-SSS");
        String timeStamp = dt1.toString(formatter);
        try {
            String imageFolder= UtilityPage.readConfig("imageFolder");
            FileUtils.copyFile(imageFile,new File(imageFolder+File.separator+fileName+timeStamp+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



