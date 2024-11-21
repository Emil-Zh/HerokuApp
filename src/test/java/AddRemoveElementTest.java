import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class AddRemoveElementTest {
    WebDriver driver;
    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void checkAddRemoveElement(){
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        addButton.click();
        addButton.click();

        List<WebElement> deleteButtons = driver.findElements(By.xpath("//button[text()='Delete']"));

        softAssert.assertEquals(deleteButtons.size(), 2, "кнопок delete не 2");
        deleteButtons.get(1).click();

        List<WebElement> deleteButtonsAfterDelete = driver.findElements(By.xpath("//button[text()='Delete']"));
        softAssert.assertEquals(deleteButtonsAfterDelete.size(), 1, "кнопок delete не 2");

        softAssert.assertAll();


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
