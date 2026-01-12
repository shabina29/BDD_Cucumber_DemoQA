package com.DemoQA.stepDefinitions;

import org.testng.Assert;
import com.DemoQA.base.BaseClass;
import com.DemoQA.pages.TextBoxPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

public class TextBoxSteps extends BaseClass {

    TextBoxPage textBoxPage;

    // 1. Updated: User starts at Homepage
    @Given("user in on the Homepage")
    public void user_in_on_the_homepage() {
        initialization(); 
        driver.get("https://demoqa.com/"); // Base URL
        textBoxPage = new TextBoxPage();
    }

    // 2. New Step: Click Elements Card
    @When("user navigates to the Elements section")
    public void user_navigates_to_the_elements_section() {
        textBoxPage.clickElementsCard();
    }

    // 3. New Step: Click Text Box Menu
    @And("user clicks on the Text Box option")
    public void user_clicks_on_the_text_box_option() {
        textBoxPage.clickTextBoxMenu();
    }

    // 4. Existing Form Steps
    @When("user enters full name {string}")
    public void user_enters_full_name(String fullname) {
        textBoxPage.enterFullName(fullname);
    }

    @And("user enters email {string}")
    public void user_enters_email(String email) {
        textBoxPage.enterEmail(email);
    }

    @And("user enters current address {string}")
    public void user_enters_current_address(String address) {
        textBoxPage.enterCurrentAddress(address);
    }

    @And("user enters permanent address {string}")
    public void user_enters_permanent_address(String address) {
        textBoxPage.enterPermanentAddress(address);
    }

    @And("user clicks the submit button")
    public void user_clicks_the_submit_button() {
        textBoxPage.clickSubmit();
    }

    // 5. Validation Step
    @Then("the output data should match the entered data")
    public void the_output_data_should_match_the_entered_data() {
        String actualName = textBoxPage.getOutputName();
        // Check if output contains "John Doe"
        Assert.assertTrue(actualName.contains("John Doe"), "Name did not match!");

        String actualEmail = textBoxPage.getOutputEmail();
        // Check if output contains "john@example.com"
        Assert.assertTrue(actualEmail.contains("john@example.com"), "Email did not match!");

        // Close the browser
        driver.quit();
    }
}