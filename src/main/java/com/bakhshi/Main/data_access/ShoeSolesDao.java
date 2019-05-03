package com.bakhshi.Main.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Contact;
import com.bakhshi.Main.Model.Customer;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.materials.shoeSoles.ShoeSoles;
import com.bakhshi.Main.Model.storeHouse.Property;

public class ShoeSolesDao {
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(ShoeSoles  e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static ShoeSoles findByID(long id) {
		Session session = getSessionFactory().openSession();
		ShoeSoles e = (ShoeSoles) session.get(ShoeSoles.class, id);
		session.close();
		return e;
	}
	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		ShoeSoles e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}
	
	public static List<ShoeSoles> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<ShoeSoles> users = session.createQuery("FROM ShoeSoles").list();
		session.close();
		return users;

	}
	public static List<ShoeSoles> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM ShoeSoles where DATE_ID ");
		query.setLong(0, date.getId());
		List<ShoeSoles> capsuls = query.list();
		session.close();
		return capsuls;

	}
	
	
	public static boolean ExistShoeSolesFactorNumber(String factorNumber){
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM ShoeSoles where factorNumber=?");
		query.setString(0, factorNumber);
		ShoeSoles capsuls = (ShoeSoles)query.uniqueResult();
		session.close();
		boolean res=capsuls!=null;
		return res;
	}
	public static List<ShoeSoles> findBySize(String size) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query= session.createQuery("FROM ShoeSoles where size=?");
		query.setString(0, size);
		List<ShoeSoles> capsuls = query.list();
		session.close();
		return capsuls;

	}

	public static List<ShoeSoles> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<ShoeSoles> users = session.createQuery("FROM ShoeSoles")
				.list();
		List<ShoeSoles> filtredList = new ArrayList<ShoeSoles>();
		for (ShoeSoles e : users) {
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
