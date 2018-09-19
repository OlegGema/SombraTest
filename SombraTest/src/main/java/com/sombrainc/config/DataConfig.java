package com.sombrainc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.sombrainc.repository")
@EnableTransactionManagement
public class DataConfig {
  @Bean
  public DataSource dataSource(){
    DriverManagerDataSource dataSource=new DriverManagerDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://localhost/sombra");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    return dataSource;
  }
  @Bean
  public JpaVendorAdapter vendorAdapter(){
    HibernateJpaVendorAdapter vendorAdapter=new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.MYSQL);
    vendorAdapter.setShowSql(true);
    return vendorAdapter;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
    LocalContainerEntityManagerFactoryBean factoryBean=new LocalContainerEntityManagerFactoryBean();
    factoryBean.setJpaVendorAdapter(vendorAdapter());
    factoryBean.setDataSource(dataSource());
    factoryBean.setPackagesToScan("com.sombrainc.entity");

    Properties properties=new Properties();
    properties.setProperty("hibernate.hbm2ddl.auto","update");
    properties.setProperty("hibernate.event.merge.entity_copy_observer","allow");

    factoryBean.setJpaProperties(properties);
    return factoryBean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
    JpaTransactionManager jpaTransactionManager=new JpaTransactionManager(entityManagerFactory);
    return jpaTransactionManager;
  }
}
