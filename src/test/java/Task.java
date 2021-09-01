import POJO.pojo;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class Task {
    @Test
    public void task1() {
        given()
                .when()
                .get("https://httpstat.us/203")
                .then()
                .log().body()
                .statusCode(203)
                .contentType(ContentType.TEXT)

        ;
    }

    @Test
    public void task2() {
        given()
                .when()
                .get("https://httpstat.us/203")
                .then()
                .log().body()
                .body(equalTo("203 Non-Authoritative Information"))
                .statusCode(203)
                .contentType(ContentType.TEXT)

        ;
    }

    @Test
    public void task3() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .log().body()
                .body("title",equalTo("quis ut nam facilis et officia qui"))
                .statusCode(200)
                .contentType(ContentType.JSON)

        ;
    }
    @Test
    public void task4() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .log().body()
                .body("completed",equalTo(false))
                .statusCode(200)
                .contentType(ContentType.JSON)

        ;
    }
    @Test
    public void task5() {
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos")
                .then()
                .log().body()
                .body("title[2]",equalTo("fugiat veniam minus"))
                .body("userId[2]",equalTo(1))
                .statusCode(200)
                .contentType(ContentType.JSON)

        ;
    }
    @Test
    public void task6() {
        pojo pojos=
        given()
                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")
                .then()
                .log().body()

                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract().as(pojo.class)

        ;
        System.out.println("pojos = " + pojos);
        System.out.println("pojos.getTitle() = " + pojos.getTitle());
        System.out.println("pojos.getCompleted() = " + pojos.getCompleted());
        System.out.println("pojos.getId() = " + pojos.getId());
        System.out.println("pojos.getUserId() = " + pojos.getUserId());
    }

    @Test
    public void task7() {
        pojo[] pojos =
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos")
                        .then()
                        .log().body()

                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().as(pojo[].class);
        System.out.println("pojos = " + Arrays.toString(pojos));

    }

    @Test
    public void task8() {
        List<pojo> pojos = Arrays.asList(
                given()
                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos")
                        .then()
                        .log().body()

                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .extract().as(pojo[].class));
        System.out.println("pojos = " + pojos);

    }


}
