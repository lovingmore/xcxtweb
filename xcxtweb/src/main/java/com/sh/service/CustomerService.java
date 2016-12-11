package com.sh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.CustomerDao;
import com.sh.entity.Customer;

@Service("CustomerService")
public class CustomerService{
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Resource
	private CustomerDao customerDao;

	public Customer get(Integer id) {
		return this.customerDao.get(getCurrentSession(), id);
	}
	
	public Customer getByUserAccount(String userAccount) {
		return this.customerDao.getByUserAccount(getCurrentSession(), userAccount);
	}

	public List<Customer> findAll() {
		return this.customerDao.findAll(getCurrentSession());
	}
	
	public List<Customer> list(Map<String, Object> map){
		return this.customerDao.list(getCurrentSession(), map);
	}
	
	public List<Customer> list(Map<String, Object> map, int startPage, int pageSize){
		return this.customerDao.list(getCurrentSession(), map,startPage,pageSize);
	}
	
	public int count(Map<String, Object> map){
		return this.customerDao.count(getCurrentSession(), map);
	}

	public void save(Customer entity) {
		this.customerDao.save(getCurrentSession(), entity);
	}

	public void update(Customer entity) {
		this.customerDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.customerDao.delete(getCurrentSession(), id);
	}

}
