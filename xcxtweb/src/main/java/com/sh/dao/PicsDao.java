package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.Pics;

@Repository("picsDao")
public class PicsDao extends BaseDao{
	
	public Pics get(Session session, Integer id) {
		String hql = "from Pics where id=?";
		List<Pics> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	public List<Pics> findAll(Session session) {
		String hql = "from Pics";
		return this.findByHql(session, hql);
	}
	
	public List<Pics> list(Session session, Map<String, Object> map) {
		String hql = "from Pics ";
		hql += getHql(map);
		hql += " order by createTime desc";
		return this.findByHql(session, hql);
	}
	
	public List<Pics> list(Session session, Map<String, Object> map, int startPage, int pageSize) {
		String hql = "from Pics ";
		hql += getHql(map);
		hql += " order by createTime desc";
		return this.findByHqlPage(session, hql, startPage, pageSize);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from Pics ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, Pics entity) {
		super.save(session, entity);
	}

	public void update(Session session, Pics entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete Pics where id=?";
		this.executeHql(session, hql, id);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("searchKey")!=null){
				sb.append(" and title like '%").append(map.get("searchKey")).append("%'");
			}
		}
		return sb.toString();
	}

}
