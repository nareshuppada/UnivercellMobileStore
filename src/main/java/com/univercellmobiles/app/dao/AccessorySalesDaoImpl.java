package com.univercellmobiles.app.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.univercellmobiles.app.beans.AccessorySales;

@Repository("accessorySalesDao")
public class AccessorySalesDaoImpl implements AccessorySalesDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(AccessorySales sales) {
		// TODO Auto-generated method stub
		getCurrentSession().save(sales);

	}

	public void update(AccessorySales sales) {
		// TODO Auto-generated method stub

		getCurrentSession().update(sales);
	}

	public AccessorySales getBySalesId(String saleId) {
		// TODO Auto-generated method stub
		AccessorySales sales = (AccessorySales) getCurrentSession().get(AccessorySales.class, saleId);
		return sales;
	}

	public void delete(String saleId) {
		// TODO Auto-generated method stub
		AccessorySales sales = getBySalesId(saleId);
		if (sales != null)
			getCurrentSession().delete(sales);

	}

	@SuppressWarnings("unchecked")
	public List<AccessorySales> getAllDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from AccessorySales").list();
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

	public List<AccessorySales> getSalesByRange(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //between str_to_date('2015-03-16','%Y-%m-%d') and  str_to_date('2015-04-16','%Y-%m-%d');
		String query = "from Sales where salesDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')";
	//	System.out.println(query);
		return getCurrentSession().createQuery(query).list();
	}

}
