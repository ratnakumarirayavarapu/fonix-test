package com.fonix.test.webapptest.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Subscriber {
	
	@Id
	String  email;
	String source;
	String destination;
	String subscription;
	
	public Subscriber() {
		
	}

	public Subscriber(String mailId, String source, String destination, String subscription) {
		super();
		this.email = mailId;
		this.source = source;
		this.destination = destination;
		this.subscription = subscription;
	}

	public String getMailId() {
		return email;
	}

	public void setMailId(String mailId) {
		this.email = mailId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	
	
}
