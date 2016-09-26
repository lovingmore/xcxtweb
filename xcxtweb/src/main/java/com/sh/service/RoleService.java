package com.sh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sh.dao.RoleDao;
import com.sh.entity.Role;

@Service("roleService")
public class RoleService{
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Resource
	private RoleDao roleDao;

	public Role get(Integer id) {
		return this.roleDao.get(getCurrentSession(), id);
	}

	public List<Role> findAll() {
		return this.roleDao.findAll(getCurrentSession());
	}
	
	public List<Role> list(Map<String, Object> map){
		return this.roleDao.list(getCurrentSession(), map);
	}
	
	public List<Role> list(Map<String, Object> map, int startPage, int pageSize){
		return this.roleDao.list(getCurrentSession(), map,startPage,pageSize);
	}
	
	public int count(Map<String, Object> map){
		return this.roleDao.count(getCurrentSession(), map);
	}

	public void save(Role entity) {
		this.roleDao.save(getCurrentSession(), entity);
	}

	public void update(Role entity) {
		this.roleDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.roleDao.delete(getCurrentSession(), id);
	}

}
