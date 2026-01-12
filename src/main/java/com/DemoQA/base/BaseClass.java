package com.DemoQA.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

    // Global variables (Accessible everywhere)
    public static WebDriver driver;
    public static Properties prop;

    // Constructor: Reads the Config file immediately
    public BaseClass() {
        try {
            prop = new Properties();
            
            // ⬇️ THIS IS THE LINE WE FIXED (Updated Address) ⬇️
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/config.properties");
 /*✅ Verification Logic (Ye kyun chalega?)
Line 25: /src/test/resources/config/config.properties

This tells Java: "Go to resources -> Go inside config folder -> Open the file". Now it matches your folder structure perfectly.*/
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Initialization: Opens Browser & Goes to URL
    public static void initialization() {
        String browserName = prop.getProperty("browser"); // Reads "chrome"

        // Logic: Check which browser to open
        if (browserName.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")) {
            driver = new FirefoxDriver();
        }

        // Standard Browser Settings
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        
        // Wait Logic (Reads "10" from config)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait"))));
        
        // Launch the URL (Reads demoqa.com from config)
        driver.get(prop.getProperty("url"));
    }
}