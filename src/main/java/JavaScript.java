import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.opentelemetry.api.internal.ValidationUtil.log;
import static java.lang.Double.parseDouble;
import static java.lang.Math.abs;
import static java.lang.Math.sin;
import static org.junit.Assert.assertTrue;

public class JavaScript {

    ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/C:/Users/anyut/chromedriver.exe");
        driver = new ChromeDriver();
        String BASE_URL = "http://suninjuly.github.io/execute_script.html";
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    public double funcCalc (double x){
        return Math.log(abs(12*sin(x)));

    }
    @Test
    public void calculationX() throws InterruptedException {
        WebElement inputValueX = driver.findElement(By.id("input_value"));
        double result = 0;
        result = funcCalc(parseDouble(inputValueX.getText()));

        WebElement inputLine = driver.findElement(By.id("answer"));
        inputLine.sendKeys(String.valueOf(result));

        WebElement buttonCheckbox = driver.findElement(By.id("robotCheckbox"));
        buttonCheckbox.click();

        WebElement radioButton = driver.findElement(By.id("robotsRule"));
        JavascriptExecutor js = driver;
        js.executeScript("arguments[0].scrollIntoView();",radioButton);
        radioButton.click();

        WebElement submitButton = driver.findElement(By.cssSelector("[type='submit']"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(200));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert text:" + alert.getText());
        assertTrue(alert.getText().contains("Congrats, you've passed the task!"));
        alert.accept();
    }


    @After
    public void TearDown() {
        driver.quit();
    }



}
