package com.bakhshi.Main.data_access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.materials.frame.Frame;
import com.bakhshi.Main.Model.materials.frame.FrameTransAction;

public class FrameTransactionsDao {

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
		FrameTransAction e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static List<FrameTransAction> read(String inOrOut,Frame frame) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("FROM FrameTransAction where inputOrOutput=? and frame_id=?");
		query.setString(0, inOrOut);
		query.setLong(1, frame.getId());
		List<FrameTransAction> users=query.list();
		session.close();
		return users;

	}
	public static long create(FrameTransAction  e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static FrameTransAction findByID(long id) {
		Session session = getSessionFactory().openSession();
		FrameTransAction e = (FrameTransAction) session.get(FrameTransAction.class, id);
		session.close();
		return e;
	}
	
	public static List<FrameTransAction> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM CapsulTransactions where DATE_ID=? ");
		query.setLong(0, date.getId());
		List<FrameTransAction> capsuls = query.list();
		session.close();
		return capsuls;

	}
	
}
