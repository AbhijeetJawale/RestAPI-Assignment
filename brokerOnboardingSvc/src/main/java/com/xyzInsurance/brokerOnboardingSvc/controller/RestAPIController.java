package com.xyzInsurance.brokerOnboardingSvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.xyzInsurance.brokerOnboardingSvc.entities.Broker;
import com.xyzInsurance.brokerOnboardingSvc.services.BrokerService;

@RestController
 public class RestAPIController {
	@Autowired
	private JdbcTemplate template;
	
	@Autowired
	private BrokerService brokerservice; 
	
	
	
	
	
	//Save Broker Details
	@PostMapping("/broker/save")
	public ResponseEntity<Broker> addbroker(@RequestBody Broker broker,@RequestHeader(value="Authorization") String token)
	{
		
		if(!"Bearer authToken".equals(token))
		{
			throw new ResponseStatusException(
					  HttpStatus.UNAUTHORIZED,"Auth Token Validation Failed"
					);
			
		}
		
		String statement = "SELECT Count(*) FROM Brokers where brokerId = "+broker.getBrokerid();
		int result = template.queryForObject(statement, Integer.class);
		if(result>0) {
			throw new ResponseStatusException(
					  HttpStatus.BAD_REQUEST, "Duplicate broker id "
					);

		}
		else
		{		
		return new ResponseEntity<Broker>(this.brokerservice.addbroker(broker), HttpStatus.CREATED);
		}
	
	}
	
	
	
	//Get Broker Details for given broker id
	@GetMapping("/broker/{brokerid}")
	public ResponseEntity<List<Broker>> getSingleBroker(@PathVariable String brokerid,@RequestHeader(value="Authorization") String token)
	{
		if(!"Bearer authToken".equals(token))
		{
			throw new ResponseStatusException(
					  HttpStatus.UNAUTHORIZED,"Auth Token Validation Failed"
					);
			
		}
		
		
		String statement = "SELECT COUNT(*) FROM Brokers where brokerId = "+brokerid;
		int result = template.queryForObject(statement, Integer.class);
		if(result == 0) {
			throw new ResponseStatusException(
					  HttpStatus.NO_CONTENT, "Broker Does Not exist"
					);
	
		}
		else {
		//return this.brokerservice.getSingleBroker(Long.parseLong(brokerid));
		return new ResponseEntity<List<Broker>>(this.brokerservice.getSingleBroker(Long.parseLong(brokerid)), HttpStatus.OK);
		}
	
	}
	
	
	
	
	//Get all broker details
	@GetMapping("/allbrokers")
	public ResponseEntity<List<Broker>> getbroker(@RequestHeader(value="Authorization") String token)
	{
		if(!"Bearer authToken".equals(token))
		{
			throw new ResponseStatusException(
					  HttpStatus.UNAUTHORIZED,"Auth Token Validation Failed"
					);
			
		}
		
			
		if(this.brokerservice.getBroker().isEmpty())
		{
			return new ResponseEntity<List<Broker>>(this.brokerservice.getBroker(), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Broker>>(this.brokerservice.getBroker(), HttpStatus.OK);
		
	}
	
	
	
	//Update Brokerdetails for Given Brokerid
	@PutMapping("/broker/{BrokerId}")
	public ResponseEntity<Broker> updatebroker(@RequestBody Broker broker,@RequestHeader(value="Authorization") String token)
	{
		if(!"Bearer authToken".equals(token))
		{
			throw new ResponseStatusException(
					  HttpStatus.UNAUTHORIZED,"Auth Token Validation Failed"
					);
			
		}
		
		String statement = "SELECT Count(*) FROM Brokers where brokerId = "+broker.getBrokerid();
		int result = template.queryForObject(statement, Integer.class);
		if(result==0) {
			
			return new ResponseEntity<Broker>(broker, HttpStatus.SERVICE_UNAVAILABLE);

		}
		
		this.brokerservice.updatebroker(broker);
		return new ResponseEntity<Broker>(broker, HttpStatus.OK);
		
		
	}
	
	
	//Delete broker record for given brokerid
	@DeleteMapping("/broker/delete/{BrokerId}")	
	public ResponseEntity<String> deleteBroker(@PathVariable String BrokerId,@RequestHeader(value="Authorization") String token)
	{
		if(!"Bearer authToken".equals(token))
		{
			throw new ResponseStatusException(
					  HttpStatus.UNAUTHORIZED,"Auth Token Validation Failed"
					);
			
		}
		
		String statement = "SELECT Count(*) FROM Brokers where brokerId = "+BrokerId;
		int result = template.queryForObject(statement, Integer.class);
		if(result==0) {
			
			
			return new ResponseEntity<String>("Something went Wrong. Record was not deleted.", HttpStatus.SERVICE_UNAVAILABLE);

		}
		
		this.brokerservice.deletbroker(BrokerId);	
		return new ResponseEntity<String>("	Record deleted successfully", HttpStatus.OK);
		
	}
	
	
	

}
