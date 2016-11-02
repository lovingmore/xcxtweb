package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.BuyPrice;

@Repository("buyPriceDao")
public class BuyPriceDao extends BaseDao{
	
	public BuyPrice get(Session session, Integer id) {
		String hql = "from BuyPrice where id=?";
		List<BuyPrice> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}
	public List<BuyPrice> getByDate(Session session, String buyDate) {
		String hql = "from BuyPrice where buyDate='"+buyDate+"'";
		return this.findByHql(session, hql);
	}

	public List<BuyPrice> findAll(Session session) {
		String hql = "from BuyPrice";
		return this.findByHql(session, hql);
	}
	
	public List<BuyPrice> list(Session session, Map<String, Object> map) {
		String hql = "from BuyPrice ";
		hql += getHql(map);
		return this.findByHql(session, hql);
	}
	
	public List<BuyPrice> list(Session session, Map<String, Object> map, int startPage, int pageSize) {
		String hql = "from BuyPrice ";
		hql += getHql(map);
		return this.findByHqlPage(session, hql, startPage, pageSize);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from BuyPrice ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, BuyPrice entity) {
		super.save(session, entity);
	}

	public void update(Session session, BuyPrice entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete BuyPrice where id=?";
		this.executeHql(session, hql, id);
	}
	
	public void deleteByBuyDate(Session session, String buyDate) {
		String hql = "delete BuyPrice where buyDate='"+buyDate+"'";
		this.executeHql(session, hql);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("searchKey")!=null && !"".equals(map.get("searchKey"))){
				sb.append(" and foodName like '%").append(map.get("searchKey")).append("%'");
			}
			if(map.get("buyDate")!=null && !"".equals(map.get("buyDate"))){
				sb.append(" and buyDate = '").append(map.get("buyDate")).append("'");
			}
		}
		return sb.toString();
	}

}
