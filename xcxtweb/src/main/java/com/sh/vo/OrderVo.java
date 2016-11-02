package com.sh.vo;

import java.util.Date;


public class OrderVo {

	private Integer Id;
	private String OrderNo; //订单号
	private Date  OrderDate; //订单日期
	private String Purchaser; //购买人
	private String Mobile; //购买人手机
	private String RecAddress;//购买人地址
	private Date PayMentDate;//付款时间
	private Date DeliveryDate;//发货时间
	private Date ReceivingDate;//收货时间
	private Integer OrderStatus;//订单状态      0 待支付 1 待发货  2 待收货  3 已结束 
	private Integer PaymentType;// 付款方式    1 微信支付  0 线下支付
	private Double freight; //运费

	private Double DiscountAmount; //优惠金额
    private Double TotalFoodAmount;  //商品总价
    private Double TotalOrderAmount;  //订单总价 合计
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public String getPurchaser() {
		return Purchaser;
	}
	public void setPurchaser(String purchaser) {
		Purchaser = purchaser;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getRecAddress() {
		return RecAddress;
	}
	public void setRecAddress(String recAddress) {
		RecAddress = recAddress;
	}
	public Date getPayMentDate() {
		return PayMentDate;
	}
	public void setPayMentDate(Date payMentDate) {
		PayMentDate = payMentDate;
	}
	public Date getDeliveryDate() {
		return DeliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		DeliveryDate = deliveryDate;
	}
	public Date getReceivingDate() {
		return ReceivingDate;
	}
	public void setReceivingDate(Date receivingDate) {
		ReceivingDate = receivingDate;
	}
	public Integer getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		OrderStatus = orderStatus;
	}
	public Integer getPaymentType() {
		return PaymentType;
	}
	public void setPaymentType(Integer paymentType) {
		PaymentType = paymentType;
	}
	public Double getFreight() {
		return freight;
	}
	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getDiscountAmount() {
		return DiscountAmount;
	}
	public void setDiscountAmount(Double discountAmount) {
		DiscountAmount = discountAmount;
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
	

    
	
}
