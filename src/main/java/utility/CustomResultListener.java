package utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class CustomResultListener implements ITestListener {

    UtilityPage screenshotUtility=new UtilityPage();
    public void onTestFailure(ITestResult result)

    {
        screenshotUtility.captureImage(result.getMethod().getMethodName().trim()+"-fail");
    }
}
