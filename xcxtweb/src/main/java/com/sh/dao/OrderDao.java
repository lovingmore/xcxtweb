package com.sh.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.sh.entity.Order;
import com.sh.vo.PurchaseOrderVo;



@Repository("orderDao")
public class OrderDao extends BaseDao{
	
	public Order get(Session session, Integer id) {
		String hql = "from Order where id=?";
		List<Order> list = this.findByHql(session, hql, id);
		if(list!=null){
			return list.get(0);
		}
		return null;
	}

	public List<Order> findAll(Session session) {
		String hql = "from Order";
		return this.findByHql(session, hql);
	}
	
	public List<Order> list(Session session, Map<String, Object> map) {
		String hql = "from Order ";
		hql += getHql(map);
		hql += " order by id desc";
		return this.findByHql(session, hql);
	}
	
	public List<Order> list(Session session, Map<String, Object> map, int startPage, int pageSize) {
		String hql = "from Order ";
		hql += getHql(map);
		hql += " order by id desc";
		return this.findByHqlPage(session, hql, startPage, pageSize);
	}
	
	public int count(Session session, Map<String, Object> map) {
		String hql = "select count(*) from Order ";
		hql += getHql(map);
		return this.getTotalRowsByHql(session, hql);
	}

	public void save(Session session, Order entity) {
		super.save(session, entity);
	}

	public void update(Session session, Order entity) {
		super.update(session, entity);
	}

	public void delete(Session session, Integer id) {
		String hql = "delete Order where id=?";
		this.executeHql(session, hql, id);
	}
	
	public int countPurchaseOrderByDate(Session session, String date){
		String dateSql = "";
		if(!StringUtils.isEmpty(date)){
			dateSql += " where o.orderdate>='"+date+" 00:00:00' and o.orderdate<='"+date+" 23:59:59'";
		}
		String sql = "select count(*) from xcxt.order o INNER JOIN orderdetail od on od.orderid=o.id "
				+ "LEFT JOIN food_info f on f.id=od.foodinfoid "+dateSql+" group by od.foodinfoid order by o.orderdate desc";
		return this.getTotalRowsBySql(session, sql);
	}
	
	public List<Object[]> findPurchaseOrderByDate(Session session, String date){
		String dateSql = "";
		if(!StringUtils.isEmpty(date)){
			dateSql += " where o.orderdate>='"+date+" 00:00:00' and o.orderdate<='"+date+" 23:59:59'";
		}
		String sql = "select od.orderid, od.foodnum, od.foodprice,o.orderdate, f.name as foodInfoname, SUM(od.discountamount) as discountamount, SUM(od.foodnum*od.foodprice) as totalfoodamount,o.Purchaser,f.norms,bp.price as foodPurchasePrice,SUM(od.foodnum * bp.price) AS totalpurchaseamount from xcxt.order o INNER JOIN orderdetail od on od.orderid=o.id "
				+ "LEFT JOIN food_info f on f.id=od.foodinfoid LEFT JOIN buy_price bp on bp.food_id = od.foodinfoid "+dateSql+" group by od.foodinfoid order by o.orderdate desc";
		return this.queryBySql(session, sql);
	}
	
	public List<Object[]> findPurchaseOrderByDate(Session session, String date, int startPage, int pageSize){
		String dateSql = "";
		if(!StringUtils.isEmpty(date)){
			dateSql += " where o.orderdate>='"+date+" 00:00:00' and o.orderdate<='"+date+" 23:59:59'";
		}
		String sql = "select od.orderid, od.foodnum, od.foodprice,o.orderdate, f.name as foodInfoname, SUM(od.discountamount) as discountamount, SUM(od.foodnum*od.foodprice) as totalfoodamount,o.Purchaser,f.norms,bp.price as foodPurchasePrice,SUM(od.foodnum * bp.price) AS totalpurchaseamount from xcxt.order o INNER JOIN orderdetail od on od.orderid=o.id "
				+ "LEFT JOIN food_info f on f.id=od.foodinfoid LEFT JOIN buy_price bp on bp.food_id = od.foodinfoid "+dateSql+" group by od.foodinfoid order by o.orderdate desc";
		return this.findBySqlPage(session, sql, startPage, pageSize);
	}
	
