import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class FileUpload {
    ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/C:/Users/anyut/chromedriver.exe");
        driver = new ChromeDriver();
        String BASE_URL = "http://suninjuly.github.io/file_input.html";
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void upLoadFieldTest() {
        WebElement firstName = driver.findElement(By.cssSelector("[name='firstname']"));
        firstName.sendKeys("Jhon");
        WebElement lastName = driver.findElement(By.cssSelector("[name='lastname']"));
        lastName.sendKeys("Travolta");
        WebElement email = driver.findElement(By.cssSelector("[name='email']"));
        email.sendKeys("anyutatel@gmail.com");
        WebElement uploadFileButton = driver.findElement(By.id("file"));
        uploadFileButton.sendKeys("C:/Users/anyut/Documents/Ter-Lan/test.txt");
        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        //System.out.println("Alert text:" + alert.getText());
        assertTrue(alert.getText().contains("Congrats, you've passed the task!"));
        //alert.accept();
    }







    @After
    public void TearDown() {
        driver.quit();
    }

}
