package com.sh.vo;

import java.util.Date;

public class OrderDetailVo {

	private Integer Id;
	private Integer OrderId;  //订单ID
	private Integer FoodInfoId;//菜品ID
	private String FoodInfoName; //菜品名称
	private Double FoodPrice;//菜品价格
	private Double FoodNum;//菜品数量
	private Double DiscountAmount; //优惠金额
	private Double TotalAmount;   //总计
    private Double TotalFoodAmount;  //商品总价
    private Double TotalOrderAmount;  //订单总价
    private Date orderDate;//订单时间
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Integer getOrderId() {
		return OrderId;
	}
	public void setOrderId(Integer orderId) {
		OrderId = orderId;
	}
	public Integer getFoodInfoId() {
		return FoodInfoId;
	}
	public void setFoodInfoId(Integer foodInfoId) {
		FoodInfoId = foodInfoId;
	}
	public Double getFoodPrice() {
		return FoodPrice;
	}
	public void setFoodPrice(Double foodPrice) {
		FoodPrice = foodPrice;
	}
	public Double getFoodNum() {
		return FoodNum;
	}
	public void setFoodNum(Double foodNum) {
		FoodNum = foodNum;
	}
	public Double getDiscountAmount() {
		return DiscountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		DiscountAmount = discountAmount;
	}
	public String getFoodInfoName() {
		return FoodInfoName;
	}
	public void setFoodInfoName(String foodInfoName) {
		FoodInfoName = foodInfoName;
	}
	public Double getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		TotalAmount = totalAmount;
	}
	public Double getTotalFoodAmount() {
		return TotalFoodAmount;
	}
	public void setTotalFoodAmount(Double totalFoodAmount) {
		TotalFoodAmount = totalFoodAmount;
	}
	public Double getTotalOrderAmount() {
		return TotalOrderAmount;
	}
	public void setTotalOrderAmount(Double totalOrderAmount) {
		TotalOrderAmount = totalOrderAmount;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	
}
