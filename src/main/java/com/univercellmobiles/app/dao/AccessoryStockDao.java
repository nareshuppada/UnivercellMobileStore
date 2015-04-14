package com.univercellmobiles.app.dao;

import java.util.List;

import com.univercellmobiles.app.beans.AccessoryStock;

public interface AccessoryStockDao {

		public void add(AccessoryStock accessoryStock);
	    public void update(AccessoryStock accessoryStock);
	    public AccessoryStock getByAccStockId(String accStockId);
	    public void delete(String accStockId);
	    public List<AccessoryStock>    getAllDetails();

}
