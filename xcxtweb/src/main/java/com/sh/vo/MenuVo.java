package com.sh.vo;
// Generated 2016-9-17 12:45:33 by Hibernate Tools 5.2.0.Beta1

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuVo {

	private Integer id;
	private Date createTime;
	private Byte listno;
	private String name;
	private Integer parentId;
	private String url;
	private List<MenuVo> cmenus = new ArrayList<MenuVo>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Byte getListno() {
		return listno;
	}
	public void setListno(Byte listno) {
		this.listno = listno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<MenuVo> getCmenus() {
		return cmenus;
	}
	public void setCmenus(List<MenuVo> cmenus) {
		this.cmenus = cmenus;
	}

}
