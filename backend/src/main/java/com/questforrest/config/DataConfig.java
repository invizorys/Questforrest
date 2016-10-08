package com.questforrest.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by root on 08.10.16.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.bobbbaich.repository")
public class DataConfig {
    private static final String STAND_SYSTEM_PROPERTY_NAME = "stand";
    private static final String DEV_PROPERTIES_PATH_TEMPLATE = "/dev/{stand}.properties";
    private static final String PROP_DATABASE_DRIVER = "db.driver";
    private static final String PROP_DATABASE_USERNAME = "db.username";
    private static final String PROP_DATABASE_PASSWORD = "db.password";
    private static final String PROP_DATABASE_URL = "db.url";
    private static final String PROP_ENTITY_MANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";
    //Hibernate
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROP_ENTITY_MANAGER_PACKAGES_TO_SCAN));
        entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
        System.out.println("entityManagerFactory()");
        return entityManagerFactoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        System.out.println("transactionManager()");
        return transactionManager;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));

        boolean testPhase = !StringUtils.isEmpty(env.getProperty(PROP_DATABASE_URL));
        if(testPhase){
            return setDbProperties(dataSource, env.getRequiredProperty(PROP_DATABASE_URL), env.getRequiredProperty(PROP_DATABASE_USERNAME), env.getRequiredProperty(PROP_DATABASE_PASSWORD));
        }

        String stand = env.getProperty(STAND_SYSTEM_PROPERTY_NAME);
        try {
            Properties devProperties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(DEV_PROPERTIES_PATH_TEMPLATE.replace("{stand}", stand)));
            return setDbProperties(dataSource, devProperties.getProperty(PROP_DATABASE_URL), devProperties.getProperty(PROP_DATABASE_USERNAME), devProperties.getProperty(PROP_DATABASE_PASSWORD));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("dataSource()");
        return dataSource;
    }

    private DataSource setDbProperties(DriverManagerDataSource dataSource, String url, String username, String password) {
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        System.out.println("setDbProperties()");
        return dataSource;
    }

    private Properties getHibernateProperties() {
        System.out.println("getHibernateProperties()");
        Properties properties = new Properties();
        properties.put(PROP_HIBERNATE_DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.put(PROP_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
        properties.put(PROP_HIBERNATE_HBM2DDL_AUTO, env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
        System.out.println("getHibernateProperties()");
        return properties;
    }
}
