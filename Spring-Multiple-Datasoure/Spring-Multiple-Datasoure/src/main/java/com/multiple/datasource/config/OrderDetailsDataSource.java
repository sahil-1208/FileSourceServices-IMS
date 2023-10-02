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
		basePackages = "com.multiple.datasource.repository.orderdetails",
		entityManagerFactoryRef = "orderdetailsEntityManagerFactory",
		transactionManagerRef = "getOrderDetailsPTM")
		
public class OrderDetailsDataSource {

	@Bean
	@ConfigurationProperties(prefix = "orderdetails.datasource")
	DataSourceProperties getOrderDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("orderdetails.datasource.configuration")
	DataSource getOrderDetailsDataSource() {
		return getOrderDataSourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}
	
	@Bean(name="orderdetailsEntityManagerFactory")
	LocalContainerEntityManagerFactoryBean getLocalEMF(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getOrderDetailsDataSource()).packages("com.multiple.datasource.entity.orderDetails").build();
	}
	
	@Bean
	PlatformTransactionManager getOrderDetailsPTM(
			final @Qualifier("orderdetailsEntityManagerFactory") LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}

	
}
