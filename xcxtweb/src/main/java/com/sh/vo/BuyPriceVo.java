package com.sh.vo;

import java.util.Date;

public class BuyPriceVo {

	private Integer id;
	private Integer foodId;
	private String foodName;
	private Integer foodCategory;
	private Double price;
	private Date buyDate;
	private Integer isDefault;
	private String foodCategoryName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Integer getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(Integer foodCategory) {
		this.foodCategory = foodCategory;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getFoodCategoryName() {
		return foodCategoryName;
	}
	public void setFoodCategoryName(String foodCategoryName) {
		this.foodCategoryName = foodCategoryName;
	}
	

}
