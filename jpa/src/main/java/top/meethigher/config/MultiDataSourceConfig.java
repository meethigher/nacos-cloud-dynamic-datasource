package top.meethigher.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RefreshScope
@Slf4j
public class MultiDataSourceConfig {


    @Value("${datasource.config.jdbcUrl}")
    private String jdbcUrl;

    @Value("${datasource.config.username}")
    private String username;

    @Value("${datasource.config.password}")
    private String password;

    @Value("${datasource.config.dialect}")
    private String dialect;




    @Bean
    @RefreshScope
    public DataSource dataSource() {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
            return new HikariDataSource(config);
        } finally {
            log.info("datasource convert to {}", jdbcUrl);
        }
    }

}
