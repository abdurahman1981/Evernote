package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.UtilityPage;

public class CreateNotePage extends UtilityPage {
    //Define all elements
    @FindBy (xpath = "//span[text()='Notes']")
    WebElement notesButton ;
    @FindBy (id = "qa-HOME_NOTE_WIDGET_CREATE_NOTE")
    WebElement newNoteButton;
    @FindBy(xpath = "//iframe[@id='qa-COMMON_EDITOR_IFRAME']")
    WebElement iframeElement;
    @FindBy (xpath = "//*[@placeholder='Title']")
    WebElement titleArea;
    @FindBy (css = "#en-note>div")
    WebElement contentArea;
    @FindBy(xpath = "//div[contains(@id,'qa-NOTES_SIDEBAR_NOTE_SNIPPET')]/span")
    WebElement contentInSidebar;
    @FindBy (id = "qa-NAV_HOME")
    WebElement homeButton;
    @FindBy (id = "qa-HOME_WIDGET_GRID_ITEM_Notes_2|0_0|0_NVA3U3|13")
    WebElement notes;
    @FindBy(xpath = "//textarea[@placeholder='Title']//parent::div/div")
    WebElement noteTitle;

    //create a constructor for initiate the class
    public CreateNotePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    //All actions
    public void clickNotesButton(){
        waitElementPresent(notesButton);
        notesButton.click();
        Log.info("Notes button was clicked");
    }
    public void clickNewNoteButton(){
        waitElementPresent(newNoteButton);
        newNoteButton.click();
        Log.info("Create New Note button was clicked");
    }
    public void enterTitle(String title){
        waitElementPresent(iframeElement);
        driver.switchTo().frame(iframeElement);
        waitElementPresent(titleArea);
        titleArea.clear();
        titleArea.click();
        titleArea.sendKeys(title);
        titleArea.sendKeys(Keys.ENTER);
        Log.info("Note Title was entered");
    }
    public void enterContent(){
        Faker faker=new Faker();
        String content=faker.lorem().paragraph();
        contentArea.sendKeys(content);
        driver.switchTo().defaultContent();
        sleep(5);
        Log.info("Note content was entered");
    }
    public void clickHomeButton(){
        waitElementPresent(homeButton);
        homeButton.click();
        Log.info("Home button was clicked");
    }

    public boolean verifyNewlyCreatedNoteExist(String title){
        waitElementPresent(notes);
        WebElement createdNoteTitle =driver.findElement(By.xpath("//*[text()='"+title+"']"));
        waitElementPresent(createdNoteTitle);
        return createdNoteTitle.isDisplayed();
    }
    public boolean verifyNoteBeOpened(String title){
        waitElementPresent(iframeElement);
        driver.switchTo().frame(iframeElement);
        waitElementPresent(titleArea);
        titleArea.click();
        String openedNoteTitle =titleArea.getAttribute("value");
        driver.switchTo().defaultContent();
        return title.equals(openedNoteTitle);
    }
    public void openNewlyCreatedNote(String title){
        clickNotesButton();
        waitElementPresent(iframeElement);
        WebElement newlyCreatedNote =driver.findElement(By.xpath("//span[text()='"+title+"']"));
        newlyCreatedNote.click();
        Log.info("Newly Created Note was opened");
    }
    //Main method for creating new note
    public void createNewNote(String title){
        clickNewNoteButton();
        enterTitle(title);
        enterContent();
        clickHomeButton();
    }
}
