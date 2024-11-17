package com.cydeo.steps;

import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;


public class LiveSessionStepDefs {
    UsersPage usersPage= new UsersPage();
    String actualUserCount;
    @When("the user gets {string} user count")
    public void the_user_gets_user_count(String status) {
        //Select select = new Select(usersPage.statusDropdown);
        //select.selectByVisibleText(status);
        BrowserUtil.selectByVisibleText(usersPage.userStatusDropdown,status);
        BrowserUtil.waitFor(1);

        String userDetails = usersPage.userCount.getText();
        actualUserCount = usersPage.getCount(userDetails);
        // page showing 1 to 10 of 721 entries


    }
    @When("the {string} user count should be equal database")
    public void the_user_count_should_be_equal_database(String status) {
        String query = "SELECT count(*) FROM users  where status = '"+status+"' and user_group_id <> 1";
        DB_Util.runQuery(query);
        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedUserCount,actualUserCount);

    }

}
