package com.bakhshi.Main.data_access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.materials.material.Materials;
import com.bakhshi.Main.Model.materials.material.MatrialTransaction;

public class MaterialTransactionDao {
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
		MatrialTransaction e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static List<MatrialTransaction> read(String inOrOut, Materials material) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session
				.createQuery("FROM MatrialTransaction where inputOrOutput=? and material_id=?");
		query.setString(0, inOrOut);
		query.setLong(1, material.getId());
		List<MatrialTransaction> users = query.list();
		session.close();
		return users;

	}

	public static long create(MatrialTransaction e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static MatrialTransaction findByID(long id) {
		Session session = getSessionFactory().openSession();
		MatrialTransaction e = (MatrialTransaction) session.get(
				MatrialTransaction.class, id);
		session.close();
		return e;
	}

	public static List<MatrialTransaction> findByDate(MyDate date) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		Query query = session
				.createQuery("FROM MatrialTransaction where DATE_ID=? ");
		query.setLong(0, date.getId());
		List<MatrialTransaction> capsuls = query.list();
		session.close();
		return capsuls;

	}

}
