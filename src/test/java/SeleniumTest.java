import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

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
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect22']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        List<WebElement> userField = driver.findElements(cssSelector("tbody"));
        String text = userField.get(0).getText();
        assertEquals("berestov_2000@bk.ru Илья Мужской 1.1, 1.2 2.3", text.trim());
    }

    @Test
    void secondFormWithoutEmail() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        String text = driver.findElement(cssSelector("[id='emailFormatError']")).getText();
        assertEquals("Неверный формат E-Mail", text.trim());
    }

    @Test
    void secondFormWithoutName() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect22']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        String text = driver.findElement(cssSelector("[id='blankNameError']")).getText();
        assertEquals("Поле имя не может быть пустым", text.trim());
    }

    @Test
    void withoutCheckboxes() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect22']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        List<WebElement> userField = driver.findElements(cssSelector("tbody"));
        String text = userField.get(0).getText();
        assertEquals("berestov_2000@bk.ru Илья Мужской Нет 2.3", text.trim());
    }

    @Test
    void withoutRadioGroups() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        List<WebElement> userField = driver.findElements(cssSelector("tbody"));
        String text = userField.get(0).getText();
        assertEquals("berestov_2000@bk.ru Илья Мужской 1.1, 1.2", text.trim());
    }

    @Test
    void withoutRadioGroupsAndCheckboxes() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        List<WebElement> userField = driver.findElements(cssSelector("tbody"));
        String text = userField.get(0).getText();
        assertEquals("berestov_2000@bk.ru Илья Мужской Нет", text.trim());
    }

    @Test
    void invalidEmail() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        String text = driver.findElement(cssSelector("[id='emailFormatError']")).getText();
        assertEquals("Неверный формат E-Mail", text.trim());
    }

    @Test
    void nameWithHyphen() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Нур-Султан");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        List<WebElement> userField = driver.findElements(cssSelector("tbody"));
        String text = userField.get(0).getText();
        assertEquals("berestov_2000@bk.ru Нур-Султан Мужской 1.1, 1.2 2.3", text.trim());
    }

    @Test
    void fullSecondForm1() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect22']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        List<WebElement> userField = driver.findElements(cssSelector("tbody"));
        String text = userField.get(0).getText();
        assertEquals("berestov_2000@bk.ru Илья Мужской 1.1, 1.2 2.3", text.trim());
    }

    @Test
    void optimalCombinationWithWoman() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestova_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Маша");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(1).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        List<WebElement> userField = driver.findElements(cssSelector("tbody"));
        String text = userField.get(0).getText();
        assertEquals("berestova_2000@bk.ru Маша Женский 1.1, 1.2 2.3", text.trim());
    }

    @Test
    void addNineVariousUsers() {
        driver.findElement(cssSelector("[type='text']")).sendKeys("test@protei.ru");
        driver.findElement(cssSelector("[type='password']")).sendKeys("test");
        driver.findElement(cssSelector("[id='authButton']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2000@bk.ru");
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья");
        List<WebElement> genderField = driver.findElements(cssSelector("option"));
        genderField.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).clear();
        driver.findElement(cssSelector("[id='dataName']")).clear();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2002@bk.ru");
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect22']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Илья Второй");
        List<WebElement> genderField2 = driver.findElements(cssSelector("option"));
        genderField2.get(0).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).clear();
        driver.findElement(cssSelector("[id='dataName']")).clear();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestova_2003@bk.ru");
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Маша Третья");
        List<WebElement> genderField3 = driver.findElements(cssSelector("option"));
        genderField3.get(1).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).clear();
        driver.findElement(cssSelector("[id='dataName']")).clear();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestova_2004@bk.ru");
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Даша Четвертая");
        List<WebElement> genderField4 = driver.findElements(cssSelector("option"));
        genderField4.get(1).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck12']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).clear();
        driver.findElement(cssSelector("[id='dataName']")).clear();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2005@bk.ru");
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect22']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Иван Пятый");
        List<WebElement> genderField5 = driver.findElements(cssSelector("option"));
        genderField5.get(1).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).clear();
        driver.findElement(cssSelector("[id='dataName']")).clear();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2006@bk.ru");
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Boris the sixth");
        List<WebElement> genderField6 = driver.findElements(cssSelector("option"));
        genderField6.get(0).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).clear();
        driver.findElement(cssSelector("[id='dataName']")).clear();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2007@bk.ru");
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect21']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("Наруто Седьмой");
        List<WebElement> genderField7 = driver.findElements(cssSelector("option"));
        genderField7.get(0).click();
        driver.findElement(cssSelector("[type='checkBox'][id='dataCheck11']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).clear();
        driver.findElement(cssSelector("[id='dataName']")).clear();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestova_2008@bk.ru");
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("zhEnYa Восьмая");
        List<WebElement> genderField8 = driver.findElements(cssSelector("option"));
        genderField8.get(1).click();
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect22']")).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();
        driver.findElement(cssSelector("[id='dataEmail']")).clear();
        driver.findElement(cssSelector("[id='dataName']")).clear();
        driver.findElement(cssSelector("[id='dataEmail']")).sendKeys("berestov_2009@bk.ru");
        driver.findElement(cssSelector("[name='radioGroup'][id='dataSelect23']")).click();
        driver.findElement(cssSelector("[id='dataName']")).sendKeys("E-oneГай Девятый");
        List<WebElement> genderField9 = driver.findElements(cssSelector("option"));
        genderField9.get(0).click();
        driver.findElement(cssSelector("[type='submit'][id='dataSend']")).click();
        driver.findElement(cssSelector("[class='uk-button uk-button-primary uk-modal-close']")).click();

      //  String text = driver.findElement(cssSelector("[tbody]")).;

        List<WebElement> userField = driver.findElements(cssSelector("tbody"));
        String text = userField.get(0).getText();
        assertEquals("berestov_2000@bk.ru Илья Мужской 1.1 2.1\n" +
                "berestov_2002@bk.ru Илья Второй Мужской 1.1 2.2\n" +
                "berestova_2003@bk.ru Маша Третья Женский 1.1 2.3\n" +
                "berestova_2004@bk.ru Даша Четвертая Женский 1.2 2.1\n" +
                "berestov_2005@bk.ru Иван Пятый Женский 1.2 2.2\n" +
                "berestov_2006@bk.ru Boris the sixth Мужской 1.2 2.3\n" +
                "berestov_2007@bk.ru Наруто Седьмой Мужской 1.1, 1.2 2.1\n" +
                "berestova_2008@bk.ru zhEnYa Восьмая Женский 1.1, 1.2 2.2\n" +
                "berestov_2009@bk.ru E-oneГай Девятый Мужской 1.1, 1.2 2.3", text.trim());
    }
}
