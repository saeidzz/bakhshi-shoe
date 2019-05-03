package com.bakhshi.Main.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.materials.material.Materials;

public class MaterialDao {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
		.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(Materials  e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static Materials findByID(long id) {
		Session session = getSessionFactory().openSession();
		Materials e = (Materials) session.get(Materials.class, id);
		session.close();
		return e;
	}
	public static List<Materials> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Materials> users = session.createQuery("FROM Materials").list();
		session.close();
		return users;

	}
	public static List<Materials> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("FROM Materials where ");
		query.setLong(0, date.getId());
		List<Materials> capsuls = query.list();
		session.close();
		return capsuls;

	}

	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Materials e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static List<Materials> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Materials> users = session.createQuery("FROM Materials")
				.list();
		List<Materials> filtredList = new ArrayList<Materials>();
		for (Materials e : users) {
			if (e.toString().contains(phrase)) {
				
				filtredList.add(e);
			}
		}

		return filtredList;
	}
	/*public static List<MaterialsList> findByCode(String code) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<MaterialsList> capsuls = session.createQuery("FROM MaterialsList where").list();
		session.close();
		return capsuls;

	}
	*/

}
