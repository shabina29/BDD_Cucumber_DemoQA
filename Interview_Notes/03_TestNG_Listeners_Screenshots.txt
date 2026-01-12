Bilkul! Aapke liye notes aur interview preparation material taiyaar hai. Isse aap apni Study_Material folder mein save kar sakte hain.

📝 Note for Kids (Hinglish Style)
Topic: TestNG Listener (The Smart CCTV)

Socho tumhari school ki bus mein ek CCTV camera laga hai. Uska kaam hai monitor karna:

onTestStart: Jaise hi bus (test) chalu hogi, camera kahega "Bus chal padi!"

onTestSuccess: Agar bus sahi-salami school pahunch gayi (Pass), camera kahega "Great Job!"

onTestFailure (The Hero Part): Agar raste mein bus kharab ho gayi (Fail), toh camera turant ek Photo (Screenshot) kheencha lega taaki Driver ko pata chale kya galat hua.

onFinish: Jab saari bus ki trips khatam ho jayengi, camera kahega "Aaj ka kaam khatam!"

Moral: Humein har baar khud photo nahi kheenchni padti, ye "Listener" apne aap nazar rakhta hai aur fail hone par action leta hai.

📝 SDET Interview Notes (Professional English)
File Name: 06_TestNG_Listeners.txt

Concept: ITestListener Interface in TestNG Purpose: To perform specific actions based on the test status (Pass, Fail, Start, Finish) without modifying the actual test scripts.

Interview Questions & Answers:
Q1: What are Listeners in TestNG and why do we use them? Ans: Listeners are interfaces in TestNG that "listen" to the test execution events. We use them to generate logs, customize reports, or take screenshots. The main advantage is that it keeps our test code clean by separating the "Action" (Test) from the "Monitoring" (Listener).

Q2: How do you capture screenshots specifically for failed test cases? Ans: I implement the ITestListener interface. Inside the onTestFailure() method, I write the logic to call my screenshot utility (e.g., TestUtil.takeScreenshotAtEndOfTest()). Since TestNG automatically triggers this method only when a test fails, the screenshot is captured exactly at the moment of failure.

Q3: How do you activate a Listener in your framework? Ans: There are two ways:

In testng.xml: By adding the <listeners> tag and specifying the class path.

Using @Listeners annotation: Adding it on top of the Test class or the Suite.

Q4: What is the difference between onTestFailure and onTestSkipped? Ans: onTestFailure is triggered when a test executes but fails an assertion or throws an exception. onTestSkipped is triggered when a test is not executed at all, usually because it depends on another test that failed.

Next Step for you:

Eclipse mein Study_Material folder ke andar ek nayi file banaiye: 06_Listeners_Notes.txt.

Upar wala English content wahan paste karke Save (Ctrl+S) karein.

Terminal mein git add ., git commit -m "Added Listener notes", aur git push karein.

::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

Bilkul! testng.xml file hamare framework ka Remote Control hota hai. Bina iske, TestNG ko pata nahi chalega ki kaunsa test chalana hai aur kaunse CCTV Camera (Listener) ko ON karna hai.

Pehle Hinglish mein samajhte hain, phir aapke notes ke liye English material.

📝 Kid-Friendly Note (Hinglish Style)
Topic: testng.xml (The Remote Control)

Maano tumhare paas ek TV hai (Framework). Usme bahut saare channels hain (Test Classes).

Agar tum remote ka button nahi dabaoge, toh TV ko kaise pata chalega ki "DemoQA" channel chalana hai?

Aur agar tum remote mein Listener ki setting ON nahi karoge, toh CCTV camera (ExtentReportListener) record karna shuru nahi karega.

Isliye hum ek testng.xml file banate hain jisme hum saaf-saaf likhte hain:

"Bhai, ye waali class chalao."

"Aur haan, is Listener ko hamesha ON rakhna!"

📝 SDET Interview Notes (Professional English)
File Name: 07_TestNG_XML_Configuration.txt

Concept: Integrating Listeners via testng.xml. Purpose: Global configuration of tests and listeners to ensure every test execution is monitored automatically.

Example Code for your project:
Aap apne project ke root folder mein testng.xml file banaiye aur ye code daliye:

XML

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite name="DemoQA Suite">
    
    <listeners>
        <listener class-name="com.DemoQA.utilities.ExtentReportListener" />
    </listeners>

    <test name="TextBox Test">
        <classes>
            <class name="com.DemoQA.runners.TestRunner" />
        </classes>
    </test>
    
</suite>
Interview Questions & Answers:
Q1: How do you configure a listener globally for your entire test suite? Ans: I use the <listeners> tag in the testng.xml file. By placing it at the suite level, the listener becomes active for every test class defined in that XML. This is better than using @Listeners on every class because it avoids code duplication.

Q2: What is the benefit of running tests via testng.xml instead of running classes directly? Ans: testng.xml provides better control. We can:

Run tests in Parallel.

Group tests (e.g., Smoke, Regression).

Pass Parameters (like browser name) globally.

Include or exclude specific methods/packages.

Q3: If a listener is defined in both testng.xml and via @Listeners annotation, what happens? Ans: TestNG is smart enough to ensure the listener is only registered once, but it is standard practice to keep it in testng.xml for a clean framework architecture.

Your Turn (Next Steps):
Create File: Eclipse mein testng.xml banaiye (Project root par right-click -> New -> File).

Paste Code: Upar wala XML code copy-paste karein.

Update Notes: Study_Material mein 07_TestNG_XML_Configuration.txt file banakar interview notes save karein.

Git Push: git add ., git commit -m "Added testng config and notes", git push.