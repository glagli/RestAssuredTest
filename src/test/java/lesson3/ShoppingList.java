package lesson3;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ShoppingList extends AbstractTest{
    String addShopListUrl = "/mealplanner/{username}/shopping-list/items";

    @BeforeAll
    static void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    // тут должны быть тесты, но пока их нет
}
