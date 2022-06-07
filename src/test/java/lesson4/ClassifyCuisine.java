package lesson4;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ClassifyCuisine extends AbstractTest {
    String classifyCuisineUrl = "/recipes/cuisine";
    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void searchTitleTest() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Italian Steamed Artichokes")
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();

    }

    @Test
    void searchIngredientListTest() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Simple Skillet Lasagna")
                .formParam("ingredientList","wild boar chops")
                .formParam("language","en")
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();

    }

    @Test
    void NotValidLanguageTest() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("title","Simple Skillet Lasagna")
                .formParam("ingredientList","wild boar chops")
                .formParam("language",1234342)
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();

    }

    @Test
    void NotParamTest() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();

    }

    @Test
    void OnlyLanguageTest() {
        given()
                .spec(requestSpecification)
                .contentType("application/x-www-form-urlencoded")
                .formParam("language","en")
                .when()
                .post(getBaseUrl() + classifyCuisineUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();

    }
}
