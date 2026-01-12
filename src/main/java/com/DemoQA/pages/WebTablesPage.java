package com.DemoQA.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.DemoQA.base.BaseClass;

public class WebTablesPage extends BaseClass {

    // --- LOCATORS ---
    private By elementsCard = By.xpath("//h5[text()='Elements']");
    private By webTablesMenu = By.xpath("//span[text()='Web Tables']");
    private By addButton = By.id("addNewRecordButton");
    
    // Registration Form
    private By firstNameInput = By.id("firstName");
    private By lastNameInput = By.id("lastName");
    private By emailInput = By.id("userEmail");
    private By ageInput = By.id("age");
    private By salaryInput = By.id("salary");
    private By departmentInput = By.id("department");
    private By submitBtn = By.id("submit");

    // Search & Validation
    private By searchBox = By.id("searchBox");
    private By firstRowCell = By.xpath("//div[@class='rt-tbody']/div[1]/div/div[1]"); 

    public WebTablesPage() {
        // Empty constructor
    }

    // --- ACTIONS ---

    public void clickElementsCard() {
        WebElement card = driver.findElement(elementsCard);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        card.click();
    }

    public void clickWebTablesMenu() {
        WebElement menu = driver.findElement(webTablesMenu);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menu);
        menu.click();
    }

    public void clickAddButton() {
        driver.findElement(addButton).click();
    }

    public void enterRegistrationDetails(String fname, String lname, String email, String age, String salary, String dept) {
        driver.findElement(firstNameInput).sendKeys(fname);
        driver.findElement(lastNameInput).sendKeys(lname);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(ageInput).sendKeys(age);
        driver.findElement(salaryInput).sendKeys(salary);
        driver.findElement(departmentInput).sendKeys(dept);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    public void searchUser(String keyword) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(keyword);
    }

    public String getFirstRowText() {
        return driver.findElement(firstRowCell).getText();
    }
}