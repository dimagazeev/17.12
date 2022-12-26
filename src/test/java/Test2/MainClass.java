package Test2;

import Test1.BaseSeleniunPage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class MainClass extends BaseSeleniunPage {
    @FindBy (xpath = "//input[@class='search-catalog__input j-wba-header-item']")
    private WebElement search;

    public MainClass(){

        PageFactory.initElements(driver, this); }

    public MainClass searchIphone(){
        BaseSeleniunPage.driver.get("https://www.wildberries.ru/");
        wait.until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.xpath("//div[@class='img-plug banners-catalog-custom__container swiper-slide swiper-slide-duplicate swiper-slide-prev']")));
        search.sendKeys("iphone13", Keys.ENTER);
        return this;}

    public SelectIphone selectIphone(){
        return new SelectIphone();
    }


}


