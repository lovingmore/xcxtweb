package com.sh.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.FoodCategoryDao;
import com.sh.entity.FoodCategory;
import com.sh.vo.CategoryLevelVo;

@Service("foodCategoryService")
public class FoodCategoryService{
	
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	@Resource
	private FoodCategoryDao foodCategoryDao;

	public FoodCategory get(Integer id) {
		return this.foodCategoryDao.get(getCurrentSession(), id);
	}

	public List<FoodCategory> findAll() {
		List<FoodCategory> list = this.foodCategoryDao.list(getCurrentSession(), null);
		return list;
	}
	
	public List<FoodCategory> list(Map<String, Object> map){
		return this.foodCategoryDao.list(getCurrentSession(), map);
	}
	
	public List<FoodCategory> list(Map<String, Object> map, int startPage, int endPage){
		return this.foodCategoryDao.list(getCurrentSession(), map,startPage,endPage);
	}
	
	public int count(Map<String, Object> map){
		return this.foodCategoryDao.count(getCurrentSession(), map);
	}

	public void save(FoodCategory entity) {
		this.foodCategoryDao.save(getCurrentSession(), entity);
	}

	public void update(FoodCategory entity) {
		this.foodCategoryDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.foodCategoryDao.delete(getCurrentSession(), id);
	}
	
	public List<CategoryLevelVo> listCategoryLevelVo(){
		List<CategoryLevelVo> vos = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("status", 0);
		map.put("parentId", 0);
		List<FoodCategory> list_fc = list(map);
		if(list_fc!=null && list_fc.size()>0){
			CategoryLevelVo vo = null;
			for(FoodCategory fc : list_fc){
				vo = new CategoryLevelVo();
				vo.setId(fc.getId());
				vo.setName(fc.getName());
				map.put("parentId", fc.getId());
				List<FoodCategory> list_fc_t = list(map);
				if(list_fc_t!=null && list_fc_t.size()>0){
					List<CategoryLevelVo> vos_t = new ArrayList<>();
					CategoryLevelVo vo_t = null;
					for(FoodCategory fc_t : list_fc_t){
						vo_t = new CategoryLevelVo();
						vo_t.setId(fc_t.getId());
						vo_t.setName(fc_t.getName());
						vos_t.add(vo_t);
					}
					vo.setChild(vos_t);
				}
				vos.add(vo);
			}
		}
		return vos;
	}
	

}
