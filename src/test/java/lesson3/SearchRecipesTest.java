package lesson3;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.text.StringContainsInOrder.stringContainsInOrder;

public class SearchRecipesTest extends AbstractTest {
    String searchRecipesUrl = "/recipes/complexSearch";
    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void queryCheckTest() {
        JsonPath response = given()
                .contentType("application/json")
                .queryParam("query", "burger")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

        Integer size = given()
                .contentType("application/json")
                .queryParam("query", "burger")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results")
                .size();

        for (int i = 0; i < size; i++) {
            assertThat(response.get(String.format("results[%d].title",i)).toString().toLowerCase(Locale.ROOT),
                    stringContainsInOrder("burger")) ;
        }
    }

    @Test
    void queryCuisineTest() {
        given()
                .contentType("application/json")
                .queryParam("cuisine", "European")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();
    }


    @Test
    void maxAlcoholTest() {
        JsonPath response = given()
                .contentType("application/json")
                .queryParam("maxAlcohol", "6")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

        Integer size = given()
                .contentType("application/json")
                .queryParam("maxAlcohol", "6")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results")
                .size();

        for (int i = 0; i < size; i++) {
            Float amount = response
                    .get(String.format("results[%d].nutrition.nutrients[0].amount",i));
            Assertions.assertTrue(amount<6.0);
        }
    }

    @Test
    void minAlcoholTest() {
        JsonPath response = given()
                .contentType("application/json")
                .queryParam("minAlcohol", "10")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

        Integer size = given()
                .contentType("application/json")
                .queryParam("minAlcohol", "10")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results")
                .size();

        for (int i = 0; i < size; i++) {
            Float amount = response
                    .get(String.format("results[%d].nutrition.nutrients[0].amount",i));
            Assertions.assertTrue(amount>10.0);
        }
    }

    @Test
    void maxCarbsTest() {
        JsonPath response = given()
                .contentType("application/json")
                .queryParam("maxCarbs", "60")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

        Integer size = given()
                .contentType("application/json")
                .queryParam("maxCarbs", "60")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results")
                .size();

        for (int i = 0; i < size; i++) {
            Float amount = response
                    .get(String.format("results[%d].nutrition.nutrients[0].amount",i));
            Assertions.assertTrue(amount<60.0);
        }
    }

    @Test
    void maxFatTest() {
        JsonPath response = given()
                .contentType("application/json")
                .queryParam("maxFat", "10")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .time(lessThan(5000L))
                .extract()
                .jsonPath();

        Integer size = given()
                .contentType("application/json")
                .queryParam("maxFat", "10")
                .queryParam("apiKey", getApiKey())
                .when()
                .get(getBaseUrl() + searchRecipesUrl)
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList("results")
                .size();

        for (int i = 0; i < size; i++) {
            Float amount = response
                    .get(String.format("results[%d].nutrition.nutrients[0].amount",i));
            Assertions.assertTrue(amount<10.0);
        }
    }

}
