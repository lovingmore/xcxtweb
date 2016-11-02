package com.sh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.CouponDao;
import com.sh.entity.Coupon;

@Service("couponService")
public class CouponService{
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private CouponDao couponDao;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Coupon get(Integer id) {
		return this.couponDao.get(getCurrentSession(), id);
	}

	public List<Coupon> findAll() {
		return this.couponDao.findAll(getCurrentSession());
	}
	
	public List<Coupon> list(Map<String, Object> map){
		return this.couponDao.list(getCurrentSession(), map);
	}
	
	public List<Coupon> list(Map<String, Object> map, int startPage, int pageSize){
		return this.couponDao.list(getCurrentSession(), map,startPage,pageSize);
	}
	
	public int count(Map<String, Object> map){
		return this.couponDao.count(getCurrentSession(), map);
	}

	public void save(Coupon entity) {
		this.couponDao.save(getCurrentSession(), entity);
	}

	public void update(Coupon entity) {
		this.couponDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.couponDao.delete(getCurrentSession(), id);
	}

}
