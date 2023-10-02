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
		basePackages = "com.multiple.datasource.repository.user",
		entityManagerFactoryRef = "userEntityManagerFactory",
		transactionManagerRef = "getUserPTM")
		
public class UserDataSource {
	@Bean
	@ConfigurationProperties(prefix = "user.datasource")
	DataSourceProperties getUserDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@ConfigurationProperties("user.datasource.configuration")
	DataSource getUserDataSource() {
		return getUserDataSourceProperties().initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}
	
	@Bean(name="userEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean getUserLocalEMF(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(getUserDataSource()).packages("com.multiple.datasource.entity.user").build();
	}
	
	@Bean
	PlatformTransactionManager getUserPTM(
			final @Qualifier("userEntityManagerFactory") LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}


}
