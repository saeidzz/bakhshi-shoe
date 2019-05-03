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
import com.bakhshi.Main.Model.productions.Production;
import com.bakhshi.Main.Model.storeHouse.Property;

public class ProPertyDao {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(Property e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}
	public static List<Property> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Property> users = session.createQuery("FROM Property").list();
		session.close();
		return users;

	}
	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Property e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static Property findByID(long id) {
		Session session = getSessionFactory().openSession();
		Property e = (Property) session.get(Property.class, id);
		session.close();
		return e;
	}

	public static List<Property> findByName(String name) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("FROM Property where title=?");
		query.setString(0, name);
		List<Property> capsuls = query.list();
		session.close();
		return capsuls;

	}

	public static List<Property> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Property> users = session.createQuery("FROM Property")
				.list();
		List<Property> filtredList = new ArrayList<Property>();
		for (Property e : users) {
			if ( e.toString().contains(phrase)) {
				
				filtredList.add(e);
			}
		}

		return filtredList;
	}
}
