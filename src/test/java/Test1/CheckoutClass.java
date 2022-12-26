package Test1;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutClass extends BaseSeleniunPage {

    @FindBy( xpath = "//button[@class='count__plus plus']")
    private WebElement addProduct;

    @FindBy(xpath = "//button[@class='btn__del j-basket-item-del']")
    private WebElement deleteProduct;

    @FindBy(xpath = "//button[@class='b-btn-do-order btn-main j-btn-confirm-order']")
    private WebElement displayed;

    @FindBy(xpath = "//a[@class='basket-empty__btn btn-main']")
    private WebElement displayedGotoMainPage;

    public CheckoutClass() {
        PageFactory.initElements(BaseSeleniunPage.driver, this);
    }

    public AssertClass assertClass(){
        Assert.assertTrue(displayed.isDisplayed());
        addProduct.click();
        deleteProduct.click();
        Assert.assertTrue(displayedGotoMainPage.isDisplayed());

        return new AssertClass();
    }


}
