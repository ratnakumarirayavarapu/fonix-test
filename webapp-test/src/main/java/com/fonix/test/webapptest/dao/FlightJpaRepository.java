package com.fonix.test.webapptest.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.fonix.test.webapptest.entity.Flight;

/*
 * Repository to perform CRUD operations on Flight database
 */
@Repository
@Transactional
public class FlightJpaRepository {

	@PersistenceContext
	EntityManager eManager;

	/*
	 * Method to retrieve weekly updates on Flight details user subscribed from origin to destination
	 */
	public List<Flight> getWeeklyUpdate(String origin, String destination) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getDefault());

		Calendar currentDate = Calendar.getInstance();
		currentDate.set(3, currentDate.get(3) + 1);
		String endDate = sdf.format(currentDate.getTime());
		Calendar cal = Calendar.getInstance();
		cal.set(3, cal.get(3) - 7);
		// cal.set(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay() -
		// 7);
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String startDate = sdf.format(cal.getTime());
		String SQL = "select f from Flight f where f.dipatureDate>= '" + timeStamp + "' and sysDate between '"
				+ startDate + "' and '" + endDate + "' and f.originPoint = '" + 
						 origin + "' and destinationPoint = '" + destination + "'";
		Query query = eManager.createQuery(SQL);
		List<Flight> list = query.getResultList();

		return list;
	}

	/*
	 * Method to retrieve monthly updates on Flight details user subscribed from origin to destination
	 */
	public List<Flight> getMonthlyUpdate(String origin, String destination) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getDefault());

		Calendar currentDate = Calendar.getInstance();
		currentDate.set(3, currentDate.get(3) + 1);
		String endDate = sdf.format(currentDate.getTime());
		Calendar cal = Calendar.getInstance();
		cal.set(2, cal.get(2) - 1);
		

		String startDate = sdf.format(cal.getTime());
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String SQL = "select f from Flight f where f.dipatureDate>= '" + timeStamp + "' and sysDate between '" + 
						startDate + "' and '" + endDate + "' and f.originPoint = '" + 
				 origin + "' and destinationPoint = '" + destination + "'";
		Query query = eManager.createQuery(SQL);
		List<Flight> list = query.getResultList();

		return list;
	}

	// add flight information to data table Flight when the crawler gets new update
	public void addFlightInfo(Flight flight) {
		eManager.merge(flight);
	}

	// best price on the flight from origin to destination for the available future flights
	public long getBestPrice(String origin, String destination) {
		// dipature date>= current date
		// condition for origin and destination
		// min price
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String SQL = "select min(f.price) from Flight f where f.dipatureDate>= '" + timeStamp
				+ "' and f.originPoint = '" + origin + "' and destinationPoint = '" + destination + "'";
		Query query = eManager.createQuery(SQL);
		List<Long> result = query.getResultList();
		if (result.get(0) != null) {
			return result.get(0);

		}
		return 0;
	}

	// best price on the flight from origin to destination for the available future flights
	public Flight getBestFlight(String origin, String destination) {
		// dipature date>= current date
		// condition for origin and destination
		// min price
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String SQL = "select * from Flight f where f.dipatureDate>= '" + timeStamp + "' and f.originPoint = '" + origin
				+ "' and destinationPoint = '" + destination + "' order by f.price";
		Query query = eManager.createQuery(SQL);
		List<Flight> result = query.getResultList();
		return result.get(0);
	}
}
