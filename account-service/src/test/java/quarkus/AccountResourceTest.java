package quarkus;

import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;

@QuarkusTest
public class AccountResourceTest {
    @Test
    void testRetrieveAll() {
        Response result = given().when().get("/accounts").then().statusCode(200).body(containsString("George Baird"),
                containsString("Mary Taylor"), containsString("Diana Rigg")).extract().response();

        List<Account> accounts = result.jsonPath().getList("$");
        assertThat(accounts, not(empty()));
        assertThat(accounts, hasSize(3));

            }
}
