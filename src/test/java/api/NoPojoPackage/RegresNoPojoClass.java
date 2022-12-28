package api.NoPojoPackage;
import api.Reqres.Specification;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class RegresNoPojoClass {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarsNoPojoTest(){
        Specification.installSpecification(Specification.responceSpecUnique(200),Specification.requestSpec(URL));
        Response response = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .body("page", equalTo(2)) // equalTo -> значения равно 2
                .body("data.id", notNullValue()) // "notNullValue" -> приходит не пустое значение
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .extract().response();
        /// созданный "response" превращаем в JSON \\\
        JsonPath jsonPath = response.jsonPath();
        /// получили список из email, id, avatar  \\\
        List<String> emails = jsonPath.get("data.email");
        List<Integer> ids = jsonPath.get("data.id");
        List<String> avatars = jsonPath.get("data.avatar");
        /// проверяем,что в наших avatar содержаться id  \\\
        for ( int i=0; i<avatars.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i).toString()));
            // "contains" метод сравнения (содержание одной строки в другой)
        }
        Assert.assertTrue(emails.stream().allMatch(x->x.endsWith("@reqres.in")));
        ////проверил, что почта оканчивается на "@reqres.in" см класс "RegresTest"\\\\
    }
    @Test
    public void successRegTestNoPojo(){
        Specification.installSpecification(Specification.responceSpecUnique(200),Specification.requestSpec(URL));
        Map<String, String> user = new HashMap<>();
        // ключ- значение указваем из боди POST запроса\\
        user.put("email","eve.holt@reqres.in");
        user.put("password", "pistol");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                // здесь body ответа \\
                .body("id", equalTo(4))
                .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        // по отдельности достаем переменные \\
        int id = jsonPath.get("id");
        String token = jsonPath.get("token");
        Assert.assertEquals(4, id);
        Assert.assertEquals("QpwL5tke4Pnpja7X4",token);
    }

    @Test
    public void unsuccessRegTestNoPojo(){
        Specification.installSpecification(Specification.responceSpecUnique(400),Specification.requestSpec(URL));
        Map<String, String> user = new HashMap<>();
        user.put("email", "sydney@fife");
        Response response = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().response();
        JsonPath jsonPath = response.jsonPath();
        String error = jsonPath.get("error");
        Assert.assertEquals("Missing password", error);
    }
    @Test
    public void sortedYearsTestNoPojo(){
        Specification.installSpecification(Specification.responceSpecUnique(200),Specification.requestSpec(URL));

                Response response = given()
                        .when()
                        .get("api/unknown")
                        .then().log().all()
                        .body("page", equalTo(1))
                        .body("per_page", equalTo(6))
                        .body("total", equalTo(12))
                        .body("total_pages", equalTo(2))

                        .body("data.id", notNullValue())
                        .body("data.name", notNullValue())
                        .body("data.year", notNullValue())
                        .body("data.color", notNullValue())
                        .body("data.pantone_value", notNullValue())
                        .extract().response();
        JsonPath jsonPath = response.jsonPath();
        /// получили список из year  \\\
        List<Integer> year = jsonPath.get("data.year");
        /// отсортировали года по возрастанию  \\\
        List<Integer> sortedYear = year.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYear, year);




    }
}
