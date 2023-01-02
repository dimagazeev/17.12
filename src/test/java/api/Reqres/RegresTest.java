package api.Reqres;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;

public class RegresTest {
    private final static String URL = "https://reqres.in/";

    @Test/**
     класс "UserData GET запрос"
     1)Использую сервис "https://reqres.in/" получить список пользователей со второй(2)стр
     2) Убедиться, что имена файлов-аватаров пользователей совпадают
     3) Убедиться, что email пользователей имеет окончание "@reqres.in"
     */
    public void checkAvatarAndIdTest(){
        Specification.installSpecification(Specification.responceSpec200(),Specification.requestSpec(URL));
        // сравнить значения напрямую из экземпляра класса
        List<UserData> users = given()
                .contentType(ContentType.JSON)
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data",UserData.class);
        /*.given -> статичный метод из rest assured(с него начинаются все запросы hhtp протокола)
         * . when(когда) -> какой у нас тип данных и куда обращаемся
         * .contentType(ContentType.JSON) -> Формат чтения(указываем,что формат ответа будет JSON)
         * .get -> тут наш GET- запрос
         * .then(затем) ->00 */
                 users.stream().forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));
        /**  по каждому пользователю произошел перебор и мы у каждого пользователя получили аватар
         * и сравнили то.что у каждого содержится id */

                 ////проверил,что почта окончивается на "@reqres.in" \\\\
                 Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
                 /* x.getId().toString() т.к getId - int мы переводим его в строку
                    (т.к мы не можем сравнивать строку с числом, поэтому переводим в toString())
                    .stream() - позволяет нам перебрать наш список и вызвать метод по очередности
                    .allMatch - все совпадения
                    .getEmail() - геттер email
                    .endsWith - с окончанием...("@reqres.in") */
        //сравнили значения через получения списков ( список с автарками) \\\\
        List<String>  avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        /* .map(UserData::getAvatar) - выыбираем одно из значений -> "getAvatar"
           .map(x->x.getId().toString()) вызвали лямду(х) в этой люмде получим getId() и переведем в строку
           .collect(Collectors.toList() этот метод всю отфильтрованную информацию засунет в список
        *  */
        List<String>  ids = users.stream().map(x->x.getId().toString()).collect(Collectors.toList());

        for(int i =0; i<avatars.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
            // "contains" метод сравнения (содержание одной строки в другой)
        }
    }
    @Test /** класс "SuccessReg" POST запрос
     1)Использую сервис "https://reqres.in/" протестировать регистрацию пользователя в системе
     2) Успешная регистрация */

    public void successRegTest(){
        Specification.installSpecification(Specification.responceSpec200(),Specification.requestSpec(URL));
        //// ожидаемый результат \\\\
        Integer id =4;
        String token = "QpwL5tke4Pnpja7X4";
        //// тело(body) запроса \\\\
        Registr user = new Registr("eve.holt@reqres.in","pistol");
        SuccessReg successReg = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
        //// Смотрим пришел ли вообще какой-нибудь ответ \\\\
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());
        //// Сравниваем ожидаемый результат с фактическим \\\\
        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());
    }



    @Test /**
     класс "UnSuccessReg" POST запрос
     1)Использую сервис "https://reqres.in/" протестировать регистрацию пользователя в системе
     2) Регистрация без пароля*/
    public void unSuccessRegTest(){
        Specification.installSpecification(Specification.responceSpec400(),Specification.requestSpec(URL));
        //// тело(body) запроса \\\\
        Registr user = new Registr("sydney@fife","");
        UnSuccessReg unSuccessReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(UnSuccessReg.class);
        //// Смотрим пришел ли вообще какой-нибудь ответ \\\\
        Assert.assertNotNull(unSuccessReg.getError());
        //// Сравниваем ожидаемый результат с фактическим \\\\
        Assert.assertEquals("Missing password", unSuccessReg.getError());
    }

    @Test /** класс "ColorData" GET запрос
     1)Использую сервис "https://reqres.in/"  убедиться, что операция List<RESOURCE> возвращает данные
     отсортированные по годам
     */
    public void sortedYearsTest(){
        Specification.installSpecification(Specification.responceSpec200(),Specification.requestSpec(URL));
        List<ColorData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data",ColorData.class);
        // получаем список с годами "getYear" \\
        List<Integer>  years = colors.stream().map(ColorData::getYear).collect(Collectors.toList());
        // теперь сортируем список по возрастанию \\
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        // сравниваем ожидаемый(sortedYears) результат с актуальным(years) \\
        Assert.assertEquals(sortedYears, years);
    }

    @Test
    /** DELETE запрос
     Использую сервис "https://reqres.in/" удалить второго пользователя и сравнить статус коды
     */
    public void deleteUserTest(){
        Specification.installSpecification(Specification.responceSpecUnique(204),Specification.requestSpec(URL));
                 given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }

    @Test
    /** класс UserTime+ UserTimeResponce PUT запрос
     Использую сервис "https://reqres.in/" обновить информацию о пользователе и
     сравнить дату обновления с обновления с текущей датой на сервере
     */
    public void TimeTest(){
        Specification.installSpecification(Specification.responceSpecUnique(200),Specification.requestSpec(URL));
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponce responce = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponce.class);
        String regex = "(.{6})$";
        // сайт "regex101.com" "." - все значения; 5 цифр; $ - конца;
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex," ");
        Assert.assertEquals(currentTime, responce.getUpdatedAt().replaceAll(regex," "));



    }
}
