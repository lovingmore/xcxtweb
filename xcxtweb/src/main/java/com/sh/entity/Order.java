package com.sh.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order",catalog="xcxt")
public class Order implements java.io.Serializable {
	
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
	
	public Order() {
	}
	
	public Order(String orderNo, Date orderDate, String purchaser,
			String mobile, String recAddress, Date payMentDate,
			Date deliveryDate, Date receivingDate,
			Integer orderStatus, Integer paymentType, Double freight) {
		super();
		OrderNo = orderNo;
		OrderDate = orderDate;
		Purchaser = purchaser;
		Mobile = mobile;
		RecAddress = recAddress;
		PayMentDate = payMentDate;
		DeliveryDate = deliveryDate;
		ReceivingDate = receivingDate;
		OrderStatus = orderStatus;
		PaymentType = paymentType;
		this.freight = freight;
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
	
	@Column(name = "orderno", length = 20)
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	
	@Column(name = "orderdate", length = 19)
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}


	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	@Column(name = "recaddress", length = 50)
	public String getRecAddress() {
		return RecAddress;
	}
	public void setRecAddress(String recAddress) {
		RecAddress = recAddress;
	}
	@Column(name = "paymentdate", length = 19)
	public Date getPayMentDate() {
		return PayMentDate;
	}
	public void setPayMentDate(Date payMentDate) {
		PayMentDate = payMentDate;
	}
	@Column(name = "deliverydate", length = 19)
	public Date getDeliveryDate() {
		return DeliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		DeliveryDate = deliveryDate;
	}
	@Column(name = "receivingdate", length = 19)
	public Date getReceivingDate() {
		return ReceivingDate;
	}
	public void setReceivingDate(Date receivingDate) {
		ReceivingDate = receivingDate;
	}

	@Column(name = "orderstatus", length = 1)
	public Integer getOrderStatus() {
		return OrderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		OrderStatus = orderStatus;
	}


	@Column(name = "purchaser", length = 10)
	public String getPurchaser() {
		return Purchaser;
	}



	public void setPurchaser(String purchaser) {
		Purchaser = purchaser;
	}


	@Column(name = "freight", precision = 22, scale = 0, columnDefinition="double default 0")
	public Double getFreight() {
		return freight;
	}



	public void setFreight(Double freight) {
		this.freight = freight;
	}


	@Column(name = "paymenttype", length = 1)
	public Integer getPaymentType() {
		return PaymentType;
	}



	public void setPaymentType(Integer paymentType) {
		PaymentType = paymentType;
	}

}
