package sample1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class runTaskUsingTimer {

	private static class MyTimeTask1 extends TimerTask {

		public void run() {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();

			driver.get("http://demo.automationtesting.in/Register.html");
			driver.manage().window().maximize();
			driver.findElement(
					By.xpath("//body/section[@id='section']/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/input[1]"))
					.sendKeys("Dev");
			driver.findElement(
					By.xpath("//body/section[@id='section']/div[1]/div[1]/div[2]/form[1]/div[1]/div[2]/input[1]"))
					.sendKeys("Dev");
			WebElement address = driver.findElement(By.tagName("textarea"));
			address.sendKeys("This is the first line of address" + "This is the second line of the address"
					+ "This is the third line of the address");
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys("sample@gmail.com");
			driver.findElement(By.xpath("//input[@type='tel']")).sendKeys("87268724939");
			driver.findElement(By.xpath("//input[@type='radio' and @value='Male']")).click();
			JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement Element = driver.findElement(By.xpath("//label[contains(text(),'Country*')]"));
			je.executeScript("arguments[0].scrollIntoView();", Element);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement selectCountry = driver.findElement(By.xpath("//select[@id='countries']"));
			selectCountry.click();
			Select dropdown = new Select(selectCountry);
			dropdown.selectByValue("India");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.close();
		}
	}

	private static class MyTimeTask2 extends TimerTask {

		public void run() {
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();

			driver.get("https://demoqa.com/menu/");
			System.out.println("demoqa webpage Displayed");

			// Maximise browser window
			driver.manage().window().maximize();

			// Adding wait
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

			// Instantiate Action Class
			Actions actions = new Actions(driver);
			// Retrieve WebElement 'Music' to perform mouse hover
			WebElement menuOption = driver.findElement(By.xpath("//div[contains(text(),'Main Item 2')]"));
			// Mouse hover menuOption 'Music'
			actions.moveToElement(menuOption).perform();
			System.out.println("Done Mouse hover on 'Main Item 2' from Menu");

			// Now Select 'Rock' from sub menu which has got displayed on mouse hover of
			// 'Music'
			WebElement subMenuOption = driver.findElement(By.xpath("//div[contains(text(),'SUB SUB LIST »')]"));
			// Mouse hover menuOption 'Rock'
			actions.moveToElement(subMenuOption).perform();
			System.out.println("Done Mouse hover on 'SUB SUB LIST »' from Menu");

			// Now , finally, it displays the desired menu list from which required option
			// needs to be selected
			// Now Select 'Alternative' from sub menu which has got displayed on mouse hover
			// of 'Rock'
			WebElement selectMenuOption = driver.findElement(By.xpath("//div[contains(text(),'Sub Sub Item 2')]"));
			selectMenuOption.click();
			System.out.println("Selected 'Sub Sub Item 2' from Menu");

			// Close the main window
			driver.close();
		}
	}

	public static void main(String[] args) throws ParseException {

		// the Date and time at which you want to execute
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = dateFormatter.parse("2012-07-06 13:05:45");

		// Now create the time and schedule it
		Timer timer = new Timer();

		// Use this if you want to execute it once
		// timer.schedule(new MyTimeTask1(),date);

		// Use this if you want to execute it repeatedly
		int period = 120000;// 120secs
		 timer.schedule(new MyTimeTask1(), date, period);
		//timer.schedule(new MyTimeTask2(), date, period);
	}

}
