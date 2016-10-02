package com.sh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.PicsDao;
import com.sh.entity.Pics;

@Service("picsService")
public class PicsService{
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Resource
	private PicsDao picsDao;

	public Pics get(Integer id) {
		return this.picsDao.get(getCurrentSession(), id);
	}

	public List<Pics> findAll() {
		return this.picsDao.findAll(getCurrentSession());
	}
	
	public List<Pics> list(Map<String, Object> map){
		return this.picsDao.list(getCurrentSession(), map);
	}
	
	public List<Pics> list(Map<String, Object> map, int startPage, int pageSize){
		return this.picsDao.list(getCurrentSession(), map,startPage,pageSize);
	}
	
	public int count(Map<String, Object> map){
		return this.picsDao.count(getCurrentSession(), map);
	}

	public void save(Pics entity) {
		this.picsDao.save(getCurrentSession(), entity);
	}

	public void update(Pics entity) {
		this.picsDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.picsDao.delete(getCurrentSession(), id);
	}

}
