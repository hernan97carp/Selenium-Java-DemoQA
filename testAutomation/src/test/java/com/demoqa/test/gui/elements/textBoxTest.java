package com.demoqa.test.gui.elements;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class textBoxTest {
    public String fullName = "Hernan Esquivel";
    
    private WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://demoqa.com/text-box");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    
    @Test
    public void submitForm() {
        driver.findElement(By.id("userName")).sendKeys(fullName);
        driver.findElement(By.id("userEmail")).sendKeys("HernanQA@hotmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("Argentina");
        driver.findElement(By.id("permanentAddress")).sendKeys("Buenos Aires");
       
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
        submitButton.click();
        
        WebElement element = driver.findElement(By.id("name"));
        String fullTextName = element.getText();
        String[] parts = fullTextName.split(":");
        String nameText = parts[1].trim();
        
        System.out.println(nameText);
        Assert.assertEquals(nameText, fullName);
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
