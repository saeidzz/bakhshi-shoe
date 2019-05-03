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
import com.bakhshi.Main.Model.materials.capsul.Capsul;

public class CustomerDao {
	
	public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration
                .buildSessionFactory(builder.build());
        return sessionFactory;
    }

        public static long create(Customer e) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(e);
        session.getTransaction().commit();
        session.close();
        return e.getId();

    }
    
	public static List<Customer> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Customer> customers = session.createQuery("FROM Customer").list();
		session.close();
		return customers;

	}


	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Customer e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static Customer findByID(long id) {
		Session session = getSessionFactory().openSession();
		Customer e = (Customer) session.get(Customer.class, id);
		session.close();
		return e;
	}

	public static Customer getCustomerByName(String name) {
		Session session = getSessionFactory().openSession();
		Query query=session.createQuery("FROM Customer where name=?");
		query.setString(0,name);
		Customer customer = (Customer)query.uniqueResult();
		session.close();
		return customer;
	}
	public static long ExistCustomer(Customer customer) {
		Session session = getSessionFactory().openSession();
		Query query=session.createQuery("FROM Customer where name=? and address=? and phoneNumber=?"); 
		query.setString(0, customer.getName());
		query.setString(1, customer.getAddress());
		query.setString(2, customer.getPhoneNumber());
		Customer customer1= (Customer)query.uniqueResult();
		session.close();
         if(customer1!=null){
        	 return customer1.getId();
         }else{
        	 return -1;
         }
	}

	public static List<Customer> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Customer> users = session.createQuery("FROM Customer")
				.list();
		List<Customer> filtredList = new ArrayList<Customer>();
		for (Customer e : users) {
			if ( e.toString().contains(phrase)) {
				
				filtredList.add(e);
			}
		}

		return filtredList;
	}


}
