package Base;

import Test.FillFormBasicTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BaseTest {
    protected final String appUrl = "http://www.automationpractice.pl/index.php";
    protected final String webUrl = "https://cosmocode.io/automation-practice-webtable/";
    protected final String browserName = "chrome";
    protected final boolean headlessBrowser = false;
    protected WebDriver driver;
    private Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected final String expectedMessage = "Your message has been successfully sent to our team.";


    @BeforeEach
    void setApp() {
        //Inicjalizacja drivera
        driver = getDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //Otwarcie URLa
        driver.get(webUrl);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        log.info("Driver is closed.");
    }

    protected String getCapitolForCountry(String Country) {
        int position = 0;
        List<WebElement> table = driver.findElements(By.cssSelector("#countries tbody tr"));

        for (WebElement x : table) {
            //log.info(x.getText());
            if (x.getText().toLowerCase().contains(Country.toLowerCase())) break;
            position++;
        }
        int capital = 3;
        WebElement capitalCity = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + (position + 1) + ") td:nth-child(" + (capital) + ")"));
        return capitalCity.getText();
    }protected String getCurrencyForCountry(String Country) {
        int position = 0;
        List<WebElement> table = driver.findElements(By.cssSelector("#countries tbody tr"));

        for (WebElement x : table) {
            //log.info(x.getText());
            if (x.getText().toLowerCase().contains(Country.toLowerCase())) break;
            position++;
        }
        int currency = 4;
        WebElement currencyOfCountry = driver.findElement(By.cssSelector("#countries tbody tr:nth-child(" + (position + 1) + ") td:nth-child(" + (currency) + ")"));
        return currencyOfCountry.getText();
    }


    protected WebDriver getDriver() {
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
