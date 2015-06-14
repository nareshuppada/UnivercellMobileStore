package com.univercellmobiles.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.univercellmobiles.app.beans.PhoneStock;
import com.univercellmobiles.app.dao.PhoneStockDao;

@Service("phoneStockService")
@Transactional
public class PhoneStockServiceImpl implements PhoneStockService{
	
	@Autowired
	PhoneStockDao phoneStockDao;

	public void add(PhoneStock phoneStock) {
		// TODO Auto-generated method stub
		phoneStockDao.add(phoneStock);
		
		
	}

	public void update(PhoneStock phoneStock) {
		// TODO Auto-generated method stub
		phoneStockDao.update(phoneStock);
		
	}

	public PhoneStock getByPhoneStockId(int phStockId) {
		// TODO Auto-generated method stub
		return phoneStockDao.getByPhoneStockId(phStockId);
	}

	public void delete(int phStockId) {
		// TODO Auto-generated method stub
		phoneStockDao.delete(phStockId);
		
	}
	
	public int sellStock(int phStockId) {
		// TODO Auto-generated method stub
		return phoneStockDao.sellStock(phStockId);
		
	}
	
	

	public List<PhoneStock> getAllDetails() {
		// TODO Auto-generated method stub
		return phoneStockDao.getAllDetails();
	}

	public List<PhoneStock> getAllAvailable() {
		// TODO Auto-generated method stub
		return phoneStockDao.getAllAvailable();	}

	public float getCurrentStockValue() {
		// TODO Auto-generated method stub
		return phoneStockDao.getCurrentStockValue();
	}

	public List<PhoneStock> getPurchaseByRange(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return phoneStockDao.getPurchaseByRange(fromDate, toDate);
	}

	public float get30DaysPurchase() {
		// TODO Auto-generated method stub
		return phoneStockDao.get30DaysPurchase();
	}

}
