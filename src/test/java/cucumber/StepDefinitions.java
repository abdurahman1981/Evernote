package cucumber;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.andreinc.mockneat.MockNeat;
import org.junit.Assert;
import pages.CreateNotePage;
import pages.DeleteNotePage;
import pages.LoginPage;
import pages.LogoutPage;
import utility.BasePage;
import utility.Log;
import utility.TestDataHolder;
import utility.UtilityPage;

public class StepDefinitions extends BasePage {

    UtilityPage utilityPage;
    Faker faker=new Faker();
    MockNeat mockNeat=MockNeat.threadLocal();

    LoginPage loginPage=new LoginPage(driver);
    CreateNotePage createNotePage=new CreateNotePage(driver);
    DeleteNotePage deleteNotePage=new DeleteNotePage(driver);

    String url= utilityPage.readConfig("url");
    String validUserEmail=UtilityPage.readConfig("validUserEmail");
    String validPassword=UtilityPage.readConfig("validPassword");

    @Given("evernote login page should be opened normally")
    public void evernote_login_page_should_be_opened_normally() {
        driver.get(url);
        Log.info("Login page was opened");
    }

    @When("enter invalid user email to username field")
    public void enter_unsuccessful_using_email_to_field() {
        String invalidUserEmail=mockNeat.emails().val();
        loginPage.enterUserEmail(invalidUserEmail);
        loginPage.clickContinueButton();
    }

    @Then("unsuccessful login message should be displayed")
    public void unsuccessful_message_should_display_under_email_address_field() {
        Assert.assertTrue(loginPage.verifyUnsuccessfulLogin());
        Log.endTestCase("Unsuccessful Login with invalid Email Test");
    }

    @When("enter valid user email and password")
    public void enter_valid_user_email_and_password() {
        Log.startTestCase("Successful Login with valid Email Test");
        Log.info("Login page was opened");
        loginPage.successfulLogin(validUserEmail,validPassword);
    }

    @Then("user page should be opened")
    public void user_page_should_be_opened() {
        Assert.assertTrue(loginPage.verifySuccessfulLogin());
        Log.endTestCase("Successful Login with valid Email Test");
    }

    @Given("user should be on homepage dashboard")
    public void user_should_be_on_homepage_dashboard() {
        Log.startTestCase("Create New Note Test");
        driver.get(url);

    }

    @When("create new note")
    public void create_new_note() {
        String title=faker.name().title();
        TestDataHolder.setNoteTitle(title);
        createNotePage.createNewNote(title);
    }

    @Then("newly created note should be displayed on notes field before logout")
    public void created_new_note_should_be_displayed_on_notes_list_before_logout() {
        String title=TestDataHolder.getNoteTitle();
        Assert.assertTrue(createNotePage.verifyNewlyCreatedNoteExist(title));
        LogoutPage logoutPage=new LogoutPage(driver);
        logoutPage.logout();
        Log.endTestCase("Create New Note Test");
    }

    @When("user open newly created note in Notes list")
    public void user_open_newly_created_note_in_Notes_list() {
        Log.startTestCase("Open Newly Created Note Test");
        Log.info("User page was opened");
        String title=TestDataHolder.getNoteTitle();
        createNotePage.openNewlyCreatedNote(title);
    }

    @Then("newly created note page should be displayed")
    public void newly_created_note_page_should_be_displayed() {
        String title=TestDataHolder.getNoteTitle();
        Assert.assertTrue(createNotePage.verifyNoteBeOpened(title));
        Log.endTestCase("Open Newly Created Note Test");
    }

    @Given("newly created note should be opened")
    public void newly_created_note_should_be_opened() {
        Log.startTestCase("Delete Newly Created Note Test");
        String title=TestDataHolder.getNoteTitle();
        createNotePage.openNewlyCreatedNote(title);
    }

    @When("click on MoveToTrash button on Actions menu")
    public void click_move_to_trash_button_on_actions_menu() {
        String title=TestDataHolder.getNoteTitle();
        deleteNotePage.deleteNote(title);
    }

    @Then("newly created note title should not be displayed in Notes list")
    public void newly_created_note_title_should_not_be_displayed_in_Notes_list() {
        String title=TestDataHolder.getNoteTitle();
        deleteNotePage.verifyNoteBeDeleted(title);
        Log.endTestCase("Delete Newly Created Note Test");
    }

}
