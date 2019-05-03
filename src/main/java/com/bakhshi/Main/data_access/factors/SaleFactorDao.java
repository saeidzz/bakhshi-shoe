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
import com.bakhshi.Main.Model.factor.PVCSaleFactor;
import com.bakhshi.Main.Model.factor.SaleFactor;

public class SaleFactorDao {
	public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }

        public static long create(SaleFactor e) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();
        session.close();
        return e.getId();

    }
    
	public static List<SaleFactor> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<SaleFactor> users = session.createQuery("FROM SaleFactor").list();
		session.close();
		return users;

	}
	
	public static List<SaleFactor> getByCustomerName(Customer customer) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<SaleFactor> users = session.createQuery("FROM SaleFactor").list();
		session.close();
		return users;

	}
	
	public static List<SaleFactor> getByLateDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM SaleFactor where DATE_ID=?");
		query.setLong(0, date.getId());
		List<SaleFactor> users = query.list();
		session.close();
		return users;

	}


	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		SaleFactor e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static SaleFactor findByID(long id) {
		Session session = getSessionFactory().openSession();
		SaleFactor e = (SaleFactor) session.get(SaleFactor.class, id);
		session.close();
		return e;
	}
	
	public static List<SaleFactor> getByCustomerNameAndMonthDate(
			Customer customer, MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<SaleFactor> users = session.createQuery("FROM SaleFactor").list();
		List<SaleFactor> filtredList= new ArrayList<SaleFactor>();
		for (SaleFactor saleFactor : users) {
			if (saleFactor.getDate().getMonth()==date.getMonth()
					&&saleFactor.getDate().getYear()==date.getYear()
					&& saleFactor.getCustomer().getId()==customer.getId()) {
				filtredList.add(saleFactor);
			}
		}
		
		session.close();
		return filtredList;
	}
	
	public static List<SaleFactor> getByCustomerNameAndYearDate(
			Customer customer, MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<SaleFactor> users = session.createQuery("FROM SaleFactor").list();
		List<SaleFactor> filtredList= new ArrayList<SaleFactor>();
		for (SaleFactor saleFactor : users) {
			if (saleFactor.getDate().getYear()==date.getYear() && saleFactor.getCustomer().getId()==customer.getId()) {
				filtredList.add(saleFactor);
			}
		}
		session.close();
		return filtredList;
	}

	

	public static List<SaleFactor> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<SaleFactor> users = session.createQuery("FROM SaleFactor")
				.list();
		List<SaleFactor> filtredList = new ArrayList<SaleFactor>();
		for (SaleFactor factor : users) {
			if ((dateIsAffective && factor.getDate().getId() == date.getId() && factor
					.toString().contains(phrase))
					|| (!dateIsAffective && factor.toString().contains(
							phrase))) {
				filtredList.add(factor);
			}
		}

		return filtredList;
	}
	}
