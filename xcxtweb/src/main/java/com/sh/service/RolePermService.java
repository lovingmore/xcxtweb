package com.sh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sh.dao.RolePermDao;
import com.sh.entity.RolePerm;

@Service("rolePermService")
public class RolePermService{
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Resource
	private RolePermDao rolePermDao;

	public RolePerm get(Integer id) {
		return this.rolePermDao.get(getCurrentSession(), id);
	}
	
	public List<RolePerm> getByRoleId(Integer roleId) {
		return this.rolePermDao.getByRoleId(getCurrentSession(), roleId);
	}

	public List<RolePerm> findAll() {
		return this.rolePermDao.findAll(getCurrentSession());
	}
	
	public int count(Map<String, Object> map) {
		return this.rolePermDao.count(getCurrentSession(), map);
	}

	public void save(RolePerm entity) {
		this.rolePermDao.save(getCurrentSession(), entity);
	}
	
	public void saveList(Integer id, List<RolePerm> list) {
		deleteByRoleId(id);
		for(RolePerm rp : list){
			save(rp);
		}
	}

	public void update(RolePerm entity) {
		this.rolePermDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.rolePermDao.delete(getCurrentSession(), id);
	}
	
	public void deleteByRoleId(Integer id) {
		this.rolePermDao.deleteByRoleId(getCurrentSession(), id);
	}

}
