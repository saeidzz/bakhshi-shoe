package com.bakhshi.Main.data_access;

import java.util.List;

import org.hibernate.Query;
//import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Absent;
import com.bakhshi.Main.Model.Employee;
import com.bakhshi.Main.Model.MyDate;

public class AbsentDao {
		public static SessionFactory getSessionFactory() {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties());
			SessionFactory sessionFactory = configuration
			.buildSessionFactory(builder.build());
			return sessionFactory;
		}

		public static long create(Absent e) {
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
			Absent e = findByID(id);
			session.delete(e);
			session.getTransaction().commit();
			session.close();

		}
		public static Absent findByID(long id) {
			Session session = getSessionFactory().openSession();
			Absent e = (Absent) session.get(Absent.class, id);
			session.close();
			return e;
		}
		
		public static List<Absent> findByDate(MyDate date) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			Query query=session.createQuery("FROM Absent where DATE_ID ");
			query.setLong(0, date.getId());
			List<Absent> absents = query.list();
			session.close();
			return absents;

		}
		
		public static List<Absent> findByEmployeeIdId(Employee emploee) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
		    Query  query=session.createQuery("FROM Absent where Employee_Id=?");
			query.setLong(0,emploee.getId());
			List<Absent> absent=query.list();
			session.close();
			return absent;

		}
		
		
}
