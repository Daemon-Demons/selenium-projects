package PlayYoutubeVideo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.JavascriptExecutor;
//import java.time.Duration;

public class PlayYoutubeVid {
    public static void main(String[] args) {
        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);

        try {
            // Open YouTube
            driver.get("https://www.youtube.com");

            // Accept cookies if the prompt shows (optional)
            try {
                WebElement acceptBtn = driver.findElement(By.xpath("//button[contains(text(),'Accept')]"));
                acceptBtn.click();
            } catch (Exception e) {
                // ignore if not found
            }

            // Find search box and search for the song
            WebElement searchBox = driver.findElement(By.name("search_query"));
            searchBox.sendKeys("Oruvan Oruvan - 4K Video Song");
            searchBox.submit();

            // Wait for results to load
            Thread.sleep(2000);

            // Click the first video in the list
            WebElement firstVideo = driver.findElements(By.id("video-title")).get(0);
            firstVideo.click();

            // Let the video play for a while
            Thread.sleep(30000); // 30 seconds

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
