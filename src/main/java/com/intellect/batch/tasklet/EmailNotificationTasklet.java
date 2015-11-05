package com.intellect.batch.tasklet;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.intellect.batch.model.Alert;

public class EmailNotificationTasklet implements Tasklet{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String selectAlertsQuery;
	
	public void setSelectAlertsQuery(String selectAlertsQuery) {
		this.selectAlertsQuery = selectAlertsQuery;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		
		jdbcTemplate.query(selectAlertsQuery, new RowMapper<Alert>(){

			@Override
			public Alert mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				return null;
			}
			
		});
			
    		return null;
	}

}
