package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.User;

@Repository("userDao")
public class UserDao extends BaseDao{

	public User get(Session session, Integer id) {
		String hql = "from User where id=?";
		List<User> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}
	
	public User getByUsername(Session session, String name) {
		String hql = "from User where username=? and dr!=1";
		List<User> list = this.findByHql(session, hql, name);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	public List<User> findAll(Session session) {
		String hql = "from User";
		return this.findByHql(session, hql);
	}
	
	public List<User> list(Session session, Map<String, Object> map) {
		String hql = "from User ";
		hql += getHql(map);
		hql += " order by createTime desc";
		return this.findByHql(session, hql);
	}
	
	public List<User> list(Session session, Map<String, Object> map, int startPage, int pageSize) {
		String hql = "from User ";
		hql += getHql(map);
		hql += " order by createTime desc";
		return this.findByHqlPage(session, hql, startPage, pageSize);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from User ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, User entity) {
		super.save(session, entity);
	}

	public void update(Session session, User entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "update User set dr=1 where id=?";
		this.executeHql(session, hql, id);
	}
	
	public User checkLogin(Session session, String username, String password, Integer userType){
		String hql = "from User where username=? and password=? and userType=?";
		List<User> list = this.findByHql(session, hql, username, password, userType);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1 and dr=0 ");
			String searchKey = (String)map.get("searchKey");
			if(!StringUtils.isEmpty(searchKey)){
				sb.append(" and username like '%").append(map.get("searchKey")).append("%'")
				.append(" or name like '%").append(map.get("searchKey")).append("%'");
			}
		}
		return sb.toString();
	}

}
