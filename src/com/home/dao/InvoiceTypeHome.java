package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionImpl;

import com.home.model.InvoiceType;
import com.home.util.ResultSetUtils;

/**
 * Home object for domain model class InvoiceType.
 * @see com.home.dao.InvoiceType
 * @author Hibernate Tools
 */
public class InvoiceTypeHome {

	private static final Log log = LogFactory.getLog(InvoiceTypeHome.class);

	private SessionFactory sessionFactory;
	
	public InvoiceTypeHome(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected SessionFactory getSessionFactory() {
		try {
			return sessionFactory;
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}


	public void persist(InvoiceType transientInstance) {
		log.debug("persisting InvoiceType instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(InvoiceType instance) throws Exception{
		log.debug("attaching dirty InvoiceType instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			re.printStackTrace();
			log.error("attach failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public void update(InvoiceType instance) throws Exception{
		log.debug("attaching dirty InvoiceType instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(instance);
			tx.commit();
			log.debug("attach successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			re.printStackTrace();
			log.error("attach failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void attachClean(InvoiceType instance) {
		log.debug("attaching clean InvoiceType instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InvoiceType persistentInstance) throws Exception{
		log.debug("deleting InvoiceType instance");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.delete(persistentInstance);
			tx.commit();
			log.debug("delete successful");
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("delete failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public InvoiceType merge(InvoiceType detachedInstance) {
		log.debug("merging InvoiceType instance");
		try {
			InvoiceType result = (InvoiceType) sessionFactory.openSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InvoiceType findById(java.lang.Integer id) throws Exception{
		log.debug("getting InvoiceType instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			InvoiceType instance = (InvoiceType)session.get(InvoiceType.class, id);
			tx.commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (Exception re) {
			if (tx!=null) tx.rollback();
			log.error("get failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<InvoiceType> findByExample(InvoiceType instance) {
		log.debug("finding InvoiceType instance by example");
		try {
			List<InvoiceType> results = (List<InvoiceType>) sessionFactory
					.openSession().createCriteria("com.home.dao.InvoiceType")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
	public int getTotalRecords() throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM InvoiceType";
			Query query = session.createQuery(sql);
			List results = query.list();
			return Integer.parseInt(results.get(0).toString());
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Product failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public List<InvoiceType> getListInvoiceType() throws Exception{
		log.debug("retrieve list Product");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("FROM InvoiceType ORDER BY id");
			List<InvoiceType> results = query.list();
			tx.commit();
			log.debug("retrieve list InvoiceType successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Product failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public List<InvoiceType> getListInvoiceType2() throws Exception{
		log.debug("retrieve list Product");
		Transaction tx = null;
		Session session = null;
		List<InvoiceType> results = new ArrayList<InvoiceType>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String sql = "Select * From invoice_type WHERE status=1 ORDER BY tab_order";
				
				try(ResultSet rs = sta.executeQuery(sql)){
					while (rs.next()) {
						InvoiceType iv = new InvoiceType();
						iv.setId(rs.getInt("id"));
						iv.setInvoiceType(rs.getString("invoice_type"));
						iv.setDescription(rs.getString("description"));
						results.add(iv);
					}
				}
			}
			tx.commit();
			log.debug("retrieve list InvoiceType successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Product failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	
}
