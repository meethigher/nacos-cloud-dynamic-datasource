package top.meethigher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

public class JpaApp {
    public static void main(String[] args) {
        SpringApplication.run(JpaApp.class, args);
    }
}