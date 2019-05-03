package com.bakhshi.Main.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.bakhshi.Main.Model.Customer;
import com.bakhshi.Main.Model.Employee;
import com.bakhshi.Main.Model.MyDate;
import com.bakhshi.Main.Model.materials.capsul.Capsul;

public class EmployeeDao {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public static long create(Employee e) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		session.saveOrUpdate(e);
		session.getTransaction().commit();
		session.close();
		return e.getId();

	}

	public static List<Employee> read() {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Employee> users = session.createQuery("FROM Employee").list();
		session.close();
		return users;

	}

	public static void delete(long id) {
		Session session = getSessionFactory().openSession();
		session.beginTransaction();
		Employee e = findByID(id);
		session.delete(e);
		session.getTransaction().commit();
		session.close();

	}

	public static Employee findByID(long id) {
		Session session = getSessionFactory().openSession();
		Employee e = (Employee) session.get(Employee.class, id);
		session.close();
		return e;
	}
	
	public static Employee getEmplyeByName(String name) {
		Session session = getSessionFactory().openSession();
		Query query=session.createQuery("FROM Employee where name=?");
		query.setString(0,name);
		Employee employee = (Employee)query.uniqueResult();
		session.close();
		return employee;
	}
	
	public static long ExistEmployee(Employee employee) {
		Session session = getSessionFactory().openSession();
		Query query=session.createQuery("FROM Employee where melliCode=? "); 
		query.setString(0, employee.getMelliCode());
		Employee employee2= (Employee)query.uniqueResult();
		session.close();
         if(employee2!=null){
        	 return employee2.getId();
         }else{
        	 return -1;
         }
	}

	public static List<Employee> serchphrase(String phrase, MyDate date,boolean dateIsAffective) {
		Session session = getSessionFactory().openSession();
		@SuppressWarnings("unchecked")
		List<Employee> users = session.createQuery("FROM Employee")
				.list();
		List<Employee> filtredList = new ArrayList<Employee>();
		for (Employee e : users) {
			if (e.toString().contains(phrase)) {
				
				filtredList.add(e);
			}
		}

		return filtredList;
	}
}