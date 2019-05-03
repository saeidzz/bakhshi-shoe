package com.bakhshi.Main.data_access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.materials.capsul.Capsul;
import com.bakhshi.Main.Model.materials.capsul.CapsulTransactions;

public class CapsulTransactionDao {

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
		CapsulTransactions e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static List<CapsulTransactions> read(String inOrOut,Capsul capsul) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("FROM CapsulTransactions where inputOrOutput=? and capsul_id=?");
		query.setString(0, inOrOut);
		query.setLong(1, capsul.getId());
		List<CapsulTransactions> users=query.list();
		session.close();
		return users;

	}
	public static long create(CapsulTransactions  e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static CapsulTransactions findByID(long id) {
		Session session = getSessionFactory().openSession();
		CapsulTransactions e = (CapsulTransactions) session.get(CapsulTransactions.class, id);
		session.close();
		return e;
	}
	
	public static List<CapsulTransactions> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM CapsulTransactions where DATE_ID=? ");
		query.setLong(0, date.getId());
		List<CapsulTransactions> capsuls = query.list();
		session.close();
		return capsuls;

	}
	

}
