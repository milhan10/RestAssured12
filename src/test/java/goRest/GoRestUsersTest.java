package goRest;

import goRest.Model.User;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
public class GoRestUsersTest {

    @Test
    public void getUsers()
    {

        List<User> userList=
        given()

                .when()
                .get("https://gorest.co.in/public/v1/users")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .log().body()
                .extract().jsonPath().getList("data", User.class)
                ;
            for (User u: userList)
            {
                System.out.println("u = " + u);
            }

            //Daha önceki örneklerde clas dönsmleri icin tüm yapiya karsilik gelen
            //gereken tüm classlari yazarak dönüstürüp istedigimiz elemanlara ulasiyorduk
            //Burada ise aradaki bir veriyi classa dnüstüererek bir list olarak atamamiza
            // imkan vererek JSONPATH i kullandik

            //path : class veya tip dönüsümüne imkan veremeyen direk veriyi verir. List<String> gibi
            //jsonPath : class dönüsümüne ve tip dönüsümüne izin vererek , veriyi istedigimiz formatta verir
    }
     int userID;
    
    @Test
    public void createUser()
    {
        userID=
        given()
                .header("Authorization","Bearer cf91b398b4346993bd82f53ce692631f5e243b1b96e7534db5990ea1650a347e")
                .contentType(ContentType.JSON)
                .body("{\"name\":\""+getRandomName()+"\", \"gender\":\"male\", \"email\":\""+getRandomEmail()+"\", \"status\":\"active\"}")
                
                .when()
                .post("https://gorest.co.in/public/v1/users")

                .then()
                .log().body()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().jsonPath().getInt("data.id")

                ;
        System.out.println("userID = " + userID);
    }

    public String getRandomEmail()
    {
        String randomString= RandomStringUtils.randomAlphabetic(8).toLowerCase();
        return randomString+"@gmail.com";
    }
    public String getRandomName()
    {
        return RandomStringUtils.randomAlphabetic(8).toLowerCase();
    }

    @Test(dependsOnMethods = "createUser")
    public void getUserById() {

        given()
                .param("id",userID)
                .when()
                .get("https://gorest.co.in/public/v1/users/"+userID)

                .then()
                .log().body()
                .body("data.id", equalTo(userID))
                .statusCode(200)
        ;
    }
    
    @Test(dependsOnMethods = "createUser")
    public void updateUserById() {
        String isim="ilhan tuna";

        given()
                .header("Authorization","Bearer cf91b398b4346993bd82f53ce692631f5e243b1b96e7534db5990ea1650a347e")
                .contentType(ContentType.JSON)
                .body("{\"name\":\""+isim+"\"}")
                .pathParam("userID",userID)
                .log().uri()
                .when()
                .put("https://gorest.co.in/public/v1/users/{userID}")

                .then()
                .log().body()
                .statusCode(200)
                .body("data.name",equalTo(isim))
        ;
    }

}