	public int countPurchaseOrderByMap(Session session, Map<String, Object> map){
		String whereSql = "";
		if(map!=null){
			whereSql += " where 1=1 ";
			if(map.get("startDate")!=null && !"".equals(map.get("startDate"))){
				whereSql += " and o.orderdate>='"+map.get("startDate")+" 00:00:00'";
			}
			if(map.get("endDate")!=null && !"".equals(map.get("endDate"))){
				whereSql += " and o.orderdate<='"+map.get("endDate")+" 23:59:59'";
			}
			if(map.get("purchaser")!=null && !"".equals(map.get("purchaser"))){
				whereSql += " and o.purchaser like '%"+map.get("purchaser")+"%'";
			}
			if(map.get("foodCategoryId")!=null){
				whereSql += " and f.category_id ="+map.get("foodCategoryId");
			}
		}
		String sql = "select count(*) from xcxt.order o INNER JOIN orderdetail od on od.orderid=o.id "
				+ "LEFT JOIN food_info f on f.id=od.foodinfoid "+whereSql+" group by od.foodinfoid order by o.orderdate desc";
		return this.getTotalRowsBySql(session, sql);
	}
	
	public List<Object[]> findPurchaseOrderByMap(Session session, Map<String, Object> map){
		String whereSql = "";
		if(map!=null){
			whereSql += " where 1=1 ";
			if(map.get("startDate")!=null && !"".equals(map.get("startDate"))){
				whereSql += " and o.orderdate>='"+map.get("startDate")+" 00:00:00'";
			}
			if(map.get("endDate")!=null && !"".equals(map.get("endDate"))){
				whereSql += " and o.orderdate<='"+map.get("endDate")+" 23:59:59'";
			}
			if(map.get("purchaser")!=null && !"".equals(map.get("purchaser"))){
				whereSql += " and o.purchaser like '%"+map.get("purchaser")+"%'";
			}
			if(map.get("foodCategoryId")!=null){
				whereSql += " and f.category_id ="+map.get("foodCategoryId");
			}
		}
		String sql = "select od.orderid, od.foodnum, od.foodprice,o.orderdate, f.name as foodInfoname, SUM(od.discountamount) as discountamount, SUM(od.foodnum*od.foodprice) as totalfoodamount,o.Purchaser,f.norms,bp.price as foodPurchasePrice,SUM(od.foodnum * bp.price) AS totalpurchaseamount from xcxt.order o INNER JOIN orderdetail od on od.orderid=o.id "
				+ "LEFT JOIN food_info f on f.id=od.foodinfoid LEFT JOIN buy_price bp on bp.food_id = od.foodinfoid "+whereSql+" group by od.foodinfoid order by o.orderdate desc";
		return this.queryBySql(session, sql);
	}
	
	public List<Object[]> findPurchaseOrderByMap(Session session, Map<String, Object> map, int startPage, int pageSize){
		String whereSql = "";
		if(map!=null){
			whereSql += " where 1=1 ";
			if(map.get("startDate")!=null && !"".equals(map.get("startDate"))){
				whereSql += " and o.orderdate>='"+map.get("startDate")+" 00:00:00'";
			}
			if(map.get("endDate")!=null && !"".equals(map.get("endDate"))){
				whereSql += " and o.orderdate<='"+map.get("endDate")+" 23:59:59'";
			}
			if(map.get("purchaser")!=null && !"".equals(map.get("purchaser"))){
				whereSql += " and o.purchaser like '%"+map.get("purchaser")+"%'";
			}
			if(map.get("foodCategoryId")!=null){
				whereSql += " and f.category_id ="+map.get("foodCategoryId");
			}
		}
		String sql = "select od.orderid, od.foodnum, od.foodprice,o.orderdate, f.name as foodInfoname, SUM(od.discountamount) as discountamount, SUM(od.foodnum*od.foodprice) as totalfoodamount,o.Purchaser,f.norms,bp.price as foodPurchasePrice,SUM(od.foodnum * bp.price) AS totalpurchaseamount from xcxt.order o INNER JOIN orderdetail od on od.orderid=o.id "
				+ "LEFT JOIN food_info f on f.id=od.foodinfoid LEFT JOIN buy_price bp on bp.food_id = od.foodinfoid "+whereSql+" group by od.foodinfoid order by o.orderdate desc";
		return this.findBySqlPage(session, sql, startPage, pageSize);
	}

	private String getHql(Map<String, Object> map){
		StringBuffer sb = new StringBuffer();
		if(map!=null){
			sb.append(" where 1=1");
			if(map.get("searchKey")!=null){
				sb.append(" and Purchaser like '%").append(map.get("searchKey")).append("%'");
			}
			if(map.get("foodName")!=null && !"".equals(map.get("foodName"))){
				
			}
			if(map.get("orderDate")!=null && !"".equals(map.get("orderDate"))){
				sb.append(" and OrderDate>='").append(map.get("orderDate")).append(" 00:00:00'").append(" and OrderDate<='").append(map.get("orderDate")).append(" 23:59:59' ");
			}
		}
		return sb.toString();
	}

}
