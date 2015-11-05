package com.intellect.batch;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LaunchApplication {

	 private static final Logger logger = LoggerFactory.getLogger(LaunchApplication.class);
	
   	public static void main(String[] args) throws SQLException, FileNotFoundException {
		logger.info("starting boot application");
        SpringApplication.run(LaunchApplication.class, args);
	}

}
