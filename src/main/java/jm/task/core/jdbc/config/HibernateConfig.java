package jm.task.core.jdbc.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;



import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan(value = "jm.task.core.jdbc")
public class HibernateConfig {

   private Environment env;

   public HibernateConfig(Environment env) {this.env = env;}

   private Properties hibernateProperties() {
      Properties props = new Properties();
      props.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
      props.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
      props.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
      return props;
   }

   private String persistenceUnitName() {
      return getClass().getSimpleName();
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
      entityManagerFactoryBean.setPersistenceUnitName(persistenceUnitName());
      entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());
      entityManagerFactoryBean.setDataSource(dataSource());
      entityManagerFactoryBean.setPackagesToScan("jm.task.core.jdbc");

      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
      entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
      entityManagerFactoryBean.setJpaProperties(hibernateProperties());
      return entityManagerFactoryBean;
   }

   @Bean
   public PlatformTransactionManager transactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
      return transactionManager;
   }

   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
      return new PersistenceExceptionTranslationPostProcessor();
   }

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName(env.getProperty("db.driver"));
      dataSource.setUrl(env.getProperty("db.url"));
      dataSource.setUsername(env.getProperty("db.username"));
      dataSource.setPassword(env.getProperty("db.password"));
      return dataSource;
   }
}
