import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DropdownTest {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyDropdownFunctionality(){
        SoftAssert softAssert = new SoftAssert();
        driver.get("http://the-internet.herokuapp.com/dropdown");
        WebElement dropdownElement = driver.findElement(By.id("dropdown"));
        Select dropdown = new Select(dropdownElement);
        List<WebElement> options = dropdown.getOptions();
        //Сначала думал сделать так
//        for (int i = 1; i < options.size(); i++){
//            softAssert.assertEquals(options.get(i).getText(), "Option " + i , "Option " +i + " has a wrong name");
//        }
//        for (int i = 1; i < options.size(); i++){
//            options.get(i).click();
//            softAssert.assertTrue(options.get(i).isSelected(), "Option " + i + " should have been selected");
//        }
        softAssert.assertEquals(options.get(1).getText(), "Option 1" , "Option 1 has a wrong name");
        softAssert.assertEquals(options.get(2).getText(), "Option 2" , "Option 2 has a wrong name");
        options.get(1).click();
        softAssert.assertTrue(options.get(1).isSelected(), "Option 1 should have been selected");
        options.get(2).click();
        softAssert.assertTrue(options.get(2).isSelected(), "Option 2 should have been selected");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
