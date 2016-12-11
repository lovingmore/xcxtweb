package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.FoodCategory;

@Repository("foodCategoryDao")
public class FoodCategoryDao extends BaseDao{
	
	public FoodCategory get(Session session, Integer id) {
		String hql = "from FoodCategory where id=?";
		List<FoodCategory> list = this.findByHql(session, hql, id);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<FoodCategory> findAll(Session session) {
		String hql = "from FoodCategory";
		return this.findByHql(session, hql);
	}
	
	public List<FoodCategory> list(Session session, Map<String, Object> map) {
		String hql = "from FoodCategory f ";
		hql += getHql(map);
		return this.findByHql(session, hql);
	}
	
	public List<FoodCategory> list(Session session, Map<String, Object> map, int startPage, int endPage) {
		String hql = "from FoodCategory f ";
		hql += getHql(map);
		hql += " order by id";
		return this.findByHqlPage(session, hql, startPage, endPage);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from FoodCategory f ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, FoodCategory entity) {
		super.save(session, entity);
	}

	public void update(Session session, FoodCategory entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete FoodCategory where id=?";
		this.executeHql(session, hql, id);
	}
	
	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("parentId")!=null){
				sb.append(" and f.parentId=").append(map.get("parentId"));
			}
			String searchKey = (String)map.get("searchKey");
			if(!StringUtils.isEmpty(searchKey)){
				sb.append(" and name like '%").append(map.get("searchKey")).append("%'");
			}
			if(map.get("status")!=null){
				sb.append(" and status=").append(map.get("status"));
			}
		}
		return sb.toString();
	}

}
