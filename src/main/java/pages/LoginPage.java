package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.UtilityPage;

public class LoginPage extends UtilityPage {
    //define all elements
    @FindBy(linkText = "Log In")
    WebElement loginButton;
    @FindBy(id = "username")
    WebElement username;
    @FindBy(id = "loginButton")
    WebElement continueButton;
    @FindBy(xpath = "//*[@id='responseMessageRow']/div")
    WebElement unsuccessfulMsg;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(xpath = "//*[text()='Home']")
    WebElement homeMenu ;

    //create a constructor for using in test run class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //methods for all actions
    public void clickLoginButton(){
        waitElementPresent(loginButton);
        loginButton.click();
    }
    public void enterUserEmail(String email)
    {
        waitElementPresent(username);
        username.sendKeys(email);
        Log.info("UserEmail was entered");
    }
    public void enterPassword(String validPassword)
    {
        waitElementPresent(password);
        password.sendKeys(validPassword);
        Log.info("Password was entered");
    }
    public void clickContinueButton()
    {
        waitElementPresent(continueButton);
        continueButton.click();
        Log.info("Continue Button was clicked");
    }
    public boolean verifyUnsuccessfulLogin()
    {
        waitElementPresent(unsuccessfulMsg);
        return unsuccessfulMsg.isDisplayed();
    }
    public boolean verifySuccessfulLogin()
    {
        waitElementPresent(homeMenu);
        return homeMenu.isDisplayed();
    }

    //Main method for unsuccessful login
    public boolean unsuccessfulLogin(String email){
        enterUserEmail(email);
        clickContinueButton();
        return verifyUnsuccessfulLogin();
    }
    //Main method for successful login
    public void successfulLogin(String email, String validPassword){
        enterUserEmail(email);
        clickContinueButton();
        enterPassword(validPassword);
        clickContinueButton();
        Log.info("Sign in Button was clicked");
    }
}
