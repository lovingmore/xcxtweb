package com.sh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.OrderDetailDao;
import com.sh.entity.OrderDetail;



@Service("orderdetailService")
public class OrderDetailService{
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Resource
	private OrderDetailDao orderdetailDao;

	public OrderDetail get(Integer id) {
		return this.orderdetailDao.get(getCurrentSession(), id);
	}

	public List<OrderDetail> findAll() {
		List<OrderDetail> list = this.orderdetailDao.list(getCurrentSession(), null);
		return list;
	}
	
	public List<OrderDetail> list(Map<String, Object> map){
		return this.orderdetailDao.list(getCurrentSession(), map);
	}
	
	public List<OrderDetail> list(Map<String, Object> map, int startPage, int endPage){
		return this.orderdetailDao.list(getCurrentSession(), map,startPage,endPage);
	}
	
	public int count(Map<String, Object> map){
		return this.orderdetailDao.count(getCurrentSession(), map);
	}

	public void save(OrderDetail entity) {
		this.orderdetailDao.save(getCurrentSession(), entity);
	}

	public void update(OrderDetail entity) {
		this.orderdetailDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.orderdetailDao.delete(getCurrentSession(), id);
	}

}
