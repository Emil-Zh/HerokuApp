import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class TyposTest {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyText(){
        SoftAssert softAssert = new SoftAssert();
        driver.get("http://the-internet.herokuapp.com/typos");
        List<WebElement> paragraphs = driver.findElements(By.tagName("p"));
        StringBuilder allParagraphsText = new StringBuilder();
        for(WebElement paragragh : paragraphs){
            allParagraphsText.append(paragragh.getText() + "\n");
        }
        String correctText = "This example demonstrates a typo being introduced. It does it randomly on each page load." +
                "\n" +
                "Sometimes you'll see a typo, other times you won't." + "\n";
        softAssert.assertEquals(allParagraphsText.toString().trim(), correctText.trim(), "Text is wrong");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
