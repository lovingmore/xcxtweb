package com.sh.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.UserDao;
import com.sh.entity.User;
import com.sh.util.PasswordUtil;

@Service("userService")
public class UserService{
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Resource
	private UserDao userDao;

	public User get(Integer id) {
		return this.userDao.get(getCurrentSession(), id);
	}
	
	public User getByUsername(String name){
		return this.userDao.getByUsername(getCurrentSession(), name);
	}

	public List<User> findAll() {
		return this.userDao.findAll(getCurrentSession());
	}
	
	public List<User> list(Map<String, Object> map){
		return this.userDao.list(getCurrentSession(), map);
	}
	
	public List<User> list(Map<String, Object> map, int startPage, int pageSize){
		return this.userDao.list(getCurrentSession(), map,startPage,pageSize);
	}
	
	public int count(Map<String, Object> map){
		return this.userDao.count(getCurrentSession(), map);
	}

	public void save(User entity) {
		this.userDao.save(getCurrentSession(), entity);
	}

	public void update(User entity) {
		this.userDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.userDao.delete(getCurrentSession(), id);
	}
	
	public User checkLogin(String username, String password) {
		return this.userDao.checkLogin(getCurrentSession(), username, PasswordUtil.encryt(password), 0);
	}

	public User checkLoginForPhone(String username, String password) {
		return this.userDao.checkLogin(getCurrentSession(), username, PasswordUtil.encryt(password), 1);
	}
	
}
