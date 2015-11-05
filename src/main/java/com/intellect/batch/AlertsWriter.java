package com.intellect.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class AlertsWriter implements ItemWriter<String>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void write(List<? extends String> items) throws Exception {

		for(String item: items){
			
			System.out.println("item is"+item);
		}
	}

}
