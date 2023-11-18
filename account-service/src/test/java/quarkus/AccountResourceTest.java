
package quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.math.BigDecimal;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class AccountResourceTest {

    @Test
    void testAllAccounts() {
        given()
          .when().get("/accounts")
          .then()
             .statusCode(200)
             .body("$.size()", equalTo(3)); // Assuming you have 3 initial accounts
    }

    @Test
    void testGetAccount() {
        given()
          .pathParam("accountNumber", 123456789L)
          .when().get("/accounts/{accountNumber}")
          .then()
             .statusCode(200)
             .body("accountNumber", equalTo(123456789L),
                   "customerName", equalTo("George Baird"),
                   "balance", equalTo(354.23f),
                   "accountStatus", equalTo("OPEN"));
    }

    @Test
    void testCreateAccount() {
        Account newAccount = new Account(999L, 888L, "John Doe", new BigDecimal("1000.00"));

        given()
          .contentType(ContentType.JSON)
          .body(newAccount)
          .when().post("/accounts")
          .then()
             .statusCode(201)
             .body("accountNumber", equalTo(999L),
                   "customerName", equalTo("John Doe"),
                   "balance", equalTo(1000.00f),
                   "accountStatus", equalTo("OPEN"));
    }

    @Test
    void testWithdrawal() {
        given()
          .pathParam("accountNumber", 123456789L)
          .body("50.00")
          .when().put("/accounts/{accountNumber}/withdrawal")
          .then()
             .statusCode(200)
             .body("balance", equalTo(304.23f));
    }
    
    @Test
    void testDeposit() {
        given()
          .pathParam("accountNumber", 123456789L)
          .body("50.00")
          .when().put("/accounts/{accountNumber}/deposit")
          .then()
             .statusCode(200)
             .body("balance", equalTo(354.23f));
    }
    

    @Test
    void testCloseAccount() {
        given()
          .pathParam("accountNumber", 123456789L)
          .when().delete("/accounts/{accountNumber}")
          .then()
             .statusCode(204);

        // Verify the account is closed
        given()
          .when().get("/accounts")
          .then()
             .statusCode(200)
             .body("$.size()", equalTo(2)); // Assuming the account was removed
    }
}
