package ro.cegeka.bank.savingsaccount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
public class SavingsAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SavingsAccountApplication.class, args);
    }


    // for convenience we have this declared here
    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

}
