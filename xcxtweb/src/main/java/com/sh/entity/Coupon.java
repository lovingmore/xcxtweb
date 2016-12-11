package com.sh.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "coupon", catalog = "xcxt")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Coupon implements java.io.Serializable {

	private Integer id;
	private String name;
	private Double money;
	private Date startDate;
	private Date endDate;
	private Integer number;
	private Integer dr;
	private Date createTime;
	private Double conditionMoney;

	public Coupon() {
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

	@Column(name="name",length=50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="money", columnDefinition="double default 0")
	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name="number", columnDefinition="int default 0")
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name="dr", columnDefinition="int default 0")
	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="condition_money", precision = 10, scale = 2, columnDefinition="double default 0")
	public Double getConditionMoney() {
		return conditionMoney;
	}

	public void setConditionMoney(Double conditionMoney) {
		this.conditionMoney = conditionMoney;
	}

}
