package com.univercellmobiles.app.dao;

import java.util.List;

import com.univercellmobiles.app.beans.Customer;

public interface CustomerDao {
	
	public void add(Customer customer);
    public void update(Customer customer);
    public Customer getByCustomerId(String custId);
    public void delete(String custId);
    public List<Customer>    getAllDetails();

}
