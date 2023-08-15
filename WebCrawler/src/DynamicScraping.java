import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DynamicScraping {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "path/to/AYAN PAL\\\\Downloads\\\\chromedriver_win32/chromedriver"); // Set the path to chromedriver executable
        ChromeDriver driver = new ChromeDriver();

        try {
            String url = "https://www.justdial.com/Kolkata/Marriage-Function-Halls/nct-10035861";
            ((WebDriver) driver).get(url);

            // Wait for the page to load and dynamic content to be populated
            Thread.sleep(5000); // Adjust the sleep time as needed

            // Find the elements containing the dynamic data
            // Example: Extracting titles
            List<WebElement> titleElements = ((WebDriver) driver).findElements(By.cssSelector("div.jsx-4d407376001b01ad.resultbox_textbox h2.resultbox_title"));

            for (WebElement titleElement : titleElements) {
                String title = titleElement.getAttribute("title");
                System.out.println("Title: " + title);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
