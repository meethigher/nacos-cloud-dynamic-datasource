package top.meethigher.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
@RefreshScope
@Slf4j
public class JdbcTemplateConfig {

    @Value("${datasource.config.jdbcUrl}")
    private String jdbcUrl;

    @Value("${datasource.config.username}")
    private String username;

    @Value("${datasource.config.password}")
    private String password;

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

    @Bean
    @RefreshScope
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        try {
            return new JdbcTemplate(dataSource);
        } finally {
            log.info("jdbctemplate convert to {}", jdbcUrl);
        }
    }

    /**
     * 配置Spring的声明式事务(即使用注解@Transactional)
     * 事务分为声明式事务、编程式事务
     */
    @Bean
    @RefreshScope
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


}
