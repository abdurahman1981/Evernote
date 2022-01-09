package testNG;

import com.github.javafaker.Faker;
import net.andreinc.mockneat.MockNeat;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DeleteNotePage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.CreateNotePage;
import utility.*;

@Listeners(CustomResultListener.class)
public class TestNGTestRunner extends BasePage {

    Faker faker = new Faker();
    MockNeat mockNeat = MockNeat.threadLocal();
    String validUserEmail = UtilityPage.readConfig("validUserEmail");
    String validPassword = UtilityPage.readConfig("validPassword");


    @BeforeClass
    public void setup() {
        setUpBrowser();
    }

    @Test (description = "user with invalid email should not be able to login")
    public void unsuccessfulLoginEmailTest() {
        Log.startTestCase("Unsuccessful Login with invalid Email Test");
        driver.get(UtilityPage.readConfig("url"));
        Log.info("Login page was opened");
        LoginPage loginPage = new LoginPage(driver);
        String invalidUserEmail = mockNeat.emails().val();
        Assert.assertTrue(loginPage.unsuccessfulLogin(invalidUserEmail));
        Log.endTestCase("Unsuccessful Login with invalid Email Test");
    }

    @Test(dependsOnMethods = "unsuccessfulLoginEmailTest", description = "user with valid email should be able to login")
    public void successfulLoginEmailTest() {
        Log.startTestCase("Successful Login with valid Email Test");
        driver.get(UtilityPage.readConfig("url"));
        Log.info("Login page was opened");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.successfulLogin(validUserEmail, validPassword);
        Assert.assertTrue(loginPage.verifySuccessfulLogin());
        Log.endTestCase("Successful Login with valid Email Test");
    }

    @Test(dependsOnMethods = "successfulLoginEmailTest", description = "user should be able to create new note")
    public void createNewNoteTest() {
        Log.startTestCase("Create New Note Test");
        CreateNotePage createNotePage = new CreateNotePage(driver);
        String title = faker.name().title();
        TestDataHolder.setNoteTitle(title);
        createNotePage.createNewNote(title);
        Assert.assertTrue(createNotePage.verifyNewlyCreatedNoteExist(title));
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.logout();
        Log.endTestCase("Create New Note Test");
    }

    @Test(dependsOnMethods = "createNewNoteTest", description = "newly created note should be opened after login")
    public void openNewlyCreatedNoteTest() {
        Log.startTestCase("Open Newly Created Note Test");
        driver.get(UtilityPage.readConfig("url"));
        Log.info("User page was opened");
        CreateNotePage createNotePage = new CreateNotePage(driver);
        String title = TestDataHolder.getNoteTitle();
        createNotePage.openNewlyCreatedNote(title);
        Assert.assertTrue(createNotePage.verifyNoteBeOpened(title));
        Log.endTestCase("Open Newly Created Note Test");
    }

    @Test(dependsOnMethods = "openNewlyCreatedNoteTest", description = "user should be able to delete newly created note")
    public void deleteNoteTest() {
        Log.startTestCase("Delete Newly Created Note Test");
        DeleteNotePage deleteNotePage = new DeleteNotePage(driver);
        String title = TestDataHolder.getNoteTitle();
        deleteNotePage.deleteNote(title);
        Assert.assertFalse(deleteNotePage.verifyNoteBeDeleted(title));
        Log.endTestCase("Delete Newly Created Note Test");
    }

    @AfterClass
    public static void tearDown() {
        closeBrowser();
    }
}
