package PlayYoutubeVideo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PlayYoutubeVid {
    public static void main(String[] args) {
        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open YouTube
            driver.get("https://www.youtube.com");

            // Accept cookies if the prompt shows (optional)
            try {
                WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Accept')]")));
                acceptBtn.click();
            } catch (Exception e) {
                // ignore if not found
            }

            // Find search box and search for the song
            WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("search_query")));
            searchBox.sendKeys("Oruvan Oruvan - 4K Video Song");
            searchBox.submit();

            // Click the first video in the list
            WebElement firstVideo = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@id='video-title']")));
            firstVideo.click();

            // Wait for possible ad and try to skip it
            try {
                Thread.sleep(5000); // Give time for ad to load
                List<WebElement> skipButtons = driver.findElements(By.className("ytp-ad-skip-button-modern"));
                if (!skipButtons.isEmpty()) {
                    skipButtons.get(0).click();
                }
            } catch (Exception e) {
                // Skip button not found or not clickable
            }

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
