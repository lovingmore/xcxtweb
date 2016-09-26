package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.FoodInfo;

@Repository("foodInfoDao")
public class FoodInfoDao extends BaseDao{
	
	public FoodInfo get(Session session, Integer id) {
		String hql = "from FoodInfo where id=?";
		List<FoodInfo> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	public List<FoodInfo> findAll(Session session) {
		String hql = "from FoodInfo";
		return this.findByHql(session, hql);
	}
	
	public List<FoodInfo> list(Session session, Map<String, Object> map) {
		String hql = "from FoodInfo ";
		hql += getHql(map);
		hql += " order by id desc";
		return this.findByHql(session, hql);
	}
	
	public List<FoodInfo> list(Session session, Map<String, Object> map, int startPage, int pageSize) {
		String hql = "from FoodInfo ";
		hql += getHql(map);
		hql += " order by id desc";
		return this.findByHqlPage(session, hql, startPage, pageSize);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from FoodInfo ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, FoodInfo entity) {
		super.save(session, entity);
	}

	public void update(Session session, FoodInfo entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete FoodInfo where id=?";
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
