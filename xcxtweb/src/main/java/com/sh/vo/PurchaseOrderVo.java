package com.sh.vo;

import java.io.Serializable;
import java.util.Date;

public class PurchaseOrderVo implements Serializable{

	private Integer orderid;  //订单ID
	private String orderno; //订单号
	private String foodInfoname; //菜品名称
	private Double foodprice;//菜品价格
	private Double foodnum;//菜品数量
	private Double discountamount; //优惠金额
    private Double totalfoodamount;  //商品总价
    private Date orderdate;//订单时间
    private String purchaser;//购买人
    private String norms;//菜品规格
    private Double foodPurchasePrice;//菜品采购价格
    
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getFoodInfoname() {
		return foodInfoname;
	}
	public void setFoodInfoname(String foodInfoname) {
		this.foodInfoname = foodInfoname;
	}
	public Double getFoodprice() {
		return foodprice;
	}
	public void setFoodprice(Double foodprice) {
		this.foodprice = foodprice;
	}
	public Double getFoodnum() {
		return foodnum;
	}
	public void setFoodnum(Double foodnum) {
		this.foodnum = foodnum;
	}
	public Double getDiscountamount() {
		return discountamount;
	}
	public void setDiscountamount(Double discountamount) {
		this.discountamount = discountamount;
	}
	public Double getTotalfoodamount() {
		return totalfoodamount;
	}
	public void setTotalfoodamount(Double totalfoodamount) {
		this.totalfoodamount = totalfoodamount;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}
	public String getPurchaser() {
		return purchaser;
	}
	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}
	public String getNorms() {
		return norms;
	}
	public void setNorms(String norms) {
		this.norms = norms;
	}
	public Double getFoodPurchasePrice() {
		return foodPurchasePrice;
	}
	public void setFoodPurchasePrice(Double foodPurchasePrice) {
		this.foodPurchasePrice = foodPurchasePrice;
	}
	
}
