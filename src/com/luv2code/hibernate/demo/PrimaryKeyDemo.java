package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// create session factory
		final SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		//create a session
		final Session session = factory.getCurrentSession();

		try {
			//Create 3 student objects
			Student student1 = new Student("Lucas", "Oliveira", "lcastro.oliveira@gmail.com");
			Student student2 = new Student("Fernanda", "Santos", "fer.santos@gmail.com");
			Student student3 = new Student("John", "Doe", "john.doe@gmail.com");
			//Begin transaction
			session.beginTransaction();
			// use the session object to save Java object
			session.save(student1);
			session.save(student2);
			session.save(student3);
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
