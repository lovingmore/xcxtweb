package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.OrderDetail;



@Repository("orderdetailDao")
public class OrderDetailDao extends BaseDao{
	
	public OrderDetail get(Session session, Integer id) {
		String hql = "from OrderDetail where id=?";
		List<OrderDetail> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}
	
	public List<OrderDetail> listByOrderId(Session session, Integer orderId) {
		String hql = "from OrderDetail where OrderId=?";
		return this.findByHql(session, hql, orderId);
	}

	public List<OrderDetail> findAll(Session session) {
		String hql = "from OrderDetail";
		return this.findByHql(session, hql);
	}
	
	public List<OrderDetail> list(Session session, Map<String, Object> map) {
		String hql = "from OrderDetail f ";
		hql += getHql(map);
		return this.findByHql(session, hql);
	}
	
	public List<OrderDetail> list(Session session, Map<String, Object> map, int startPage, int endPage) {
		String hql = "from OrderDetail f ";
		hql += getHql(map);
		hql += " order by id";
		return this.findByHqlPage(session, hql, startPage, endPage);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from OrderDetail f ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, OrderDetail entity) {
		super.save(session, entity);
	}

	public void update(Session session, OrderDetail entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete OrderDetail where id=?";
		this.executeHql(session, hql, id);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			String searchKey = (String)map.get("searchKey");
			if(!StringUtils.isEmpty(searchKey)){
				sb.append(" and OrderId like '%").append(map.get("searchKey")).append("%'");
			}
		}
		return sb.toString();
	}

}
