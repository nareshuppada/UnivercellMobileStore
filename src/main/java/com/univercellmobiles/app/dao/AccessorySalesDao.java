package com.univercellmobiles.app.dao;

import java.util.Date;
import java.util.List;

import com.univercellmobiles.app.beans.AccessorySales;

public interface AccessorySalesDao {
	
	public void add(AccessorySales sales);
    public void update(AccessorySales sales);
    public AccessorySales getBySalesId(String saleId);
    public void delete(String saleId);
    public List<AccessorySales>    getAllDetails();
	public float getAllProfit();
	public List<AccessorySales> getSalesByRange(Date fromDate, Date toDate);

}
