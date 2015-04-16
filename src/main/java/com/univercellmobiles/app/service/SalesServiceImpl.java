package com.univercellmobiles.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univercellmobiles.app.beans.Sales;
import com.univercellmobiles.app.dao.SalesDao;

@Service("salesService")
@Transactional
public class SalesServiceImpl implements SalesService {
	
	@Autowired
	SalesDao salesDao;

	public void add(Sales sales) {
		// TODO Auto-generated method stub
		salesDao.add(sales);
		
	}

	public void update(Sales sales) {
		// TODO Auto-generated method stub
		salesDao.update(sales);
		
	}

	public Sales getBySalesId(String saleId) {
		// TODO Auto-generated method stub
		return salesDao.getBySalesId(saleId);
	}

	public void delete(String saleId) {
		// TODO Auto-generated method stub
		salesDao.delete(saleId);
		
	}

	public List<Sales> getAllDetails() {
		// TODO Auto-generated method stub
		return salesDao.getAllDetails();
	}

	public float getAllProfit() {
		// TODO Auto-generated method stub
		return salesDao.getAllProfit();
	}

}
