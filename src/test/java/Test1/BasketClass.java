package Test1;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasketClass extends BaseSeleniunPage {

    @FindBy(xpath = "//div[@id='c111289181']//img[@class='j-thumbnail thumbnail']")
    private WebElement fastViewBasket;

    @FindBy(xpath = "//span[text()='S'][@class='sizes-list__size']")
    private WebElement selectSizeS;

    @FindBy(xpath = "//button[@class='btn-main']")
    private WebElement addToShoppingCart;

    @FindBy(xpath = "//a[@class='btn-base j-go-to-basket']")
    private WebElement basket;

    public BasketClass() {
        PageFactory.initElements(BaseSeleniunPage.driver, this);

    }
    public BasketClass viewBascket(){
        fastViewBasket.click();
        //wait.until(ExpectedConditions.visibilityOfAllElements(fastViewBasket));
        selectSizeS.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(selectSizeS));
        addToShoppingCart.click();
        basket.click();
        return this;
    }
    public CheckoutClass checkoutBasket(){
        return new CheckoutClass();
    }
}
