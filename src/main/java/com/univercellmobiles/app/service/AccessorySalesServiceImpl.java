package com.univercellmobiles.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univercellmobiles.app.beans.AccessorySales;
import com.univercellmobiles.app.dao.AccessorySalesDao;

@Service("accessorySalesService")
@Transactional
public class AccessorySalesServiceImpl implements AccessorySalesService {
	
	@Autowired
	AccessorySalesDao accessorySalesDao;

	public void add(AccessorySales sales) {
		// TODO Auto-generated method stub
		accessorySalesDao.add(sales);
		
	}

	public void update(AccessorySales sales) {
		// TODO Auto-generated method stub
		accessorySalesDao.update(sales);
		
	}

	public AccessorySales getBySalesId(String saleId) {
		// TODO Auto-generated method stub
		return accessorySalesDao.getBySalesId(saleId);
	}

	public void delete(String saleId) {
		// TODO Auto-generated method stub
		accessorySalesDao.delete(saleId);
		
	}

	public List<AccessorySales> getAllDetails() {
		// TODO Auto-generated method stub
		return accessorySalesDao.getAllDetails();
	}

	public float getAllProfit() {
		// TODO Auto-generated method stub
		return accessorySalesDao.getAllProfit();
	}

	public List<AccessorySales> getSalesByRange(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return accessorySalesDao.getSalesByRange(fromDate,toDate);
	}

}
