package com.multiple.datasource.config;

import javax.sql.DataSource;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.multiple.datasource.repository.supplier", 
					entityManagerFactoryRef = "supplierEntityManagerFactory",
					transactionManagerRef = "getPlatformTxMgr")
public class SupplierDataSource {
	@Bean
	@ConfigurationProperties(prefix = "supplier.datasource")
	DataSourceProperties getDataSourceProperty() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "supplier.datasource.configuration")
	DataSource getDataSource() {
		return getDataSourceProperty().initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}

	@Bean(name = "supplierEntityManagerFactory")
	@Primary
	LocalContainerEntityManagerFactoryBean supplierEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getDataSource()).packages("com.multiple.datasource.entity.supplier").build();
	}

	@Bean
	@Primary
	PlatformTransactionManager getPlatformTxMgr(
			final @Qualifier("supplierEntityManagerFactory") LocalContainerEntityManagerFactoryBean supplierEntityManagerFactory) {
		return new JpaTransactionManager(supplierEntityManagerFactory.getObject());
	}

}
