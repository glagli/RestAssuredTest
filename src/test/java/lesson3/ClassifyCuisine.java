package lesson3;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ClassifyCuisine extends AbstractTest{
    String classifyCuisineUrl = "/recipes/cuisine";
    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void searchTitleTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", getApiKey())
                .formParam("title","Italian Steamed Artichokes")
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

    }

    @Test
    void searchIngredientListTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", getApiKey())
                .formParam("title","Simple Skillet Lasagna")
                .formParam("ingredientList","wild boar chops")
                .formParam("language","en")
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

    }

    @Test
    void NotValidLanguageTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", getApiKey())
                .formParam("title","Simple Skillet Lasagna")
                .formParam("ingredientList","wild boar chops")
                .formParam("language",1234342)
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

    }

    @Test
    void NotParamTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", getApiKey())
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

    }

    @Test
    void OnlyLanguageTest() {
        JsonPath response = given()
                .contentType("application/x-www-form-urlencoded")
                .queryParam("apiKey", getApiKey())
                .formParam("language","en")
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

    }
}
