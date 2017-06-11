package com.durable.db;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.collect.specific.bean.HouseMsg;

public class Save {
	
	public void execute(ArrayList<HouseMsg> list){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Iterator<HouseMsg> iterator = list.iterator();
		System.err.println("总共   " + list.size() + "条记录");
		while(iterator.hasNext()){
			HouseMsg next = iterator.next();
			entityManager.persist(next);
		}
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
