package Test1;

import org.junit.Assert;
import org.junit.Test;


public class FirstTest extends BaseSeleniumTest {
        @Test
        public void checkTest(){
            AssertClass Title = new TestClass()
            .createTicket()
                    .openProductClass()
                    .selectProduct()
                    .basketClass()
                    .viewBascket()
                    .checkoutBasket()
                    .assertClass();
            Assert.assertEquals(Title.getTitleHeaderBasket(), AssertBasket.Test_Header_title);
            Assert.assertEquals(Title.getTitleBasket(),AssertBasket.Test_Title);

        }

    }

