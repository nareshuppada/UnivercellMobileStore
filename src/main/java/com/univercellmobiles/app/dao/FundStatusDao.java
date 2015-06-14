package com.univercellmobiles.app.dao;

import java.util.List;

import com.univercellmobiles.app.beans.FundStatus;
import com.univercellmobiles.app.beans.Transactions;

public interface FundStatusDao {
	
	public void add(FundStatus fund);
	public void update(FundStatus fund);
	public FundStatus getFundsById(int fundStatusId);
	public void delete(int fundStatusId);
	public List<FundStatus> getAllDetails();
	public List<FundStatus> getCurrentTxnDetails();
	public List<FundStatus> getLastMonthDetails();


}
