package com.sh.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="customer", catalog="xcxt")
public class Customer implements java.io.Serializable {

	private Integer Id;
	private String shopBrand;  //店标
	private String userAccount;//账号
	private String shopName;   //店名称
	private String name;       //负责人
	private String shopAddr;   //门店地址
	private Integer orderNum;  //下单次数
	private Double orderAmount;//购买总金额
	private Integer status;    //状态
	
	
	public Customer() {
	}
	
	public Customer(String shopBrand, String userAccount, String shopName,
			String name, String shopAddr, Integer orderNum,
			Double orderAmount,Integer status) {
		super();
		this.shopBrand = shopBrand;
		this.userAccount = userAccount;
		this.shopName = shopName;
		this.name = name;
		this.shopAddr = shopAddr;
		this.orderNum = orderNum;
		this.orderAmount = orderAmount;
		this.status = status;
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
	@Column(name = "shopbrand", length = 50)
	public String getShopBrand() {
		return shopBrand;
	}
	public void setShopBrand(String shopBrand) {
		this.shopBrand = shopBrand;
	}
	@Column(name = "useraccount", length = 11)
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	@Column(name = "shopname", length = 50)
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	@Column(name = "name", length = 10)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "shopaddr", length = 50)
	public String getShopAddr() {
		return shopAddr;
	}
	public void setShopAddr(String shopAddr) {
		this.shopAddr = shopAddr;
	}
	@Column(name = "ordernum", columnDefinition="int default 0")
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	@Column(name = "orderamount", precision = 22, scale = 0, columnDefinition="double default 0")
	public Double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	@Column(name = "status", columnDefinition="int default 0")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
	
}
