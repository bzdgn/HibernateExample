package com.levo.example;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.levo.example.configuration.AppConfig;
import com.levo.example.configuration.HibernateUtil;
import com.levo.example.model.Person;

/**
 * Hello world!
 *
 */
public class App  {
	
    public static void main( String[] args ) {
    	
    	ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
    	
    	/*
    	 * Session Starts
    	 */
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	
    	// New Transaction for Create
    	session.beginTransaction();
    	
    	Person person = new Person();
    	person.setId(1);
    	person.setFirstName("Levent");
    	person.setLastName("Divilioglu");
    	person.setPhoneNumber("0-555-XX-XXXX");
    	person.setOccupied(false);
    	session.save(person);	// save person
    	
    	// Commit Transaction
    	session.getTransaction().commit();
    	
    	// New Transaction for Retrieve
    	session.beginTransaction();
    	
    	Person retrievedUser = (Person) session.get(Person.class, 1);
    	
    	if(retrievedUser != null) {
    		System.out.println("Person Id        : " + person.getId());
    		System.out.println("Person First Name: " + person.getFirstName());
    		System.out.println("Person Last Name : " + person.getLastName());
    		System.out.println("Person Phone Num : " + person.getPhoneNumber());
    		System.out.println("Person Occupied? : " + person.isOccupied());
    	}
    	
    	// Close session
    	session.close();
    	
    	// checkout: http://stackoverflow.com/questions/14423980/how-to-close-a-spring-applicationcontext
    	((ConfigurableApplicationContext)appContext).close();
    }
    
}
