package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by jrabidea on 8/27/15.
 */
public class CookiePanel extends BasePageObject {

    By bakeryName = By.id("bakeryName");
    By confirmButton = By.id("promptOption0");
    By randomButton = By.id("promptOption1");
    By cookie = By.id("bigCookie");

    public CookiePanel(WebDriver driver){
        super(driver);
    }

    // Click the big cookie
    public void clickCookie(){
        driver.findElement(cookie).click();
    }


    // Changes the bakery name for an achievement
    public void changeBakeryName(){
        driver.findElement(bakeryName).click();
        driver.findElement(randomButton).click();
        driver.findElement(confirmButton).click();
    }

}
