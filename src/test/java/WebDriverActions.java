import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class WebDriverActions {
    private WebDriver driver;
    private WebDriverWait wait;

    // Set up Chrome WebDriver before tests
    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));  // Implicit wait for element loading
    }

    // Perform various actions on the web page
    @Test(priority = 1)
    public void performActions() {
        driver.get("https://rahulnxttrends.ccbp.tech/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        // Enter text into input fields (Name, Email, Password)
        driver.findElement(By.name("name")).sendKeys("Ramprasad");
        Reporter.log("Entered Name: 'Ramprasad'");
        driver.findElement(By.name("email")).sendKeys("Ramprasad@gmail.com");
        Reporter.log("Entered Email: 'Ramprasad@gmail.com'");
        driver.findElement(By.name("password")).sendKeys("Ramprasad@1875");
        Reporter.log("Entered Password");

        // Wait until country dropdown is visible and select 'India'
        WebElement countryDropdown = driver.findElement(By.id("country"));
        wait.until(ExpectedConditions.visibilityOf(countryDropdown));
        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText("India");
        Reporter.log("Selected Country: 'India'");

        // Select 'Male' radio button
        driver.findElement(By.id("male")).click();
        Reporter.log("Selected Radio Button: 'Male'");

        // Check the 'Terms and Conditions' checkbox
        driver.findElement(By.id("terms")).click();
        Reporter.log("Checked 'Terms and Conditions' checkbox");
    }

    // Tear down Chrome WebDriver after tests
    @AfterTest
    public void tearDown() {
        // Close the browser and end the session
        if (driver != null) {
            driver.quit();
        }
    }
}