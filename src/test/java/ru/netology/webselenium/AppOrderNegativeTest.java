package ru.netology.webselenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppOrderNegativeTest {

    private WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }


    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldTestNegativePath1() {
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Иванова Анна");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("+780055");
        driver.findElement(By.cssSelector("label[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector(".input_invalid[data-test-id=name] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestNegativePath2() {
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Петр Петров123");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("+79677777777");
        driver.findElement(By.cssSelector("label[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        String actual = driver.findElement(By.cssSelector(".input_invalid[data-test-id=name] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestNegativePath3() {
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Петров Петр");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("label[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Поле обязательно для заполнения";
        String actual = driver.findElement(By.cssSelector(".input_invalid[data-test-id=name] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestNegativePath4() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Сергей Иванов");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("+7913-848-35-83");
        driver.findElement(By.cssSelector("label[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector(".input_invalid[data-test-id=phone] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestNegativePath5() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Анна Иванова");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("Номер");
        driver.findElement(By.cssSelector("label[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.";
        String actual = driver.findElement(By.cssSelector(".input_invalid[data-test-id=phone] span.input__sub")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldTestNegativePath6() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("span[data-test-id=name] input")).sendKeys("Иван Иванов");
        driver.findElement(By.cssSelector("span[data-test-id=phone] input")).sendKeys("+78005553535");
        //driver.findElement(By.cssSelector("label[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String expected = "Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй";
        String actual = driver.findElement(By.cssSelector("label[data-test-id=agreement].input_invalid")).getText().trim();
        assertEquals(expected, actual);
    }
}


