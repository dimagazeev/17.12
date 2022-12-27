package Test1;

import com.typesafe.config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.security.auth.login.Configuration;
import javax.security.auth.login.ConfigurationSpi;
import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

abstract public class BaseSeleniumTest {
        protected WebDriver driver;
        protected WebDriverWait wait;


        @Before
        public void start(){


            WebDriverManager.chromedriver().setup();
            /**автоматически скачивает нужный нам браузер последней версии */

            driver = new ChromeDriver();
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            driver.manage().window().maximize(); // раскрываем браузер на полное окно
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);


            /** выставляем ожидание элементов на странице в 10 сек */

            BaseSeleniunPage.setDriver(driver);
            BaseSeleniunPage.setDriverWait(wait);


        }
    /*@After
        public void finish() {driver.close(); //метод для закрытия DRIVER
        }*/

    }

