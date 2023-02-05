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
import java.util.ArrayList;

import static java.lang.Double.parseDouble;
import static java.lang.Math.*;
import static org.junit.Assert.assertTrue;

public class SwitchToTab {
    ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/C:/Users/anyut/chromedriver.exe");
        driver = new ChromeDriver();
        String BASE_URL = "http://suninjuly.github.io/redirect_accept.html";
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }
    public double funcCalc (double x){
        return log(abs(12*sin(x)));}

    @Test
    public void switchToAnotherTab(){
        WebElement button = driver.findElement(By.cssSelector("[type='submit']"));
        button.click();
        ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(1));
        WebElement xValue = driver.findElement(By.id("input_value"));
        System.out.println(xValue);

        double result = 0;
        result = funcCalc(parseDouble(xValue.getText()));

        WebElement inputLine = driver.findElement(By.id("answer"));
        inputLine.sendKeys(String.valueOf(result));
        WebElement submitButton = driver.findElement(By.cssSelector("[class='btn btn-primary']"));
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
