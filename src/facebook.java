package sapling;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class facebook {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "/Users/rahulmittapalli/Downloads/Selenium_drivers/chromedriver");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.facebook.com");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Properties prop=new Properties();
		FileInputStream fis =new FileInputStream("/Users/rahulmittapalli/eclipse-workspace/Sample/src/sapling/datadriven.properties");		
		prop.load(fis);		
		driver.findElement(By.id("email")).sendKeys(prop.getProperty("email"));
		driver.findElement(By.id("pass")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		//clicking on pages sidebar 
		WebElement sidebar=driver.findElement(By.id("appsNav"));
		sidebar.findElement(By.linkText("Pages")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Liked Pages")).click();
		try {
			WebDriverWait wait =new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("See More")));
			driver.findElement(By.linkText("See More")).click();	
		}
		catch(Exception e)
		{
			System.out.println("No seemore button is displayed");
		}
		facebook fb = new facebook();
		int c1=fb.seemorepages(driver);
		System.out.println("c1 value is "+c1);
		//fb.unlikepage(driver);
		fb.scrollingtobottom(driver);
		int c2=fb.remainingElements(driver);
		System.out.println("c2 value is "+c2);
		fb.finalcount(c1, c2);
		driver.close();
		//
	}

	public int seemorepages(WebDriver driver) {
		// clicking on seemore button
		WebElement top = driver.findElement(By.className("_7wjh"));
		int firstcount=0;
		List<WebElement> text = top
				.findElements(By.xpath("//li[contains(@class,'_81m5')]/div/div/div/div/div/a/span[1]"));
		// final size
		System.out.println("size is " + text.size());
		for (int i = 0; i < text.size(); i++) {
			System.out.println(text.get(i).getText());
			firstcount++;
		}
		return firstcount;

	}

	public void unlikepage(WebDriver driver) throws InterruptedException {
		// clicking on liked button
		WebElement top = driver.findElement(By.className("_7wjh"));
		List<WebElement> unlike = top
				.findElements(By.xpath("//li[contains(@class,'_81m5')]/div/div/div[2]/div/button"));
		System.out.println("unlike size is " + unlike.size());
		for (int i = 0; i < 5; i++) {
			System.out.println("inside values");
			unlike.get(i).click();
			Thread.sleep(500);
			System.out.println("clicked on " + i);
		}

	}

	public void scrollingtobottom(WebDriver driver) throws InterruptedException {
		// scrolling
		JavascriptExecutor js = (JavascriptExecutor) driver;
		while (!driver.getPageSource().contains("End of Liked Pages")) {
			js.executeScript("window.scrollBy(0,1000)");
			}
	}

	public int remainingElements(WebDriver driver) throws InterruptedException {
		// finding the remaining elements
		WebElement Hello = driver.findElement(By.id("all_liked_pages"));
		int remainingcount=0;
		WebElement intialpage = Hello.findElement(By.className("_7wjh"));
		List<WebElement> pages = intialpage
				.findElements(By.xpath("//div[contains(@class,'_7vum')]/div/div/div/div/div/a/span[1]"));
		int count = pages.size();
		//System.out.println("remaining count is " + count);
		// fetching text from the liked pages
		for (int j = 0; j < count; j++) {
			System.out.println(pages.get(j).getText());
			remainingcount++;
		}
		return remainingcount;
	}
	public void finalcount(int a,int b)
	{
		int sum=0;
		sum=a+b;
		System.out.println("the firstcount value is "+a);
		System.out.println("the Secondcount value is "+b);
		System.out.println("sum of total links is "+sum);
	}

}
