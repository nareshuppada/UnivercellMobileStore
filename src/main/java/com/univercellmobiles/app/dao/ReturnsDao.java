package com.univercellmobiles.app.dao;

import java.util.Date;
import java.util.List;

import com.univercellmobiles.app.beans.ReturnStock;

public interface ReturnsDao {
	
	public void add(ReturnStock sales);
    public void update(ReturnStock sales);
    public ReturnStock getBySalesId(String saleId);
    public void delete(String saleId);
    public List<ReturnStock>    getAllDetails();
	public float getAllReturnsAmount();
	public List<ReturnStock> getReturnStockByRange(Date fromDate, Date toDate);
	public float getTodaysProfit();
	public float getTodaysSale();
	public List<ReturnStock> getAllAvailable();
	public void resolveStock(int i);

}
