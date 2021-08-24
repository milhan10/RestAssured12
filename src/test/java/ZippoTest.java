import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class ZippoTest {

    @Test
    public void  test()
    {
        given()
                //hazirlik islemleri yapacagiz
                .when()
                //link ve aksion islemleri
                .then()
                //test ve extrakt islemleri
        ;
    }

    @Test
    public void statusCodeTest(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body() //log. all btün responsu gösterir
                .statusCode(200) // status kontrolü

                ;
    }

    @Test
    public void contentTypeTest()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .contentType(ContentType.JSON)
              ;
    }

    @Test
    public void logTest()
    {
        given()
                .log().all()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
        ;
    }

    @Test
    public void checkStateInResponseBody()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("country",equalTo("United States"))//body.country == United States
                .statusCode(200)
        ;
    }
    @Test
    public void californiaTest()
    {
        given()
                .when()
                .get("http://api.zippopotam.us/us/90210")
                .then()
                .log().body()
                .body("places[0].state",equalTo("California"))//
                .statusCode(200)
        ;
    }
}
