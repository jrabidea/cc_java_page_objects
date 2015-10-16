package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.CookiePanel;
import pages.MiddlePanel;
import pages.StorePanel;
import services.TimerService;

/**
 * Created by jrabidea on 8/27/15.
 */
public class RunCookieClicker extends BaseTest{

    CookiePanel cp;
    MiddlePanel mp;
    TimerService ts;
    StorePanel sp;

    @Test(dataProvider = "selenium webdriver")
    public void runCookieClicker(WebDriver driver){
        setupDriver(driver);
        cp = new CookiePanel(driver);
        mp = new MiddlePanel(driver);
        ts = new TimerService();
        sp = new StorePanel(driver);
        mp.turnOffGraphics();
        mp.loadGame();
        ts.timer();
        Boolean play = true;
        Boolean setMax = true;
        while(play) {
            cp.clickCookie();
            sp.buyProductUpgrade();
            if (setMax == true) {
                sp.buyStoreProducts(true);
                setMax = false;
            } else {
                sp.buyStoreProducts(false);
            }
            if (ts.minutes == 5) {
                System.out.println("Saving game.....");
                mp.saveGame();
                ts.minutes = 0;
                setMax = true;
            }
        }
    }
}
