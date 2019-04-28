package com.fonix.test.webapptest.service.job;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
	
	/*
	 * Assuming that my crawler is getting flight details from resource/flightinfo.txt, rather than pulling from web using web crawler 
	 */
	public void  runCrawler() {
		// get flight info
		List<Flight> flightList = getFlightInfo();
		for(Flight flight:flightList) {
			// save in data base
			flightRepo.addFlightInfo(flight);
			
			// send to daily subscriber get by origin and destination daily subscribers
			List<Subscriber> dailySubscribers = subRepo.getSubscribersByFrequency("daily");
			sendNotificationToSubscribers(dailySubscribers,flight);
			
			
			// check for best deal, if found send to 
			long bestPrice = flightRepo.getBestPrice(flight.getOriginPoint(),flight.getDestinationPoint());
			// best price is 0 when there is no records or when all the flights from origin to destination departure date in past
			if(bestPrice==0||flight.getPrice()<bestPrice) {
				List<Subscriber> uncappedSubscribers = subRepo.getSubscribersByFrequency("Uncapped");
				sendNotificationToSubscribers(uncappedSubscribers,flight);
			}
		}
	}

	private void sendNotificationToSubscribers(List<Subscriber> dailySubscribers, Flight flight) {
		for(Subscriber sub:dailySubscribers) {
			System.out.println("Hi "+sub.getMailId()+ " update on flight from " + sub.getSource() 
			+ " to " + sub.getDestination() + "--> " + flight.getFlightNumber() + " " + flight.getDipatureDate()+ " " + flight.getPrice());
		}
	}

	private List<Flight> getFlightInfo() {
		String fileName = "flightinfo.txt";
		List<Flight> flights = new ArrayList<>();
		
		ClassLoader classLoader = new CrawlerService().getClass().getClassLoader();
		
		// read file into stream, try-with-resources
		try  {
			//File file = new File(fileName);
			
			File file = new File(classLoader.getResource(fileName).getFile());
			System.out.println("file found " + file.exists());
			//String fileName = "flightinfo.txt";
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String flight[] = sc.nextLine().split(",");
				int flightNumber = Integer.parseInt(flight[0]);
				String origin = flight[1];
				String destination = flight[2];
				
				Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(flight[3]);
				long price = Long.parseLong(flight[4]);
				Timestamp timeStamp = new Timestamp(date.getTime());
				flights.add(new Flight(flightNumber,origin,destination,timeStamp,price,new Timestamp(new Date().getTime())));
			}
		} catch (ParseException | FileNotFoundException e) {
			e.printStackTrace();
		}

		return flights;
	}
}
