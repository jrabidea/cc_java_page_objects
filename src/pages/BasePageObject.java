package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by jrabidea on 8/27/15.
 */
public abstract class BasePageObject {

    protected WebDriver driver;
    public static final String baseURL = "http://orteil.dashnet.org/cookieclicker/";

    public BasePageObject(WebDriver driver){
        this.driver = driver;

    }

}
