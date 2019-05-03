package com.bakhshi.Main.data_access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Employee;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.materials.frame.Frame;

public class MyDateDao {
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(MyDate e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static MyDate findByID(long id) {
		Session session = getSessionFactory().openSession();
		MyDate e = (MyDate) session.get(MyDate.class, id);
		session.close();
		return e;
	}
	public static List<MyDate> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<MyDate> users = session.createQuery("FROM MyDate").list();
		session.close();
		return users;

	}
	public static long ExistDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		Query query=session.createQuery("FROM MyDate where year=? and month=? and day=?"); 
		query.setLong(0,date.getYear());
		query.setLong(1,date.getMonth());
		query.setLong(2,date.getDay());
		MyDate date2= (MyDate)query.uniqueResult();
		session.close();
         if(date2!=null){
        	 return date2.getId();
         }else{
        	 return -1;
         }
	}
}
