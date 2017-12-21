package com.epam.config;

import java.sql.SQLException;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan({"com.epam"})
@EnableJpaRepositories("com.epam.repository")
@EnableTransactionManagement
public class ApplicationConfiguration {

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
    bean.setDatabase(Database.H2);
    bean.setGenerateDdl(true);
    return bean;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setDataSource(dataSource);
    bean.setJpaVendorAdapter(jpaVendorAdapter);
    bean.setPackagesToScan("com.epam");
    bean.setJpaProperties(getHibernateProperties());
    return bean;
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    return new EmbeddedDatabaseBuilder().setName("test")
        .setType(EmbeddedDatabaseType.H2)
        .addScript("backup.sql")
        .build();
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }

  private Properties getHibernateProperties() {
    Properties properties = new Properties();
    properties.put("hibernate.enable_lazy_load_no_trans", true);
    return properties;
  }

}
