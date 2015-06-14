package com.univercellmobiles.app.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.univercellmobiles.app.beans.FundStatus;
import com.univercellmobiles.app.beans.Sales;
import com.univercellmobiles.app.beans.Transactions;

@Repository("fundStatusDao")
public class FundStatusDaoImpl implements FundStatusDao {
	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(FundStatus fund) {
		// TODO Auto-generated method stub
		getCurrentSession().save(fund);

	}

	public void update(FundStatus fund) {
		// TODO Auto-generated method stub

		getCurrentSession().update(fund);
	}

	public FundStatus getFundsById(int fundStatusId) {
		// TODO Auto-generated method stub
		FundStatus fund = (FundStatus) getCurrentSession().get(FundStatus.class, fundStatusId);
		return fund;
	}

	public void delete(int fundStatusId) {
		// TODO Auto-generated method stub
		FundStatus fund = getFundsById(fundStatusId);
		if (fund != null)
			getCurrentSession().delete(fund);

	}

	@SuppressWarnings("unchecked")
	public List<FundStatus> getAllDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from FundStatus").list();
	}

	public List<FundStatus> getCurrentTxnDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from FundStatus order by statusId desc").list();
	}

	public List<FundStatus> getLastMonthDetails() {
		// TODO Auto-generated method stub
		 Calendar cal = Calendar.getInstance();
	     cal.setTimeZone(TimeZone.getTimeZone("IST"));
	     cal.add(Calendar.DATE, 1);
	     Date toDate = cal.getTime();
	     cal.add(Calendar.MONTH, -1);
	     Date fromDate=cal.getTime();
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     String query = "from FundStatus where today between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')";
	 		System.out.println(query);
	 	//	return getCurrentSession().createQuery(query).list();

		return getCurrentSession().createQuery(query).list();
	}


}
