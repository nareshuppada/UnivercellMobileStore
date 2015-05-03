package com.univercellmobiles.app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.univercellmobiles.app.beans.Brand;
import com.univercellmobiles.app.beans.PhoneModel;

@Repository("phoneModel")
public class PhoneModelDaoImpl implements PhoneModelDao{
	@Autowired 
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


	public void add(PhoneModel phoneModel) {
		// TODO Auto-generated method stub
		getCurrentSession().save(phoneModel);
	}

	public void update(PhoneModel phoneModel) {
		// TODO Auto-generated method stub
		getCurrentSession().update(phoneModel);
		
	}

	public PhoneModel getByModelName(String modelName) {
		// TODO Auto-generated method stub
		PhoneModel phoneModel = (PhoneModel) getCurrentSession().get(PhoneModel.class, modelName);
		return phoneModel;
	}

	public void delete(String modelName) {
		// TODO Auto-generated method stub
		PhoneModel phoneModel = getByModelName(modelName);
		if(phoneModel != null)
			getCurrentSession().delete(phoneModel);
		
	}

	@SuppressWarnings("unchecked")
	public List<PhoneModel> getAllDetails() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from PhoneModel").list();
	}
	public List<String> getAllModelNames() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select modelName from PhoneModel").list();
	}
	public PhoneModel getByModelId(int modelId) {
		// TODO Auto-generated method stub
		PhoneModel model = (PhoneModel) getCurrentSession().get(PhoneModel.class, modelId);
		return model;
	}
	public void delete(int modelId) {
		PhoneModel phoneModel = getByModelId(modelId);
		if(phoneModel != null)
			getCurrentSession().delete(phoneModel);
		
		
	}

}
