package com.intellect.batch.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;

import com.intellect.batch.model.Alert;

public class AlertsWriter implements ItemWriter<Alert>{
    public AlertsWriter(String query) {
		this.query = query;
	}

	private static final Logger logger = LoggerFactory.getLogger(AlertsWriter.class);

    
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private String query;
	
	@Override
	public void write(List<? extends Alert> items) throws Exception {
	
		logger.info("in the writer");
		
		for(final Alert item: items){
			
			jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
			    @Override  
			    public Boolean doInPreparedStatement(PreparedStatement ps)  
			            throws SQLException, DataAccessException {  
			      
			    	ps.setString(1, item.getAlertNumber());
			    	ps.setString(2, item.getTransportMode());
			    	ps.setString(3, item.getOriginCountry());
			    	ps.setString(4, item.getExportCountry());
			    	ps.setString(5, item.getShippingTerm());
			    	ps.setString(6, item.getConsigneeCode());
			    	ps.setString(7, item.getCustomerName());
			    	ps.setString(8, item.getCompanyCode());

			    	
			        return ps.execute();  
			              
			    }  
			    });  
			
		}
	}

	

}
