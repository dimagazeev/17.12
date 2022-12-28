package api.Kucoin;

import com.sun.javafx.collections.MappingChange;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class StreamApiExamples {
    /** этот метод создали для того,чтобы этот метод нам возвращал список крипты,
    чтоб в дальнейшей обращаться к переменной "getTicker" */
    public List<TickerData> getTicker(){
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then().log().body() // извлекли тело
                .extract().jsonPath().getList("data.ticker", TickerData.class);
        // извлекли и получили весь список криптовалюты в Pojo class(TickerData)
    }

    @Test
    public void checkCrypto(){
        /// Отфильтровали крипту по окончанию "USDT" \\\
        List<TickerData> usdTickers =
                getTicker().stream().filter(x->x.getSymbol().endsWith("USDT")).collect(Collectors.toList());
        Assert.assertTrue(usdTickers.stream().allMatch(x->x.getSymbol().endsWith("USDT")));
                /* .stream -> это поток информации (подобие перебора по типу: for, for each)
                    но более упрощенный перебор строковвой записи
                 *  x-> лямда переменная из списка, где мы указываем метод "stream",
                    вместо "х" может быть написано хоть что(ведь это переменная)
                 * .endsWith("USDT") окончание в название крипты(пример:BTC-USDT)
                 * .allMatch - абсолютно все совпадения
                 * .collect(Collectors.toList() этот метод всю отфильтрованную информацию засунет в список
                 */
    }
    @Test /**  1) получаем список всех криптовалют с окончанием "USDT"
               2) фильтруем по убыванию в стоимости изменение крипты за сутки, значение "ChangeRate"
               3) получаем топ10 из всего списка */
    public void sortHighToLow(){
         List<TickerData> highToLow =getTicker().stream().filter(x->x.getSymbol().endsWith("USDT"))
                 .sorted(new Comparator<TickerData>() {
                     @Override // анонимный класс
                     public int compare(TickerData o1, TickerData o2) {
                         return o2.getChangeRate().compareTo(o1.getChangeRate());
                     } /* .compareTo -> метод для сравнения 2х переменных
                          .sorted - метод для сортировки */
                 })
                 .collect(Collectors.toList());
        /** Получаем ТОП10 из списка */
         List<TickerData> top10 =highToLow.stream().limit(10).collect(Collectors.toList());
         int i = 9;
        /** Проверили,что действительно на первом месте находиться крипта с названием "KLAY-USDT" */
         Assert.assertEquals(top10.get(0).getSymbol(), "HEGIC-USDT");
         /* limit(10) -> означает, что мы возьмем 10 элементов из отсортированного списка */
    }

    @Test
           /** 1) получаем список всех криптовалют с окончанием "USDT"
               2) сортируем по худшему изменению стоимости
            крипты за сутки, значение "ChangeRate"
               3) получаем топ10 из всего списка */
    public void sortToLowHigh(){
        List<TickerData> lowToHigh = getTicker().stream().filter(x->x.getSymbol().endsWith("USDT"))
                .sorted(new TicketComparator()).limit(10).collect(Collectors.toList());
        int i = 0;
    }

    @Test
    public void map(){
        List<String> lowerCases = getTicker().stream().map(x->x.getSymbol().toLowerCase()).collect(Collectors.toList());
        int o = 9;
        /* .map преобразовывает один тип данных в другой
           .toLowerCase()) - преобразовывает все заглавные буквы в маленькие
           Cоздаем хэшкарту(usd) в качестве ключа у нас будет название, значение - цена
           */
        Map<String, Double> usd = new HashMap<>();

    }

}
