package com.univercellmobiles.app.dao;

import java.util.Date;
import java.util.List;

import com.univercellmobiles.app.beans.Sales;

public interface SalesDao {
	
	public void add(Sales sales);
    public void update(Sales sales);
    public Sales getBySalesId(String saleId);
    public void delete(String saleId);
    public List<Sales>    getAllDetails();
	public float getAllProfit();
	public List<Sales> getSalesByRange(Date fromDate, Date toDate);
	public float getTodaysProfit();
	public float getTodaysSale();
	public float get30DayProfit();
	public float get30DaysSales();

}
