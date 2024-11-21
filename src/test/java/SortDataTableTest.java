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
import java.util.ArrayList;
import java.util.List;

public class SortDataTableTest {
    WebDriver driver;
    private static class Person{
        String first_name,second_name, email, due, website;

        public Person(String first_name, String second_name, String email, String due, String website) {
            this.first_name = first_name;
            this.second_name = second_name;
            this.email = email;
            this.due = due;
            this.website = website;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getSecond_name() {
            return second_name;
        }

        public String getEmail() {
            return email;
        }

        public String getDue() {
            return due;
        }

        public String getWebsite() {
            return website;
        }
        public String toString() {
            return "Person{" +
                    "first_name='" + first_name + '\'' +
                    ", second_name='" + second_name + '\'' +
                    ", email='" + email + '\'' +
                    ", due='" + due + '\'' +
                    ", website='" + website + '\'' +
                    '}';
        }

    }
    @BeforeMethod
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void verifyDataTable(){
        SoftAssert softAssert = new SoftAssert();
        driver.get("http://the-internet.herokuapp.com/tables");
        WebElement table = driver.findElement(By.id("table1"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));
        List<Person> people = new ArrayList<>();
        for (int i = 1; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));

            Person person = new Person(
                    cells.get(1).getText(),
                    cells.get(0).getText(),
                    cells.get(2).getText(),
                    cells.get(3).getText(),
                    cells.get(4).getText()
            );
            people.add(person);
        }
        List<Person> expectedPeople = new ArrayList<>();
        expectedPeople.add(new Person("John", "Smith", "jsmith@gmail.com", "$50.00", "http://www.jsmith.com"));
        expectedPeople.add(new Person("Frank", "Bach", "fbach@yahoo.com", "$51.00", "http://www.frank.com"));
        expectedPeople.add(new Person("Jason", "Doe", "jdoe@hotmail.com", "$100.00", "http://www.jdoe.com"));
        expectedPeople.add(new Person("Tim", "Conway", "tconway@earthlink.net", "$50.00", "http://www.timconway.com"));
        for(int i = 0; i < expectedPeople.size(); i ++){
            Assert.assertEquals(people.get(i).toString().trim(), expectedPeople.get(i).toString().trim(), "Wrong person date");
        }
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
