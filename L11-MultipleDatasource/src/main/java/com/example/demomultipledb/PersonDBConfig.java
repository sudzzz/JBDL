package com.example.demomultipledb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.example.demomultipledb.persondb"},
        entityManagerFactoryRef = "personEntityManager",
        transactionManagerRef = "personTxnManager"
)
public class PersonDBConfig {

    @Autowired
    Environment environment;

    @Bean
    @ConfigurationProperties(prefix = "spring.personds")
    public DataSource personDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean personEntityManager(){
        LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();

        em.setDataSource(personDatasource());
        em.setPackagesToScan("com.example.demomultipledb.persondb");

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();

        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.ddl-auto", "update");
        properties.put("hibernate.hbm2ddl.auto", environment.getProperty("personds.hibernate.ddl-auto"));
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");

        em.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public PlatformTransactionManager personTxnManager(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(personEntityManager().getObject());
//        jpaTransactionManager.setDataSource();

        return jpaTransactionManager;
    }


}
