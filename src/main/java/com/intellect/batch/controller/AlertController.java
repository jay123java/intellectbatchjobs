package com.intellect.batch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.batch.model.Alert;
import com.intellect.batch.repository.AlertRepository;

@RestController
@RequestMapping("/alerts")
public class AlertController {
	 @Autowired
	  private AlertRepository repo;
	 
	  
	  @RequestMapping(method = RequestMethod.GET)
	  public List<Alert> findAlerts() {
		  
		  List<Alert> repoAlerts = repo.findAll();
		  
		  System.out.println("alerts Size"+repoAlerts.size());
		  List<Alert> list = new ArrayList();
		  
	    return repoAlerts;
	  }
	  
	  @RequestMapping(method = RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)
	  public List findAlertsXml() {
		  System.out.println("came here to resolve");
		  List list = new ArrayList();
		  Alert al = new Alert();
		  al.setConsigneeCode("test");
		  list.add(al);
		  return list;
	  }
	  
	  @RequestMapping(value="/text", method = RequestMethod.GET, produces="text/plain")
	  public String getPlainAlerts() {
		  
	    return "myalerts";
	  }
}
