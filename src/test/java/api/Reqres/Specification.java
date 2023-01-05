package api.Reqres;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specification {
    ////// спецификация для запроса \\\\\\
    public static RequestSpecification requestSpec(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)                     // наш юрл адрес
                .setContentType(ContentType.JSON)    // тип ответа
                .build();                            // - собрать
    }
    //// спецификация для ответа (ответ 200ок)\\\\
    public static ResponseSpecification responceSpec200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)  // ожидаемый код ответа
                .build();
    }
    //// спецификация для ответа (ответ 400)\\\\
    public static ResponseSpecification responceSpec400(){
        return new ResponseSpecBuilder()
                .expectStatusCode(400)  // ожидаемый код ответа
                .build();
    }
    //// спецификация кастомная \\\\
    public static ResponseSpecification responceSpecUnique(int status) {
        return new ResponseSpecBuilder()
                .expectStatusCode(status)  // ожидаемый код ответа
                .build();
    }


    public static void installSpecification(ResponseSpecification responce, RequestSpecification request){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = responce;

    }
}
