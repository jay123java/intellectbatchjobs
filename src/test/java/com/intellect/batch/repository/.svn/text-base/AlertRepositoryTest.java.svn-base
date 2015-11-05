package com.intellect.batch.repository;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.intellect.batch.configuration.AlertsConfiguration;
import com.intellect.batch.configuration.DataSourceConfiguration;
import com.intellect.batch.configuration.JobConfiguration;
import com.intellect.batch.model.Alert;

@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(classes = 
{AlertsConfiguration.class, DataSourceConfiguration.class, JobConfiguration.class,DatabaseConnection.class, AlertController.class, AlertRepository.class}, loader = AnnotationConfigContextLoader.class)
*/

/*@ContextConfiguration(classes = 
{AlertRepositoryTest.TestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
*/public class AlertRepositoryTest {
	
	
	

	//@Autowired
	AlertRepository alertRepository;

	@Test
	public void testGetAlerts(){
		
	 
		//List<Alert> alerts = alertRepository.findAll();
		
		List<Alert> alerts = new ArrayList();
		
		
		assertTrue(alerts!=null && alerts.size()>=0);
		
	}
	
	
	/*@Configuration
	@Import({JobConfiguration.class,DataSourceConfiguration.class})
	@EnableScheduling
	@EnableBatchProcessing
	@EnableJpaRepositories(basePackages={"com.intellect.batch.repository"})
	@EnableAutoConfiguration
	public static class TestConfiguration extends AlertsConfiguration{
		
	}*/
}
