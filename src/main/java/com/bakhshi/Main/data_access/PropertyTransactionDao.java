package com.bakhshi.Main.data_access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.storeHouse.Property;
import com.bakhshi.Main.Model.storeHouse.PropertyTransaction;


public class PropertyTransactionDao {
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(PropertyTransaction e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}


	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		PropertyTransaction e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}
	public static PropertyTransaction findByID(long id) {
		Session session = getSessionFactory().openSession();
		PropertyTransaction e = (PropertyTransaction) session.get(PropertyTransaction.class, id);
		session.close();
		return e;
	}
	
	public static List<PropertyTransaction> findByDate(PropertyTransaction date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM PropertyTransaction where DATE_ID ");
		query.setLong(0, date.getId());
		List<PropertyTransaction> absents = query.list();
		session.close();
		return absents;

	}
	
	public static List<PropertyTransaction> findByProperty(Property property) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
	    Query  query=session.createQuery("FROM PropertyTransaction where property_id=?");
		query.setLong(0,property.getId());
		List<PropertyTransaction> absent=query.list();
		session.close();
		return absent;

	}
}
