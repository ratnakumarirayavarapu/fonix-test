package com.fonix.test.webapptest.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.fonix.test.webapptest.entity.Subscriber;


@Repository
@Transactional
public class SubscriberJpaRepository {
	
	@PersistenceContext
	EntityManager eManager;
	
	public List<Subscriber> getSubscribersByFrequency(String subscriptionType) {
	      //Scalar function
	      Query query = eManager.createQuery("Select s from Subscriber s where subscription = '" + subscriptionType + "'");
	      List<Subscriber> list = query.getResultList();

	      for(Subscriber e:list) {
	         System.out.println("subscriber "+e.getMailId());
	      }
		return list;
	}
	
	public void addSubscriber(Subscriber subscriber) {
		eManager.merge(subscriber);
	}
}