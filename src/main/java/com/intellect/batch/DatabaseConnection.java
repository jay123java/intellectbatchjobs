package com.intellect.batch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {
	
	

	@Value("${bookingsQuery}")
    private String bookingsQuery;


	public String getBookingsQuery() {
		return bookingsQuery;
	}


	public void setBookingsQuery(String bookingsQuery) {
		this.bookingsQuery = bookingsQuery;
	}
	

}
