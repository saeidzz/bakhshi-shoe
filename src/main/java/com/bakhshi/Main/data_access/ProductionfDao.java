package com.bakhshi.Main.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.productions.Production;

public class ProductionfDao {
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(Production e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static Production findByID(long id) {
		Session session = getSessionFactory().openSession();
		Production e = (Production) session.get(Production.class, id);
		session.close();
		return e;
	}

	public static List<Production> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM Production where DATE_ID=? ");
		query.setLong(0, date.getId());
		List<Production> capsuls = query.list();
		session.close();
		return capsuls;

	}
	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Production e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}
	public static List<Production> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Production> proList = session.createQuery("FROM Production").list();
		session.close();
		return proList;

	}

	public static List<Production> findByProductor(String name) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM Production where productor=?");
		query.setString(0, name);
		List<Production> capsuls = query.list();
		session.close();
		return capsuls;

	}

	public static List<Production> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Production> users = session.createQuery("FROM Production")
				.list();
		List<Production> filtredList = new ArrayList<Production>();
		for (Production e : users) {
			if ((dateIsAffective && e.getDate().getId() == date.getId() && e
					.toString().contains(phrase))
					|| (!dateIsAffective && e.toString().contains(
							phrase))) { 
				
				filtredList.add(e);
			}
		}

		return filtredList;
	}
}
