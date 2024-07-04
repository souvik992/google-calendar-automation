package demo;

import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("calendar")) {
            System.out.println("The URL of the Calendar homepage contains \"calendar\".");
        } else {
            System.out.println("The URL of the Calendar homepage doesn't contain \"calendar\".");
        }
    }

    public void testCase02() {
        try {
            System.out.println("Start Test case: testCase02");
            driver.get("https://calendar.google.com/");

            WebElement signInButton =
            driver.findElement(By.xpath("//span[contains(text(),'Sign in')]"));
            signInButton.click();
            Thread.sleep(10000);

            WebElement emailArea = driver.findElement(By.id("identifierId"));

            emailArea.sendKeys("");
            WebElement nextButton = driver.findElement(By.id("identifierNext"));
            nextButton.click();
            Thread.sleep(10000);
            WebElement passwordField =
            driver.findElement(By.xpath("//input[@name='Passwd']"));

            passwordField.sendKeys("Asdfzx#007");
            WebElement passwordNextButton = driver.findElement(By.id("passwordNext"));
            passwordNextButton.click();
            Thread.sleep(5000);

            JavascriptExecutor js = (JavascriptExecutor) driver;

            WebElement switcherMenu = driver.findElement(By.className("XyKLOd"));
            switcherMenu.click();
            // js.executeScript("arguments[0].click();", switcherMenu);

            WebElement monthOption = driver.findElement(By.xpath("//span[contains(text(),'Month')]"));
            monthOption.click();
            String expectedView = "Month";
            WebElement currentView = driver.findElement(By.xpath("(//span[@class='VfPpkd-vQzf8d'])[5]"));
            String monthView = currentView.getText();
            if(monthView.equals(expectedView)){
                System.out.println("current view is Month");
            }

            WebElement createTab = driver.findElement(By.className("mr0WL"));
            createTab.click();
            // js.executeScript("arguments[0].click();", createTab);
            Thread.sleep(2000);

            WebElement taskTab = driver.findElement(By.xpath("(//div[@class='uyYuVb oJeWuf'])[2]"));
            
            Thread.sleep(5000);
            taskTab.click();

            // js.executeScript("arguments[0].click();", taskTab);

            Thread.sleep(5000);

            WebElement taskTitle = driver.findElement(By.xpath("//input[@placeholder='Add title']"));
            taskTitle.sendKeys("Crio INTV Task Automation");

            WebElement descriptionArea = driver.findElement(By.xpath("//textarea[@type='text']"));
            descriptionArea.sendKeys("Crio INTV Calendar Task Automation");

            WebElement saveButton = driver.findElement(By.xpath("//button[@jsname='x8hlje']"));
            saveButton.click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testCase03(){
        try {
            System.out.println("Start Test case: testCase03");
            driver.get("https://calendar.google.com/");
            
            JavascriptExecutor js = (JavascriptExecutor) driver;

            Thread.sleep(3000);

            WebElement createdTask = driver.findElement(By.xpath("(//div[@class='qLWd9c'])[5]"));
            createdTask.click();
            Thread.sleep(3000);

            WebElement editIcon = driver.findElement(By.xpath("//div[@jsaction='JIbuQc:DyVDA']"));
            editIcon.click();
            Thread.sleep(2000);

            WebElement descriptionArea = driver.findElement(By.xpath("//textarea[@rows='3']"));
            descriptionArea.clear();
            descriptionArea.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");

            WebElement saveButton = driver.findElement(By.xpath("//button[@jsname='x8hlje']"));
            saveButton.click();

            Thread.sleep(3000);

            createdTask.click();

            WebElement taskDescription = driver.findElement(By.xpath("//div[@id='xDetDlgDesc']"));
            String expectedText = "Description:\nCrio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application";
            if(expectedText.equals(taskDescription.getText())){
                System.out.println("Edited description matched!");
            }else{
                System.out.println("Edited description not matched!");
                System.out.println(taskDescription.getText());
            }

            WebElement closeIcon = driver.findElement(By.xpath("//button[@id='xDetDlgCloseBu']"));
            closeIcon.click();


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void testCase04(){
        try {
            System.out.println("Start Test case: testCase03");
            driver.get("https://calendar.google.com/");

            JavascriptExecutor js = (JavascriptExecutor) driver;

            Thread.sleep(3000);

            WebElement createdTask = driver.findElement(By.xpath("(//div[@class='qLWd9c'])[5]"));
            createdTask.click();
            Thread.sleep(3000);

            WebElement verifyTitle = driver.findElement(By.xpath("//span[@id='rAECCd']"));
            String titletext = verifyTitle.getText();
            System.out.println(titletext);

            WebElement deletIcon = driver.findElement(By.xpath("//div[@jsaction='JIbuQc:qAGoT']"));
            deletIcon.click();
            Thread.sleep(4000);

            WebElement verifyDelete = driver.findElement(By.xpath("//div[contains(text(),'Task deleted')]"));
            String expectedMessage = "Task deleted";
            String verifyDeleteMessage = verifyDelete.getText();
            System.out.println(verifyDeleteMessage);
            if(verifyDeleteMessage.equals(expectedMessage)){
                System.out.println("Delete message displayed!");
            }else{
                System.out.println("Delete message Not Displayed!");
            }



        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
