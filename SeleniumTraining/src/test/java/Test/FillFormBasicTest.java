package Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


public class FillFormBasicTest {

    private final String browserName = "chrome";
    private final boolean headlessBrowser = false;
    private WebDriver driver;
    private final String appUrl = "http://www.automationpractice.pl/index.php";
    private Logger log = LoggerFactory.getLogger(FillFormBasicTest.class);

    @Test
    void fillFormBasicScenario() {
        //System.out.println("Start test!");
        log.info("Test start!");
        //Inicjalizacja drivera
        driver = getDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //Otwarcie URLa
        driver.get(appUrl);

        //Zaczynamy klikać
        //Krok 1 klik "contact us"
        WebElement contactUsLink = driver.findElement(By.cssSelector("#contact-link a"));
        contactUsLink.click();
        log.info("Step 1: Contact us kliknięty.");

        //Krok 2 wybierz Subject Heading
        WebElement dropdownList = driver.findElement(By.cssSelector("#id_contact"));
        Select select = new Select(dropdownList);
        select.selectByVisibleText("Webmaster");
        log.info("Step 2: Subject Heading został wybrany.");

        //Krok 3 wpisz email
        WebElement emailInput = driver.findElement(By.cssSelector("#email"));
        emailInput.sendKeys("mail@op.pl");
        log.info("Step 3: email wpisany.");

        //Krok 4 wpisz Oreder refference
        //WebElement orderRef = driver.findElement(By.cssSelector("#id_order"));
        WebElement orderRef = driver.findElement(By.id("id_order"));
        orderRef.sendKeys("123567");
        log.info("Step 4: order ref wpisany.");

        //Krok 5 wpisz wiadomość
        WebElement messageInput = driver.findElement(By.cssSelector("#message"));
        messageInput.sendKeys("""
                To jest pierwsza linijka tekstu,
                to jest druga linijka
                to jest ostatnia linijka...
                """);
        log.info("Step 5: Wiadomość wpisana.");

        //Krok 6 dodaj plik
        WebElement fileUpload = driver.findElement(By.id("fileUpload"));
        fileUpload.sendKeys("C:\\Recovery.txt");
        log.info("Step 6: Załadowano załącznik.");

        //Krok 7 wysłanie formularza
        WebElement submitMessage = driver.findElement(By.cssSelector("#submitMessage"));
        submitMessage.click();
        log.info("Step 7: Wysłanie formularza.");

        //Krok 8 Weryfikacja Succes message
        WebElement succesMessage = driver.findElement(By.className("alert-success"));
        String expectedMessage = "Your message has been successfully sent to our team.";
        String actualMessage = succesMessage.getText();
        assertThat(actualMessage).isEqualTo(expectedMessage);
        log.info("Step 8: Weryfikacja Succes message.");

        //System.out.println("Test stop!");
        log.info("Test stop!");
        log.debug("Tutaj jest wiadomość z debuggera.");
        driver.quit();
        // nie ma sensu używać w tej kolejności driver.close();
        log.info("Driver is closed.");
    }

    private WebDriver getDriver() {
        switch (this.browserName) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (this.headlessBrowser) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.addArguments("--start-maximized");
                firefoxOptions.addArguments("--remote-allow-origins=*");
                if (this.headlessBrowser) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                WebDriverManager.edgedriver().setup();
                edgeOptions.addArguments("--start-maximized");
                edgeOptions.addArguments("--remote-allow-origins=*");
                if (this.headlessBrowser) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
            }
            default -> {
                System.out.println("Nieprawidłowa przeglądarka");
            }
        }
        return driver;
    }
}
