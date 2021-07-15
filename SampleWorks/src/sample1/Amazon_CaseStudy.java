package sample1;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Amazon_CaseStudy {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException, IOException {
// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "C://Users//Adol-sys-410//Downloads//ujars//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();

// select Electronics from drop down
		Select select = new Select(driver.findElement(By.id("searchDropdownBox")));
		select.selectByVisibleText("Electronics");

// enter earphones in search box text in search box and select the third
// option from auto sugesstive drop down
		driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("earphones");
		Thread.sleep(9000);

		List<WebElement> list = driver.findElements((By.xpath("//div[@id='nav-flyout-iss-anchor']/div/div/div/div")));

		System.out.println("Size is" + list.size());
// to select 2nd Option from auto suggestive drop down
		for (int i = 0; i < list.size(); i++) {

			if (i == 2) {
				System.out.println(list.get(i).getText());
				String result = list.get(i).getText();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				list.get(i).click();
				System.out.println("You have clicked : " + result);

			}
		}

		try (OutputStream filestream = new BufferedOutputStream(new FileOutputStream("E://Write.txt")); // .csv ->file
				Writer outStreamWriter = new OutputStreamWriter(filestream, StandardCharsets.UTF_8);
				BufferedWriter buffWriter = new BufferedWriter(outStreamWriter))

		{

// Get the list of all items along with their products in webpage
			ArrayList<WebElement> box = (ArrayList<WebElement>) driver
					.findElements(By.cssSelector(".s-include-content-margin.s-border-bottom.s-latency-cf-section"));

			int j = 1;
			System.out.println(box.size());
			for (int i = 0; i < box.size(); i++) {

				WebElement body = box.get(i);
				String item = body.findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']"))
						.getText();

				List<WebElement> priceTag = body.findElements(By.xpath(".//span[@class='a-price-whole']"));

				if (priceTag.size() != 0) {

// If list size is non-zero, price is present

					System.out.println(j + "-->" + item + "Price present " + ": Rs." + priceTag.get(0).getText());
					buffWriter.append(j + "-->" + item + "Price present " + ": Rs." + priceTag.get(0).getText());
					// buffWriter.append(separator);

					buffWriter.newLine();

				}

				else {

// Else if size is 0, then price is not present

					System.out.println(j + "-->" + item + ":" + "Price Not available in website");

					buffWriter.append(j + "-->" + item + ":" + "Price Not available in website");
					buffWriter.newLine();
				}

				buffWriter.flush();
				j++;
			}

		}
	}
}