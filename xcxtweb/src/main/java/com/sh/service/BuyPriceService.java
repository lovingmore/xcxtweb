package com.sh.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.sh.dao.BuyPriceDao;
import com.sh.entity.BuyPrice;
import com.sh.util.DateUtil;

@Service("buyPriceService")
public class BuyPriceService{
	@Resource
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Resource
	private BuyPriceDao buyPriceDao;

	public BuyPrice get(Integer id) {
		return this.buyPriceDao.get(getCurrentSession(), id);
	}
	
	public List<BuyPrice> getByDate(String buyDate) {
		return this.buyPriceDao.getByDate(getCurrentSession(), buyDate);
	}

	public List<BuyPrice> findAll() {
		return this.buyPriceDao.findAll(getCurrentSession());
	}
	
	public List<BuyPrice> list(Map<String, Object> map){
		return this.buyPriceDao.list(getCurrentSession(), map);
	}
	
	public List<BuyPrice> list(Map<String, Object> map, int startPage, int pageSize){
		return this.buyPriceDao.list(getCurrentSession(), map,startPage,pageSize);
	}
	
	public int count(Map<String, Object> map){
		return this.buyPriceDao.count(getCurrentSession(), map);
	}

	public void save(BuyPrice entity) {
		this.buyPriceDao.save(getCurrentSession(), entity);
	}

	public void update(BuyPrice entity) {
		this.buyPriceDao.update(getCurrentSession(), entity);
	}

	public void delete(Integer id) {
		this.buyPriceDao.delete(getCurrentSession(), id);
	}
	
	public void deleteAndSave(String buyDate, Integer[] foodCategory, Integer[] foodId, String[] price) {
		if(!StringUtils.isEmpty(buyDate) && foodCategory!=null && foodId!=null && price!=null){
			Date bDate = DateUtil.strToDate(buyDate);
			BuyPrice bp = null;
			this.buyPriceDao.deleteByBuyDate(getCurrentSession(), buyDate);
			for(int i=0;i<foodCategory.length;i++){
				bp = new BuyPrice();
				bp.setBuyDate(bDate);
				bp.setFoodCategory(foodCategory[i]);
				bp.setFoodId(foodId[i]);
				bp.setPrice(Double.parseDouble(price[i]));
				this.buyPriceDao.save(getCurrentSession(), bp);
			}
		}
	}

}
