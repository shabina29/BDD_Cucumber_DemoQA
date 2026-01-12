📝 Folder: Interview_Notes
File Name: 07_GitHub_Deployment_Steps.txt

Content:

What is a Remote? A connection to your online repository (Locker).

What is a Commit? A snapshot of your code at a specific time.

What is a Push? Uploading your local commits to the online server.

🎓 SDET Interview Q&A (3-4 Years Exp)
Q1: Walk me through the steps to push your local framework to a new GitHub repository. Answer: 1. "First, I initialize the local directory as a Git repository using git init." 2. "Then, I add the files to the staging area using git add . and commit them with a descriptive message using git commit -m." 3. "Next, I create a new repository on GitHub and link it to my local project using git remote add origin [URL]." 4. "Finally, I push the code using git push -u origin main."

Q2: I see you have a target folder and .settings in your project. Should these be on GitHub? Answer (Crucial): "Actually, no. Compiled files like the target folder, .class files, and IDE-specific settings like .project or .settings should be excluded. To do this, I create a .gitignore file at the root level. This keeps the repository clean and ensures we only share source code and configuration files like pom.xml."

Q3: How do you handle your config.properties if it contains sensitive data like passwords? Answer: "We never push sensitive data to GitHub. I would either add the config.properties to the .gitignore or use Environment Variables / Secret Management tools in a real CI/CD pipeline to keep passwords secure."

🚀 Next Level Action: Add a .gitignore
Currently, your GitHub has some "trash" files (like the target folder which is just generated reports). Let's make it look professional.

In Eclipse, right-click the project root -> New -> File.

Name it exactly: .gitignore (starts with a dot).

Paste these lines:

Plaintext

/target/
/.settings/
/.project
/.classpath
/test-output/
*.class
Now, push this change to GitHub:

Bash

git add .gitignore
git commit -m "Added gitignore to keep repo clean"
git push origin main





🎓 SDET Interview Note: Troubleshooting Git
Topic: Git Errors Question: "What does 'fatal: repository not found' mean in Git?"

Simple Answer for you: "Sir, ye error tab aata hai jab hum galat Remote URL add kar dete hain ya phir repository ka naam change ho jata hai. Isse fix karne ke liye hum git remote -v se check karte hain ki kaunsa URL linked hai, aur git remote set-url ya remove/add karke sahi URL update kar dete hain."

ontent:

Purpose: To monitor test execution and perform actions (like taking screenshots) automatically based on test results.

Interface: We use ITestListener which comes from TestNG.

Key Methods:

onTestStart: Triggers when a test execution begins.

onTestSuccess: Triggers only when a test passes.

onTestFailure: The most important method; triggers when a test fails. We use this to call TestUtil.takeScreenshotAtEndOfTest().

onFinish: Triggers after all tests in the suite are completed.

🎓 SDET Interview (3-4 Years Exp) - Q&A
Q1: How do you achieve "Automatic Screenshot Capture" on failure in your framework? Answer: "In my framework, I avoid writing screenshot logic inside every @Test or Step Definition. Instead, I implement the ITestListener interface. I override the onTestFailure method to trigger a screenshot utility (TestUtil) whenever a failure occurs. This ensures a clean separation of concerns and follows the DRY (Don't Repeat Yourself) principle."

Q2: How does TestNG know that it has to trigger your Listener class? Answer: "There are two ways to register a listener. First, by using the @Listeners(PackageName.ClassName.class) annotation at the class level. Second, and more professionally, by adding the <listeners> tag in the testng.xml file. This applies the listener to the entire test suite."

Q3: Why did you use an Interface (ITestListener) instead of a Class? Answer: "Because ITestListener is a contract provided by TestNG that ensures we have all the hooks (start, success, failure) available to us. Since Java doesn't support multiple inheritance of classes, implementing an interface allows my Listener class to still extend other classes like BaseClass if needed."

🚀 Follow-up "Killer" Questions (Expert Level)
"If your test fails in the @BeforeMethod (setup), will your listener capture a screenshot?"

Hint: No, onTestFailure only triggers if the @Test fails. For setup failures, you need onConfigurationFailure.

"How do you attach the screenshot you just took to your Extent Report?"

Hint: You use MediaEntityBuilder.createScreenCaptureFromPath(path).build() inside the listener.

"What is the difference between ITestListener and IInvokedMethodListener?"

Hint: ITestListener is for the test result, IInvokedMethodListener triggers before/after every single method (including setup/teardown).


📝 Topic: TestNG Listeners & Failure Screenshots
📖 Bachche ki Tarah Samjho (The "CCTV" Concept)
Socho tum ek race (Test Case) mein bhag rahe ho. Side mein ek CCTV Camera laga hai. Us camera ka kaam race mein bhagna nahi hai, uska kaam sirf "dekhna" aur "record" karna hai:

onTestStart: Jaise hi race SHURU hui, camera ne record kiya—"Chalo, race start ho gayi!"

onTestSuccess: Agar tumne race JEET li (Pass), camera ne record kiya—"Bohot badhiya, test pass!"

