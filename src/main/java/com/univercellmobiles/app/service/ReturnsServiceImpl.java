package com.univercellmobiles.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univercellmobiles.app.beans.ReturnStock;
import com.univercellmobiles.app.dao.ReturnsDao;

@Service("returnService")
@Transactional
public class ReturnsServiceImpl implements ReturnsService {
	
	@Autowired
	ReturnsDao returnsDao;

	public void add(ReturnStock sales) {
		// TODO Auto-generated method stub
		returnsDao.add(sales);
		
	}

	public void update(ReturnStock sales) {
		// TODO Auto-generated method stub
		returnsDao.update(sales);
		
	}

	public ReturnStock getBySalesId(String saleId) {
		// TODO Auto-generated method stub
		return returnsDao.getBySalesId(saleId);
	}

	public void delete(String saleId) {
		// TODO Auto-generated method stub
		returnsDao.delete(saleId);
		
	}

	public List<ReturnStock> getAllDetails() {
		// TODO Auto-generated method stub
		return returnsDao.getAllDetails();
	}

	public float getAllReturnsAmount() {
		// TODO Auto-generated method stub
		return returnsDao.getAllReturnsAmount();
	}

	public List<ReturnStock> getSalesByRange(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return returnsDao.getReturnStockByRange(fromDate,toDate);
	}

	public float getTodaysProfit() {
		// TODO Auto-generated method stub
		return returnsDao.getTodaysProfit();
	}

	public float getTodaySale() {
		// TODO Auto-generated method stub
		return returnsDao.getTodaysSale();
	}

	public List<ReturnStock> getAllAvailable() {
		// TODO Auto-generated method stub
		return returnsDao.getAllAvailable();
	}

	public void resolveStock(int i) {
		// TODO Auto-generated method stub
		returnsDao.resolveStock(i) ;
		
	}



}
