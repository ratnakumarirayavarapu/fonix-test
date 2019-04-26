package com.fonix.test.webapptest.service.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fonix.test.webapptest.dao.FlightJpaRepository;
import com.fonix.test.webapptest.dao.SubscriberJpaRepository;
import com.fonix.test.webapptest.entity.Flight;
import com.fonix.test.webapptest.entity.Subscriber;

@Component
public class CrawlerService {
	@Autowired
	FlightJpaRepository flightRepo;
	@Autowired
	SubscriberJpaRepository subRepo;
	
	public void  runCrawler() {
		// get flight info
		Flight flight = getFlightInfo();
		// save in data base
		flightRepo.addFlightInfo(flight);
		
		// send to daily subscriber get by origin and destination daily subscribers
		List<Subscriber> dailySubscribers = subRepo.getSubscribersByFrequency("daily");
		sendNotificationToSubscribers(dailySubscribers,flight);
		
		
		// check for best deal, if found send to 
		long bestPrice = flightRepo.getBestPrice(flight.getOriginPoint(),flight.getDestinationPoint());
		if(flight.getPrice()<bestPrice) {
			List<Subscriber> uncappedSubscribers = subRepo.getSubscribersByFrequency("Uncapped");
			sendNotificationToSubscribers(uncappedSubscribers,flight);
		}
	}

	private void sendNotificationToSubscribers(List<Subscriber> dailySubscribers, Flight flight) {
		for(Subscriber sub:dailySubscribers) {
			System.out.println("Hi "+sub.getMailId()+ " update on flight from " + sub.getSource() 
			+ " to " + sub.getDestination() + "--> " + flight.getFlightNumber() + " " + flight.getDipatureDate()+ " " + flight.getPrice());
		}
	}

	private Flight getFlightInfo() {
		return null;
	}
}
