@EvernoteTest
Feature: Evernote user should be able to login, then be able to create, open and delete new note

  @unsuccessfulEmailLoginTest
  Scenario:  user with invalid email should not be able to login
    Given evernote login page should be opened normally
    When  enter invalid user email to username field
    Then  unsuccessful login message should be displayed

  @successfulEmailLoginTest
  Scenario:  user with valid email should be able to login
    Given evernote login page should be opened normally
    When  enter valid user email and password
    Then  user page should be opened

  @createNewNoteTest
  Scenario: user should be able to create new note
    Given user should be on homepage dashboard
    When  create new note
    Then  newly created note should be displayed on notes field before logout

  @openNewlyCreatedNoteTest
    Scenario: newly created note should be opened after login
    Given user should be on homepage dashboard
    When  user open newly created note in Notes list
    Then  newly created note page should be displayed

  @deleteNewlyCreatedNoteTest
  Scenario: user should be able to delete newly created note
    Given newly created note should be opened
    When  click on MoveToTrash button on Actions menu
    Then  newly created note title should not be displayed in Notes list
