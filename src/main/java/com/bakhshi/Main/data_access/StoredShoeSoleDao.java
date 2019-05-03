package com.bakhshi.Main.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.storeHouse.storedShoeSole.StoredShoeSole;;

public class StoredShoeSoleDao {
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(StoredShoeSole  e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static StoredShoeSole findByID(long id) {
		Session session = getSessionFactory().openSession();
		StoredShoeSole e = (StoredShoeSole) session.get(StoredShoeSole.class, id);
		session.close();
		return e;
	}
	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		StoredShoeSole e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}
	
	public static List<StoredShoeSole> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<StoredShoeSole> users = session.createQuery("FROM StoredShoeSole").list();
		session.close();
		return users;

	}
	public static List<StoredShoeSole> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM StoredShoeSole where DATE_ID ");
		query.setLong(0, date.getId());
		List<StoredShoeSole> capsuls = query.list();
		session.close();
		return capsuls;

	}
	
	
	public static List<StoredShoeSole> findBySize(String size) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query= session.createQuery("FROM StoredShoeSole where size=?");
		query.setString(0, size);
		List<StoredShoeSole> capsuls = query.list();
		session.close();
		return capsuls;

	}

	public static List<StoredShoeSole> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<StoredShoeSole> users = session.createQuery("FROM StoredShoeSole")
				.list();
		List<StoredShoeSole> filtredList = new ArrayList<StoredShoeSole>();
		for (StoredShoeSole e : users) {
			if (e.toString().contains(phrase)) {
				filtredList.add(e);
			}
		}

		return filtredList;
	}

}