onTestFailure: Lekin agar tum GIR gaye (Fail), toh camera turant tumhari ek Photo (Screenshot) khinch lega, taaki Teacher (Reporter) ko pata chale ki tum kahan aur kyun gire.

Ye Listener wahi camera hai. Ye hamare code ke sath interfere nahi karta, bas chup-chaap baith ke dekhta hai ki test pass hua ya fail, aur fail hone par apne aap screenshot le leta hai.

🎓 SDET Interview Q&A (For 3-4 Years Experience)
Q1: How do you handle screenshots in your framework? Answer: "Sir, main har test script mein screenshot ka code nahi likhta. Maine ek TestNG Listener implement kiya hai. Maine ExtentReportListener class banayi jo ITestListener interface ko use karti hai. Isme ek method hota hai onTestFailure. Jaise hi koi test fail hota hai, TestNG automatically is method ko trigger kar deta hai, jo aage hamare TestUtil class se screenshot khinch leta hai. Isse code repeat nahi hota (DRY Principle)."

Q2: How do you register this Listener in your project? Answer: "Do tarike hain:

testng.xml: <listeners> tag ka use karke pure suite ke liye.

Class Level: Class ke upar @Listeners(ExtentReportListener.class) annotation laga kar."

Q3: What happens if your browser crashes before the screenshot is taken? Answer: "Agar browser crash ho gaya (Session ID null ho gaya), toh driver.getScreenshotAs() method WebDriverException throw karega. Isliye hum hamesha screenshot code ko try-catch block mein rakhte hain taaki poora framework crash na ho."

🚀 Follow-up Questions (Next Level)
Expert Question: "Can we use Listeners with Cucumber?"

Answer: "Yes! Agar hum TestNG ke sath Cucumber chala rahe hain (using TestRunner), toh TestNG listeners tab bhi kaam karenge. Lekin Cucumber ka apna 'Hooks' (@After) bhi hota hai jo same kaam kar sakta hai."

Expert Question: "Difference between ITestListener and IRetryAnalyzer?"

Answer: "ITestListener sirf results ko monitor karta hai (Pass/Fail). IRetryAnalyzer failed tests ko re-run (dobara chalane) ke liye use hota hai."

✅ Checklist for You:
Aapne Interview_Notes folder mein ye note save kar liya?

Kya aapke TextBoxPage.java mein extends BaseClass likhne se red error chala gaya?

Kya aapne pom.xml mein commons-io ki dependency daal di?

📖 Tutor Note: Git & GitHub (The "Time Machine")
Bachche ke liye explanation (Hinglish):

Git (The Time Machine): Socho tum ek painting bana rahe ho. Har 10 minute baad tum ek button dabate ho jo us painting ki photo khinch leta hai. Agar tumne painting kharab kar di, toh tum button daba kar 10 minute pehle wali painting par wapas ja sakte ho. Ye "Local" kaam Git karta hai.

GitHub (The Cloud Bank): Ye ek online locker hai. Tumne jo painting (Code) banayi, usse internet par is locker mein daal diya. Ab tum duniya ke kisi bhi computer se apna locker khol kar wo painting nikal sakte ho.

📝 Folder: Interview_Notes
File Name: 06_Git_and_GitHub_Basics.txt

Content:

Git: Version Control System (Local machine par code track karta hai).

GitHub: Cloud-based hosting service (Code ko online store karta hai).

Basic Commands:

git init: Naya folder (repository) banane ke liye.

git add .: Saare naye changes ko line mein khada karne ke liye (Staging).

git commit -m "message": Changes ko pakka (save) karne ke liye.

git push: Code ko GitHub par bhejne ke liye.

🎓 SDET Interview Q&A (For 3-4 Years Experience)
Q1: What is the difference between git pull and git fetch? Answer: "git fetch sirf remote server se information lata hai ki kya naya badlav hua hai, lekin wo aapka local code change nahi karta. Jabki git pull fetch bhi karta hai aur sath hi sath wo naye code ko aapke local code ke sath merge (jod) bhi deta hai."

Q2: How do you handle "Merge Conflicts" in your framework? Answer: "Merge conflict tab hota hai jab do log same file ki same line change karte hain. Isse solve karne ke liye main conflict wali file kholta hoon, decide karta hoon ki kaunsa code rakhna hai, manually change karta hoon, aur phir git add aur git commit karke resolve karta hoon."

Q3: What is a .gitignore file and why is it important? Answer: ".gitignore ek aisi file hai jisme hum wo files ya folders likhte hain jo hum GitHub par nahi bhejna chahte. Jaise target/ folder (compiled code), .settings, ya password wali config.properties files. Isse repository saaf rehti hai."

🚀 Follow-up Questions (Next Level)
Expert Question: "What is 'Git Rebase' and how is it different from 'Git Merge'?"

Answer: "Merge do branches ko jodta hai aur ek naya commit banata hai. Rebase puri commit history ko rewrite karta hai taaki history ek linear line mein dikhe."

Expert Question: "How do you revert a commit that has already been pushed to the remote server?"

Answer: "Main git revert <commit_id> command use karta hoon. Ye purane commit ko delete nahi karta balki uske opposite ek naya commit banata hai jo purane changes ko undo kar deta hai. Isse history safe rehti hai."