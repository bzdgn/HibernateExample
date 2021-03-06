package com.levo.example.configuration;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	/*
	 * will run once !
	 */
	static {
		try {
			Configuration configuration = new Configuration().configure();
			
			serviceRegistry = new ServiceRegistryBuilder()
									.applySettings(configuration.getProperties())
									.buildServiceRegistry();
			
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			
		} catch(HibernateException exception) {
			System.out.println("Problem creating session factory !");
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
