package Test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TestClass extends BaseSeleniunPage {

    @FindBy(xpath ="//button[@class='nav-element__burger j-menu-burger-btn j-wba-header-item hide-mobile']")
    private WebElement menuBar;

    @FindBy(linkText = ("Мужчинам"))
    private  WebElement gender;

    @FindBy(linkText = "Верхняя одежда")
    private WebElement outerwear;


    public TestClass(){

        PageFactory.initElements(BaseSeleniunPage.driver, this);
    }

    public TestClass createTicket (){
        BaseSeleniunPage.driver.get("https://www.wildberries.ru/");
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//div[@class='img-plug banners-catalog-custom__container swiper-slide swiper-slide-duplicate swiper-slide-prev']")));
        menuBar.click();
        gender.click();
        outerwear.click();
        return this;
    }
    public ProductClass openProductClass(){
        return new ProductClass();
    }

}













