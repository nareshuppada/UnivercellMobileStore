package com.univercellmobiles.app.service;

import java.util.List;

import com.univercellmobiles.app.beans.Brand;
import com.univercellmobiles.app.beans.Transactions;

public interface TransactionService {

	
	public void add(Transactions trans);
    public void update(Transactions trans);
    public Transactions getByTransId(int transId);
    public void delete(int transId);
    public List<Transactions>    getAllDetails();
	public List<Transactions> getAllExpenseDetails();
	public List<Transactions> getAllAssetDetails();
	public List<Transactions> getAllInvestmentDetails();
	public float getAssetsBalance();
	public float getExpenseBalance();
	public float getInvestmentOut();
	public float getInvestmentBalance();
	public float getTodaysExpense();
	public float getAvgExpense();
	public List<Transactions> getLastMonthExpenseDetails();
	public float get30DaysExpense();

}
