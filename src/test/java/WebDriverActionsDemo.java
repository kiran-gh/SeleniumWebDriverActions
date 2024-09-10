import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverActionsDemo {
    WebDriver driver;
    WebDriverWait wait;

    // Setup method to initialize WebDriver and maximize the browser window
    @BeforeMethod
    public void setup() {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/test/java/webdriver/chromedriver.exe");

        // Initialize ChromeDriver and maximize the window
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void performWebDriverActions() {
        // Navigate to the drag and drop web page
        driver.get("https://qadraganddrop.ccbp.tech/");

        // Click on the "Blogs Hub" card to open the frame
        WebElement blogsHubCard = driver.findElement(By.xpath("//div[@class='card']//h3[normalize-space()='Blogs Hub']"));
        blogsHubCard.click();

        // Switch to iframe containing the content for Blogs Hub
        driver.switchTo().frame("appFrameCard");

        // Click on the 'Home' link inside the iframe
        driver.findElement(By.linkText("Home")).click();

        // Scroll down within the iframe by 500 pixels using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 500)");
        Reporter.log("Scrolled inside Blogs Hub iframe by 500 pixels");

        // Switch back to the default content from the iframe
        driver.switchTo().defaultContent();

        // Initialize Actions class to perform drag and drop actions
        Actions actions = new Actions(driver);

        // Perform drag and drop: Blogs Hub -> Recipe Application
        WebElement blogsHubSource = driver.findElement(By.xpath("//div[@class='card']//h3[normalize-space()='Blogs Hub']"));
        WebElement recipeAppTarget = driver.findElement(By.xpath("//div[@class='card']//h3[normalize-space()='Recipe Application']"));
        actions.dragAndDrop(blogsHubSource, recipeAppTarget).perform();
        Reporter.log("Dragged and dropped Blogs Hub to Recipe Application");

        // Perform drag and drop: Recipe Application -> Todo Application
        WebElement recipeAppSource = driver.findElement(By.xpath("//div[@class='card']//h3[normalize-space()='Recipe Application']"));
        WebElement todoAppTarget = driver.findElement(By.xpath("//div[@class='card']//h3[normalize-space()='Todo Application']"));
        actions.dragAndDrop(recipeAppSource, todoAppTarget).perform();
        Reporter.log("Dragged and dropped Recipe Application to Todo Application");
    }
}
