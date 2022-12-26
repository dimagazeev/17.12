package Test1;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductClass extends BaseSeleniunPage {
    @FindBy(xpath = "//label[@data-value='1791']")
    private WebElement anorak;

    @FindBy(xpath = "//label[@data-value='48']")
    private WebElement data;

    @FindBy(xpath = "//div[@class='j-filter-content filter__content ']//button[@type='button'][1]")
    private WebElement showAll;

    @FindBy(xpath = "//div[@data-filter-name='fbrand']//input[@class='j-search-filter']")
    private WebElement search;

    @FindBy(xpath = "//label[@data-value='101907']")
    private WebElement brendZara;

    @FindBy(xpath = "//label[@data-value='43296']")
    private WebElement size;

    public ProductClass(){
        PageFactory.initElements(BaseSeleniunPage.driver, this);
    }

    public ProductClass selectProduct(){
        anorak.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(anorak));
        data.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(data));
        showAll.click();
        search.sendKeys("zara");
        wait.until(ExpectedConditions.visibilityOfAllElements(search));
        brendZara.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(brendZara));
        //size.click();
        //wait.until(ExpectedConditions.visibilityOfAllElements(size));
        return this;
    }
    public BasketClass basketClass(){
        return new BasketClass();
    }

}
