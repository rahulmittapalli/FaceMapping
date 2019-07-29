
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.util.List;

public class Facemappinghome {

    WebDriver driver = null;
    ExtentReports reports;
    ExtentTest testInfo;
    ExtentHtmlReporter htmlReporter;

    @BeforeTest

    public void setup() {
        htmlReporter = new ExtentHtmlReporter(new File(System.getProperty("user.home") + "/Desktop/AutomationReports.html"));
        htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir")) + "/extent-config.xml");
        reports = new ExtentReports();
        reports.setSystemInfo("Sample", "QA");
        reports.attachReporter(htmlReporter);
    }
   @Test
    public void chrome() throws InterruptedException {
        //this.driver=driver;
        Nametest();
        testInfo.log(Status.INFO, "Facemapping Translation is Working");
    }

    public void Nametest() throws InterruptedException {
        // TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", "/Users/rahulmittapalli/Downloads/Selenium drivers/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://pst.facemapping.com");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement value = driver.findElement(By.cssSelector(".text-center.cf"));
        String text = value.getText();
        System.out.println("value is " + text);
        if (text.equals("face mapping® skin analysi")) {
            System.out.println("Translation file is not loaded");
            driver.close();
        } else {
            Thread.sleep(2000);
            driver.findElement(By.id("email")).sendKeys("mittapalli.rahul@gmail.com");
            driver.findElement(By.id("passwd")).sendKeys("12345678");
            driver.findElement(By.xpath("//input[@type='submit']")).click();
            Thread.sleep(5000);
            System.out.println(driver.getCurrentUrl());
            List<WebElement> information = driver.findElements(By.cssSelector(".title.text-center"));
            System.out.println(information.get(0).getText());
            if (information.get(0).getText().equals("professional skin therapist1")) {
                System.out.println("Translation file is not loaded");
                driver.close();
            } else {
                System.out.println(information.get(1).getText());
                if (information.get(1).getText().equals("clients")) {
                    System.out.println("Translation file is not loaded");
                    driver.close();
                } else {
                    Thread.sleep(5000);
                    driver.findElement(By.xpath("//*[@placeholder='Select or Search client']")).click();
                    List<WebElement> clients = driver.findElements(By.tagName("li"));
                    System.out.println("Clients Size is " + clients.size());
                    for (int i = 0; i < clients.size(); i++) {
                        System.out.println("Clients are " + clients.get(i).getText());
                        if (clients.size() >= 1) {
                            clients.get(1).click();
                            break;
                        } else if (clients.size() == 0) {
                            System.out.println("No clients Found");
                            break;
                        } else {
                            clients.get(1).click();
                            break;
                        }
                    }
                    Thread.sleep(5000);
                    driver.findElement(By.cssSelector(".glyphicon.glyphicon-menu-left")).click();
                    List<WebElement> information2 = driver.findElements(By.cssSelector(".title.text-center"));
                    System.out.println(information2.get(1).getText());
                    if (information2.get(1).getText().equals("clients")) {
                        System.out.println("Translation file is not loaded");
                        driver.close();
                    } else {
                        Thread.sleep(2000);
                        List<WebElement> tag = driver.findElements(By.tagName("a"));
                        for (int i = 0; i < tag.size(); i++) {
                            if (tag.get(i).getText().contains(" sign out")) {
                                tag.get(i).click();
                                break;
                            }
                        }
                        Thread.sleep(5000);
                        driver.close();
                    }
                }
            }
        }
    }

    @BeforeMethod
    public void register(Method method)
    {
        String testName=method.getName();
        testInfo=reports.createTest(testName);
    }
    @AfterMethod
    public void status(ITestResult result) throws Exception {
        if(result.getStatus()==ITestResult.SUCCESS)
        {
            testInfo.log(Status.PASS,"The test method named as "+result.getName()+ " is passed");
        }
        else if(result.getStatus()==ITestResult.FAILURE)
        {
            testInfo.log(Status.FAIL,"The test method named as "+result.getName()+ " is failed");
            testInfo.log(Status.FAIL,"Testfailure"+ result.getThrowable());
            String screenshotPath = screenshot.getScreenshot(driver,"Screenshot");
            //To add it in the extent report
            testInfo.log(Status.FAIL, result.getThrowable());
            testInfo.log(Status.FAIL,"Snapshot below is "+testInfo.addScreenCaptureFromPath(screenshotPath));

        }
        else if(result.getStatus()==ITestResult.SKIP)
        {
            testInfo.log(Status.SKIP,"The test method named as "+result.getName()+ " is skipped");
        }
    }
    @AfterTest
    public void cleanup()
    {
        reports.flush();
    }

}

