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

@Repository
@Transactional
public class FlightJpaRepository {

	@PersistenceContext
	EntityManager eManager;

	public List<Flight> getWeeklyUpdate() {
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
		String SQL = "select f from Flight f where f.dipatureDate>= '" + timeStamp + "' and sysDate between '" + startDate + "' and '" + endDate + "'";
		System.out.println(SQL);
		Query query = eManager.createQuery(SQL);
		List<Flight> list = query.getResultList();

		for (Flight e : list) {
			System.out.println("subscriber " + e.getFlightNumber());
		}
		return list;
	}

	public List<Flight> getMonthlyUpdate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getDefault());

		Calendar currentDate = Calendar.getInstance();
		currentDate.set(3, currentDate.get(3) + 1);
		String endDate = sdf.format(currentDate.getTime());
		Calendar cal = Calendar.getInstance();
		cal.set(2, cal.get(2) - 1);
		// cal.set(currentDate.getYear(), currentDate.getMonth(), currentDate.getDay() -
		// 7);

		String startDate = sdf.format(cal.getTime());
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String SQL = "select f from Flight f where f.dipatureDate>= '" + timeStamp + "' and sysDate between '" + startDate + "' and '" + endDate + "'";
		System.out.println(SQL);
		Query query = eManager.createQuery(SQL);
		List<Flight> list = query.getResultList();

		for (Flight e : list) {
			System.out.println("subscriber " + e.getFlightNumber());
		}
		return list;
	}

	public void addFlightInfo(Flight flight) {
		eManager.merge(flight);
	}

	public long getBestPrice(String origin, String destination) {
		// dipature date>= current date
		// condition for origin and destination
		// min price
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String SQL = "select min(f.price) from Flight f where f.dipatureDate>= '" + timeStamp + "' and f.originPoint = '"
				+ origin + "' and destinationPoint = '" + destination + "'";
		Query query = eManager.createQuery(SQL);
		List<Long> result = query.getResultList();
		System.out.println(result.get(0));
		return result.get(0);
	}
	
	public Flight getBestFlight(String origin, String destination) {
		// dipature date>= current date
		// condition for origin and destination
		// min price
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		String SQL = "select * from Flight f where f.dipatureDate>= '" + timeStamp + "' and f.originPoint = '"
				+ origin + "' and destinationPoint = '" + destination + "' order by f.price";
		Query query = eManager.createQuery(SQL);
		List<Flight> result = query.getResultList();
		System.out.println(result.get(0));
		return result.get(0);
	}
}
