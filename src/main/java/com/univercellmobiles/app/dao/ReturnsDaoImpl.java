package com.univercellmobiles.app.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.univercellmobiles.app.beans.PhoneStock;
import com.univercellmobiles.app.beans.ReturnStock;

@Repository("returnsDao")
public class ReturnsDaoImpl implements ReturnsDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(ReturnStock sales) {
		// TODO Auto-generated method stub
		getCurrentSession().save(sales); 

	}

	public void update(ReturnStock sales) {
		// TODO Auto-generated method stub

		getCurrentSession().update(sales);
	}

	public ReturnStock getBySalesId(String saleId) {
		// TODO Auto-generated method stub
		ReturnStock sales = (ReturnStock) getCurrentSession().get(ReturnStock.class, saleId);
		return sales;
	}

	public void delete(String saleId) {
		// TODO Auto-generated method stub
		ReturnStock sales = getBySalesId(saleId);
		if (sales != null)
			getCurrentSession().delete(sales);

	}

	@SuppressWarnings("unchecked")
	public List<ReturnStock> getAllDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from ReturnStock").list();
	}
	
	public ReturnStock getByPhoneStockId(int phStockId) {
		// TODO Auto-generated method stub
		ReturnStock phoneStock = (ReturnStock) getCurrentSession().get(
				ReturnStock.class, phStockId);
		return phoneStock;
	}

	public void resolveStock(int phStockId) {
		// TODO Auto-generated method stub
		ReturnStock phoneStock = getByPhoneStockId(phStockId);
		if (phoneStock != null){
			int stockAvailable=phoneStock.getAvailable();
			if(stockAvailable>0){
				phoneStock.setAvailable(stockAvailable-1);
			}
			getCurrentSession().update(phoneStock);
		}
		return;

	}
	public float getAllReturnsAmount() {
		// TODO Auto-generated method stub
		try{
		float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(dp) from ReturnStock where available >0 ").list().get(0).toString());
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
		float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(margin) from ReturnStock where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
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
		float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(salePrice) from ReturnStock where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
		return sum;
		}
		catch(Exception e){
			return 0;
		}
	}

	public List<ReturnStock> getReturnStockByRange(Date fromDate, Date toDate) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(toDate);
        cal.add(Calendar.DATE, 1);
        toDate = cal.getTime();		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //between str_to_date('2015-03-16','%Y-%m-%d') and  str_to_date('2015-04-16','%Y-%m-%d');
		String query = "from ReturnStock where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')";
	//	System.out.println(query);
		return getCurrentSession().createQuery(query).list();
	}

	public List<ReturnStock> getAllAvailable() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from ReturnStock where available>0").list();
	}
	

}
