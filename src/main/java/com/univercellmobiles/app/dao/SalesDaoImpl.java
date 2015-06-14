package com.univercellmobiles.app.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.univercellmobiles.app.beans.Sales;

@Repository("salesDao")
public class SalesDaoImpl implements SalesDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(Sales sales) {
		// TODO Auto-generated method stub
		getCurrentSession().save(sales);

	}

	public void update(Sales sales) {
		// TODO Auto-generated method stub

		getCurrentSession().update(sales);
	}

	public Sales getBySalesId(String saleId) {
		// TODO Auto-generated method stub
		Sales sales = (Sales) getCurrentSession().get(Sales.class, saleId);
		return sales;
	}

	public void delete(String saleId) {
		// TODO Auto-generated method stub
		Sales sales = getBySalesId(saleId);
		if (sales != null)
			getCurrentSession().delete(sales);

	}

	@SuppressWarnings("unchecked")
	public List<Sales> getAllDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Sales").list();
	}

	public float getAllProfit() {
		// TODO Auto-generated method stub
		try{
		float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(margin) from Sales").list().get(0).toString());
		return sum;
		}
		catch(Exception e){
			return 0;
		}
	}
	
	public float getTodaysProfit() {
		// TODO Auto-generated method stub
		Date fromDate = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.add(Calendar.DATE, 1);
        Date toDate = cal.getTime();	
		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
		float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(margin) from Sales where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
		return sum;
		}
		catch(Exception e){
			return 0;
		}
	}
	
	public float getTodaysSale() {
		// TODO Auto-generated method stub
		Date fromDate = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.add(Calendar.DATE, 1);
        Date toDate = cal.getTime();	
		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
		float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(salePrice) from Sales where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
		return sum;
		}
		catch(Exception e){
			return 0;
		}
	}

	public List<Sales> getSalesByRange(Date fromDate, Date toDate) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(toDate);
        cal.add(Calendar.DATE, 1);
        toDate = cal.getTime();		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //between str_to_date('2015-03-16','%Y-%m-%d') and  str_to_date('2015-04-16','%Y-%m-%d');
		String query = "from Sales where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')";
	//	System.out.println(query);
		return getCurrentSession().createQuery(query).list();
	}
	
	
	public float get30DayProfit() {
		// TODO Auto-generated method stub
				Calendar cal = Calendar.getInstance();
		        cal.setTime(new Date());
		        cal.add(Calendar.DATE, -30);
		        Date fromDate = cal.getTime();
		        cal.setTime(new Date());
		        cal.add(Calendar.DATE, 1);
		        Date toDate = cal.getTime();	
				// TODO Auto-generated method stub
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try{
				float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(margin) from Sales where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
				return sum;
				}
				catch(Exception e){
					return 0;
				}
	}

	public float get30DaysSales() {
		// TODO Auto-generated method stub
				Calendar cal = Calendar.getInstance();
		        cal.setTime(new Date());
		        cal.add(Calendar.DATE, -30);
		        Date fromDate = cal.getTime();
		        cal.setTime(new Date());
		        cal.add(Calendar.DATE, 1);
		        Date toDate = cal.getTime();	
				// TODO Auto-generated method stub
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try{
				float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(salePrice) from Sales where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
				return sum;
				}
				catch(Exception e){
					return 0;
				}
	}


	

}
