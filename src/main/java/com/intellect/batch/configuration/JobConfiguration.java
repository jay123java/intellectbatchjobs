package com.intellect.batch.configuration;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.intellect.batch.DatabaseConnection;
import com.intellect.batch.model.Alert;
import com.intellect.batch.tasklet.EmailNotificationTasklet;
import com.intellect.batch.writer.AlertsWriter;

@Configuration
public class JobConfiguration {
	

	@Bean(name="alertsWriter")
	public ItemWriter<Alert> userWriter(@Value("${insertBookingsAlert}") String bookingAlertsQuery ) {
		ItemWriter<Alert> writer = new AlertsWriter(bookingAlertsQuery);
		
		return writer;
	}	


	@Bean(name="hblalertsWriter")
	public ItemWriter<Alert> houseBillAlertWriter(@Value("${insertHouseBillAlerts}") String hblAlertsQuery ) {
		ItemWriter<Alert> writer = new AlertsWriter(hblAlertsQuery);
		
		return writer;
	}	


	@Bean(name="bookingsReader")
	public ItemReader<Alert> bookingsReader(@Qualifier("intellectDataSource") DataSource datasource, DatabaseConnection connection){
		
		
		JdbcCursorItemReader<Alert> reader = new JdbcCursorItemReader<Alert>();
		reader.setSql(connection.getBookingsQuery());
		reader.setDataSource(datasource);
		
		reader.setRowMapper(new RowMapper<Alert>(){

			@Override
			public Alert mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Alert alert = new Alert();
				String consigneeCode = rs.getString("CONSIGNEE_CODE");
				String transportMode = rs.getString("TRANSPORT_MODE");
				String originCountry = rs.getString("ORIGIN_COUNTRY");
				String exportCountry = rs.getString("EXPORT_COUNTRY");
				String shippingTerm = rs.getString("SHIPPING_TERM");
				String alertNo = rs.getString("PO_NUMBER");
				String companyCode = rs.getString("COMPANY_CODE");
				//String email = rs.getString("email");
				
				alert.setConsigneeCode(consigneeCode);
				alert.setTransportMode(transportMode);
				alert.setOriginCountry(originCountry);
				alert.setExportCountry(exportCountry);
				alert.setShippingTerm(shippingTerm);
				alert.setCustomerName(rs.getString("CUSTOMER_NAME"));
				alert.setAlertNumber(alertNo);
				alert.setCompanyCode(companyCode);
				
				return alert;
			}
			
		});
		
		return reader;
	}
	


	@Bean(name="hblReader")
	public ItemReader<Alert> hblReader(@Qualifier("intellectDataSource") DataSource datasource, @Value("${houseBillQuery}") String hblReaderQuery){
		
		
		JdbcCursorItemReader<Alert> reader = new JdbcCursorItemReader<Alert>();
		reader.setSql(hblReaderQuery);
		reader.setDataSource(datasource);
		
		reader.setRowMapper(new RowMapper<Alert>(){

			@Override
			public Alert mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Alert alert = new Alert();
				String consigneeCode = rs.getString("CONSIGNEE_CODE");
				String transportMode = rs.getString("TRANSPORT_MODE");
				String originCountry = rs.getString("ORIGIN_COUNTRY");
				String exportCountry = rs.getString("EXPORT_COUNTRY");
				String shippingTerm = rs.getString("SHIPPING_TERM");
				String  alertNo = rs.getString("BOOKING_NO");
				String companyCode = rs.getString("COMPANY_CODE");

				alert.setConsigneeCode(consigneeCode);
				alert.setTransportMode(transportMode);
				alert.setOriginCountry(originCountry);
				alert.setExportCountry(exportCountry);
				alert.setShippingTerm(shippingTerm);
				alert.setCustomerName(rs.getString("CUSTOMER_NAME"));
				alert.setAlertNumber(alertNo);
				alert.setCompanyCode(companyCode);

				return alert;
			}
			
		});
		
		return reader;
	}
	

	


