package Test2;

import Test1.BaseSeleniunPage;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SelectIphone extends BaseSeleniunPage {

    @FindBy (xpath = "//label[@data-value='120']//span[text()= 'до 5 дней']")
    private WebElement deliveryTime;

    @FindBy (xpath = "//label[@data-value='-100']")
    private WebElement salesMan;

    @FindBy (xpath = "//label[@data-value='255']")
    private WebElement selectColor;

    @FindBy (xpath = "//label[@data-value='17043']")
    private WebElement diagonal;

    @FindBy (xpath = "//label[@data-value='5866733']")
    private WebElement model;

    @FindBy (xpath = "//label[@data-value='12868']")
    private WebElement memory;

    @FindBy (xpath = "//label[@data-value='5866733']")
    private WebElement operationalMemory;

    @FindBy (xpath = "//div[@id='c119938058']//img[@class='j-thumbnail thumbnail']")
    private WebElement previewIphone13;

    public SelectIphone(){
        PageFactory.initElements(driver,this);
    }
    public SelectIphone choseMyIphone(){
        deliveryTime.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(deliveryTime));
        salesMan.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(salesMan));
        selectColor.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(selectColor));
        diagonal.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(diagonal));
        model.click();
        //wait.until(ExpectedConditions.visibilityOfAllElements(model));
        memory.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(memory));
        //operationalMemory.click();
        return this;
    }
    public PreviewClass previewClass(){
        //wait.until(ExpectedConditions.visibilityOfAllElements(operationalMemory));
        previewIphone13.click();
        return new PreviewClass();
    }
}
