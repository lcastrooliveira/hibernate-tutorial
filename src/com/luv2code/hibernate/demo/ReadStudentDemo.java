package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// create session factory
		final SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//Create student object
			Student student = new Student("Daff", "Ducky", "daffy@luv2code.com");
			//Begin transaction
			session.beginTransaction();
			// use the session object to save Java object
			session.save(student);
			// commit transaction
			session.getTransaction().commit();
			
			//My new code
			//find out the student's id: primary key
			System.out.println("Saved student Generated ID "+student.getId());
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id
			System.out.println("Getting student with id: "+student.getId());
			
			// If not found returns null
			Student retrievedStudent = session.get(Student.class, student.getId());
			System.out.println("Get complete: "+retrievedStudent);
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		} finally {
			factory.close();
		}
	}

}
