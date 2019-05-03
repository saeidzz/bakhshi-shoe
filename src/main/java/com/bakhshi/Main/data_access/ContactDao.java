package com.bakhshi.Main.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Contact;
import com.bakhshi.Main.Model.MyDate;


public class ContactDao {
	
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(Contact e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static List<Contact> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Contact> Contacts = session.createQuery("FROM Contact").list();
		session.close();
		return Contacts;

	}


	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Contact e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static Contact findByID(long id) {
		Session session = getSessionFactory().openSession();
		Contact e = (Contact) session.get(Contact.class, id);
		session.close();
		return e;
	}

	public static List<Contact> getContactByName(String name) {
		Session session = getSessionFactory().openSession();
		Query query=session.createQuery("FROM Contact where name=?"); 
		query.setString(0, name);
		List<Contact> Contacts = query.list();

		session.close();
		return Contacts;
	}
	public static long ExistContact(Contact contact) {
		Session session = getSessionFactory().openSession();
		Query query=session.createQuery("FROM Contact where name=? and address=? and phoneNumber=?"); 
		query.setString(0, contact.getName());
		query.setString(1, contact.getAddress());
		query.setString(2, contact.getPhoneNumber());
		Contact contact1 = (Contact)query.uniqueResult();
		session.close();
       
		return contact1!=null?contact1.getId():-1;
         
	}
	public static void export(String path) {
		Session session = getSessionFactory().openSession();
		Query query=session.createQuery("backup to "+path+".zip"); 
		query.executeUpdate();
		session.close();
	}
	public static List<Contact> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Contact> users = session.createQuery("FROM Contact")
				.list();
		List<Contact> filtredList = new ArrayList<Contact>();
		for (Contact contact : users) {
			if (( contact.toString().contains(phrase))) {
				filtredList.add(contact);
			}
		}

		return filtredList;
	}
}