package com.bakhshi.Main.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Contact;
import com.bakhshi.Main.Model.Employee;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.materials.frame.Frame;

public class FrameDao {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(Frame  e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}
	public static List<Frame> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Frame> users = session.createQuery("FROM Frame").list();
		session.close();
		return users;

	}

	public static Frame findByID(long id) {
		Session session = getSessionFactory().openSession();
		Frame e = (Frame) session.get(Frame.class, id);
		session.close();
		return e;
	}
	
	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Frame e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}
	
	public static List<Frame> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM Frame where DATE_ID=? ");
		query.setLong(0, date.getId());
		List<Frame> capsuls = query.list();
		session.close();
		return capsuls;

	}

	public static List<Frame> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Frame> users = session.createQuery("FROM Frame")
				.list();
		List<Frame> filtredList = new ArrayList<Frame>();
		for (Frame e : users) {
			if (e.toString().contains(phrase)) {
				
				filtredList.add(e);
			}
		}

		return filtredList;
	}
	/*public static List<Frame> findByCode(String code) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Frame> capsuls = session.createQuery("FROM Frame where").list();
		session.close();
		return capsuls;

	}
	*/
}
