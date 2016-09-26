package com.sh.entity;
// Generated 2016-9-21 21:20:00 by Hibernate Tools 5.2.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FoodCategory generated by hbm2java
 */
@Entity
@Table(name = "food_category", catalog = "xcxt")
public class FoodCategory implements java.io.Serializable {

	private Integer id;
	private String name;
	private Integer parentId;
	private Integer status;

	public FoodCategory() {
	}

	public FoodCategory(String name, Integer parentId, Integer status) {
		this.name = name;
		this.parentId = parentId;
		this.status = status;
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

	@Column(name = "parent_id", columnDefinition="INT default 0")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "status", columnDefinition="int default 0")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
