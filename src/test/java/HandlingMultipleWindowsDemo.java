import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class HandlingMultipleWindowsDemo {

    WebDriver driver;

    // Set up Chrome WebDriver before tests
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));  // Implicit wait for element loading
    }

    @Test(priority = 1)
    public void handleMultipleWindows() {
        // Navigate to the application URL
        driver.get("https://qahndlwindows.ccbp.tech/");

        // Click to open a new window
        driver.findElement(By.id("newWindow")).click();

        // Store the parent window handle
        String parentWindowHandle = driver.getWindowHandle();

        // Get all window handles (parent + child)
        Set<String> allWindows = driver.getWindowHandles();

        // Iterate through all windows
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(parentWindowHandle)) {
                // Switch to the child window
                driver.switchTo().window(windowHandle);

                // Perform search operation in the new window
                driver.findElement(By.name("q")).sendKeys("it's time to show off your skills images", Keys.ENTER);

                // Close the child window after the action
                driver.close();
            }
        }

        // Switch back to the parent window
        driver.switchTo().window(parentWindowHandle);
    }

    @Test(priority = 2)
    public void handleMultipleTabs() {
        // Navigate to the application URL
        driver.get("https://qahndlwindows.ccbp.tech/");

        // Click to open a new window
        driver.findElement(By.id("newTab")).click();

        // Store the parent window handle
        String parentWindowHandle = driver.getWindowHandle();

        // Get all window handles (parent + child)
        Set<String> allWindows = driver.getWindowHandles();

        // Iterate through all windows
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(parentWindowHandle)) {
                // Switch to the child window
                driver.switchTo().window(windowHandle);

                // Perform search operation in the new window
                driver.findElement(By.name("q")).sendKeys("it's time to show off your skills images", Keys.ENTER);

                // Close the child window after the action
                driver.close();
            }
        }

        // Switch back to the parent window
        driver.switchTo().window(parentWindowHandle);
    }

    @Test(priority = 3)
    public void handleFlipkartWindow() {
        // Navigate to the application URL
        driver.get("https://qahndlwindows.ccbp.tech/");

        // Click to open a new window
        driver.findElement(By.id("newFlipkartWindow")).click();

        // Store the parent window handle
        String parentWindowHandle = driver.getWindowHandle();

        // Get all window handles (parent + child)
        Set<String> allWindows = driver.getWindowHandles();

        // Iterate through all windows
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(parentWindowHandle)) {
                // Switch to the child window
                driver.switchTo().window(windowHandle);
                // Close the child window after the action
                driver.close();
            }
        }

        // Switch back to the parent window
        driver.switchTo().window(parentWindowHandle);
    }

    // Tear down Chrome WebDriver after tests
    @AfterTest
    public void tearDown() {
        // Close the parent window and quit the browser session
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}