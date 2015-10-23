package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;

/**
 * Created by jrabidea on 8/28/15.
 */
public class StorePanel extends BasePageObject {

    By products = By.xpath("//*[@class = 'product unlocked enabled']");
    private static int totalProductsBought = 0;
    private static String[] productsBoughtId = {"productOwned0", "productOwned1", "productOwned2", "productOwned3"
            , "productOwned4","productOwned5", "productOwned6", "productOwned7", "productOwned8", "productOwned9"};
    private static int round = 11;
    By productUpgrade = By.id("upgrade0");

    public StorePanel (WebDriver driver){
        super(driver);
    }

    //A WebElement array is created for all the products in the store that have the class 'product unlocked enabled'
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

    // Buys the store products
    public void buyStoreProducts(Boolean checkMax){

        int maximum = round;

        WebElement[] productsArray = getProductElements();

        // Sets the maximum number of products to buy for an indiviual product
        if (checkMax == true) {
            maximum = setMaxBuy(getTotalProductsBought());
        }

        // For each product, the product is bought if the individual product is less than the set maximum value
        for (int i = 0; i < productsArray.length; i++) {
            if (getNumProductsBought(productsBoughtId[i]) < maximum) {
                productsArray[i].click();
            }
        }

    }


    // Gets the total number of products bought for all products
    public int getTotalProductsBought(){


        for(int i = 0; i < getProductElements().length; i++){
            // Checks to see if the element returns an empty string. If it does, then 0 is added to the total number
            // of products. If an element returns a number, then the number is added to the total number of
            // products bought.
            if (driver.findElement(By.id(productsBoughtId[i])).getText().equals("")){

                totalProductsBought = totalProductsBought + 0;
            }else {
                totalProductsBought = totalProductsBought
                        + Integer.parseInt(driver.findElement(By.id(productsBoughtId[i])).getText());
            }
        }

        return totalProductsBought;

    }

    // Gets the number of products bought for an individual product
    public int getNumProductsBought(String productId){

        // Checks to see if the element returns an empty string. If it does then the number of products bought for
        //  the product is set to return 0. If it returns a number, then that number is returned.
        if(driver.findElement(By.id(productId)).getText().equals("")){
            return 0;
        }else {
            return Integer.parseInt(driver.findElement(By.id(productId)).getText());
        }
    }

    // Set the maximum number of products. The total number of products bought is the argument.
    public int setMaxBuy(int productsBought){

        if(productsBought <= 80){
            round = 11;
        }
        else if (productsBought > 81 && productsBought < 210){
            round = 21;
        }
        else if (productsBought > 210 && productsBought < 490){
            round = 51;
        }

        return round;
    }

    public void buyProductUpgrade(){

        try {
            if (driver.findElement(productUpgrade).getAttribute("className").equals("crate upgrade enabled")) {
                driver.findElement(productUpgrade).click();
            }
        }catch(NoSuchElementException d){
            d.getMessage();
            System.out.println("Products have not been purchased yet");
        }

    }

}
