package com.intellect.batch.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.intellect.batch.DatabaseConnection;

@Configuration
@PropertySource("classpath:application.properties")

public class DataSourceConfiguration {
	
	 private static final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);


    /*@Value("${url}")
    private String connectionUrl;

	@Value("${dbusername}")
    private String username;

	@Value("${password}")
    private String password;

	@Value("${driver}")
    private String driver;*/
	
	/*@Bean(name="intellectDataSource")
	@Autowired
	@Primary
	public DataSource intellectDataSource(DatabaseConnection connection) throws SQLException {

		logger.info("setting up datasource");

		System.out.println(connection.getConnectionUrl());
		BasicDataSource dbcp = new BasicDataSource();
		dbcp.setDriverClassName(connection.getDriver());
		dbcp.setUrl(connection.getConnectionUrl());
		dbcp.setUsername(connection.getUsername());
		dbcp.setPassword(connection.getPassword());
		dbcp.setInitialSize(10);
		dbcp.setRemoveAbandoned(true);
		dbcp.setMaxActive(15);
		return dbcp;
	   
	
	}*/
	
	
	 @Bean(name="intellectDataSource")
	   @ConfigurationProperties(prefix="datasource.alerts")
	 @Primary
	    public DataSource primaryDataSource() {
	    return DataSourceBuilder.create().build();
	    }
	   
	
	 @Bean(name="batchDataSource")
	   @ConfigurationProperties(prefix="datasource.batch")
	    public DataSource secondaryDataSource() {
	    return DataSourceBuilder.create().build();
	    }
	   

	


}
