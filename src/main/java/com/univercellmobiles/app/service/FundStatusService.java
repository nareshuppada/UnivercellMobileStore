package com.univercellmobiles.app.service;

import java.util.List;

import com.univercellmobiles.app.beans.Brand;
import com.univercellmobiles.app.beans.FundStatus;
import com.univercellmobiles.app.beans.Transactions;

public interface FundStatusService {

	
	public void add(FundStatus fund);
    public void update(FundStatus fund);
    public FundStatus getByFundId(int fundStatusId);
    public void delete(int fundStatusId);
    public List<FundStatus> getAllDetails();
	public List<FundStatus> getCurrentTxnDetails();
	public List<FundStatus> getLastMonthDetails();
	

}
