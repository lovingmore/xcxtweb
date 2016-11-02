package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.Customer;

@Repository("customerDao")
public class CustomerDao extends BaseDao{
	
	public Customer get(Session session, Integer id) {
		String hql = "from Customer where id=?";
		List<Customer> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	public List<Customer> findAll(Session session) {
		String hql = "from Customer";
		return this.findByHql(session, hql);
	}
	
	public List<Customer> list(Session session, Map<String, Object> map) {
		String hql = "from Customer ";
		hql += getHql(map);
		hql += " order by id desc";
		return this.findByHql(session, hql);
	}
	
	public List<Customer> list(Session session, Map<String, Object> map, int startPage, int pageSize) {
		String hql = "from Customer ";
		hql += getHql(map);
		hql += " order by id desc";
		return this.findByHqlPage(session, hql, startPage, pageSize);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from Customer ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, Customer entity) {
		super.save(session, entity);
	}

	public void update(Session session, Customer entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete Customer where id=?";
		this.executeHql(session, hql, id);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("searchKey")!=null){
				sb.append(" and ( name like '%").append(map.get("searchKey")).append("%'");
				sb.append(" or shopName like '%").append(map.get("searchKey")).append("%'");
				sb.append(" or shopAddr like '%").append(map.get("searchKey")).append("%')");
			}
		}
		return sb.toString();
	}

}
