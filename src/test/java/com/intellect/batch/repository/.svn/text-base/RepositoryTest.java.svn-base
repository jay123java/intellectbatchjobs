package com.intellect.batch.repository;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.intellect.batch.base.AbstractBaseTest;
import com.intellect.batch.model.Alert;

public class RepositoryTest extends AbstractBaseTest{
	
@Autowired AlertRepository repository;
	
   @Test
   public void testFindAlerts(){
	   List<Alert> alerts = repository.findAll();
	   
	   assertTrue(alerts!=null && alerts.size()>=0);
	
   }
}
