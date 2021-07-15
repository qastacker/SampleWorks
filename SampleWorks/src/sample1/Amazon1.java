package sample1;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon1 {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C://Users//Adol-sys-410//Downloads//ujars//chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get("https://www.amazon.in/");

		System.out.println(driver.getTitle());

//     Actions a= new Actions(driver);       

//        WebElement move=driver.findElement(By.cssSelector("a[id='nav-link-accountList']"));

		driver.findElement(By.cssSelector("a[id='nav-link-accountList']")).click();

		driver.findElement(By.cssSelector("input#ap_email")).sendKeys("azharhot04@gmail.com");

		driver.findElement(By.cssSelector("input#continue.a-button-input")).click();

		driver.findElement(By.cssSelector("#ap_password")).sendKeys("victorfly");

		driver.findElement(By.xpath("//*[@id='remember_me_learn_more_link']")).click();

		driver.findElement(By.xpath("//i[@class='a-icon a-icon-close']")).click();

		driver.findElement(By.xpath(("//input[@name='rememberMe']"))).click();

		Thread.sleep(2500);

		driver.findElement(By.cssSelector("input#signInSubmit")).click();

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).click();

		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("mobiles");

		driver.findElement(By.xpath("//input[@class='nav-input']")).click();
		Thread.sleep(2500);
		driver.findElement(By.xpath("//img[@alt='Redmi 7 (Comet Blue, 3GB RAM, 32GB Storage)']")).click();

		Thread.sleep(2500);

		Set<String> ids = driver.getWindowHandles();

		java.util.Iterator<String> it = ids.iterator();

		String parentid = it.next();

		String childid = it.next();

		driver.switchTo().window(childid);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,500)");

		// driver.findElement(By.xpath("//span[text()='2GB RAM']")).click();

		Thread.sleep(2500);

		driver.findElement(By.xpath("//div[@class='twisterTextDiv text']//span[text()='2GB RAM']")).click();

		System.out.println(
				driver.findElement(By.xpath("//div[@class='twisterTextDiv text']//span[text()='2GB RAM']")).getText());

	}

}