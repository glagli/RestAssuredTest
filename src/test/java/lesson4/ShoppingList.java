package lesson4;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ShoppingList extends AbstractTest{
    String newUserUrl = "/users/connect";
    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    void addAndDeleteRecipeTest() {
        JsonPath newUserResponse = given()
                .spec(requestSpecification)
                .body("{\n"
                        + " \"username\": \"glagli\",\n"
                        + " \"firstName\": \"glagli\",\n"
                        + " \"lastName\": \"glagli\",\n"
                        + " \"email\": \"glagli@yandex.ru\"\n"
                        + "}")
                .when()
                .post(getBaseUrl() + newUserUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();

        String hash = newUserResponse.get("hash");
        String username = newUserResponse.get("username");
        //System.out.println(hash+" hash");
        // System.out.println(username+" username");

        String addRecipeUrl = "/mealplanner/"+username+"/shopping-list/items";
        JsonPath addRecipeResponse = given()
                .spec(requestSpecification)
                .queryParam("hash", hash)
                .body("{\n"
                        + " \"item\": \"1 package baking powder\",\n"
                        + " \"aisle\": \"Baking\",\n"
                        + " \"parse\": true\n"
                        + "}")
                .when()
                .post(getBaseUrl() + addRecipeUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();

        Integer id = addRecipeResponse.get("id");
        //System.out.println(id+" id");

        String getRecipeUrl = "/mealplanner/"+username+"/shopping-list";
        given()
                .spec(requestSpecification)
                .queryParam("hash", hash)
                .when()
                .get(getBaseUrl() + getRecipeUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();

        String deleteRecipeUrl = "/mealplanner/"+username+"/shopping-list/items/"+id;
        JsonPath responseDelete =given()
                .spec(requestSpecification)
                .queryParam("hash", hash)
                .when()
                .delete(getBaseUrl() + deleteRecipeUrl)
                .then()
                .spec(responseSpecification)
                .extract()
                .jsonPath();
        assertThat(responseDelete.get("status"), equalTo("success"));
    }

}
