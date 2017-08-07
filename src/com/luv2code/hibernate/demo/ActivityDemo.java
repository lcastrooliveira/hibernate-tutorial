package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class ActivityDemo {

	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		try {			

			//create
			
			session.beginTransaction();
			
			Employee employee = new Employee("fulano", "ciclano", "microsoft");
			Employee employeeb = new Employee("chico", "beltrano", "google");

			session.save(employee);
			session.save(employeeb);

			Employee retrieve = session.get(Employee.class, employee.getId());
			System.out.println("Retrieved employee: "+retrieve);

			//retrieve all employees who work at microsoft
			List<Employee> employees = session.createQuery("from Employee where company='microsoft'").getResultList();
			for(Employee temp : employees)
				System.out.println(temp);

			//Delete first employee
			session.delete(employee);
			
			session.getTransaction().commit();

		} finally {
			sessionFactory.close();
		}
	}

}
