package com.home.dao;

// Generated Jan 12, 2016 11:21:58 PM by Hibernate Tools 4.0.0

import static org.hibernate.criterion.Example.create;

import java.math.BigDecimal;
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
	
	public InvoiceData getInvoiceData(int management_id) throws Exception{
		log.debug("retrieve list getListInvoiceData");
		Session session = null;
		InvoiceData invoice = new InvoiceData();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = 
					"SELECT i.*, m.file_path, m.file_name, m.step, it.invoice_type " +
					" FROM invoice_data i "+
					" JOIN management m ON m.id=i.management_id "+
					" LEFT JOIN invoice_type it ON i.invoice_type_id=it.id "+
					" WHERE management_id = ?";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, management_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			if(rs.next()){
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
				invoice.setCustomer_id_id(rs.getInt("customer_id"));
				invoice.setCustomer_code(rs.getString("customer_code"));
				invoice.setCustomer_name(rs.getString("customer_name"));
				
				Customer cus1 = new Customer();
				cus1.setId(rs.getInt("customer_id_level1"));
				cus1.setCustomerCode(rs.getString("customer_code_level1"));
				cus1.setStatisticName(rs.getString("customer_name_level1"));
				//invoice.setCustomer_id_level1(cus1);
				invoice.setCustomer_id_level1_id(rs.getInt("customer_id_level1"));
				invoice.setCustomer_code_level1(rs.getString("customer_code_level1"));
				invoice.setCustomer_name_level1(rs.getString("customer_name_level1"));
				
				User user = new User();
				user.setId(rs.getInt("staff_id"));
				user.setUserName(rs.getString("staff_name"));
				//invoice.setStaff_id(user);
				invoice.setStaff_id_id(rs.getInt("staff_id"));
				invoice.setStaff_name(rs.getString("staff_name"));
				
				invoice.setDate1_receipt_of_product(rs.getDate("date1_receipt_of_product"));
				invoice.setDate2_company_receipt_of_invoice(rs.getDate("date2_company_receipt_of_invoice"));
				invoice.setDate3_cus1_delivery_invoice(rs.getDate("date3_cus1_delivery_invoice"));
				invoice.setDate_sent_late(rs.getInt("date_sent_late"));
				invoice.setNotes(rs.getString("notes"));
				
				invoice.setProduct_ids(rs.getString("product_ids"));
				invoice.setProduct_names(rs.getString("product_names"));
				invoice.setTotal_boxs(rs.getString("total_boxs"));
				invoice.setQuantitys(rs.getString("quantitys"));
				invoice.setTotal_prices(rs.getString("total_prices"));
				invoice.setUnit_prices(rs.getString("unit_prices"));
				invoice.setSum_total_price(rs.getBigDecimal("sum_total_price"));
				
			}
			rs.close();
			return invoice;
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
	
	public List<InvoiceData> getListInvoiceData(
			java.sql.Date start_day, 
			java.sql.Date end_day, 
			int invoice_type,
			int staff_id,
			int customer_id,
			String customer_name,
			int sent_late) throws Exception{
		log.debug("retrieve list getListInvoiceData");
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
					" WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?))"
					+ " AND (0="+(invoice_type<=0?0:1)+" Or (invoice_type_id=?))"
					+ " AND (0="+(staff_id<=0?0:1)+" Or (i.staff_id=?))"
					+ " AND (0="+(customer_id<=0?0:1)+" Or (customer_id=?) OR 0="+(customer_name.length()==0?0:1)+" OR (LOWER(customer_name) like ?))"
					+ " AND (0="+(sent_late<=0?0:1)+" Or (date_sent_late"+(sent_late==1?">0":"=0")+"))"
					//+ " Order by date1_receipt_of_product, id";
					+ " Order by id";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);
			pre.setInt(3, invoice_type);
			pre.setInt(4, staff_id);
			pre.setInt(5, customer_id);
			pre.setString(6, "%"+customer_name.toLowerCase() + "%");
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
				
				invoice.setDate1_receipt_of_product(rs.getDate("date1_receipt_of_product"));
				invoice.setDate2_company_receipt_of_invoice(rs.getDate("date2_company_receipt_of_invoice"));
				invoice.setDate3_cus1_delivery_invoice(rs.getDate("date3_cus1_delivery_invoice"));
				invoice.setDate_sent_late(rs.getInt("date_sent_late"));
				invoice.setNotes(rs.getString("notes"));
				
				invoice.setProduct_ids(rs.getString("product_ids"));
				invoice.setProduct_names(rs.getString("product_names"));
				invoice.setTotal_boxs(rs.getString("total_boxs"));
				invoice.setQuantitys(rs.getString("quantitys"));
				invoice.setTotal_prices(rs.getString("total_prices"));
				invoice.setUnit_prices(rs.getString("unit_prices"));
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
	
	private InvoiceData getInvoiceData(ResultSet rs) throws Exception{
		try {
			InvoiceData invoice = new InvoiceData();
			invoice.setId(rs.getInt("id"));
			
			Management m = new Management();
			m.setId(rs.getInt("management_id"));
//			m.setFile_path(rs.getString("file_path"));
//			m.setFile_name(rs.getString("file_name"));
//			m.setStep(rs.getInt("step"));
			invoice.setManagement_id(m);
			
//			InvoiceType iv_type = new InvoiceType();
//			iv_type.setId(rs.getInt("invoice_type_id"));
//			iv_type.setInvoiceType(rs.getString("invoice_type_name"));
//			invoice.setInvoice_type_id(iv_type);
			invoice.setInvoice_type_name(StringUtil.notNull(rs.getString("invoice_type_name")));

			Customer cus = new Customer();
			cus.setId(rs.getInt("customer_id"));
			cus.setCustomerCode(StringUtil.notNull(rs.getString("customer_code")));
			cus.setStatisticName(StringUtil.notNull(rs.getString("customer_name")));
			invoice.setCustomer_id(cus);
			invoice.setCustomer_code(StringUtil.notNull(rs.getString("customer_code")));
			invoice.setCustomer_name(StringUtil.notNull(rs.getString("customer_name")));
			
			Customer cus1 = new Customer();
			cus1.setId(rs.getInt("customer_id_level1"));
			cus1.setCustomerCode(StringUtil.notNull(rs.getString("customer_code_level1")));
			cus1.setStatisticName(StringUtil.notNull(rs.getString("customer_name_level1")));
			invoice.setCustomer_id_level1(cus1);
			invoice.setCustomer_code_level1(StringUtil.notNull(rs.getString("customer_code_level1")));
			invoice.setCustomer_name_level1(StringUtil.notNull(rs.getString("customer_name_level1")));
			
			User user = new User();
			user.setId(rs.getInt("staff_id"));
			user.setUserName(StringUtil.notNull(rs.getString("staff_name")));
			invoice.setStaff_id(user);
			invoice.setStaff_name(StringUtil.notNull(rs.getString("staff_name")));
			
			invoice.setDate1_receipt_of_product(rs.getDate("date1_receipt_of_product"));
			invoice.setDate2_company_receipt_of_invoice(rs.getDate("date2_company_receipt_of_invoice"));
			invoice.setDate3_cus1_delivery_invoice(rs.getDate("date3_cus1_delivery_invoice"));
			invoice.setDate_sent_late(rs.getInt("date_sent_late"));
			invoice.setNotes(StringUtil.notNull(rs.getString("notes")));
			
			invoice.setProduct_ids(StringUtil.notNull(rs.getString("product_ids")));
			invoice.setProduct_names(StringUtil.notNull(rs.getString("product_names")));
			invoice.setTotal_boxs(StringUtil.notNull(rs.getString("total_boxs")));
			invoice.setQuantitys(StringUtil.notNull(rs.getString("quantitys")));
			invoice.setTotal_prices(StringUtil.notNull(rs.getString("total_prices")));
			invoice.setUnit_prices(StringUtil.notNull(rs.getString("unit_prices")));
			invoice.setSum_total_price(rs.getBigDecimal("sum_total_price"));
			
			return invoice;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public List<InvoiceData> getReportInvoiceDataByCus1(
			java.sql.Date start_day, 
			java.sql.Date end_day) throws Exception{
		log.debug("retrieve list getReportInvoiceDataByCus1");
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT d.* FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?)) AND customer_code_level1 is not null"
					+ " ORDER BY customer_name_level1,customer_code_level1";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);		
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				results.add(getInvoiceData(rs));
			}
			rs.close();
			log.debug("retrieve getReportInvoiceDataByCus1 successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getReportInvoiceDataByCus1 failed", re);
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
	
	public List<InvoiceData> getReportInvoiceDataByStaff(
			java.sql.Date start_day, 
			java.sql.Date end_day,
			int staff_id) throws Exception{
		log.debug("retrieve list getReportInvoiceDataByStaff");
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT d.* FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?)) "
					+ " AND (0="+(staff_id<=0?0:1)+" Or (d.staff_id=?)) AND staff_name is not null"
					+ " ORDER BY staff_name";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);
			pre.setInt(3, staff_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				results.add(getInvoiceData(rs));
			}
			rs.close();
			log.debug("retrieve getReportInvoiceDataByStaff successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getReportInvoiceDataByStaff failed", re);
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
	
	public List<InvoiceData> getReportInvoiceDataByCus2(
			java.sql.Date start_day, 
			java.sql.Date end_day,
			int customer_id) throws Exception{
		log.debug("retrieve list getReportInvoiceDataByCus2");
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT d.* FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?))  AND customer_code is not null"
					+ " AND (0="+(customer_id<=0?0:1)+" Or (customer_id=?))"
					+ " ORDER BY customer_code";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);	
			pre.setInt(3, customer_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				results.add(getInvoiceData(rs));
			}
			rs.close();
			log.debug("retrieve getReportInvoiceDataByCus2 successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getReportInvoiceDataByCus2 failed", re);
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
	
	public List<InvoiceData> getReportInvoiceDataDetailByCus2(
			java.sql.Date start_day, 
			java.sql.Date end_day,
			int customer_id) throws Exception{
		log.debug("retrieve list getReportInvoiceDataDetailByCus2");
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT d.* FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?))  AND customer_code is not null"
					+ " AND (0="+(customer_id<=0?0:1)+" Or (customer_id=?))"
					+ " ORDER BY customer_code";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);	
			pre.setInt(3, customer_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				results.add(getInvoiceData(rs));
			}
			rs.close();
			log.debug("retrieve getReportInvoiceDataDetailByCus2 successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getReportInvoiceDataDetailByCus2 failed", re);
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
	
	public List<InvoiceData> getReportInvoiceDataDaily(
			java.sql.Date start_day, 
			java.sql.Date end_day,
			int staff_id) throws Exception{
		log.debug("retrieve list getReportInvoiceDataByStaff");
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT d.* FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?)) "
					+ " AND (0="+(staff_id<=0?0:1)+" Or (d.staff_id=?)) AND staff_id is not null AND customer_code is not null"
					+ " ORDER BY staff_id, customer_code";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);
			pre.setInt(3, staff_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				results.add(getInvoiceData(rs));
			}
			rs.close();
			log.debug("retrieve getReportInvoiceDataByStaff successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getReportInvoiceDataByStaff failed", re);
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
	
	
	
	public long getTotalCustomer2FollowByCus1(int customer_id) throws Exception{
		log.debug("retrieve list getTotalCustomer2Follow");
		Session session = null;
		long total = 0;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT count(*) FROM customer where "
					+ "customer1_level1_id = ? "
					+ "or customer2_level1_id = ? "
					+ "or customer3_level1_id = ? "
					+ "or customer4_level1_id = ? "
					+ "or customer5_level1_id = ? ";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, customer_id);
			pre.setInt(2, customer_id);
			pre.setInt(3, customer_id);
			pre.setInt(4, customer_id);
			pre.setInt(5, customer_id);
			//System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			if(rs.next()){
				total = rs.getLong(1);
			}
			rs.close();
			log.debug("retrieve getTotalCustomer2Follow successful, result size: " + total);
			return total;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getTotalCustomer2Follow failed", re);
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
	
	public List<String[]> getTotalProductBeforePhase(
			java.sql.Date start_day, 
			java.sql.Date end_day,
			int customer1_code, 
			String product_code) throws Exception{
		log.debug("retrieve list getTotalProductBeforePhase");
		Session session = null;
		List<String[]> results = new ArrayList<String[]>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			//String sql = "SELECT product_ids, quantitys FROM invoice_data where product_ids like ? and customer_id_level1=?";
			String sql = "SELECT d.product_ids, d.quantitys "
					+ " FROM management m JOIN invoice_data d "
					+ " ON m.id = d.management_id "
					+ " WHERE duplicate_status=0 AND d.product_ids like ? and d.customer_id_level1=? AND (d.date1_receipt_of_product >= ? And d.date1_receipt_of_product < ?)";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setString(1,  "%" + product_code + "%");
			pre.setInt(2, customer1_code);
			pre.setDate(3, start_day);
			pre.setDate(4, end_day);
			//System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				results.add(new String[]{
					StringUtil.notNull(rs.getString("product_ids"))	,
					StringUtil.notNull(rs.getString("quantitys"))	
				});
			}
			rs.close();
			log.debug("retrieve getTotalProductBeforePhase successful, result size: " + results);
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getTotalProductBeforePhase failed", re);
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
	
	
	public long getTotalCustomer2FollowByStaff(int staff_id) throws Exception{
		log.debug("retrieve list getTotalCustomer2FollowByStaff");
		Session session = null;
		long total = 0;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT count(*) FROM customer where user_id = ?";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, staff_id);
			//System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			if(rs.next()){
				total = rs.getLong(1);
			}
			rs.close();
			log.debug("retrieve getTotalCustomer2FollowByStaff successful, result size: " + total);
			return total;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getTotalCustomer2FollowByStaff failed", re);
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
	
	public long getTotalCustomerNoSentInvoice(
			java.sql.Date start_day, 
			java.sql.Date end_day,
			int staff_id) throws Exception{
		log.debug("retrieve list getTotalCustomerNoSentInvoice");
		Session session = null;
		long total = 0;
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT count(*) FROM customer where user_id = ? "
					+ " AND id not in ("
					+ "Select customer_id_level1 FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?)) "
					+ " AND (0="+(staff_id<=0?0:1)+" Or (d.staff_id=?)) AND customer_id_level1 is not null"
					+ ")";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setInt(1, staff_id);
			pre.setDate(2, start_day);
			pre.setDate(3, end_day);
			pre.setInt(4, staff_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			if(rs.next()){
				total = rs.getLong(1);
			}
			rs.close();
			log.debug("retrieve getTotalCustomerNoSentInvoice successful, result size: " + total);
			return total;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getTotalCustomerNoSentInvoice failed", re);
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
	
	
	public BigDecimal getTotalRevenueBeforePhaseByStaff(
			java.sql.Date start_day, 
			java.sql.Date end_day,
			int staff_id) throws Exception{
		log.debug("retrieve list getTotalRevenueBeforePhaseByStaff");
		Session session = null;
		BigDecimal total = new BigDecimal(0);
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql =  "Select sum_total_price FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?)) "
					+ " AND (0="+(staff_id<=0?0:1)+" Or (d.staff_id=?)) AND staff_name is not null";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);
			pre.setInt(3, staff_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				BigDecimal sum_total_price = rs.getBigDecimal("sum_total_price");
				if(sum_total_price != null){
					total = total.add(sum_total_price);
				}
			}
			rs.close();
			log.debug("retrieve getTotalRevenueBeforePhaseByStaff successful, result size: " + total);
			return total;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getTotalRevenueBeforePhaseByStaff failed", re);
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
		
	public BigDecimal getTotalRevenueBeforePhaseByCus(
			java.sql.Date start_day, 
			java.sql.Date end_day,
			int customer_id) throws Exception{
		log.debug("retrieve list getTotalRevenueBeforePhaseByCus");
		Session session = null;
		BigDecimal total = new BigDecimal(0);
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql =  "Select sum_total_price FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND "
					+ " (0="+(start_day==null?0:1)+" Or (date1_receipt_of_product >= ? And date1_receipt_of_product <= ?)) "
					+ " AND (0="+(customer_id<=0?0:1)+" Or (d.customer_id=?)) AND customer_name is not null";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, start_day);
			pre.setDate(2, end_day);
			pre.setInt(3, customer_id);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				BigDecimal sum_total_price = rs.getBigDecimal("sum_total_price");
				if(sum_total_price != null){
					total = total.add(sum_total_price);
				}
			}
			rs.close();
			log.debug("retrieve getTotalRevenueBeforePhaseByCus successful, result size: " + total);
			return total;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getTotalRevenueBeforePhaseByCus failed", re);
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
		
	//chức năng báo cáo định kỳ mỗi ngày tiến độ nhập toa
	public List<InvoiceData> getReportIndexDaily(
			java.sql.Date index_date,
			int present_user) throws Exception{
		log.debug("retrieve list getReportIndexDaily");
		Session session = null;
		List<InvoiceData> results = new ArrayList<InvoiceData>();
		try {
			session = sessionFactory.openSession();
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();

			String sql = "SELECT d.* FROM management m JOIN invoice_data d ON m.id = d.management_id "
					+ " WHERE  duplicate_status=0 AND step = 2 AND "
					+ " (0="+(index_date==null?0:1)+" Or (DATE(index_time) = ?)) "
					+ " AND (0="+(present_user<=0?0:1)+" Or (present_user=?)) "
					+ " ORDER BY sum_total_price DESC, customer_code";
			
			//System.out.println(sql);
			PreparedStatement pre = conn.prepareStatement(sql);
			pre.setDate(1, index_date);
			pre.setInt(2, present_user);
			System.out.println(pre.toString());
			ResultSet rs = pre.executeQuery();
			while(rs.next()){
				results.add(getInvoiceData(rs));
			}
			rs.close();
			log.debug("retrieve getReportIndexDaily successful, result size: " + results.size());
			return results;
		} catch (Exception re) {
			re.printStackTrace();
			log.error("retrieve getReportIndexDaily failed", re);
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
