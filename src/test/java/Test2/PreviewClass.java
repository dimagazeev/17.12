package Test2;

import Test1.BaseSeleniumTest;
import Test1.BaseSeleniunPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PreviewClass extends BaseSeleniunPage {

    @FindBy(xpath = "//button[@class='btn-main']")
    private WebElement addInBasket;

    @FindBy(xpath = "//a[@class='btn-base j-go-to-basket']")
    private WebElement goInBasket;

    public PreviewClass(){
        PageFactory.initElements(driver,this);
    }

    public PreviewClass previewClass(){
        Assert.assertTrue(addInBasket.isDisplayed());
        addInBasket.click();
        Assert.assertTrue(goInBasket.isDisplayed());
        goInBasket.click();
        return this;
    }

}
