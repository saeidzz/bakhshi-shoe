package com.bakhshi.Main.data_access.factors;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Customer;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.factor.EVASaleFactor;
import com.bakhshi.Main.Model.factor.PVCSaleFactor;
import com.bakhshi.Main.data_access.MyDateDao;

public class PvcFactorDao {
	 public static SessionFactory getSessionFactory() {
	        Configuration configuration = new Configuration().configure();
	        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
	                .applySettings(configuration.getProperties());
	        SessionFactory sessionFactory = configuration
	                .buildSessionFactory(builder.build());
	        return sessionFactory;
	    }

	        public static long create(PVCSaleFactor e) {
	        Session session = getSessionFactory().openSession();
	        session.beginTransaction();
	        session.saveOrUpdate(e);
	        session.getTransaction().commit();
	        session.flush();
	        return e.getId();

	    }
	    
		public static List<PVCSaleFactor> read() {
			MyDate date=new MyDate();
			long dateId=MyDateDao.ExistDate(date);
			date.setId(dateId);
			
			
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			Query query=session.createQuery("FROM PVCSaleFactor where DATE_ID=?");
			query.setLong(0, date.getId());
			List<PVCSaleFactor> users = query.list();
			session.flush();
			return users;

		}
		
		public static List<PVCSaleFactor> read(MyDate date) {	
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			Query query=session.createQuery("FROM PVCSaleFactor where DATE_ID=?");
			query.setLong(0, date.getId());
			List<PVCSaleFactor> users = query.list();
			session.flush();
			return users;

		}
		
		public static List<PVCSaleFactor> getByCustomerName(Customer customer) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			Query query=session.createQuery("FROM PVCSaleFactor where CUSTOMER_ID=?");
			List<PVCSaleFactor> users = query.list();
			session.flush();
			return users;

		}
		
		public static List<PVCSaleFactor> getByLateDate(MyDate date) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			Query query=session.createQuery("FROM PVCSaleFactor where DATE_ID=?");
			query.setLong(0, date.getId());
			List<PVCSaleFactor> users = query.list();
			session.flush();
			return users;

		}


		public static void delete(long id) {
			Session session = getSessionFactory().openSession();
			session.beginTransaction();
			PVCSaleFactor e = findByID(id);
			session.delete(e);
			session.getTransaction().commit();
			session.flush();

		}

		public static PVCSaleFactor findByID(long id) {
			Session session = getSessionFactory().openSession();
			PVCSaleFactor e = (PVCSaleFactor) session.get(PVCSaleFactor.class, id);
			session.flush();
			return e;
		}
		
		public static List<PVCSaleFactor> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<PVCSaleFactor> users = session.createQuery("FROM PVCSaleFactor")
					.list();
			List<PVCSaleFactor> filtredList = new ArrayList<PVCSaleFactor>();
			for (PVCSaleFactor pvcFactor : users) {
				if ((dateIsAffective && pvcFactor.getDate().getId() == date.getId() && pvcFactor
						.toString().contains(phrase))
						|| (!dateIsAffective && pvcFactor.toString().contains(
								phrase))) {
					filtredList.add(pvcFactor);
				}
			}

			return filtredList;
		}
		
		public static List<PVCSaleFactor> getByCustomerNameAndMonthDate(
				Customer customer, MyDate date) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<PVCSaleFactor> users = session.createQuery("FROM PVCSaleFactor").list();
			List<PVCSaleFactor> filtredList= new ArrayList<PVCSaleFactor>();
			for (PVCSaleFactor pvcSaleFactor : users) {
				if (pvcSaleFactor.getDate().getMonth()==date.getMonth()
						&&pvcSaleFactor.getDate().getYear()==date.getYear()
						&& pvcSaleFactor.getCustomer().getId()==customer.getId()) {
					filtredList.add(pvcSaleFactor);
				}
			}
			
			session.flush();
			return filtredList;
		}
		
		public static List<PVCSaleFactor> getByCustomerNameAndYearDate(
				Customer customer, MyDate date) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<PVCSaleFactor> users = session.createQuery("FROM PVCSaleFactor").list();
			List<PVCSaleFactor> filtredList= new ArrayList<PVCSaleFactor>();
			for (PVCSaleFactor pvcSaleFactor : users) {
				if (pvcSaleFactor.getDate().getYear()==date.getYear() && pvcSaleFactor.getCustomer().getId()==customer.getId()) {
					filtredList.add(pvcSaleFactor);
				}
			}
			session.flush();
			return filtredList;
		}

		

		public static List<PVCSaleFactor>  getByCustomerNameAndDayDate(Customer customer, MyDate date) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<PVCSaleFactor> users = session.createQuery("FROM PVCSaleFactor").list();
			List<PVCSaleFactor> filtredList= new ArrayList<PVCSaleFactor>();
			for (PVCSaleFactor pvcSaleFactor : users) {
				if (	pvcSaleFactor.getDate().getDay()==date.getDay()
						&&pvcSaleFactor.getDate().getMonth()==date.getMonth()
						&&pvcSaleFactor.getDate().getYear()==date.getYear()
						&& pvcSaleFactor.getCustomer().getId()==customer.getId()) {
					filtredList.add(pvcSaleFactor);
				}
			}
			
			session.flush();
			return filtredList;
		}
		
		public static List<PVCSaleFactor> getByCustomerNameAndMonthDate( MyDate date) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<PVCSaleFactor> users = session.createQuery("FROM PVCSaleFactor").list();
			List<PVCSaleFactor> filtredList= new ArrayList<PVCSaleFactor>();
			for (PVCSaleFactor pvcSaleFactor : users) {
				if (pvcSaleFactor.getDate().getMonth()==date.getMonth()
						&&pvcSaleFactor.getDate().getYear()==date.getYear()) {
					filtredList.add(pvcSaleFactor);
				}
			}
			
			session.flush();
			return filtredList;
		}
		
		public static List<PVCSaleFactor> getByCustomerNameAndYearDate(
			 MyDate date) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<PVCSaleFactor> users = session.createQuery("FROM PVCSaleFactor").list();
			List<PVCSaleFactor> filtredList= new ArrayList<PVCSaleFactor>();
			for (PVCSaleFactor pvcSaleFactor : users) {
				if (pvcSaleFactor.getDate().getYear()==date.getYear()) {
					filtredList.add(pvcSaleFactor);
				}
			}
			session.flush();
			return filtredList;
		}

		

		public static List<PVCSaleFactor>  getByCustomerNameAndDayDate(MyDate date) {
			Session session = getSessionFactory().openSession();
			@SuppressWarnings("unchecked")
			List<PVCSaleFactor> users = session.createQuery("FROM PVCSaleFactor").list();
			List<PVCSaleFactor> filtredList= new ArrayList<PVCSaleFactor>();
			for (PVCSaleFactor pvcSaleFactor : users) {
				if (	pvcSaleFactor.getDate().getDay()==date.getDay()
						&&pvcSaleFactor.getDate().getMonth()==date.getMonth()
						&&pvcSaleFactor.getDate().getYear()==date.getYear()) {
					filtredList.add(pvcSaleFactor);
				}
			}
			
			session.flush();
			return filtredList;
		}
}
