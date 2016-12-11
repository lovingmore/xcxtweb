package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.Menu;

@Repository("menuDao")
public class MenuDao extends BaseDao{
	
	public Menu get(Session session, Integer id) {
		String hql = "from Menu where id=?";
		List<Menu> list = this.findByHql(session, hql, id);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<Menu> findAll(Session session) {
		String hql = "from Menu";
		return this.findByHql(session, hql);
	}
	
	public List<Menu> list(Session session, Map<String, Object> map) {
		String hql = "from Menu m ";
		hql += getHql(map);
		hql += " order by listno";
		return this.findByHql(session, hql);
	}
	
	public List<Menu> list(Session session, Map<String, Object> map, int startPage, int endPage) {
		String hql = "from Menu m ";
		hql += getHql(map);
		hql += " order by createTime";
		return this.findByHqlPage(session, hql, startPage, endPage);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from Menu m ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, Menu entity) {
		super.save(session, entity);
	}

	public void update(Session session, Menu entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete Menu where id=?";
		this.executeHql(session, hql, id);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("parentId")!=null){
				sb.append(" and m.parentId=").append(map.get("parentId"));
			}
			if(map.get("searchKey")!=null){
				sb.append(" and name like '%").append(map.get("searchKey")).append("%'");
			}
			if(map.get("userId")!=null){
				sb.append(" and EXISTS(select 1 from RolePerm rp,Role r,User u where rp.roleId=u.roleId and rp.menuId=m.id and u.id=")
				.append(map.get("userId")).append(")");
			}
		}
		return sb.toString();
	}

}
