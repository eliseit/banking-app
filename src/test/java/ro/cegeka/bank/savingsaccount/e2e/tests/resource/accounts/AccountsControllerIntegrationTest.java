package ro.cegeka.bank.savingsaccount.e2e.tests.resource.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.cegeka.bank.savingsaccount.e2e.tests.E2EResourceIntegrationTest;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static ro.cegeka.bank.savingsaccount.accounts.endpoints.AccountsController.ACCOUNTS_URL;

public class AccountsControllerIntegrationTest extends E2EResourceIntegrationTest {


    @Test
    public void givenRequestForAccounts_whenRequest_thenStatusOK() {

        ResponseEntity<String> response = template.getForEntity(base.toString() + ACCOUNTS_URL,
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsIgnoringCase("hello");
    }

    @Test
    public void givenSubmittedData_whenPOSTRequest_thenStatusOK() {

        Map<String, String> formData = new HashMap<String, String>();
        formData.put("currency", "RON");
        formData.put("type", "SAVINGS");
        formData.put("frequency", "MONTHLY");
        formData.put("amount", BigDecimal.TEN.toString());

        String response = given()
                .urlEncodingEnabled(true)
                .contentType("application/x-www-form-urlencoded")
                .params(formData)
                .when()
                .post(base.toString() + ACCOUNTS_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract().body().asString();

        assertThat(response).containsIgnoringCase("Savings account was created!");

    }
}
