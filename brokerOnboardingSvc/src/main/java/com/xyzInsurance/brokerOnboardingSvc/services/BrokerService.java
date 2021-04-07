package com.xyzInsurance.brokerOnboardingSvc.services;

import java.util.List;

import com.xyzInsurance.brokerOnboardingSvc.entities.Broker;

public interface BrokerService {
	
	
	public Broker addbroker(Broker broker);
	public List<Broker> getSingleBroker(long brokerid);
	public List<Broker> getBroker();
	public void updatebroker(Broker broker);
	void deletbroker(String brokerid);


}
	