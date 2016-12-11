package com.sh.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orderdetail",catalog="xcxt")
public class OrderDetail implements java.io.Serializable {

	private Integer Id;
	private Integer OrderId;  //订单ID
	private Integer FoodInfoId;//菜品ID
	private Double FoodPrice;//菜品价格
	private Double FoodNum;//菜品数量
	private Double DiscountAmount; //优惠金额
	private String orderNo;//订单编号
	
	
	public OrderDetail() {
	}
	
	public OrderDetail(Integer orderId, Integer foodInfoId, Double foodPrice,
			Double foodNum, Double discountAmount) {
		super();
		OrderId = orderId;
		FoodInfoId = foodInfoId;
		FoodPrice = foodPrice;
		FoodNum = foodNum;
		DiscountAmount = discountAmount;
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	@Column(name = "foodinfoid", columnDefinition="int default 0")
	public Integer getFoodInfoId() {
		return FoodInfoId;
	}
	public void setFoodInfoId(Integer foodInfoId) {
		FoodInfoId = foodInfoId;
	}
	@Column(name = "foodprice", precision = 22, scale = 0, columnDefinition="double default 0")
	public Double getFoodPrice() {
		return FoodPrice;
	}
	public void setFoodPrice(Double foodPrice) {
		FoodPrice = foodPrice;
	}
	@Column(name = "foodnum", precision = 22, scale = 0, columnDefinition="double default 0")
	public Double getFoodNum() {
		return FoodNum;
	}
	public void setFoodNum(Double foodNum) {
		FoodNum = foodNum;
	}
	@Column(name = "discountamount", precision = 22, scale = 0, columnDefinition="double default 0")
	public Double getDiscountAmount() {
		return DiscountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		DiscountAmount = discountAmount;
	}


	@Column(name = "orderid", columnDefinition="int default 0")
	public Integer getOrderId() {
		return OrderId;
	}

	public void setOrderId(Integer orderId) {
		OrderId = orderId;
	}

	@Column(name = "orderno", length=40)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
}
