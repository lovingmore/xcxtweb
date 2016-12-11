package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.Coupon;

@Repository("couponDao")
public class CouponDao extends BaseDao{
	
	public Coupon get(Session session, Integer id) {
		String hql = "from Coupon where id=?";
		List<Coupon> list = this.findByHql(session, hql, id);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<Coupon> findAll(Session session) {
		String hql = "from Coupon";
		return this.findByHql(session, hql);
	}
	
	public List<Coupon> list(Session session, Map<String, Object> map) {
		String hql = "from Coupon ";
		hql += getHql(map);
		hql += " order by createTime desc";
		return this.findByHql(session, hql);
	}
	
	public List<Coupon> list(Session session, Map<String, Object> map, int startPage, int pageSize) {
		String hql = "from Coupon ";
		hql += getHql(map);
		hql += " order by createTime desc";
		return this.findByHqlPage(session, hql, startPage, pageSize);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from Coupon ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, Coupon entity) {
		super.save(session, entity);
	}

	public void update(Session session, Coupon entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete Coupon where id=?";
		this.executeHql(session, hql, id);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("searchKey")!=null){
				sb.append(" and name like '%").append(map.get("searchKey")).append("%'");
			}
			if(map.get("dr")!=null){
				sb.append(" and dr=").append(map.get("dr"));
			}
		}
		return sb.toString();
	}

}
