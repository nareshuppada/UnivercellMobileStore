package com.univercellmobiles.app.service;

import java.util.Date;
import java.util.List;

import com.univercellmobiles.app.beans.PhoneStock;

public interface PhoneStockService {
	
	public void add(PhoneStock phoneStock);
    public void update(PhoneStock phoneStock);
    public PhoneStock getByPhoneStockId(int phStockId);
    public void delete(int phStockId);
    public int sellStock(int phStockId);
    public List<PhoneStock>    getAllDetails();
	public List<PhoneStock> getAllAvailable();
	public float getCurrentStockValue();
	public List<PhoneStock> getPurchaseByRange(Date fromDate, Date toDate);
	public float get30DaysPurchase();

}
