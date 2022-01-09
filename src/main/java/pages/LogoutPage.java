package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.UtilityPage;

public class LogoutPage extends UtilityPage {
    //define all elements
    @FindBy (id = "qa-NAV_USER")
    WebElement userIcon;
    @FindBy (xpath = "//*[contains(text(),'Sign out')]")
    WebElement signOutButton;
    @FindBy(id = "qa-LOGOUT_CONFIRM_DIALOG_CANCEL")
    WebElement alertBackToAppButton;
    @FindBy(id = "qa-LOGOUT_CONFIRM_DIALOG_SUBMIT")
    WebElement alertExitButton;
    //create a constructor for using in test run class
    public LogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //methods for all actions
    public void clickUserIcon(){
        waitElementPresent(userIcon);
        userIcon.click();
        Log.info("User Icon has been clicked");
    }
    public void clickSignOutButton(){
        waitElementPresent(signOutButton);
        signOutButton.click();
        Log.info("Signout Button was clicked");
        sleep(3);

        if(alertExitButton.isDisplayed()){
            alertExitButton.click();
            Log.info("Exit Anyway Button was clicked");
        }
    }

    //Main method for logout
    public void logout(){
        clickUserIcon();
        clickSignOutButton();
        Log.info("User Sign out successfully");
    }
}
