package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage {
//    WebElement contactUsLink = driver.findElement(By.cssSelector("#contact-link a"));
//        contactUsLink.click();
//        log.info("Step 1: Contact us kliknięty.");

    private Logger log = LoggerFactory.getLogger(MainPage.class);
    @FindBy(css = "#contact-link a") // linia 12 i 13 to jest odpowiednik lini 8
    private WebElement contactUsLink;

    public void clickContactLink() {
        contactUsLink.click();
        log.info("Step 1: Contact us kliknięty.");
    }

    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
