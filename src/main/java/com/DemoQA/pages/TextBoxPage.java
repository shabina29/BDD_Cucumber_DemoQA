package com.DemoQA.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.DemoQA.base.BaseClass;

public class TextBoxPage extends BaseClass {

    // --- NAVIGATION LOCATORS ---
    // The "Elements" card on the homepage
    private By elementsCard = By.xpath("//h5[text()='Elements']");
    // The "Text Box" option in the left sidebar menu
    private By textBoxMenu = By.xpath("//span[text()='Text Box']");

    // --- FORM LOCATORS ---
    private By fullNameInput = By.id("userName");
    private By emailInput = By.id("userEmail");
    private By currentAddressInput = By.id("currentAddress");
    private By permanentAddressInput = By.id("permanentAddress");
    private By submitBtn = By.id("submit");

    // --- OUTPUT LOCATORS (For Validation) ---
    private By outputName = By.xpath("//div[@id='output']//p[@id='name']");
    private By outputEmail = By.xpath("//div[@id='output']//p[@id='email']");

    // --- CONSTRUCTOR ---
    public TextBoxPage() {
        // Empty constructor
    }

    // --- NAVIGATION ACTIONS ---
    
    public void clickElementsCard() {
        // The Elements card is often at the bottom, so we scroll to it first
        WebElement card = driver.findElement(elementsCard);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", card);
        card.click();
    }

    public void clickTextBoxMenu() {
        // Sometimes the sidebar is also low, so safe to scroll/click via JS or normal click
        // We will try normal click first, if it fails, JS is the backup plan in your mind
        driver.findElement(textBoxMenu).click();
    }

    // --- FORM ACTIONS ---

    public void enterFullName(String name) {
        driver.findElement(fullNameInput).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    public void enterCurrentAddress(String address) {
        driver.findElement(currentAddressInput).sendKeys(address);
    }

    public void enterPermanentAddress(String address) {
        driver.findElement(permanentAddressInput).sendKeys(address);
    }

    public void clickSubmit() {
        // Using JS Executor to avoid "ElementClickIntercepted" error from ads
        WebElement button = driver.findElement(submitBtn);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
    }

    // --- VALIDATION ACTIONS ---

    public String getOutputName() {
        return driver.findElement(outputName).getText();
    }

    public String getOutputEmail() {
        return driver.findElement(outputEmail).getText();
    }
}