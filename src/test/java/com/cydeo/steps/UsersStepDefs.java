package com.cydeo.steps;

import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class UsersStepDefs {
UsersPage usersPage = new UsersPage();
String expectedStatus;
String email;

    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {
        BrowserUtil.waitFor(2);
    usersPage.editUser.click();
    }

    @When("the user changes user status {string} to {string}")
    public void the_user_changes_user_status_to(String ACTIVE, String INACTIVE) {
        BrowserUtil.waitFor(2);
    //we need to change status of the first user that we clicked
        Select select = new Select(usersPage.statusDropdown);
        select.selectByVisibleText(INACTIVE);
        //save the email of the user that we change status
         email = usersPage.email.getAttribute("value");
        System.out.println(email);
        BrowserUtil.waitFor(2);
        expectedStatus = INACTIVE;
    }

    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
        BrowserUtil.waitFor(1);
       usersPage.saveChanges.click();

    }

    @Then("{string} message should appear")
    public void message_should_appear(String expected) {
      String  actualMessage =  usersPage.toastMessage.getText();
       Assert.assertEquals(expected, actualMessage);

    }

    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {
        // create a new database query
        DB_Util.runQuery("select status from users where email = '" +email+"'");
        String actualStatus = DB_Util.getFirstRowFirstColumn();
        // check that the status is correct
        //expectedStatus
        System.out.println("Expected from database : " + actualStatus);
        Assert.assertEquals(expectedStatus, actualStatus);

    }

    @Then("the user changes current user status {string} to {string}")
    public void the_user_changes_current_user_status_to(String inactive, String active) {
        BrowserUtil.waitFor(1);
        // this step for switch user button page to inactive users
        Select select = new Select(usersPage.userStatusDropdown);
        // select inactive
        select.selectByVisibleText(inactive);
        usersPage.searchField.sendKeys(email);
        BrowserUtil.waitFor(3);
        usersPage.editUser.click();
        BrowserUtil.waitFor(1);
        //we need to change status of the first user that we clicked
        Select select2 = new Select(usersPage.statusDropdown);
        BrowserUtil.waitFor(1);
        select2.selectByVisibleText(active);
        BrowserUtil.waitFor(2);
        usersPage.saveChanges.click();

    }




}
