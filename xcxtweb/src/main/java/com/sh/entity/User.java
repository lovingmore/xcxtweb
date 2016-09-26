package com.sh.entity;
// Generated 2016-9-21 21:18:40 by Hibernate Tools 5.2.0.Beta1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "xcxt")
public class User implements java.io.Serializable {

	private Integer id;
	private Date createTime;
	private Integer dr;
	private String name;
	private String openId;
	private String password;
	private Integer roleId;
	private Byte userType;
	private String username;

	public User() {
	}

	public User(Date createTime, Integer dr, String name, String openId, String password, Integer roleId, Byte userType,
			String username) {
		this.createTime = createTime;
		this.dr = dr;
		this.name = name;
		this.openId = openId;
		this.password = password;
		this.roleId = roleId;
		this.userType = userType;
		this.username = username;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "dr")
	public Integer getDr() {
		return this.dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	@Column(name = "name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "open_id", length = 30)
	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "password", length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "role_id", columnDefinition="INT default 0")
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "user_type")
	public Byte getUserType() {
		return this.userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	@Column(name = "username", length = 32)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
