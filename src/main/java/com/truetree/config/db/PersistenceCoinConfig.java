package com.truetree.config.db;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableJpaRepositories(
        basePackages = {"com.truetree.app.domain.coin.*"},
        entityManagerFactoryRef = "coinEntityManager",
        transactionManagerRef = "coinTransactionManager"
)
@PropertySource({"classpath:application.properties"})
@Configuration
public class PersistenceCoinConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean coinEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(coinDataSource());
        em.setPackagesToScan("com.truetree.app.domain.coin.*");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaPropertyMap(HibernateCommonProperties.commonProperties());
        return em;
    }

    @Bean
    @ConfigurationProperties(prefix = "coin")
    public DataSource coinDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(coinDbProperties.getJdbcDriverClassName());
//        dataSource.setUsername(coinDbProperties.getUserName());
//        dataSource.setPassword(coinDbProperties.getPassWord());
//        dataSource.setUrl(coinDbProperties.getUrl());
//        return dataSource;
        return DataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager coinTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(coinEntityManager().getObject());
        return transactionManager;
    }

}
