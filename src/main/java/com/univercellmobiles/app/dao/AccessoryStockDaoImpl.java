package com.univercellmobiles.app.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.univercellmobiles.app.beans.AccessoryStock;


@Repository("accessoryStockDao")
public class AccessoryStockDaoImpl implements AccessoryStockDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(AccessoryStock accessoryStock) {
		// TODO Auto-generated method stub
		getCurrentSession().save(accessoryStock);
		
	}

	public void update(AccessoryStock accessoryStock) {
		// TODO Auto-generated method stub
		//AccessoryStock accessoryStockFromDb = getByAccStockId(accessoryStock.getAccStockId());
		getCurrentSession().update(accessoryStock);
		
	}

	public AccessoryStock getByAccStockId(int accStockId) {
		// TODO Auto-generated method stub
		AccessoryStock acessoryStock = (AccessoryStock) getCurrentSession().get(AccessoryStock.class, accStockId);
		return acessoryStock;
	}

	public void delete(int accStockId) {
		// TODO Auto-generated method stub
		AccessoryStock accessoryStock = getByAccStockId(accStockId);
		if(accessoryStock != null)
			getCurrentSession().delete(accessoryStock);
		
	}

	@SuppressWarnings("unchecked")
	public List<AccessoryStock> getAllDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from AccessoryStock").list();
	}

	@SuppressWarnings("unchecked")
	public List<AccessoryStock> getAllAvailable() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from AccessoryStock where quantity>0").list();
	}

	public float getCurrentStockValue() {
		// TODO Auto-generated method stub
		return Float.parseFloat(getCurrentSession().createQuery("select sum(dp) from AccessoryStock").list().get(0).toString());
	}

	public int sellStock(int accStockId) {
		// TODO Auto-generated method stub
		AccessoryStock accessoryStock = getByAccStockId(accStockId);
		if (accessoryStock != null){
			int qty =accessoryStock.getQuantity();
			if(qty>0){
				accessoryStock.setQuantity(qty-1);
			}
			getCurrentSession().update(accessoryStock);
		}
		return 0;
	}

	@SuppressWarnings("unchecked")
	public List<AccessoryStock> getPurchaseByRange(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 //between str_to_date('2015-03-16','%Y-%m-%d') and  str_to_date('2015-04-16','%Y-%m-%d');
				String query = "from AccessoryStock where arrivalDate between str_to_date('"+sdf.format(fromDate)+"','%Y-%m-%d') and  str_to_date('"+sdf.format(toDate)+"','%Y-%m-%d')";
			//	System.out.println(query);
				return getCurrentSession().createQuery(query).list();
	}

	

		

}
