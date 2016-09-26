package com.sh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.FoodInfoDao;
import com.sh.entity.FoodInfo;
import com.sh.entity.FoodInfo;

@Service("FoodInfoService")
public class FoodInfoService{
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Resource
	private FoodInfoDao foodInfoDao;

	public FoodInfo get(Integer id) {
		return this.foodInfoDao.get(getCurrentSession(), id);
	}

	public List<FoodInfo> findAll() {
		return this.foodInfoDao.findAll(getCurrentSession());
	}
	
	public List<FoodInfo> list(Map<String, Object> map){
		return this.foodInfoDao.list(getCurrentSession(), map);
	}
	
	public List<FoodInfo> list(Map<String, Object> map, int startPage, int pageSize){
		return this.foodInfoDao.list(getCurrentSession(), map,startPage,pageSize);
	}
	
	public int count(Map<String, Object> map){
		return this.foodInfoDao.count(getCurrentSession(), map);
	}

	public void save(FoodInfo entity) {
		this.foodInfoDao.save(getCurrentSession(), entity);
	}

	public void update(FoodInfo entity) {
		this.foodInfoDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.foodInfoDao.delete(getCurrentSession(), id);
	}

}
