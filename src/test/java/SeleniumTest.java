import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;

public class SeleniumTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--disable-dev-shm-usage");
        //options.addArguments("--no-sandbox");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("file://artifacts/qa-test.html");
        //.artifacts/qa-test.html
        // http://localhost:63342/TestProtei/artifacts/qa-test.html?_ijt=
    }

    @Test
    void tryTest() {
        driver.findElement(cssSelector("[place holder id='loginEmail']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[input type='password']")).sendKeys("test");
        String text = driver.findElement(cssSelector(".uk-form-row[id=dataCheck11]")).getText();
        assertEquals("Вариант1.1", text.trim());
    }


}

