package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by jrabidea on 8/28/15.
 */
public class StorePanel extends BasePageObject {

    By products = By.xpath("//*[@class = 'product unlocked enabled']");
    private static int totalProductsBought = 0;
    private static String[] productsBoughtId = {"productOwned0", "productOwned1", "productOwned2", "productOwned3"
            , "productOwned","productOwned5", "productOwned6", "productOwned7", "productOwned8", "productOwned9"};
    private static int round = 11;

    public StorePanel (WebDriver driver){
        super(driver);
    }

    public WebElement[] getProductElements(){

        List<WebElement> productElementList = driver.findElements(products);
        WebElement[] productElements = new WebElement[productElementList.size()];
        int i = 0;
        for(WebElement q : productElementList){
            productElements[i] = q;
            i++;
        }

        return productElements;
    }


    public void buyStoreProducts(Boolean checkMax){

        WebElement[] productsArray = getProductElements();
        int maximum = round;

        if (checkMax == true) {
            maximum = setMaxBuy(getTotalProductsBought());
        }

        for (int i = 0; i < getProductElements().length; i++) {
            if (getNumProductsBought(productsBoughtId[i]) < maximum) {
                productsArray[i].click();
            }
        }

    }

    public int getTotalProductsBought(){

        for(int i = 0; i < getProductElements().length; i++){

            if (driver.findElement(By.id(productsBoughtId[i])).getText().equals("")){

                totalProductsBought = totalProductsBought + 0;
            }else {
                totalProductsBought = totalProductsBought
                        + Integer.parseInt(driver.findElement(By.id(productsBoughtId[i])).getText());
            }
        }

        return totalProductsBought;

    }

    public int getNumProductsBought(String productId){

        if(driver.findElement(By.id(productId)).getText().equals("")){
            return 0;
        }else {
            return Integer.parseInt(driver.findElement(By.id(productId)).getText());
        }
    }

    public int setMaxBuy(int productsBought){

        if(productsBought > 80){
            round = 11;
        }

        else if (productsBought == 80){
            round = 21;
        }
        else if (productsBought > 210 && productsBought < 490){
            round = 51;
        }

        return round;
    }

}
