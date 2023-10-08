package FindBrokenLinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FindBrokenLinks {
	
	
	WebDriver driver;

	@BeforeTest
	public void setUp() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "/Users/mehediredoy/eclipse-workspace/BrokenLinks/drivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Thread.sleep(3000);

	}

	@Test
	public void searchResuktDisplayed() {



		List<WebElement> links = driver.findElements(By.tagName("a"));

		for (WebElement link : links) {

			String url = link.getAttribute("href");
			
			System.out.println("--------------");
			System.out.println("url");

			if (url == null || url.isEmpty()) {

				System.out.println("URL is Empty!");

				continue;

			}

			try {

				HttpURLConnection huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.connect();

				if (huc.getResponseCode() >= 400) {

					System.out.println(url + " is broken");

				} else {

					System.out.println(url + " is valid");

				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		
		
	}

	@AfterTest
	public void tearDown() {

		driver.close();
		driver.quit();

	}

	
	
	
	

}
