package democalls;

import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.test.scenario.webdrivermethods;

import io.github.bonigarcia.wdm.WebDriverManager;

public class brokenlinks2 {
 
	public static void main(String [] args)
	{
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get("https://www.toolsqa.com/selenium-webdriver/selenium-tutorial/");
	   // driver.findElement(By.id("input")).sendKeys("automation websites");
	    Actions actions = new Actions(driver);
	 //   actions.sendKeys(Keys.ENTER).perform();
	    
	    List<WebElement> links = driver.findElements(By.tagName("a"));
		for(WebElement sitelinks: links)
		{
			String urls = sitelinks.getAttribute("href");
			
		  if (urls != null && !urls.isEmpty() && !urls.startsWith("javascript")) {
                try {
                    URL linkURL = new URL(urls);

                    // Use try-with-resources to ensure connection is closed
                    try (HttpsURLConnection connections = (HttpsURLConnection) linkURL.openConnection()) {
                        connections.setRequestMethod("HEAD"); // Use HEAD for efficiency
                        int responseCode = connection.getResponseCode();

                        if (responseCode != HttpsURLConnection.HTTP_OK) {
                            System.out.println("Broken link: " + urls + " (Response code: " + responseCode + ")");
                        }
                        // No need for explicit disconnect() with try-with-resources
                    } // Connection is automatically closed here

                } catch (IOException e) { // Catch only IOExceptions or subclasses
                    System.out.println("Error checking link: " + urls + " - " + e.getMessage());
                }
            }
			
		}
		
	}
}

