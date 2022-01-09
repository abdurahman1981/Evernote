package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.Log;
import utility.UtilityPage;

public class DeleteNotePage extends UtilityPage {

    //Define all elements
    @FindBy (xpath = "//span[text()='Notes']")
    WebElement notesButton ;
    @FindBy (id = "qa-NOTES_SIDEBAR")
    WebElement notesSidebar;
    @FindBy(xpath = "//iframe[@id='qa-COMMON_EDITOR_IFRAME']")
    WebElement iframeElement;
    @FindBy (id = "qa-NOTE_ACTIONS")
    WebElement actionDropDown;
    @FindBy (id = "qa-ACTION_DELETE")
    WebElement moveToTrashButton;

    //create a constructor for initiate the class
    public DeleteNotePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //All actions
    public void clickNotesButton(){
        waitElementPresent(notesButton);
        notesButton.click();
        Log.info("Notes button was clicked");
    }
    public void clickActionsDropDown(){
        waitElementPresent(iframeElement);
        waitElementClickabel(actionDropDown);
        actionDropDown.click();
        Log.info("Actions Dropdown list was clicked");
    }
    public void clickMoveToTrashButton(){
        waitElementClickabel(moveToTrashButton);
        moveToTrashButton.click();
        waitElementPresent(notesSidebar);
        Log.info("MoveToTrash button was clicked");
    }
    public boolean verifyNoteBeDeleted(String title){
        clickNotesButton();
        waitElementPresent(notesSidebar);
        try {
            driver.findElement(By.xpath("//*[@id='qa-NOTES_SIDEBAR']//*[text()='" + title + "']"));
        }catch (NoSuchElementException e){
        }
        return false;
    }

    //Main method for writing new note
    public void deleteNote(String title){
        clickActionsDropDown();
        clickMoveToTrashButton();
        clickNotesButton();
    }
}
