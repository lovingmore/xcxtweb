package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.RolePerm;

@Repository("rolePermDao")
public class RolePermDao extends BaseDao{

	public RolePerm get(Session session, Integer id) {
		String hql = "from RolePerm where id=?";
		List<RolePerm> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}
	
	public List<RolePerm> getByRoleId(Session session, Integer roleId) {
		String hql = "from RolePerm where roleId=?";
		List<RolePerm> list = this.findByHql(session, hql, roleId);
		return list;
	}

	public List<RolePerm> findAll(Session session) {
		String hql = "from RolePerm";
		return this.findByHql(session, hql);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from RolePerm ";
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, RolePerm entity) {
		super.save(session, entity);
	}

	public void update(Session session, RolePerm entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete RolePerm where id=?";
		this.executeHql(session, hql, id);
	}
	
	public void deleteByRoleId(Session session, Integer id) {
		String hql = "delete RolePerm where roleId=?";
		this.executeHql(session, hql, id);
	}

}
