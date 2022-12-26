package Test1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class BaseSeleniunPage {

    protected static WebDriver driver;
    protected static WebDriverWait wait;


    public static void setDriver(WebDriver webDriver){

        driver = webDriver;
    }
    public static void setDriverWait(WebDriverWait webdriverWait){
        wait = webdriverWait;
    }
}
