package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import pages.BasePageObject;
import pages.MiddlePanel;

import java.util.Objects;

/**
 * Created by jrabidea on 8/27/15.
 */
public abstract class BaseTest {

    public WebDriver driver;

    @DataProvider(name = "selenium webdriver")
    public Object[][] webDrivers() {
        return new Object[][]{{new FirefoxDriver()}
        };
    }

    public void setupDriver(WebDriver driver) {
        this.driver = driver;
        driver.get(BasePageObject.baseURL);
    }
}
