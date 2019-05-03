package com.bakhshi.Main.data_access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.storeHouse.storedShoeSole.StoredShoeSole;
import com.bakhshi.Main.Model.storeHouse.storedShoeSole.StoredShoeSoleTransaction;

public class StoredShoeSoleTransactionDao {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(StoredShoeSoleTransaction e) {
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
		StoredShoeSoleTransaction e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}
	public static StoredShoeSoleTransaction findByID(long id) {
		Session session = getSessionFactory().openSession();
		StoredShoeSoleTransaction e = (StoredShoeSoleTransaction) session.get(StoredShoeSoleTransaction.class, id);
		session.close();
		return e;
	}
	
	public static List<StoredShoeSoleTransaction> findByDate(StoredShoeSoleTransaction date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM StoredShoeSoleTransaction where DATE_ID ");
		query.setLong(0, date.getId());
		List<StoredShoeSoleTransaction> absents = query.list();
		session.close();
		return absents;

	}
	
	public static List<StoredShoeSoleTransaction> findByShoeSole(StoredShoeSole shoeSole) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
	    Query  query=session.createQuery("FROM StoredShoeSoleTransaction where storedShoeSole_id=?");
		query.setLong(0,shoeSole.getId());
		List<StoredShoeSoleTransaction> absent=query.list();
		session.close();
		return absent;

	}

}
