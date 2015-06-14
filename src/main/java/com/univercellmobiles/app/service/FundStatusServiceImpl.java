package com.univercellmobiles.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univercellmobiles.app.beans.Brand;
import com.univercellmobiles.app.beans.FundStatus;
import com.univercellmobiles.app.beans.Transactions;
import com.univercellmobiles.app.dao.BrandDao;
import com.univercellmobiles.app.dao.FundStatusDao;
import com.univercellmobiles.app.dao.TransactionsDao;

@Service("fundStatusService")
@Transactional
public class FundStatusServiceImpl implements FundStatusService {
	@Autowired
	FundStatusDao fundsDao;

	public void add(FundStatus fund) {
		// TODO Auto-generated method stub
		fundsDao.add(fund);
	}

	public void update(FundStatus fund) {
		// TODO Auto-generated method stub
		fundsDao.update(fund);
	}

	public FundStatus getByFundId(int fundStatusId) {
		// TODO Auto-generated method stub
		return fundsDao.getFundsById(fundStatusId);
	}



	public List<FundStatus> getAllDetails() {
		// TODO Auto-generated method stub
		return fundsDao.getAllDetails();
	}

	public void delete(int fundStatusId) {
		// TODO Auto-generated method stub
		fundsDao.delete(fundStatusId);
		
	}

	public List<FundStatus> getCurrentTxnDetails() {
		// TODO Auto-generated method stub
		return fundsDao.getCurrentTxnDetails();
	}

	public List<FundStatus> getLastMonthDetails() {
		// TODO Auto-generated method stub
		return fundsDao.getLastMonthDetails();
	}

	

	
	
	

}
