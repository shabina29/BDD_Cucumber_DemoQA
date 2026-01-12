package com.DemoQA.runners;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.DemoQA.stepDefinitions",
    
    // ADVANCED PLUGINS
    plugin = {
        "pretty",                              // Console ko sundar banata hai
        "html:target/cucumber-reports.html",   // Manager ke liye HTML report
        "json:target/cucumber.json",           // Jenkins ke liye JSON report
        "rerun:target/failed_scenarios.txt"    // FAILED tests ko ek file mein save karta hai
    },
    
    monochrome = true,
    publish = true,
    tags = "" // Empty rakha hai taaki sab chale
)
public class TestRunner extends AbstractTestNGCucumberTests {

    // --- ADVANCED FEATURE: PARALLEL EXECUTION ---
    // Ye method TestNG ko batata hai: "Bhai, tests ko Parallel chalao!"
    
    @Override
    @DataProvider(parallel = false) // true karne se parallel execution hota hai
    public Object[][] scenarios() {
        return super.scenarios();
    }
}

/*1. features = "src/test/resources/features"
Matlab: "Sawaal Kahan Hai?" (Where is the Requirement?)

Kya hai: Yeh tumhare .feature files (Gherkin/English files) ka Path (Address) hai.

Simple Bhasha Mein: Tum Cucumber ko bol rahe ho:

"Sun bhai, src folder mein ja, wahan test mein ja, phir resources mein ja... wahan features folder ke andar meri English wali files rakhi hain. Unhe padh le."

Technical Note: Notice karo yahan Slashes (/) use hote hain, kyunki yeh folders ka physical rasta hai.

2. glue = "com.DemoQA.stepDefinitions"
Matlab: "Jawaab Kahan Hai?" (Where is the Code?)

Kya hai: Yeh tumhare Java Code (Step Definitions) ka Package Name hai.

Naam "Glue" kyun hai?

Glue (Gond) ka kaam kya hota hai? Chipkana.

Yeh line tumhare English Feature File ko tumhare Java Code se chipkati (connect karti) hai.

Simple Bhasha Mein: Tum Cucumber ko bol rahe ho:

"Jo English tune Feature file mein padhi, uska Java code com.DemoQA.stepDefinitions package ke andar milega. Wahan jaake dhoond le ki kaunsa method chalana hai."

Technical Note: Notice karo yahan Dots (.) use hote hain (Jaise com.DemoQA...), kyunki Java mein hum packages ko dots se likhte hain, slashes se nahi.

💡 Ek dum desi example (Exam Hall) 📝
Imagine karo tum Exam hall mein ho.

features = Question Paper. (Ismein likha hai ki karna kya hai. "Q1: Login karo")

glue = Answer Sheet. (Ismein likha hai ki wo kaam kaise hoga. "Ans1: driver.findElement...")

Agar tumne glue galat bataya, toh Cucumber chillayega: "Question toh mil gaya, par Answer sheet nahi mil rahi!" (Isi ko technical bhasha mein UndefinedStepException kehte hain).

⚠️ Interview Trap (Dhyan rakhna)
Interviewer poochega: "Features aur Glue mein kya fark hai path likhne mein?"

Answer: "Sir, Features expects the Folder Path (using slashes /), whereas Glue expects the Java Package Name (using dots .)."*/