@Bean(name="missedBookingsJob")
public Job missedBookingsJob(JobBuilderFactory jobs, @Qualifier(value="bookingsStep") Step step) {
	return jobs.get("missedBookingsJob").incrementer(new RunIdIncrementer())
			.flow(step).end().build();
}

@Bean(name="bookingsStep")
public Step bookingsStep(StepBuilderFactory stepBuilderFactory,  @Qualifier(value="bookingsReader")
		ItemReader reader, @Qualifier(value="alertsWriter") ItemWriter writer,
		  TaskExecutor taskExecutor) {
	return stepBuilderFactory.get("bookingsStep")
			
			. chunk(30).reader(reader)
			.writer(writer).faultTolerant().taskExecutor(taskExecutor).build();
}
	


@Bean(name="missedHblJob")
public Job missedHblJob(JobBuilderFactory jobs, @Qualifier(value="houseBillStep") Step step) {
	return jobs.get("missedHblJob").incrementer(new RunIdIncrementer())
			.flow(step).end().build();
}

@Bean(name="houseBillStep")
public Step houseBillStep(StepBuilderFactory stepBuilderFactory,  @Qualifier(value="hblReader")
		ItemReader reader, @Qualifier(value="hblalertsWriter") ItemWriter writer,
		  TaskExecutor taskExecutor) {
	return stepBuilderFactory.get("houseBillStep")
			
			. chunk(30).reader(reader)
			.writer(writer).faultTolerant().taskExecutor(taskExecutor).build();
}



@Bean(name="emailNotificationJob")
public Job emailNoticicationJob(JobBuilderFactory jobs, @Qualifier(value="bookingsemailNotificationStep") Step step) {
	return jobs.get("emailNotificationJob").incrementer(new RunIdIncrementer())
			.flow(step).end().build();
}

@Bean(name="bookingsemailNotificationStep")
public Step emailNotificationStep(StepBuilderFactory stepBuilderFactory,  @Qualifier(value="bookingsEmailNotificationTasklet")
Tasklet tasklet
	) {
	return stepBuilderFactory.get("bookingsemailNotificationStep").tasklet(tasklet).build();
}

  @Bean(name="bookingsEmailNotificationTasklet")
  public Tasklet emailNotiicationTasklet(@Value("${selectBookingsAlertsQuery}") String selectAlertsQuery, 
		  @Value("${bookingsAlert.subject}") String alertSubject, @Value("${bookingsAlert.template}") String alertTemplatePath) {
	  EmailNotificationTasklet  tasklet = new EmailNotificationTasklet();
	  tasklet.setSelectAlertsQuery(selectAlertsQuery);
	  tasklet.setAlertSubject(alertSubject);
	  tasklet.setEmailTemplatePath(alertTemplatePath);
    return tasklet; 
  }
  
  @Bean(name="houseBillemailNotificationJob")
  public Job housebillNotificationJob(JobBuilderFactory jobs, @Qualifier(value="housebillemailNotificationStep") Step step) {
  	return jobs.get("houseBillemailNotificationJob").incrementer(new RunIdIncrementer())
  			.flow(step).end().build();
  }
  
  @Bean(name="housebillemailNotificationStep")
  public Step houseBillemailNotificationStep(StepBuilderFactory stepBuilderFactory,  @Qualifier(value="housebillEmailNotificationTasklet")
  Tasklet tasklet
  	) {
  	return stepBuilderFactory.get("housebillemailNotificationStep").tasklet(tasklet).build();
  }

    @Bean(name="housebillEmailNotificationTasklet")
    public Tasklet houseBillemailNotiicationTasklet(@Value("${selectHouseBillAlertsQuery}") String selectAlertsQuery,
    		@Value("${housebillAlert.subject}") String alertSubject, @Value("${housebillAlert.template}") String alertTemplatePath) {
  	  EmailNotificationTasklet  tasklet = new EmailNotificationTasklet();
  	  tasklet.setSelectAlertsQuery(selectAlertsQuery);
  	  tasklet.setAlertSubject(alertSubject);
	  tasklet.setEmailTemplatePath(alertTemplatePath);
      return tasklet; 
    }

    
    




}
