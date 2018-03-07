package com.gc.model;

import java.util.ArrayList;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.gc.util.HibernateUtil;

public class DAOItemImpl implements DAOItem {

	@Override
	public void addItem(Item item) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(item);
		tx.commit();
		session.close();
	}

	@Override
	public ArrayList<Item> getAllItems() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria crit = session.createCriteria(Item.class);
		ArrayList<Item> itemList = (ArrayList<Item>) crit.list();
		System.out.println(itemList.size());
		
		tx.commit();
		session.close();
		return itemList;
	}

	@Override
	public void deleteItem(Item item) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.delete(item);
		tx.commit();
		session.close();
		
	}

	@Override
	public ArrayList<Item> getOutfit() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
