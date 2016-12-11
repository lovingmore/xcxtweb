package com.sh.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.dao.CouponDao;
import com.sh.dao.CustomerDao;
import com.sh.dao.FoodInfoDao;
import com.sh.dao.OrderDao;
import com.sh.dao.OrderDetailDao;
import com.sh.dao.ShopCartDao;
import com.sh.entity.Coupon;
import com.sh.entity.Customer;
import com.sh.entity.FoodInfo;
import com.sh.entity.Order;
import com.sh.entity.OrderDetail;
import com.sh.entity.ShopCart;
import com.sh.util.CodeUtil;
import com.sh.vo.OrderDetailVo;
import com.sh.vo.PurchaseOrderVo;
import com.sh.vo.phone.OrderPhoneVo;



@Service("OrderService")
public class OrderService{
	@Resource
	private SessionFactory sessionFactory;
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private FoodInfoDao foodInfoDao;
	@Autowired
	private ShopCartDao shopCartDao;
	@Autowired
	private CouponDao couponDao;
	
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
	
	/**
	 * 保存用户订单
	 * @param userId
	 * @param foodInfoIds
	 * @param foodCounts
	 * @param customerId
	 */
	public void saveOrderForPhone(Integer userId, Integer[] foodInfoIds, 
			Integer[] foodCounts, Integer customerId, Integer couponId){
		Order order = new Order();
		Customer customer = customerDao.get(getCurrentSession(), customerId);
		if(customer!=null){
			order.setMobile(customer.getUserAccount());
			order.setOrderDate(new Date());
			order.setOrderNo(CodeUtil.getOrderId());
			order.setOrderStatus(0);
			order.setPurchaser(customer.getShopName());
			order.setRecAddress(customer.getShopAddr());
			order.setUserId(userId);
			if(couponId!=null && couponId!=0){
				Coupon coupon = this.couponDao.get(getCurrentSession(), couponId);
				if(coupon!=null){
					order.setCouponId(coupon.getId());
					order.setCouponMoney(coupon.getMoney());
				}
			}
			this.orderDao.save(getCurrentSession(), order);
			OrderDetail orderDetail = null;
			for(int i=0;i<foodInfoIds.length;i++){
				orderDetail = new OrderDetail();
				FoodInfo foodInfo = foodInfoDao.get(getCurrentSession(), foodInfoIds[i]);
				if(foodInfo!=null){
					orderDetail.setFoodInfoId(foodInfo.getId());
					orderDetail.setFoodNum(Double.valueOf(foodCounts[i]));
					orderDetail.setFoodPrice(foodInfo.getPrice());
					orderDetail.setOrderNo(order.getOrderNo());
					orderDetail.setOrderId(order.getId());
					this.orderDetailDao.save(getCurrentSession(), orderDetail);
					//删除购物车
					ShopCart shopCart = this.shopCartDao.getByUserIdFoodId(getCurrentSession(), userId, orderDetail.getFoodInfoId());
					if(shopCart!=null){
						this.shopCartDao.delete(getCurrentSession(), shopCart.getId());
					}
				}
			}
		}
		
	}
	
	public OrderPhoneVo getByOrderNo(Integer orderId){
		OrderPhoneVo vo = new OrderPhoneVo();
		Order order = this.orderDao.get(getCurrentSession(), orderId);
		if(order!=null){
			BeanUtils.copyProperties(order, vo);
//			0 待支付 1 待发货  2 待收货  3 已结束
			String orderStatusStr = "";
			switch (order.getOrderStatus()) {
			case 0:
				orderStatusStr = "待支付";
				break;
			case 1:
				orderStatusStr = "待发货";		
				break;
			case 2:
				orderStatusStr = "待收货";
				break;
			case 3:
				orderStatusStr = "已完成";
				break;
			default:
				break;
			}
			vo.setOrderStatusStr(orderStatusStr);
			List<OrderDetail> details = this.orderDetailDao.listByOrderId(getCurrentSession(), orderId);
			List<OrderDetailVo> vos = new ArrayList<>();
			if(details!=null && details.size()>0){
				DecimalFormat decimal = new DecimalFormat("#.00");
				Double totalOrderAmount = 0d;
				Double totalFoodAmount = 0d;
				OrderDetailVo detailVo = null;
				for(OrderDetail detail : details){
					detailVo = new OrderDetailVo();
					BeanUtils.copyProperties(detail, detailVo);
					FoodInfo foodInfo = this.foodInfoDao.get(getCurrentSession(), detail.getFoodInfoId());
					if(foodInfo!=null){
						detailVo.setFoodFacePic(foodInfo.getFacePic());
						detailVo.setFoodInfoName(foodInfo.getName());
					}
					vos.add(detailVo);
					totalFoodAmount += detail.getFoodPrice()*detail.getFoodNum();
					double discount = 0;
					if(detail.getDiscountAmount()!=null){
						discount = detail.getDiscountAmount();
					}
					totalOrderAmount +=totalFoodAmount-discount;
				}
				vo.setVos(vos);
				vo.setTotalFoodAmount(Double.valueOf(decimal.format(totalFoodAmount)));
				vo.setTotalOrderAmount(Double.valueOf(decimal.format(totalOrderAmount-vo.getCouponMoney())));
			}
		}
		return vo;
	}
	
	public List<OrderPhoneVo> listVoByUserIdStatus(Integer userId, Integer status) {
		List<OrderPhoneVo> list_vo = new ArrayList<>();
		List<Order> list_order = this.orderDao.listVoByUserIdStatus(getCurrentSession(), userId, status);
		if(list_order!=null && list_order.size()>0){
			OrderPhoneVo vo = null;
			for(Order order : list_order){
				vo = getByOrderNo(order.getId());
				list_vo.add(vo);
			}
		}
		return list_vo;
	}

}
