import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class NewTab {

    ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/C:/Users/anyut/chromedriver.exe");
        driver = new ChromeDriver();
        String BASE_URL = "https://demoqa.com/browser-windows";
        driver.get(BASE_URL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void switchToAnotherTab() {
        WebElement button = driver.findElement(By.id("tabButton"));
        button.click();
        ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(handles.get(1));
        WebElement secondPage = driver.findElement(By.id("sampleHeading"));
        assertTrue(secondPage.getText().contains("This is a sample page"));
    }

    @After
    public void TearDown() {
        driver.quit();
    }
}
