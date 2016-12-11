package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.FoodInfo;

@Repository("foodInfoDao")
public class FoodInfoDao extends BaseDao{
	
	public FoodInfo get(Session session, Integer id) {
		String hql = "from FoodInfo where id=?";
		List<FoodInfo> list = this.findByHql(session, hql, id);
		if(list!=null && list.size()>0){
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
	
	public List<FoodInfo> listByAllCategory(Session session, Integer categoryId, String foodInfoName) {
		String nameSql = "";
		if(!StringUtils.isEmpty(foodInfoName)){
			nameSql = " and fi.name like '%"+foodInfoName+"%' ";
		}
		String categorySql = "";
		if(categoryId!=null){
			categorySql = " and (fc.id=? or fc.parentId=?) ";
		}
		String hql = "select fi from FoodInfo fi,FoodCategory fc where fi.categoryId=fc.id and fi.status=0 "+categorySql+nameSql;
		if(!StringUtils.isEmpty(categorySql)){
			return this.findByHql(session, hql,categoryId,categoryId);
		}
		return this.findByHql(session, hql);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("searchKey")!=null){
				sb.append(" and name like '%").append(map.get("searchKey")).append("%'");
			}
			if(map.get("categoryId")!=null){
				sb.append(" and categoryId=").append(map.get("categoryId"));
			}
		}
		return sb.toString();
	}

}
