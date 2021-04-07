package com.xyzInsurance.brokerOnboardingSvc.entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
public class Broker {
	private long Brokerid;
	private String BrokerFirstName;
	private String BrokerLastName;
	public Broker(long brokerid, String brokerFirstName, String brokerLastName) {
		super();
		Brokerid = brokerid;
		BrokerFirstName = brokerFirstName;
		BrokerLastName = brokerLastName;
	}
	public Broker() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getBrokerid() {
		return Brokerid;
	}
	public void setBrokerid(long brokerid) {
		Brokerid = brokerid;
	}
	public String getBrokerFirstName() {
		return BrokerFirstName;
	}
	public void setBrokerFirstName(String brokerFirstName) {
		BrokerFirstName = brokerFirstName;
	}
	public String getBrokerLastName() {
		return BrokerLastName;
	}
	public void setBrokerLastName(String brokerLastName) {
		BrokerLastName = brokerLastName;
	}
	@Override
	public String toString() {
		return "Brokers [Brokerid=" + Brokerid + ", BrokerFirstName=" + BrokerFirstName + ", BrokerLastName="
				+ BrokerLastName + "]";
	}

}
