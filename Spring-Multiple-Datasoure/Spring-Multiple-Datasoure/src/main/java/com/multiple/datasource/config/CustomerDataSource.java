package com.multiple.datasource.config;

import javax.sql.DataSource;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.multiple.datasource.repository.customer",
		entityManagerFactoryRef = "customerEntityManagerFactory",
		transactionManagerRef = "getOrderPTM")
public class CustomerDataSource {

	@Bean
	@ConfigurationProperties(prefix = "customer.datasource")
	DataSourceProperties getDatasourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("customer.datasource.configuration")
	DataSource getDatasource() {
		return getDatasourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}
	
	@Bean(name="customerEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean getLocalEMF(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getDatasource()).packages("com.multiple.datasource.entity.customer").build();
	}
	
	@Bean
	PlatformTransactionManager getOrderPTM(
			final @Qualifier("customerEntityManagerFactory") LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}

}
