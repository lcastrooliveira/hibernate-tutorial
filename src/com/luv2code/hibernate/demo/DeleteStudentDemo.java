package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// create session factory
		final SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			
			int studentId = 1;
			
			System.out.println("Saved student. Generated id: "+studentId);
			
			session = factory.getCurrentSession();
			
			session.beginTransaction();
			
			System.out.println("Get student with ID "+studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Delete the student");
			//session.delete(myStudent);
			
			System.out.println("Alternate approach");
			session.createQuery("delete from Student where id='2'").executeUpdate();
			
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
