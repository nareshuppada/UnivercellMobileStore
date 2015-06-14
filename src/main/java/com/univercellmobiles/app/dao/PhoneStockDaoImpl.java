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

@Repository("phoneStockDao")
public class PhoneStockDaoImpl implements PhoneStockDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(PhoneStock phoneStock) {
		// TODO Auto-generated method stub
		getCurrentSession().save(phoneStock);
	}

	public void update(PhoneStock phoneStock) {
		// TODO Auto-generated method stub

		getCurrentSession().update(phoneStock);

	}

	public PhoneStock getByPhoneStockId(int phStockId) {
		// TODO Auto-generated method stub
		PhoneStock phoneStock = (PhoneStock) getCurrentSession().get(
				PhoneStock.class, phStockId);
		return phoneStock;
	}

	public void delete(int phStockId) {
		// TODO Auto-generated method stub
		PhoneStock phoneStock = getByPhoneStockId(phStockId);
		if (phoneStock != null)
			getCurrentSession().delete(phoneStock);

	}
	
	public int sellStock(int phStockId) {
		// TODO Auto-generated method stub
		PhoneStock phoneStock = getByPhoneStockId(phStockId);
		if (phoneStock != null){
			int stockAvailable=phoneStock.getAvailable();
			if(stockAvailable>0){
				phoneStock.setAvailable(stockAvailable-1);
			}
			getCurrentSession().update(phoneStock);
		}
		return 0;

	}

	@SuppressWarnings("unchecked")
	public List<PhoneStock> getAllDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from PhoneStock").list();
	}
	
	public List<PhoneStock> getAllAvailable() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from PhoneStock where available>0").list();
	}

	public float getCurrentStockValue() {
		// TODO Auto-generated method stub
		return Float.parseFloat(getCurrentSession().createQuery("select sum(dp) from PhoneStock where available>0").list().get(0).toString());
	}

	public List<PhoneStock> getPurchaseByRange(Date fromDate, Date toDate) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(toDate);
        cal.add(Calendar.DATE, 1);
        toDate = cal.getTime();	
		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //between str_to_date('2015-03-16','%Y-%m-%d') and  str_to_date('2015-04-16','%Y-%m-%d');
		String query = "from PhoneStock where arrivalDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')";
	//	System.out.println(query);
		return getCurrentSession().createQuery(query).list();
	}

	
	public float get30DaysPurchase() {
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
				float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(dp) from PhoneStock where arrivalDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
				return sum;
				}
				catch(Exception e){
					return 0;
				}
	}
	



}
