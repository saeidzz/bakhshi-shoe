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
import com.bakhshi.Main.data_access.MaterialDao;
import com.bakhshi.Main.data_access.MyDateDao;

public class EvaFactorDao {
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(EVASaleFactor e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static List<EVASaleFactor> read() {
		MyDate date=new MyDate();
		long dateId=MyDateDao.ExistDate(date);
		date.setId(dateId);
		
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("FROM EVASaleFactor where DATE_ID=?");
		query.setLong(0, date.getId());
		List<EVASaleFactor> users = query.list();
		session.close();
		return users;

	}

	public static List<EVASaleFactor> read(MyDate date) {	
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("FROM EVASaleFactor where DATE_ID=?");
		query.setLong(0, date.getId());
		List<EVASaleFactor> users = query.list();
		session.close();
		return users;

	}
	public static List<EVASaleFactor> getByCustomerName(Customer customer) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session
				.createQuery("FROM EVASaleFactor where CUSTOMER_ID=?");
		query.setLong(0, customer.getId());
		List<EVASaleFactor> users = query.list();
		session.close();
		return users;

	}

	public static List<EVASaleFactor> getByLateDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session.createQuery("FROM EVASaleFactor where DATE_ID=?");
		query.setLong(0, date.getId());
		List<EVASaleFactor> users = query.list();
		session.close();
		return users;

	}

	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		EVASaleFactor e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static EVASaleFactor findByID(long id) {
		Session session = getSessionFactory().openSession();
		EVASaleFactor e = (EVASaleFactor) session.get(EVASaleFactor.class, id);
		session.close();
		return e;
	}

	public static List<EVASaleFactor> getByCustomerNameAndDayDate(
			Customer customer, MyDate date) {

		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<EVASaleFactor> users = session.createQuery("FROM EVASaleFactor")
				.list();
		List<EVASaleFactor> filtredList = new ArrayList<EVASaleFactor>();
		for (EVASaleFactor evaSaleFactor : users) {
			if (evaSaleFactor.getDate().getDay() == date.getDay()&&
					evaSaleFactor.getDate().getMonth() == date.getMonth()&&
					evaSaleFactor.getDate().getYear() == date.getYear()
					&& evaSaleFactor.getCustomer().getId() == customer.getId()) {
				filtredList.add(evaSaleFactor);
			}
		}

		session.close();
		return filtredList;
	}
	
	public static List<EVASaleFactor> getByCustomerNameAndMonthDate(
			Customer customer, MyDate date) {

		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<EVASaleFactor> users = session.createQuery("FROM EVASaleFactor")
				.list();
		List<EVASaleFactor> filtredList = new ArrayList<EVASaleFactor>();
		for (EVASaleFactor evaSaleFactor : users) {
			if (evaSaleFactor.getDate().getMonth() == date.getMonth()&&
					evaSaleFactor.getDate().getYear() == date.getYear()
					&& evaSaleFactor.getCustomer().getId() == customer.getId()) {
				filtredList.add(evaSaleFactor);
			}
		}

		session.close();
		return filtredList;
	}

	public static List<EVASaleFactor> getByCustomerNameAndYearDate(
			Customer customer, MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<EVASaleFactor> users = session.createQuery("FROM EVASaleFactor")
				.list();
		List<EVASaleFactor> filtredList = new ArrayList<EVASaleFactor>();
		for (EVASaleFactor evaSaleFactor : users) {
			if (evaSaleFactor.getDate().getYear() == date.getYear()
					&& evaSaleFactor.getCustomer().getId() == customer.getId()) {
				filtredList.add(evaSaleFactor);
			}
		}
		session.close();
		return filtredList;
	}
	
	public static List<EVASaleFactor> getByCustomerNameAndDayDate( MyDate date) {

		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<EVASaleFactor> users = session.createQuery("FROM EVASaleFactor")
				.list();
		List<EVASaleFactor> filtredList = new ArrayList<EVASaleFactor>();
		for (EVASaleFactor evaSaleFactor : users) {
			if (evaSaleFactor.getDate().getDay() == date.getDay()&&
					evaSaleFactor.getDate().getMonth() == date.getMonth()&&
					evaSaleFactor.getDate().getYear() == date.getYear()) {
				filtredList.add(evaSaleFactor);
			}
		}

		session.close();
		return filtredList;
	}
	
	public static List<EVASaleFactor> getByCustomerNameAndMonthDate( MyDate date) {

		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<EVASaleFactor> users = session.createQuery("FROM EVASaleFactor")
				.list();
		List<EVASaleFactor> filtredList = new ArrayList<EVASaleFactor>();
		for (EVASaleFactor evaSaleFactor : users) {
			if (evaSaleFactor.getDate().getMonth() == date.getMonth()&&
					evaSaleFactor.getDate().getYear() == date.getYear()) {
				filtredList.add(evaSaleFactor);
			}
		}

		session.close();
		return filtredList;
	}

	public static List<EVASaleFactor> getByCustomerNameAndYearDate( MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<EVASaleFactor> users = session.createQuery("FROM EVASaleFactor")
				.list();
		List<EVASaleFactor> filtredList = new ArrayList<EVASaleFactor>();
		for (EVASaleFactor evaSaleFactor : users) {
			if (evaSaleFactor.getDate().getYear() == date.getYear()) {
				filtredList.add(evaSaleFactor);
			}
		}
		session.close();
		return filtredList;
	}

	public static List<EVASaleFactor> serchphrase(String phrase, MyDate date,
			boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<EVASaleFactor> users = session.createQuery("FROM EVASaleFactor")
				.list();
		List<EVASaleFactor> filtredList = new ArrayList<EVASaleFactor>();
		for (EVASaleFactor evaSaleFactor : users) {
			if ((dateIsAffective && evaSaleFactor.getDate().getId() == date.getId() && evaSaleFactor
					.toString().contains(phrase))
					|| (!dateIsAffective && evaSaleFactor.toString().contains(
							phrase))) {
				filtredList.add(evaSaleFactor);
			}
		}

		return filtredList;
	}
}
