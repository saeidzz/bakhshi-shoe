package com.bakhshi.Main.data_access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Absent;
import com.bakhshi.Main.Model.Contact;
import com.bakhshi.Main.Model.Employee;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.Off;

public class OffDao {
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(Off e) {
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
		Off e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}
	public static Off findByID(long id) {
		Session session = getSessionFactory().openSession();
		Off e = (Off) session.get(Off.class, id);
		session.close();
		return e;
	}
	
	public static List<Off> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM Off where DATE_ID=?");
		query.setLong(0, date.getId());
		List<Off> absents = query.list();
		session.close();
		return absents;

	}
	
	public static List<Off> findByEmployeeIdId(Employee emploee) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
	    Query  query=session.createQuery("FROM Off where EMPLOYEE_ID=?");
		query.setLong(0,emploee.getId());
		List<Off> absents = query.list();
		session.close();
		return absents;

	}

}
