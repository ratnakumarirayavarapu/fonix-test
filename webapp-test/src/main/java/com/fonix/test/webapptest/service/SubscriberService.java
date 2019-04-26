package com.fonix.test.webapptest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fonix.test.webapptest.dao.FlightJpaRepository;
import com.fonix.test.webapptest.dao.SubscriberJpaRepository;
import com.fonix.test.webapptest.entity.Flight;
import com.fonix.test.webapptest.entity.Subscriber;

@Controller
public class SubscriberService {
	
	@Autowired
	SubscriberJpaRepository repo;
	
	@Autowired
	Subscriber subscriber;
	
	@Autowired
	FlightJpaRepository flightRepo;
	
	@RequestMapping("home")
	public String showLoginPage() {
		System.out.println("hi");
		return "home.jsp";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String handleUserLogin(ModelMap model, @RequestParam String source, @RequestParam String destination,
			@RequestParam String mail, @RequestParam String subscription) {
		model.put("source", source);
		model.put("destination", destination);
		model.put("email", mail);
		model.put("subscription", subscription);
		subscriber.setMailId(mail);
		subscriber.setSource(source);
		subscriber.setDestination(destination);
		subscriber.setSubscription(subscription);
		
		System.out.println("successfully subscribed. You will get flight details from " + source +" to " + destination + " on "+ mail);
		repo.addSubscriber(subscriber);
		
		if(subscription.equalsIgnoreCase("weekly")) {
			List<Flight> flights = flightRepo.getWeeklyUpdate();
			System.out.println("Hi "+ subscriber.getMailId() +"Your first weekly update ");
			for(Flight flight:flights) {
				System.out.println(flight.getFlightNumber() + " " +flight.getDipatureDate() + flight.getPrice());
			}
		} else if(subscription.equalsIgnoreCase("monthly")) {
			List<Flight> flights = flightRepo.getMonthlyUpdate();
			System.out.println("Hi "+ subscriber.getMailId() +"Your first monthly update ");
			for(Flight flight:flights) {
				System.out.println(flight.getFlightNumber() + " " +flight.getDipatureDate() + flight.getPrice());
			}
		} else if(subscription.equalsIgnoreCase("uncaped")) {
			Flight flight = flightRepo.getBestFlight(source, destination);
			System.out.println("Hi "+ subscriber.getMailId() +"current best flight from " + source + " to " + destination +"as per our records");
			System.out.println(flight.getFlightNumber() + " " + flight.getDipatureDate() + " " + flight.getPrice());
		}
		
		return "welcome.jsp";
		
		//You will get flight details from ${source} to ${destination} ${subscription} on ${email}.
	}
	
}
