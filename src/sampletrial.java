

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.util.List;

public class sampletrial {
    @Test

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
}