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
import com.univercellmobiles.app.beans.Transactions;

@Repository("transactionsDao")
public class TransactionsDaoImpl implements TransactionsDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(Transactions trans) {
		// TODO Auto-generated method stub
		getCurrentSession().save(trans);

	}

	public void update(Transactions trans) {
		// TODO Auto-generated method stub

		getCurrentSession().update(trans);
	}

	public Transactions getByTransId(int transId) {
		// TODO Auto-generated method stub
		Transactions trans = (Transactions) getCurrentSession().get(Transactions.class, transId);
		return trans;
	}

	public void delete(int transId) {
		// TODO Auto-generated method stub
		Transactions trans = getByTransId(transId);
		if (trans != null)
			getCurrentSession().delete(trans);

	}

	@SuppressWarnings("unchecked")
	public List<Transactions> getAllDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Transactions").list();
	}

	public List<Transactions> getAllExpenseDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Transactions where type = -1").list();
	}
	
	public float getTodaysExpense() {
		// TODO Auto-generated method stub
		Date fromDate = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(fromDate);
        cal.add(Calendar.DATE, 1);
        Date toDate = cal.getTime();	
		// TODO Auto-generated method stub
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try{
		float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(amount) from Transactions where type = -1 and expenseDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
		return sum;
		}
		catch(Exception e){
			return 0;
		}
	}

	public List<Transactions> getAllAssetDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Transactions where type = 2").list();
	}

	public List<Transactions> getAllInvestmentDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Transactions where type = 1").list();
	}

	public float getAssetsBalance() {
		// TODO Auto-generated method stub
		try{
		float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(amount) from Transactions where type = 2").list().get(0).toString());
		return sum;
		}
		catch(Exception e){
			return 0;
		}
		
	}

	public float getExpenseBalance() {
		// TODO Auto-generated method stub
		try{
		float sum =  Float.parseFloat(getCurrentSession().createQuery("select sum(amount) from Transactions where type = -1").list().get(0).toString());
		return sum;
	}
	catch(Exception e){
		return 0;
	}
	}

	public float getInvestmentOut() {
		// TODO Auto-generated method stub
		try{
		float sum =  Float.parseFloat(getCurrentSession().createQuery("select sum(amount) from Transactions where type = 1 and amount<0").list().get(0).toString());
		return sum;
	}
	catch(Exception e){
		return 0;
	}
	}

	public float getInvestmentBalance() {
		// TODO Auto-generated method stub
		try{
		float sum =  Float.parseFloat(getCurrentSession().createQuery("select sum(amount) from Transactions where type = 1").list().get(0).toString());
		return sum;
	}
	catch(Exception e){
		return 0;
	}
	}

	public float getAvgExpense() {
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
				float sum = Float.parseFloat(getCurrentSession().createQuery("select sum(amount) from Transactions where type = -1 and expenseDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')").list().get(0).toString());
				return sum;
				}
				catch(Exception e){
					return 0;
				}
	}

}
