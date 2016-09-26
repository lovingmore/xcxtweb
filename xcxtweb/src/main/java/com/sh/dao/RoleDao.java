package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.Role;

@Repository("roleDao")
public class RoleDao extends BaseDao{
	
	public Role get(Session session, Integer id) {
		String hql = "from Role where id=?";
		List<Role> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	public List<Role> findAll(Session session) {
		String hql = "from Role";
		return this.findByHql(session, hql);
	}
	
	public List<Role> list(Session session, Map<String, Object> map) {
		String hql = "from Role ";
		hql += getHql(map);
		hql += " order by createTime desc";
		return this.findByHql(session, hql);
	}
	
	public List<Role> list(Session session, Map<String, Object> map, int startPage, int pageSize) {
		String hql = "from Role ";
		hql += getHql(map);
		hql += " order by createTime desc";
		return this.findByHqlPage(session, hql, startPage, pageSize);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from Role ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, Role entity) {
		super.save(session, entity);
	}

	public void update(Session session, Role entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete Role where id=?";
		this.executeHql(session, hql, id);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("searchKey")!=null){
				sb.append(" and name like '%").append(map.get("searchKey")).append("%'");
			}
		}
		return sb.toString();
	}

}
