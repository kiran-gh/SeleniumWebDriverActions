import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.time.Duration;

public class IFramesDemo {
    WebDriver driver;

    // Test to practice handling iframes and navigating through different web elements
    @Test(priority = 1)
    public void testIframesAndNavigation() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "src/test/java/webdriver/chromedriver.exe");

        // Initialize Chrome WebDriver and set up browser properties
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the application URL
        driver.get("https://qanxtapps.ccbp.tech/");

        // Select the first card on the page
        driver.findElement(By.cssSelector("div[class='card']")).click();

        // Switch to the first iframe (identified by its name 'appFrameCard')
        driver.switchTo().frame("appFrameCard");

        // Add a to-do item to the input field and click the add button
        driver.findElement(By.id("todoUserInput")).sendKeys("Running");
        driver.findElement(By.id("addTodoButton")).click();

        // Switch back to the main content from iframe
        driver.switchTo().defaultContent();

        // Click on the "Blogs Hub" section
        driver.findElement(By.xpath("//h3[normalize-space()='Blogs Hub']")).click();

        // Switch to the next iframe by index (index 0)
        driver.switchTo().frame(0);

        // Click on the "About" link inside the iframe
        driver.findElement(By.linkText("About")).click();

        // Switch back to the main content again
        driver.switchTo().defaultContent();

        // Click on the "Recipe Application" section
        driver.findElement(By.xpath("//h3[normalize-space()='Recipe Application']")).click();

        // Switch to iframe by locating the element using its ID
        driver.switchTo().frame(driver.findElement(By.id("appFrameCard")));

        // Click on the "Main Courses" item inside the iframe
        driver.findElement(By.xpath("//li[normalize-space()='Main Courses']")).click();
    }
}