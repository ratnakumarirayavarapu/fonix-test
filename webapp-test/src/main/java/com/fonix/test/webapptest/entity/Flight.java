package com.fonix.test.webapptest.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*
 * Entity for Flight data table
 */
@Entity
public class Flight {
	
	@Id
	int flightNumber;
	String originPoint;
	String destinationPoint;
	Date dipatureDate;
	long price;
	@GeneratedValue
	Date sysDate;
	
	public Flight() {
		
	}

	public Flight(int flightNumber, String originPoint, String destinationPoint, Date dipatureDate, long price,
			Date sysDate) {
		super();
		this.flightNumber = flightNumber;
		this.originPoint = originPoint;
		this.destinationPoint = destinationPoint;
		this.dipatureDate = dipatureDate;
		this.price = price;
		this.sysDate = sysDate;
	}
	

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getSysDate() {
		return sysDate;
	}

	public void setSysDate(Date sysDate) {
		this.sysDate = sysDate;
	}

	public String getOriginPoint() {
		return originPoint;
	}

	public void setOriginPoint(String originPoint) {
		this.originPoint = originPoint;
	}

	public String getDestinationPoint() {
		return destinationPoint;
	}

	public void setDestinationPoint(String destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

	public Date getDipatureDate() {
		return dipatureDate;
	}

	public void setDipatureDate(Date dipatureDate) {
		this.dipatureDate = dipatureDate;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	
	
}
