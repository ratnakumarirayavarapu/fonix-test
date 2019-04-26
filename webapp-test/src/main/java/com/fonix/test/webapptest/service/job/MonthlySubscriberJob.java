package com.fonix.test.webapptest.service.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fonix.test.webapptest.dao.FlightJpaRepository;
import com.fonix.test.webapptest.dao.SubscriberJpaRepository;
import com.fonix.test.webapptest.entity.Flight;
import com.fonix.test.webapptest.entity.Subscriber;

@Component
public class MonthlySubscriberJob {
	
	@Autowired
	SubscriberJpaRepository repo;
	
	@Autowired
	FlightJpaRepository flightRepo;
	
	//@Scheduled(cron = "0 0 10 1 1/1 ? *")
	@Scheduled(cron = "0 0 10 1 * *")
	public void run() throws InterruptedException {
		// get monthly subscribers list from
				List<Subscriber> monthlySubscribers = repo.getSubscribersByFrequency("monthly");
				
				// pull month data from database
				List<Flight> monthlyFlightUpdates = flightRepo.getWeeklyUpdate();
				
				// publish to weekly subscribers 
				for(Subscriber sub:monthlySubscribers) {
					System.out.println("HI " + sub.getMailId() + " your monthly update on flights from " + sub.getSource() + " " + sub.getDestination());
					for(Flight flight:monthlyFlightUpdates) {
						System.out.println("flight " + flight.getFlightNumber() + " " + flight.getDipatureDate() + " " +flight.getPrice());
					}
				}
	}
	
}
