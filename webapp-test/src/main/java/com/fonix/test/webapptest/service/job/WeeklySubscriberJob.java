package com.fonix.test.webapptest.service.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fonix.test.webapptest.dao.FlightJpaRepository;
import com.fonix.test.webapptest.dao.SubscriberJpaRepository;
import com.fonix.test.webapptest.entity.Flight;
import com.fonix.test.webapptest.entity.Subscriber;

@Component
public class WeeklySubscriberJob {
	
	@Autowired
	SubscriberJpaRepository repo;
	
	@Autowired
	FlightJpaRepository flightRepo;
	
	//@Scheduled(cron = "0 0 10 ? * MON *")
	@Scheduled(cron = "0 00 10 * * MON")
	public void run() throws InterruptedException {
		// get weekly subscribers list from
		List<Subscriber> weeklySubscribers = repo.getSubscribersByFrequency("weekly");
		
		// publish to weekly subscribers 
		for(Subscriber sub:weeklySubscribers) {
			System.out.println("HI " + sub.getMailId() + " your weekly update on flights from " + sub.getSource() + " " + sub.getDestination());
			// pull week data from database
			List<Flight> weeklyFlightUpdates = flightRepo.getWeeklyUpdate(sub.getSource(),sub.getDestination());
			for(Flight flight:weeklyFlightUpdates) {
				System.out.println("flight " + flight.getFlightNumber() + " " + flight.getDipatureDate() + " " +flight.getPrice());
			}
		}
	}
}
