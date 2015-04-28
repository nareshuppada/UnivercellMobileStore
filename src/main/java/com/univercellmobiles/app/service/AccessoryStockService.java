package com.univercellmobiles.app.service;

import java.util.Date;
import java.util.List;

import com.univercellmobiles.app.beans.AccessoryStock;

public interface AccessoryStockService {

		public void add(AccessoryStock accessoryStock);
	    public void update(AccessoryStock accessoryStock);
	    public AccessoryStock getByAccStockId(int accStockId);
	    public void delete(int accStockId);
	    public List<AccessoryStock>    getAllDetails();
	    public List<AccessoryStock> getAllAvailable();
		public float getCurrentStockValue();
		public int sellStock(int accStockId);
		public List<AccessoryStock> getPurchaseByRange(Date fromDate, Date toDate);

}
