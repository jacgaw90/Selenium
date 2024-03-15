package Test;

import Base.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WebTableAdvanceTest extends BaseTest {
Logger log = LoggerFactory.getLogger(WebTableAdvanceTest.class);
    @ParameterizedTest
    @CsvFileSource(resources = "/countries.csv")
    @DisplayName("Check countries and capitals")
    void WebTablePolandTest(String country, String capital){
        assertThat(getCapitolForCountry(country)).isEqualTo(capital);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/countriess.csv")
    @DisplayName("Check countries and currencies")
    void getCurrencyForCountry(String country, String currency) {
        assertThat(getCurrencyForCountry(country)).isEqualTo(currency);
    }
}
