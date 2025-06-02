package SpeedTest;

import org.openqa.selenium.chrome.*;
import org.openqa.selenium.WebDriver;

public class SpeedTest {
    public static void main(String[] args) {
        // Set the path to chromedriver executable
        System.setProperty("webdriver.chrome.driver", "chromedriver/chromedriver.exe");

        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open fast.com
        driver.get("https://fast.com");

        // Optional: wait for a few seconds to see the speed test start
        try {
            Thread.sleep(10000); // 10 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }
}