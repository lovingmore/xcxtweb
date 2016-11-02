package com.sh.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.OrderDao;
import com.sh.dao.OrderDetailDao;
import com.sh.entity.Order;
import com.sh.vo.PurchaseOrderVo;



@Service("OrderService")
public class OrderService{
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Order get(Integer id) {
		return this.orderDao.get(getCurrentSession(), id);
	}

	public List<Order> findAll() {
		return this.orderDao.findAll(getCurrentSession());
	}
	
	public List<Order> list(Map<String, Object> map){
		return this.orderDao.list(getCurrentSession(), map);
	}
	
	public List<Order> list(Map<String, Object> map, int startPage, int pageSize){
		return this.orderDao.list(getCurrentSession(), map,startPage,pageSize);
	}
	
	public int count(Map<String, Object> map){
		return this.orderDao.count(getCurrentSession(), map);
	}

	public void save(Order entity) {
		this.orderDao.save(getCurrentSession(), entity);
	}

	public void update(Order entity) {
		this.orderDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.orderDao.delete(getCurrentSession(), id);
	}
	
	public int countPurchaseOrderByDate(String date){
		return this.orderDao.countPurchaseOrderByDate(getCurrentSession(), date);
	}
	
	public List<PurchaseOrderVo> findPurchaseOrderByDate(String date){
		List<Object[]> list = this.orderDao.findPurchaseOrderByDate(getCurrentSession(), date);
		List<PurchaseOrderVo> list_vo = new ArrayList<PurchaseOrderVo>();
		if(list!=null && list.size()>0){
			PurchaseOrderVo vo = null;
			for(int i=0;i<list.size();i++){
				Object[] objs = list.get(i);
				vo = new PurchaseOrderVo();
				vo.setDiscountamount(objs[5]==null?null:(Double)objs[5]);
				vo.setFoodInfoname(objs[4]==null?null:(String)objs[4]);
				vo.setFoodnum(objs[1]==null?null:(Double)objs[1]);
				vo.setFoodprice(objs[2]==null?null:(Double)objs[2]);
				vo.setOrderdate(objs[3]==null?null:(Date)objs[3]);
				vo.setOrderid(objs[0]==null?null:(Integer)objs[0]);
				vo.setTotalfoodamount(objs[6]==null?null:(Double)objs[6]);
				vo.setPurchaser(objs[7]==null?null:(String)objs[7]);
				vo.setNorms(objs[8]==null?null:(String)objs[8]);
				vo.setFoodPurchasePrice(objs[9]==null?null:(Double)objs[9]);
				list_vo.add(vo);
			}
		}
		return list_vo;
	}
	
	public List<PurchaseOrderVo> findPurchaseOrderByDate(String date, int startPage, int pageSize){
		List<Object[]> list = this.orderDao.findPurchaseOrderByDate(getCurrentSession(), date, startPage, pageSize);
		List<PurchaseOrderVo> list_vo = new ArrayList<PurchaseOrderVo>();
		if(list!=null && list.size()>0){
			PurchaseOrderVo vo = null;
			for(int i=0;i<list.size();i++){
				Object[] objs = list.get(i);
				vo = new PurchaseOrderVo();
				vo.setDiscountamount(objs[5]==null?null:(Double)objs[5]);
				vo.setFoodInfoname(objs[4]==null?null:(String)objs[4]);
				vo.setFoodnum(objs[1]==null?null:(Double)objs[1]);
				vo.setFoodprice(objs[2]==null?null:(Double)objs[2]);
				vo.setOrderdate(objs[3]==null?null:(Date)objs[3]);
				vo.setOrderid(objs[0]==null?null:(Integer)objs[0]);
				vo.setTotalfoodamount(objs[6]==null?null:(Double)objs[6]);
				vo.setPurchaser(objs[7]==null?null:(String)objs[7]);
				vo.setNorms(objs[8]==null?null:(String)objs[8]);
				vo.setFoodPurchasePrice(objs[9]==null?null:(Double)objs[9]);
				list_vo.add(vo);
			}
		}
		return list_vo;
	}
	
	public int countPurchaseOrderByMap(Map<String, Object> map){
		return this.orderDao.countPurchaseOrderByMap(getCurrentSession(), map);
	}
	
	public List<PurchaseOrderVo> findPurchaseOrderByMap(Map<String, Object> map){
		List<Object[]> list = this.orderDao.findPurchaseOrderByMap(getCurrentSession(), map);
		List<PurchaseOrderVo> list_vo = new ArrayList<PurchaseOrderVo>();
		if(list!=null && list.size()>0){
			list_vo = new ArrayList<PurchaseOrderVo>();
			PurchaseOrderVo vo = null;
			for(int i=0;i<list.size();i++){
				Object[] objs = list.get(i);
				vo = new PurchaseOrderVo();
				vo.setDiscountamount(objs[5]==null?null:(Double)objs[5]);
				vo.setFoodInfoname(objs[4]==null?null:(String)objs[4]);
				vo.setFoodnum(objs[1]==null?null:(Double)objs[1]);
				vo.setFoodprice(objs[2]==null?null:(Double)objs[2]);
				vo.setOrderdate(objs[3]==null?null:(Date)objs[3]);
				vo.setOrderid(objs[0]==null?null:(Integer)objs[0]);
				vo.setTotalfoodamount(objs[6]==null?null:(Double)objs[6]);
				vo.setPurchaser(objs[7]==null?null:(String)objs[7]);
				vo.setNorms(objs[8]==null?null:(String)objs[8]);
				vo.setFoodPurchasePrice(objs[9]==null?null:(Double)objs[9]);
				list_vo.add(vo);
			}
		}
		return list_vo;
	}
	
	public List<PurchaseOrderVo> findPurchaseOrderByMap(Map<String, Object> map, int startPage, int pageSize){
		List<Object[]> list = this.orderDao.findPurchaseOrderByMap(getCurrentSession(), map, startPage, pageSize);
		List<PurchaseOrderVo> list_vo = new ArrayList<PurchaseOrderVo>();
		if(list!=null && list.size()>0){
			PurchaseOrderVo vo = null;
			for(int i=0;i<list.size();i++){
				Object[] objs = list.get(i);
				vo = new PurchaseOrderVo();
				vo.setDiscountamount(objs[5]==null?null:(Double)objs[5]);
				vo.setFoodInfoname(objs[4]==null?null:(String)objs[4]);
				vo.setFoodnum(objs[1]==null?null:(Double)objs[1]);
				vo.setFoodprice(objs[2]==null?null:(Double)objs[2]);
				vo.setOrderdate(objs[3]==null?null:(Date)objs[3]);
				vo.setOrderid(objs[0]==null?null:(Integer)objs[0]);
				vo.setTotalfoodamount(objs[6]==null?null:(Double)objs[6]);
				vo.setPurchaser(objs[7]==null?null:(String)objs[7]);
				vo.setNorms(objs[8]==null?null:(String)objs[8]);
				vo.setFoodPurchasePrice(objs[9]==null?null:(Double)objs[9]);
				list_vo.add(vo);
			}
		}
		return list_vo;
	}

}
