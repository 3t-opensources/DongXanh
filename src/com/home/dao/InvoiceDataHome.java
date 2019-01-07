package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.sql.Connection;
import java.sql.PreparedStatement;
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

import com.home.model.Customer;
import com.home.model.InvoiceData;
import com.home.model.InvoiceType;
import com.home.model.Management;
import com.home.model.Product;
import com.home.model.Statistic;
import com.home.model.User;
import com.home.util.ResultSetUtils;
import com.home.util.StringUtil;

/**
 * Home object for domain model class InvoiceData.
 * @see com.home.dao.InvoiceData
 * @author Hibernate Tools
 */
public class InvoiceDataHome {

	private static final Log log = LogFactory.getLog(InvoiceDataHome.class);

	private SessionFactory sessionFactory;
	
	public InvoiceDataHome(SessionFactory sessionFactory) {
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


	public void persist(InvoiceData transientInstance) {
		log.debug("persisting InvoiceData instance");
		try {
			sessionFactory.openSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
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
	
	public void update(InvoiceData instance) throws Exception{
		log.debug("attaching dirty InvoiceData instance");
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
	public void attachClean(InvoiceData instance) {
		log.debug("attaching clean InvoiceData instance");
		try {
			sessionFactory.openSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(InvoiceData persistentInstance) throws Exception{
		log.debug("deleting InvoiceData instance");
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

	public InvoiceData merge(InvoiceData detachedInstance) {
		log.debug("merging InvoiceData instance");
		try {
			InvoiceData result = (InvoiceData) sessionFactory.openSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public InvoiceData findById(java.lang.Integer id) throws Exception{
		log.debug("getting InvoiceData instance with id: " + id);
		Transaction tx = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			InvoiceData instance = (InvoiceData)session.get(InvoiceData.class, id);
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
	public List<InvoiceData> findByExample(InvoiceData instance) {
		log.debug("finding InvoiceData instance by example");
		try {
			List<InvoiceData> results = (List<InvoiceData>) sessionFactory
					.openSession().createCriteria("com.home.dao.InvoiceData")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	
	public List<InvoiceData> getListInvoiceDatas(int startPageIndex, int recordsPerPage) throws Exception{
		log.debug("retrieve list Product");
		Transaction tx = null;
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
//			tx = session.beginTransaction();
//			Query query = session.createQuery("FROM InvoiceData");
//			query.setFirstResult(startPageIndex);
//			query.setMaxResults(recordsPerPage);
//			List<InvoiceData> results = query.list();
//			tx.commit();
			
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			int range = startPageIndex+recordsPerPage;
			ResultSet rs = conn.createStatement().executeQuery(
					"SELECT * FROM (SELECT @i:=@i+1 AS iterator, t.* FROM InvoiceData t,(SELECT @i:=0) foo Order By id) AS XX WHERE iterator > "+startPageIndex+" AND iterator <= " + range);
			results = ResultSetUtils.parserResultSet(rs, InvoiceData.class);
			rs.close();
			log.debug("retrieve list InvoiceData successful, result size: " + results.size());
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
			String sql = "SELECT COUNT(*) AS COUNT FROM InvoiceData";
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
	
	public List<InvoiceData> getListInvoiceData(int management_id) throws Exception{
		log.debug("retrieve list Product");
		Transaction tx = null;
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			try (Statement sta = conn.createStatement()) {
				String sql = "Select * FROM invoice_data WHERE management_id="+management_id+" ORDER BY id";
				try(ResultSet rs = sta.executeQuery(sql)){
					results = ResultSetUtils.parserResultSet(rs, InvoiceData.class);
				}
			}
			tx.commit();
			log.debug("retrieve list InvoiceData successful, result size: " + results.size());
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
	
	public List<InvoiceData> getListInvoiceData(
			java.sql.Date start_day, 
			java.sql.Date end_day, 
			int invoice_type,
			int staff_id,
			int customer_id,
			int sent_late) throws Exception{
		log.debug("retrieve list InvoiceData");
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = 
					"SELECT i.*, m.file_path, m.file_name, m.step, it.invoice_type " +
					" FROM invoice_data i "+
					" JOIN management m ON m.id=i.management_id "+
					" LEFT JOIN invoice_type it ON i.invoice_type_id=it.id "+
					" WHERE  "
					+ " (0="+(start_day==null?0:1)+" Or (date_company_received >= ? And date_company_received <= ?))"
					+ " AND (0="+(invoice_type<=0?0:1)+" Or (invoice_type_id=?))"
					+ " AND (0="+(staff_id<=0?0:1)+" Or (i.staff_id=?))"
					+ " AND (0="+(customer_id<=0?0:1)+" Or (customer_id=?))"
					+ " AND (0="+(sent_late<=0?0:1)+" Or (date_sent_late>0))"
					+ " Order by date_company_received, id";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);
			pre.setInt(3, invoice_type);
			pre.setInt(4, staff_id);
			pre.setInt(5, customer_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			int no = 1;
			while(rs.next()){
				InvoiceData invoice = new InvoiceData();
				invoice.setId(rs.getInt("id"));
				
				Management m = new Management();
				m.setId(rs.getInt("management_id"));
				m.setFile_path(rs.getString("file_path"));
				m.setFile_name(rs.getString("file_name"));
				m.setStep(rs.getInt("step"));
				invoice.setManagement_id(m);
				
				InvoiceType iv_type = new InvoiceType();
				iv_type.setId(rs.getInt("invoice_type_id"));
				iv_type.setInvoiceType(rs.getString("invoice_type"));
				invoice.setInvoice_type_id(iv_type);
				invoice.setInvoice_type_name(rs.getString("invoice_type_name"));

				Customer cus = new Customer();
				cus.setId(rs.getInt("customer_id"));
				cus.setCustomerCode(rs.getString("customer_code"));
				cus.setStatisticName(rs.getString("customer_name"));
				//invoice.setCustomer_id(cus);
				invoice.setCustomer_code(rs.getString("customer_code"));
				invoice.setCustomer_name(rs.getString("customer_name"));
				
				Customer cus1 = new Customer();
				cus1.setId(rs.getInt("customer_id_level1"));
				cus1.setCustomerCode(rs.getString("customer_code_level1"));
				cus1.setStatisticName(rs.getString("customer_name_level1"));
				//invoice.setCustomer_id_level1(cus1);
				invoice.setCustomer_code_level1(rs.getString("customer_code_level1"));
				invoice.setCustomer_name_level1(rs.getString("customer_name_level1"));
				
				User user = new User();
				user.setId(rs.getInt("staff_id"));
				user.setUserName(rs.getString("staff_name"));
				//invoice.setStaff_id(user);
				invoice.setStaff_name(rs.getString("staff_name"));
				
				invoice.setDate_company_received(rs.getDate("date_company_received"));
				invoice.setDate_product_received(rs.getDate("date_product_received"));
				invoice.setDate_sent_late(rs.getInt("date_sent_late"));
				invoice.setNotes(rs.getString("notes"));
				
				invoice.setProduct_ids(rs.getString("product_ids"));
				invoice.setProduct_names(rs.getString("product_names"));
				invoice.setTotal_boxs(rs.getString("total_boxs"));
				invoice.setQuantitys(rs.getString("quantitys"));
				invoice.setTotal_prices(rs.getString("total_prices"));
				invoice.setSum_total_price(rs.getBigDecimal("sum_total_price"));
				
				results.add(invoice);
			}
			rs.close();
			log.debug("retrieve list Product successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve list Product failed", re);
			throw re;
		} finally{
			try {
				if(session != null){
					session.flush();
					session.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	
}
