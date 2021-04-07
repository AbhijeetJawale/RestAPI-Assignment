package com.xyzInsurance.brokerOnboardingSvc.services;
import com.xyzInsurance.brokerOnboardingSvc.entities.Broker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;


@Service
public class BrokerServiceImplement implements BrokerService {
	@Autowired
	private JdbcTemplate template;
	List<Broker> list;
	
	//Adding the new broker
	public Broker addbroker(Broker broker) {
		
		String statement = "INSERT INTO Brokers(BrokerId,BrokerFirstName,BrokerLastName) VALUES("+broker.getBrokerid()+","+"'"+broker.getBrokerFirstName()+"'"+","+"'"+broker.getBrokerLastName()+"'"+")";
		int result = template.update(statement);
		if (result>0)
		{
			
		return broker;
		}
		return null;
		
	}
	
	//For getting a single broker via the brokerid
	public List<Broker> getSingleBroker(long brokerid) {
		
		String statement = "SELECT * FROM Brokers where brokerId = "+brokerid;
		
		
		List<Broker> broker = template.query(statement, new RowMapper<Broker>() {
			

			@Override
			public Broker mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				return new Broker(rs.getLong("BrokerId"),rs.getString("BrokerFirstName"),rs.getString("BrokerLastName"));
			}
			
		});
		
		return broker;
	}
	
	
	
	//Get all the brokers in the database
	public List<Broker> getBroker()
	{
		List<Broker> broker = template.query("SELECT * FROM Brokers", new RowMapper<Broker>() {

			@Override
			public Broker mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				return new Broker(rs.getLong("BrokerId"),rs.getString("BrokerFirstName"),rs.getString("BrokerLastName"));
			}
			
		});
		return broker;
		
	}
	
	
	//To update the brokers 
	public void updatebroker(Broker broker) {
		String statement = "UPDATE Brokers SET BrokerFirstName = "+"'"+broker.getBrokerFirstName()+"'"+","+"BrokerLastName = "+"'"+broker.getBrokerLastName()+"'"+"WHERE BrokerId="+broker.getBrokerid();
		template.update(statement);
		
		
	}
	
	//To delete the brokers
	public void deletbroker(String brokerid) {
		String statement = "DELETE FROM Brokers WHERE BrokerId = "+brokerid;
		template.update(statement);
		
		
	}


}
