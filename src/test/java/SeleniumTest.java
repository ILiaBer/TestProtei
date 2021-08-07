import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

public class SeleniumTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
       // options.addArguments("--disable-dev-shm-usage");
        //options.addArguments("--no-sandbox");
       // options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("file:///C://Games//TestProtei//artifacts//qa-test.html");
    }

    @Test
    void optimalFirstForm() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        String text = driver.findElement(cssSelector("[for='dataGender']")).getText();
        assertEquals("Пол:", text.trim());
    }

    @Test
    void withoutMail() {
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        String text = driver.findElement(cssSelector("[id='emailFormatError']")).getText();
        assertEquals("Неверный формат E-Mail", text.trim());
    }

    @Test
    void withoutPassword() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[id='authButton']")).click();
        String text = driver.findElement(cssSelector("[id='invalidEmailPassword']")).getText();
        assertEquals("Неверный E-Mail или пароль", text.trim());
    }

    @Test
    void wrongMail() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ENG");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        String text = driver.findElement(cssSelector("[id='invalidEmailPassword']")).getText();
        assertEquals("Неверный E-Mail или пароль", text.trim());
    }

    @Test
    void wrongPassword() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test123");
        driver.findElement(cssSelector("[id='authButton']")).click();
        String text = driver.findElement(cssSelector("[id='invalidEmailPassword']")).getText();
        assertEquals("Неверный E-Mail или пароль", text.trim());
    }

    @Test
    void capsLkMail() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("TEST@PROTEI.RU");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        String text = driver.findElement(cssSelector("[id='invalidEmailPassword']")).getText();
        assertEquals("Неверный E-Mail или пароль", text.trim());
    }

    @Test
    void capsLkPassword() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("TEST");
        driver.findElement(cssSelector("[id='authButton']")).click();
        String text = driver.findElement(cssSelector("[id='invalidEmailPassword']")).getText();
        assertEquals("Неверный E-Mail или пароль", text.trim());
    }

    @Test
    void emptySecondForm() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        String text = driver.findElement(cssSelector("[id='emailFormatError']")).getText();
        assertEquals("Неверный формат E-Mail", text.trim());
    }

    @Test
    void fullSecondForm() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        driver.findElement(cssSelector("[id='dataGender']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect22']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector(".uk-button uk-button-primary uk-modal-close")).click();
       // String text = .getText();
        //assertEquals("Данные добавлены.", text.trim());
    }
}
