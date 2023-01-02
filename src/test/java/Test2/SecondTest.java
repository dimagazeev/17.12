package Test2;

import Test1.BaseSeleniumTest;
import org.junit.Test;

public class SecondTest extends BaseSeleniumTest {
    @Test
    public void checkingTest(){
        MainClass mainClass =  new MainClass();
        mainClass.searchIphone()
                 .selectIphone()
                 .choseMyIphone()
                 .previewClass()
                 .previewClass();

    }
}
