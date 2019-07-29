

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;

public class bioluminc {
    @Test

    public void Nametest() throws InterruptedException {
        // TODO Auto-generated method stub
        System.setProperty("webdriver.chrome.driver", "/Users/rahulmittapalli/Downloads/Selenium drivers/chromedriver");
        WebDriver driver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--use-fake-ui-for-media-stream=1");
        options.addArguments("--use-file-for-fake-video-capture=/users/rahulmittapalli/Downloads/example.y4m");
        options.addArguments("--disable-user-media-security");
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("media.default_video_capture_Device", "\\\\?\\root#media#0002#{65e8773d-8f56-11d0-a3b9-00a0c9223196}\\global");
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
        driver.get("https://www.biolumincmirror.com/");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.findElement(By.id("getStarted")).click();
        System.out.println("Clicked");
        Thread.sleep(15000);
        WebElement camera=driver.findElement(By.id("_drawing"));
        System.out.println("Camera is Displayed "+camera.isDisplayed());
        WebElement information=driver.findElement(By.id("_imageData"));
        System.out.println("Image is Displayed "+information.isDisplayed());
        Thread.sleep(5000);
        WebElement product= driver.findElement(By.xpath("//*[@class='m_0 pt_15 LTMD_font']"));
        System.out.println("Product name is "+product.getText());
        String Name="NEW! biolumin-c serum";
        Assert.assertEquals(Name,product.getText());
        WebElement Button=driver.findElement(By.id("mainproduct"));
        System.out.println("Button visibility is "+Button.isDisplayed());
        Thread.sleep(5000);
        driver.close();
    }
}