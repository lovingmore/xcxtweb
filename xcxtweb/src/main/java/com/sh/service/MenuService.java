package com.sh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sh.dao.MenuDao;
import com.sh.entity.Menu;
import com.sh.vo.MenuVo;

@Service("menuService")
public class MenuService{
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Resource
	private MenuDao menuDao;

	public Menu get(Integer id) {
		return this.menuDao.get(getCurrentSession(), id);
	}

	public List<MenuVo> findAll(Map<String, Object> map) {
		List<Menu> list = this.menuDao.list(getCurrentSession(), map);
		List<MenuVo> listVo = new ArrayList<MenuVo>();
		if(list!=null){
			MenuVo menuVo = null;
			for(Menu m : list){
				menuVo = new MenuVo();
				BeanUtils.copyProperties(m, menuVo);
//				if(m.getParentId()!=null && m.getParentId()==0){
					map.put("parentId", m.getId());
					menuVo.setCmenus(findAll(map));
//				}
				listVo.add(menuVo);
			}
		}
		return listVo;
	}
	
	public List<Menu> list(Map<String, Object> map){
		return this.menuDao.list(getCurrentSession(), map);
	}
	
	public List<Menu> list(Map<String, Object> map, int startPage, int endPage){
		return this.menuDao.list(getCurrentSession(), map,startPage,endPage);
	}
	
	public int count(Map<String, Object> map){
		return this.menuDao.count(getCurrentSession(), map);
	}

	public void save(Menu entity) {
		this.menuDao.save(getCurrentSession(), entity);
	}

	public void update(Menu entity) {
		this.menuDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.menuDao.delete(getCurrentSession(), id);
	}

}
