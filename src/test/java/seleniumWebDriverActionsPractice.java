package seleniumWebDriverDemo;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

public class seleniumWebDriverActionsPractice {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod()
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    //    working with select drop down
    @Test(priority = 1)
    public void webDriverDifferentActions() {
        driver.get("https://rahulnxttrends.ccbp.tech/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(25));
//        working with input text element
        driver.findElement(By.name("name")).sendKeys("Ramprasad");
        Reporter.log("Name entered as 'Ramprasad' successfully");
        WebElement selectCountryName = driver.findElement(By.id("country"));
        wait.until(ExpectedConditions.visibilityOf(selectCountryName));
//        Working with select options
        Select selectCountry = new Select(selectCountryName);
        selectCountry.selectByVisibleText("India");
        Reporter.log("Country Drop down selected by visible text as 'India'");
//        working with radio buttons
        driver.findElement(By.id("male")).click();
        Reporter.log("Radio button male is selected successfully");
//        working with checkbox
        driver.findElement(By.id("terms")).click();
        Reporter.log("Check box checked successfully");
        driver.close();
    }

    @Test(priority = 2)
    public void workingWithIframes() throws IOException {
        driver.get("https://qanxtapps.ccbp.tech/");
        WebElement blogHub = driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Blogs Hub\"]"));


        File blogHubFile = blogHub.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(blogHubFile, new File(".//Screenshot/blogHubFile.png"));
        driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Blogs Hub\"]")).click();
        Reporter.log("Blogs Hub tab heading screen shot taken and saved  successfully");
        driver.switchTo().frame("appFrameCard");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("user-name"))));
        System.out.println(driver.findElement(By.className("user-name")).getText());
        Reporter.log("Blogs Hub iframe heading printed successfully");
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Recipe Application\"]")).click();
        driver.switchTo().frame("appFrameCard");
        System.out.println(driver.findElement(By.tagName("h1")).getText());
        Reporter.log("Recipe Application iframe heading printed successfully");
        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Todo Application\"]")).click();
        driver.switchTo().frame("appFrameCard");
        System.out.println(driver.findElement(By.tagName("h1")).getText());
        Reporter.log("Todo Application iframe heading printed successfully");
        driver.close();
    }

    @Test(priority = 3)
    public void workingWithWindows() {
        driver.get("https://qahndlwindows.ccbp.tech/");
        driver.findElement(By.id("newWindow")).click();
        String parentWindow = driver.getWindowHandle();
        Set<String> childWindows = driver.getWindowHandles();
        for (String childWindow : childWindows) {
            if (!(childWindow.equals(parentWindow))) {
                driver.switchTo().window(childWindow);
                driver.findElement(By.name("q")).sendKeys("child window");
                driver.close();
                driver.switchTo().window(parentWindow);
            }
        }
        Reporter.log("Switching from parent window to child window performed successfully");
        driver.findElement(By.id("newTab")).click();
        Set<String> childWindowsTab = driver.getWindowHandles();
        for (String childWindowTab : childWindowsTab) {
            if (!(childWindowTab.equals(parentWindow))) {
                driver.switchTo().window(childWindowTab);
                driver.findElement(By.name("q")).sendKeys("child table");
                driver.close();
                driver.switchTo().window(parentWindow);
                Actions move = new Actions(driver);
                WebElement moveToEle = driver.findElement(By.id("newFlipkartWindow"));
                move.moveToElement(moveToEle).perform();

            }
        }
        Reporter.log("Switching from parent tab to child tab performed successfully");
        driver.close();
    }

    @Test(priority = 4)
    public void moreWebDriverActions() {
        driver.get("https://qadraganddrop.ccbp.tech/");
        driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Blogs Hub\"]")).click();
        driver.switchTo().frame("appFrameCard");
        driver.findElement(By.linkText("Home")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 500)");
        Reporter.log("Scrolled inside Blogs hub iframe by 500 pixels bottom");
        driver.switchTo().defaultContent();
        Actions dragDrop = new Actions(driver);
        WebElement blogsHubSourceEle = driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Blogs Hub\"]"));
        WebElement recipeApplicationTargetEle = driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Recipe Application\"]"));
        dragDrop.dragAndDrop(blogsHubSourceEle, recipeApplicationTargetEle).perform();
        Reporter.log("Blogs Hub & Recipe Application tabs are interchanged by performing drag and drop");
        WebElement recipeApplicationSourceEle = driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Recipe Application\"]"));
        WebElement todoApplicationTargetEle = driver.findElement(By.xpath("//div[@class=\"card\"]//h3[normalize-space()=\"Todo Application\"]"));
        dragDrop.dragAndDrop(recipeApplicationSourceEle, todoApplicationTargetEle).perform();
        Reporter.log("Recipe Application & To do Application tabs are interchanged by performing drag and drop");
        driver.close();
    }
}
