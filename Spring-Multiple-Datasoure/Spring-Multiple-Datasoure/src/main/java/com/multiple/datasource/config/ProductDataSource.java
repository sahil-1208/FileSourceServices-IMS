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
		basePackages = "com.multiple.datasource.repository.product",
		entityManagerFactoryRef = "productEntityManagerFactory",
		transactionManagerRef = "getProductPTM")
		

public class ProductDataSource {

	@Bean
	@ConfigurationProperties(prefix = "product.datasource")
	DataSourceProperties getProductDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("product.datasource.configuration")
	DataSource getproductDataSource() {
		return getProductDataSourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}
	
	@Bean(name="productEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean getLocalEMF(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getproductDataSource()).packages("com.multiple.datasource.entity.product").build();
	}
	
	@Bean
	PlatformTransactionManager getProductPTM(
			final @Qualifier("productEntityManagerFactory") LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}

	
}