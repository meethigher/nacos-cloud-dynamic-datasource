package top.meethigher.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RefreshScope
@Slf4j
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPostgresql",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerPostgresql", //配置 事物管理器  transactionManager
        basePackages = {"top.meethigher.db"}//设置持久层所在位置
)
public class JPAConfig {



    @Resource
    private DataSource dataSource;

    @Resource
    private JpaProperties jpaProperties;


    @Resource
    private HibernateProperties hibernateProperties;

    @Resource
    private EntityManagerFactoryBuilder entityManagerFactoryBuilder;




    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPostgresql() {
        log.info("1");
        Map<String, String> map = new HashMap<>();
        // 设置对应的数据库方言
//        map.put("hibernate.dialect", dialect);
        jpaProperties.setProperties(map);
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return entityManagerFactoryBuilder
                //设置数据源
                .dataSource(dataSource)
                //设置数据源属性
                .properties(properties)
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类
                .packages("top.meethigher.db")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
                .persistenceUnit("postgresqlPersistenceUnit")
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManagerPostgresql(@Qualifier("entityManagerFactoryPostgresql") LocalContainerEntityManagerFactoryBean entityManagerFactoryPostgresql) {
        return new JpaTransactionManager(entityManagerFactoryPostgresql.getObject());
    }
}
