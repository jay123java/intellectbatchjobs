package com.intellect.batch.configuration;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@Import({JobConfiguration.class,DataSourceConfiguration.class})
@EnableScheduling
@EnableBatchProcessing
@EnableJpaRepositories(basePackages={"com.intellect.batch.repository"})
@EnableAutoConfiguration

@PropertySource("classpath:application.properties")

public class AlertsConfiguration extends DefaultBatchConfigurer{

	 private static final Logger logger = LoggerFactory.getLogger(AlertsConfiguration.class);

	 

	
	@Override
	protected JobRepository createJobRepository() throws Exception {
		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean();
		factory.setTransactionManager(getTransactionManager());
		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		/*JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
		factory.setDataSource(((DataSourceTransactionManager)getTransactionManager()).getDataSource());
		factory.setTransactionManager(getTransactionManager());
		factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
		factory.afterPropertiesSet();*/
		return  factory.getObject();
	}
	
	
	 
	 @Bean
		public TaskExecutor taskExecutor() {
			ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
			taskExecutor.setMaxPoolSize(8);
			taskExecutor.afterPropertiesSet();
			return taskExecutor;
		}
	 
  

   @Override
   @Autowired 
	public void setDataSource(@Qualifier("batchDataSource") DataSource dataSource) {
		super.setDataSource(dataSource);
	}
	
	@Bean
	public JdbcTemplate getTemplate(@Qualifier("intellectDataSource") DataSource datasource){
		
		return new JdbcTemplate(datasource);
	}
	
	
	
	
	
	
	

	
	
	

}	
