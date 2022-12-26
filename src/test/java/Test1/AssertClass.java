package Test1;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssertClass extends BaseSeleniunPage {

    @FindBy(xpath = "//h1[@class='section-header']")
    private WebElement titleHeader;

    @FindBy(xpath = "//p[@class='basket-empty__text']")
    private WebElement title;

    public AssertClass(){
        PageFactory.initElements(BaseSeleniunPage.driver, this);
    }

    public String getTitleHeaderBasket(){
        return titleHeader.getText();
    }

    public String getTitleBasket(){
        return title.getText();
    }


}
