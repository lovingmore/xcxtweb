package com.sh.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.FoodCategoryDao;
import com.sh.entity.BuyPrice;
import com.sh.entity.FoodCategory;
import com.sh.util.DateUtil;

@Service("foodCategoryService")
public class FoodCategoryService{
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Resource
	private FoodCategoryDao foodCategoryDao;

	public FoodCategory get(Integer id) {
		return this.foodCategoryDao.get(getCurrentSession(), id);
	}

	public List<FoodCategory> findAll() {
		List<FoodCategory> list = this.foodCategoryDao.list(getCurrentSession(), null);
		return list;
	}
	
	public List<FoodCategory> list(Map<String, Object> map){
		return this.foodCategoryDao.list(getCurrentSession(), map);
	}
	
	public List<FoodCategory> list(Map<String, Object> map, int startPage, int endPage){
		return this.foodCategoryDao.list(getCurrentSession(), map,startPage,endPage);
	}
	
	public int count(Map<String, Object> map){
		return this.foodCategoryDao.count(getCurrentSession(), map);
	}

	public void save(FoodCategory entity) {
		this.foodCategoryDao.save(getCurrentSession(), entity);
	}

	public void update(FoodCategory entity) {
		this.foodCategoryDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.foodCategoryDao.delete(getCurrentSession(), id);
	}
	

}
