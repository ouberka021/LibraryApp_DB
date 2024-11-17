package com.cydeo.steps;

import com.cydeo.pages.DashBoardPage;
import com.cydeo.pages.LoginPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LoginStepDefs {
    LoginPage loginpage = new LoginPage();
    DashBoardPage dashboardpage = new DashBoardPage();
    String actualUsername;
    String actualEmail;

    @Given("the user logged in  {string} and {string}")
    public void the_user_logged_in_and(String email, String password) {
        loginpage.login(email, password);
        this.actualEmail = email;

        BrowserUtil.waitFor(1);
    }


    @When("user gets username  from user fields")
    public void user_gets_username_from_user_fields() {

        actualUsername = dashboardpage.accountHolderName.getText();
        System.out.println("Actual Username --> " + actualUsername);

        // DB_Util.createConnection(); created in the hooks page
        String query = "select  full_name from users where email = '" + actualEmail + "'";
        DB_Util.runQuery(query);
        String expectedUsername = DB_Util.getFirstRowFirstColumn();
        System.out.println("Expected Username --> " + expectedUsername);

        Assert.assertEquals(expectedUsername, actualUsername);
    }

    @Then("the username should be same with database")
    public void the_username_should_be_same_with_database() {

    }


}
