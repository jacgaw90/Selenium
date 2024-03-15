package Test;

import Base.BaseTest;
import Pages.ContactPage;
import Pages.MainPage;
import Pages.SuccessfullMessagePage;
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

public class FillFromAdvanceTest extends BaseTest {

    private Logger log = LoggerFactory.getLogger(FillFromAdvanceTest.class);
    MainPage mainPage;
    ContactPage contactPage;
    SuccessfullMessagePage successfullMessagePage;

    @Test
    void fillFormBasicScenario() {
        //System.out.println("Start test!");
        mainPage = new MainPage(driver);
        contactPage = new ContactPage(driver);
        successfullMessagePage = new SuccessfullMessagePage(driver);

        //Krok 1
        mainPage.clickContactLink();
        //Krok 2
        contactPage.selectOptionFromDropdownList("Webmaster");
        contactPage.inputEmailAdress("mailor@op.pl");

        contactPage.inputOrderNumber("12321");

        contactPage.inputFile("C:\\Recovery.txt");

        contactPage.inputMessage("""
                To jest pierwsza linijka tekstu,
                to jest druga linijka
                to jest ostatnia linijka...
                """);

        contactPage.clickSendButton();

        //Sprawdzenie

        assertThat(successfullMessagePage.getSuccessMessageTxt()).isEqualTo(expectedMessage);
        //log.info("Test stop!");

    }
}
