package com.DemoQA.stepDefinitions;

import org.testng.Assert;
import com.DemoQA.base.BaseClass;
import com.DemoQA.pages.WebTablesPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class WebTablesSteps extends BaseClass {
    
    WebTablesPage webTablesPage;

    // UNIQUE STEP 1: Launches app
    @Given("user launches the application")
    public void user_launches_the_application() {
        initialization();
        driver.get("https://demoqa.com/");
        webTablesPage = new WebTablesPage();
    }

    @And("user clicks on the Elements")
    public void user_clicks_on_the_elements() {
        webTablesPage.clickElementsCard();
    }

    @When("user is on the WebTables page")
    public void user_is_on_the_web_tables_page()
    {
        webTablesPage.clickWebTablesMenu();
    }

    @And("user click on the Add button") 
    public void user_click_on_the_add_button() {
        webTablesPage.clickAddButton();
    }

    @And("user enters Registration details {string} {string} {string} {string} {string} {string}")
    public void user_enters_registration_details(String fname, String lname, String email, String age, String salary, String dept) {
        webTablesPage.enterRegistrationDetails(fname, lname, email, age, salary, dept);
    }

    // UNIQUE STEP 2: Registration Submit
    @And("user clicks the registration submit button")
    public void user_clicks_the_registration_submit_button() {
        webTablesPage.clickSubmit();
    }

    @Then("the new user {string} should appear in the search results")
    public void the_new_user_should_appear_in_the_search_results(String email) {
        webTablesPage.searchUser(email);
        String actualText = webTablesPage.getFirstRowText(); // Pehla cell le lo
        Assert.assertFalse(actualText.trim().isEmpty(), "User was not found in the table!");// Ensure it's not empty
        // Validation successful
        
        // Close browser after test
        driver.quit();
    }
}