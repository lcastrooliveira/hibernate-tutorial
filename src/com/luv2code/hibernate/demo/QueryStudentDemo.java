package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;
import com.luv2code.hibernate.demo.entity.Student;


public class QueryStudentDemo {

	public static void main(String[] args) {
		// create session factory
		final SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create a session
		final Session session = factory.getCurrentSession();
		
		try {

			//Begin transaction
			session.beginTransaction();

			//query the students
			List<Student> theStudents = session.createQuery("from Student").getResultList();

			//display the students
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.lastName = 'Doe'").getResultList();
			
			//display the students
			System.out.println("Students who have the last name of Doe");
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where"+" s.lastName='Doe' OR s.firstName='Daff'").getResultList();
			
			//display the students
			System.out.println("Students who have the last name of Doe OR firstName='Daffy'");
			displayStudents(theStudents);
			
			
			theStudents = session.createQuery("from Student s where s.email like '%luv2code.com'").getResultList();
			
			//display the students
			System.out.println("Students whose email ends with luv2code.com");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents)
			System.out.println(tempStudent);
	}

}
