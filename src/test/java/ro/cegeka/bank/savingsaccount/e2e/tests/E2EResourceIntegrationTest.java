package ro.cegeka.bank.savingsaccount.e2e.tests;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.net.URL;

public abstract class E2EResourceIntegrationTest extends E2EIntegrationTest {

    protected URL base;
    @Autowired
    protected TestRestTemplate template;
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
    }

}
