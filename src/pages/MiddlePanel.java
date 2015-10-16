package pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.List;

/**
 * Created by jrabidea on 8/27/15.
 */
public class MiddlePanel extends BasePageObject{

    By menu = By.id("prefsButton");
    By stats = By.id("statsButton");
    By graphics = By.xpath("//*[@class = 'subsection']//div[@class = 'listing'][5]//a");
    By exportSave = By.xpath("//*[@class = 'listing']//a[@onclick = 'Game.ExportSave();']");
    By importSave = By.xpath("//*[@class = 'listing']//a[@onclick = 'Game.ImportSave();']");
    By textArea = By.id("textareaPrompt");
    By allDoneButton = By.id("promptOption0");
    By loadButton = By.id("promptContent");

    private static final File file = new File("CookieClicker.txt");

    public MiddlePanel(WebDriver driver){
        super(driver);
    }

    public void clickMenu(){
        driver.findElement(menu).click();
    }

    public void menuOpenWait(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By.id("fancyButton"))));
    }

    // WebElement array is created for all the graphic buttons
    public WebElement[] getGraphicWebElements(){

        List<WebElement> graphicElements = driver.findElements(graphics);
        WebElement[] graphicSettings = new WebElement[graphicElements.size()];
        int i = 0;
        for(WebElement d : graphicElements) {
            graphicSettings[i] = d;
            i++;
        }
        return graphicSettings;


    }

    // Turns off graphics for performance
    public void turnOffGraphics(){

        clickMenu();
        menuOpenWait();
        WebElement[] graphicElements = getGraphicWebElements();

        for(int i = 0;i < graphicElements.length;i++){
            if(i == 2 || i == 5 || i == 6 ){
                System.out.println("Skipping graphic option");
            }
            else {
                graphicElements[i].click();
            }
        }
        clickMenu();
    }

    public void clickAllDone(){
        driver.findElement(allDoneButton).click();
    }

    public void saveGame(){

        clickMenu();
        menuOpenWait();
        driver.findElement(exportSave).click();
        String saveCode = driver.findElement(textArea).getText();
        try {
            FileUtils.writeStringToFile(file, saveCode);
        }catch (IOException d){
            d.getMessage();
        }
        clickAllDone();
        clickMenu();
    }

    public void loadGame(){
        clickMenu();
        menuOpenWait();
        String codeImport = null;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null){
                codeImport = line;
            }
            driver.findElement(importSave).click();
            driver.findElement(textArea).sendKeys(codeImport);
            driver.findElement(loadButton).click();
            clickAllDone();
            clickMenu();
        }catch(FileNotFoundException f){
            f.getMessage();
            System.out.print("The save file has not been created");
            clickMenu();
        }catch(IOException p){
            p.getMessage();
        }catch(NullPointerException k){
            k.getMessage();
        }
    }

}
