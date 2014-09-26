package com.github.bobbylough.springReferenceData.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.github.bobbylough.springReferenceData.dao")
@PropertySource("classpath:application.properties")
public class DatabaseConfiguration {

	/**
	 *
	 * This will be setting up a datasource using HyperSQL (hsqldb) in memory
	 */
	@Bean
	public DataSource hsqlDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
		basicDataSource.setUsername("sa");
		basicDataSource.setPassword("");
		basicDataSource.setUrl("jdbc:hsqldb:mem:mydb");
		return basicDataSource;
	}

	/**
	 *
	 * This setups the session factory
	 */
	@Bean
	public LocalSessionFactoryBean sessionFactory(Environment environment, DataSource dataSource) {
		String packageOfModelBeans = "com.github.bobbylough.springReferenceData.model";

		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(buildHibernateProperties(environment));
		factoryBean.setPackagesToScan(packageOfModelBeans);
		return factoryBean;
	}


	/**
	 *
	 * Loading all the hibernate properties from a properties file
	 */
	protected Properties buildHibernateProperties(Environment env) {
		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		hibernateProperties.setProperty("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		hibernateProperties.setProperty("javax.persistence.validation.mode", env.getProperty("javax.persistence.validation.mode"));

		return hibernateProperties;
	}

	/**
	 *
	 * This is setting up the hibernate transaction manager
	 *
	 */
	@Bean
	public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

}