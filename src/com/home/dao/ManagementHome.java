package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;

import com.home.model.InvoiceData;
import com.home.model.Management;
import com.home.util.ResultSetUtils;
import com.home.util.StringUtil;

/**
 * Home object for domain model class Management.
 * @see com.home.dao.Management
 * @author Hibernate Tools
 */
public class ManagementHome {

	private static final Log log = LogFactory.getLog(ManagementHome.class);

	private SessionFactory sessionFactory;
	
	public ManagementHome(SessionFactory sessionFactory) {
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


	public void persist(Management transientInstance) {
		log.debug("persisting Management instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Management instance) throws Exception{
		log.debug("attaching dirty Management instance");
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
	
	public void attachDirty(InvoiceData instance) throws Exception{
		log.debug("attaching dirty InvoiceData instance");
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
	
	public void update(Management instance) throws Exception{
		log.debug("attaching dirty Management instance");
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
	public void attachClean(Management instance) {
		log.debug("attaching clean Management instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Management persistentInstance) throws Exception{
		log.debug("deleting Management instance");
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

	public Management merge(Management detachedInstance) {
		log.debug("merging Management instance");
		try {
			Management result = (Management) sessionFactory.openSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Management findById(java.lang.Integer id) throws Exception{
		log.debug("getting Management instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Management instance = (Management)session.get(Management.class, id);
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
	public List<Management> findByExample(Management instance) {
		log.debug("finding Management instance by example");
		try {
			List<Management> results = (List<Management>) sessionFactory
					.openSession().createCriteria("com.home.dao.Management")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
	public List<Management> getListManagements(int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list Product");
		Transaction tx = null;
		Session session = null;
		List<Management> results = new ArrayList<Management>();
		try {
			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//			Query query = session.createQuery("FROM Management");
//			query.setFirstResult(startPageIndex);
//			query.setMaxResults(recordsPerPage);
//			List<Management> results = query.list();
//			tx.commit();
			
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM Management t,(SELECT @i:=0) foo Order By id) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			results = ResultSetUtils.parserResultSet(rs, Management.class);
			rs.close();
			log.debug("retrieve list Management successful, result size: " + results.size());
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
	
	public int getTotalRecords() throws Exception{
		Session session = null;
		try {
			session = sessionFactory.openSession();
			String sql = "SELECT COUNT(*) AS COUNT FROM Management";
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
	
	public List<Management> getAllJobCapture() throws Exception{
		log.debug("retrieve list Product");
		Transaction tx = null;
		Session session = null;
		List<Management> results = new ArrayList<Management>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			Statement st = conn.createStatement();
			
			//Query query = session.createQuery("FROM Management WHERE step=1 AND duplicate_status=0 AND present_user=0 ORDER BY id");
			//List<Management> results = query.list();
			try(ResultSet rs1 = st.executeQuery("Select * From management WHERE step=1 AND duplicate_status=0 ORDER BY id")) {
				results = ResultSetUtils.parserResultSet(rs1, Management.class);
			}
			tx.commit();
			log.debug("retrieve list Management successful, result size: " + results.size());
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
	
	public List<Management> getJobCapture(int present_user, int limit) throws Exception{
		log.debug("retrieve list Product");
		Transaction tx = null;
		Session session = null;
		List<Management> results = new ArrayList<Management>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			Statement st = conn.createStatement();
			
			//Query query = session.createQuery("FROM Management WHERE step=1 AND duplicate_status=0 AND present_user="+present_user+" ORDER BY id");
			try(ResultSet rs1 = st.executeQuery("Select m.*, i.id as invoice_data_id  From management m JOIN invoice_data i "
					+ " ON m.id = i.management_id"
					+ " WHERE step=1 AND duplicate_status=0 AND present_user="+present_user+" ORDER BY id")) {
				results = ResultSetUtils.parserResultSet(rs1, Management.class);
				if(results == null || results.isEmpty()){
					//query = session.createQuery("FROM Management WHERE step=1 AND duplicate_status=0 AND present_user=0 ORDER BY id Limit " + limit);
					try(ResultSet rs2 = st.executeQuery("Select m.*, i.id as invoice_data_id  From management m JOIN invoice_data i "
							+ " ON m.id = i.management_id"
							+ " WHERE step=1 AND duplicate_status=0 AND present_user=0 ORDER BY id Limit " + limit)){
						 results = ResultSetUtils.parserResultSet(rs2, Management.class);
						 for (Management m : results) {
							 m.setPresent_user(present_user);
							 if(m.getCreated_time() == null){
								 m.setCreated_time(new java.sql.Timestamp(new Date().getTime()));
							 }
							 session.update(m);
						 }
					}
					
				}
			} 
			tx.commit();
			st.close();
			log.debug("retrieve list Management successful, result size: " + results.size());
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
	
	public void saveJobCapture(int management_id, InvoiceData data) throws Exception{
		log.debug("saving InvoiceData");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Management management = (Management)session.get(Management.class, management_id);
			management.setStep(2);
			management.setCapture_status(0);
			
			session.update(management);
			session.update(data);
			tx.commit();
			log.debug("saveJobCapture successful");
		} catch (Exception re) {
			re.printStackTrace();
			log.error("saveJobCapture failed", re);
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
	
	public void setJobDuplicate(int management_id) throws Exception{
		log.debug("saving InvoiceData");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Management management = (Management)session.get(Management.class, management_id);
			management.setDuplicate_status(1);
			management.setCapture_status(0);
			session.update(management);
			tx.commit();
			log.debug("saveJobCapture successful");
		} catch (Exception re) {
			re.printStackTrace();
			log.error("saveJobCapture failed", re);
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
	
	public boolean isImageDuplicate(String hash_file) throws Exception {
		log.debug("Checking Statistic duplicate with: ");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//Criteria cre = session.createCriteria(Management.class);
			//cre.add(Restrictions.eq("hash_file", hash_file));
			//Management instance = (Management) cre.uniqueResult();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			boolean isExist = false;
			try(PreparedStatement pre = conn.prepareStatement("Select * From management Where hash_file = ? Limit 1")){
				pre.setString(1, hash_file);
				try(ResultSet rs = pre.executeQuery()){
					if(rs.next()){
						isExist = true;
					}
				}
			}
			tx.commit();
			return isExist;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	////////////////////////////////////////////////////////////////
	
	public boolean checkInvoiceRecordDuplicate(String customer_id_level1, java.sql.Date date_invoice_sent, String product_id, String quantity) throws Exception {
		log.debug("isInvoiceRecordDuplicate");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//Criteria cre = session.createCriteria(Management.class);
			//cre.add(Restrictions.eq("hash_file", hash_file));
			//Management instance = (Management) cre.uniqueResult();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			boolean isExist = false;
			try(PreparedStatement pre = conn.prepareStatement("Select * From invoice_data "
					+ " Where customer_id_level1=? "
					+ " and date_invoice_sent=? "
					+ " and product_ids like ? "
					+ " and quantitys like ? "
					+ " Limit 1")){
				pre.setString(1, customer_id_level1);
				pre.setDate(2, date_invoice_sent);
				pre.setString(3, "%"+product_id+"`%");
				pre.setString(4, "%"+quantity+"`%");
				
				try(ResultSet rs = pre.executeQuery()){
					if(rs.next()){
						String[] product_ids = StringUtil.notNull(rs.getString("product_ids")).split("`");
						String[] quantitys = StringUtil.notNull(rs.getString("quantitys")).split("`");
						for (int i = 0; i < product_ids.length; i++) {
							if(product_ids[i].equalsIgnoreCase(product_id)
									&& quantitys[i].equalsIgnoreCase(quantity)){
								isExist = true;
								break;
							}
						}
					}					
				}
			}
			tx.commit();
			return isExist;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean checkInvoiceRecordDuplicate(String customer_id_level1, java.sql.Date date_invoice_sent, String product_id, String quantity, int management_id) throws Exception {
		log.debug("isInvoiceRecordDuplicate");
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//Criteria cre = session.createCriteria(Management.class);
			//cre.add(Restrictions.eq("hash_file", hash_file));
			//Management instance = (Management) cre.uniqueResult();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			boolean isExist = false;
			try(PreparedStatement pre = conn.prepareStatement("Select * From invoice_data "
					+ " Where customer_id_level1=? "
					+ " and date_invoice_sent=? "
					+ " and product_ids like ? "
					+ " and quantitys like ? "
					+ " and management_id != ? "
					+ " Limit 1")){
				pre.setString(1, customer_id_level1);
				pre.setDate(2, date_invoice_sent);
				pre.setString(3, "%"+product_id+"`%");
				pre.setString(4, "%"+quantity+"`%");
				pre.setInt(5, management_id);
				
				try(ResultSet rs = pre.executeQuery()){
					if(rs.next()){
						String[] product_ids = StringUtil.notNull(rs.getString("product_ids")).split("`");
						String[] quantitys = StringUtil.notNull(rs.getString("quantitys")).split("`");
						for (int i = 0; i < product_ids.length; i++) {
							if(product_ids[i].equalsIgnoreCase(product_id)
									&& quantitys[i].equalsIgnoreCase(quantity)){
								isExist = true;
								break;
							}
						}
					}					
				}
			}
			tx.commit();
			return isExist;
		} catch (Exception re) {
			log.error("get failed", re);
			throw re;
		} finally {
			try {
				if (session != null) {
					session.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public List<String[]> deleteJobCapture(String listId) throws Exception{
		log.debug("deleteJobCapture");
		Transaction tx = null;
		Session session = null;
		List<String[]> results = new ArrayList<String[]>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			String[] arr = listId.replace(",", ";").split(";");
			for (String id : arr) {
				if(id.trim().length() > 0){
					try {
						Management management = (Management)session.get(Management.class, Integer.parseInt(id));
						management.setDuplicate_status(1);
						management.setCapture_status(0);
						session.update(management);
						new File(management.getFile_path() + "/" + management.getFile_name()).delete();
						results.add(new String[]{id, "OK"});
					} catch (Exception e) {
						e.printStackTrace();
						results.add(new String[]{id, "Fail"});
					}
				}
			}
			
			tx.commit();
			log.debug("deleteJobCapture successful");
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("deleteJobCapture failed", re);
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
