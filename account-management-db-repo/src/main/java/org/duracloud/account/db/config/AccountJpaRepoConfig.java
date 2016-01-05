/*
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 *
 *     http://duracloud.org/license/
 */
package org.duracloud.account.db.config;

import java.text.MessageFormat;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Daniel Bernstein
 * 
 */
@Configuration
@EnableJpaRepositories(basePackages = { "org.duracloud.account.db" }, 
                       entityManagerFactoryRef = AccountJpaRepoConfig.ENTITY_MANAGER_FACTORY_BEAN, 
                       transactionManagerRef = AccountJpaRepoConfig.TRANSACTION_MANAGER_BEAN)
@EnableTransactionManagement
public class AccountJpaRepoConfig {
    private static final String ACCOUNT_REPO_ENTITY_MANAGER_FACTORY_BEAN =
        "accountRepoEntityManagerFactory";
    public static final String ACCOUNT_REPO_DATA_SOURCE_BEAN =
        "accountRepoDataSource";
    public static final String TRANSACTION_MANAGER_BEAN =
        "accountJpaRepoTransactionManager";
    public static final String ENTITY_MANAGER_FACTORY_BEAN =
        ACCOUNT_REPO_ENTITY_MANAGER_FACTORY_BEAN;

    
    @Bean(name = ACCOUNT_REPO_DATA_SOURCE_BEAN, destroyMethod = "close")
    public BasicDataSource accountRepoDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(MessageFormat.format("jdbc:mysql://{0}:{1}/{2}" +
        		                                "?characterEncoding=utf8" +
        		                                "&characterSetResults=utf8",
                                               System.getProperty(ConfigConstants.MC_DB_HOST,"localhost"),
                                               System.getProperty(ConfigConstants.MC_DB_PORT,"3306"),
                                               System.getProperty(ConfigConstants.MC_DB_NAME, "ama")));
        dataSource.setUsername(System.getProperty(ConfigConstants.MC_DB_USER, "ama"));
        dataSource.setPassword(System.getProperty(ConfigConstants.MC_DB_PASS, "ama"));
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }

    @Primary
    @Bean(name=TRANSACTION_MANAGER_BEAN)
    public PlatformTransactionManager
        accountRepoTransactionManager(@Qualifier(ACCOUNT_REPO_ENTITY_MANAGER_FACTORY_BEAN) EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager tm =
            new JpaTransactionManager(entityManagerFactory);
        tm.setJpaDialect(new HibernateJpaDialect());
        return tm;
    }

    @Bean(name = ACCOUNT_REPO_ENTITY_MANAGER_FACTORY_BEAN)
    public LocalContainerEntityManagerFactoryBean
        accountRepoEntityManagerFactory(@Qualifier(ACCOUNT_REPO_DATA_SOURCE_BEAN) DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf =
            new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPersistenceUnitName("account-repo-pu");
        emf.setPackagesToScan("org.duracloud.account.db");

        String hbm2ddlAuto =
            System.getProperty("hibernate.hbm2ddl.auto");

        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setGenerateDdl(hbm2ddlAuto != null);
        va.setDatabase(Database.MYSQL);
        emf.setJpaVendorAdapter(va);
        
        Properties props = new Properties();
        if(hbm2ddlAuto != null){
            props.setProperty("hibernate.hbm2ddl.auto", hbm2ddlAuto);
        }
        props.setProperty("hibernate.dialect",
                          "org.hibernate.dialect.MySQL5Dialect");
        props.setProperty("hibernate.ejb.naming_strategy",
                          "org.hibernate.cfg.ImprovedNamingStrategy");
        props.setProperty("hibernate.cache.provider_class",
                          "org.hibernate.cache.HashtableCacheProvider");
        props.setProperty("jadira.usertype.autoRegisterUserTypes", "true");
        props.setProperty("jadira.usertype.databaseZone", "jvm");
        props.setProperty("hibernate.show_sql", System.getProperty("hibernate.show_sql", "false"));
        props.setProperty("hibernate.format_sql",  System.getProperty("hibernate.format_sql", "false"));
        props.setProperty("hibernate.show_comments", "false");
        emf.setJpaProperties(props);
        return emf;
    }
}
