package com.gc.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gc.util.HibernateUtil;

public class DAOUserImpl implements DAOUser {

	@Override
	public void createUser(User user) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(user);
		tx.commit();
		session.close();
		
	}

	@Override
	public void updateUser(User user) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.update(user);
		tx.commit();
		session.close();
		
	}

}
















