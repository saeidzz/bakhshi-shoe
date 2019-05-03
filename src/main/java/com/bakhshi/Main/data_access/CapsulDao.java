package com.bakhshi.Main.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Absent;
import com.bakhshi.Main.Model.Contact;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.factor.SaleFactor;
import com.bakhshi.Main.Model.materials.capsul.Capsul;
import com.bakhshi.Main.Model.storeHouse.Property;

public class CapsulDao {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Capsul e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static List<Capsul> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Capsul> users = session.createQuery("FROM Capsul").list();
		session.close();
		return users;

	}
	public static long create(Capsul  e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static Capsul findByID(long id) {
		Session session = getSessionFactory().openSession();
		Capsul e = (Capsul) session.get(Capsul.class, id);
		session.close();
		return e;
	}
	
	public static List<Capsul> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM Capsul where DATE_ID=? ");
		query.setLong(0, date.getId());
		List<Capsul> capsuls = query.list();
		session.close();
		return capsuls;

	}

	public static List<Capsul> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Capsul> users = session.createQuery("FROM Capsul")
				.list();
		List<Capsul> filtredList = new ArrayList<Capsul>();
		for (Capsul capsul : users) {
			if (capsul.toString().contains(phrase)) {
				filtredList.add(capsul);
			}
		}

		return filtredList;
	}
	/*public static List<Capsul> findByCode(String code) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Capsul> capsuls = session.createQuery("FROM Capsul where ").list();
		session.close();
		return capsuls;

	}*/
}
