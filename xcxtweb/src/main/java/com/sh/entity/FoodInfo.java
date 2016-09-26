package com.sh.entity;
// Generated 2016-9-22 21:42:28 by Hibernate Tools 5.2.0.Beta1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

/**
 * FoodInfo generated by hbm2java
 */
@Entity
@Table(name = "food_info", catalog = "xcxt")
public class FoodInfo implements java.io.Serializable {

	private Integer id;
	private String name;
	private String norms;
	private Double price;
	private Double redemptionPrice;
	private Date redemptionDate;
	private String content;
	private String unit;
	private Integer categoryId;
	private Integer status;
	private String facePic;
	private Integer dr;

	public FoodInfo() {
	}

	public FoodInfo(String name, String norms, Double price, Double redemptionPrice, Date redemptionDate, String content, 
			String unit, Integer categoryId, Integer status, String facePic, Integer dr) {
		this.name = name;
		this.norms = norms;
		this.price = price;
		this.redemptionPrice = redemptionPrice;
		this.redemptionDate = redemptionDate;
		this.content = content;
		this.unit = unit;
		this.categoryId = categoryId;
		this.status = status;
		this.facePic = facePic;
		this.dr = dr;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "norms", length = 50)
	public String getNorms() {
		return this.norms;
	}

	public void setNorms(String norms) {
		this.norms = norms;
	}

	@Column(name = "price", precision = 22, scale = 0, columnDefinition="double default 0")
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "redemption_price", precision = 22, scale = 0, columnDefinition="double default 0")
	public Double getRedemptionPrice() {
		return this.redemptionPrice;
	}

	public void setRedemptionPrice(Double redemptionPrice) {
		this.redemptionPrice = redemptionPrice;
	}

	public Date getRedemptionDate() {
		return redemptionDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "redemption_date")
	public void setRedemptionDate(Date redemptionDate) {
		this.redemptionDate = redemptionDate;
	}

	public String getContent() {
		return content;
	}

	@Type(type="text")
	@Column(name = "content")
	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "unit", length = 10)
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Column(name = "category_id", columnDefinition="int default 0")
	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "status", columnDefinition="int default 0")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "face_pic", length = 100)
	public String getFacePic() {
		return this.facePic;
	}

	public void setFacePic(String facePic) {
		this.facePic = facePic;
	}

	@Column(name = "dr", columnDefinition="int default 0")
	public Integer getDr() {
		return this.dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

}