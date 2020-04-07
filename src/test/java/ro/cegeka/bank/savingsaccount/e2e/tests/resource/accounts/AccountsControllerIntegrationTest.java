package ro.cegeka.bank.savingsaccount.e2e.tests.resource.accounts;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ro.cegeka.bank.savingsaccount.e2e.tests.E2EResourceIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static ro.cegeka.bank.savingsaccount.accounts.endpoints.AccountsController.ACCOUNTS_URL;

public class AccountsControllerIntegrationTest extends E2EResourceIntegrationTest {


    @Test
    public void givenRequestForAccounts_whenRequest_thenStatusOK() {

        ResponseEntity<String> response = template.getForEntity(base.toString() + ACCOUNTS_URL,
                String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
