package com.univercellmobiles.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univercellmobiles.app.beans.Brand;
import com.univercellmobiles.app.beans.Transactions;
import com.univercellmobiles.app.dao.BrandDao;
import com.univercellmobiles.app.dao.TransactionsDao;

@Service("transactionService")
@Transactional
public class TransactionServiceImpl implements TransactionService {
	@Autowired
	TransactionsDao transDao;

	public void add(Transactions trans) {
		// TODO Auto-generated method stub
		transDao.add(trans);
	}

	public void update(Transactions trans) {
		// TODO Auto-generated method stub
		transDao.update(trans);
		
	}

	public Transactions getByTransId(int transId) {
		// TODO Auto-generated method stub
		
		return transDao.getByTransId(transId);
	}

	public void delete(int transId) {
		// TODO Auto-generated method stub
		transDao.delete(transId);
		
		
	}

	public List<Transactions> getAllDetails() {
		// TODO Auto-generated method stub
		return transDao.getAllDetails();
	}

	public List<Transactions> getAllExpenseDetails() {
		// TODO Auto-generated method stub
		return  transDao.getAllExpenseDetails();
	}
	public List<Transactions> getAllAssetDetails() {
		// TODO Auto-generated method stub
		return  transDao.getAllAssetDetails();
	}
	public List<Transactions> getAllInvestmentDetails() {
		// TODO Auto-generated method stub
		return  transDao.getAllInvestmentDetails();
	}

	public float getAssetsBalance() {
		// TODO Auto-generated method stub
		return transDao.getAssetsBalance();
	}

	public float getExpenseBalance() {
		// TODO Auto-generated method stub
		return transDao.getExpenseBalance();
	}

	public float getInvestmentOut() {
		// TODO Auto-generated method stub
		return transDao.getInvestmentOut();
	}

	public float getInvestmentBalance() {
		// TODO Auto-generated method stub
		return transDao.getInvestmentBalance();
	}

	public float getTodaysExpense() {
		// TODO Auto-generated method stub
		return transDao.getTodaysExpense();
	}

	public float getAvgExpense() {
		// TODO Auto-generated method stub
		return transDao.getAvgExpense()/30;
	}
	
	
	

}
