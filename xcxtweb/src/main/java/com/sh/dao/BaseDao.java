/**
 * 
 */
package com.sh.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

/**
 * 基础dao类
 * 
 * @author
 * 
 */
public class BaseDao {

	/**
	 * 通过hql查询,(根据序号)
	 * 
	 * @param session
	 * @param hql
	 * @param args
	 * @return
	 */
	public List findByHql(Session session, String hql, Object... args) {
		// session.beginTransaction();
		Query query = session.createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		List list = query.list();
		// session.getTransaction().commit();
		return list;
	}

	/**
	 * 通过hql查询，（根据名称）
	 * 
	 * @param session
	 * @param hql
	 * @param paramMap
	 * @return
	 */
	public List findByHql(Session session, String hql,
			Map<String, Object> paramMap) {
		Query query = session.createQuery(hql);
		if (paramMap != null && !paramMap.isEmpty()) {
			Iterator<String> it = paramMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = paramMap.get(key);
				if (value instanceof Collection) {
					query.setParameterList(key, (Collection) value);
				} else if (value instanceof Object[]) {
					query.setParameterList(key, (Object[]) value);
				} else {
					query.setParameter(key, value);
				}
			}
		}
		return query.list();
	}

	/**
	 * 通过hql分页查询，（根据序号）
	 * 
	 * @param session
	 * @param hql
	 * @param first
	 *            开始下标
	 * @param max
	 *            最大数
	 * @param args
	 * @return
	 */
	public List findByHqlPage(Session session, String hql, int first, int max,
			Object... args) {
		Query query = session.createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}

	/**
	 * 通过hql分页查询，（根据名称）
	 * 
	 * @param session
	 * @param hql
	 * @param first
	 *            开始下标
	 * @param max
	 *            最大数
	 * @param paramMap参数map
	 * @return
	 */
	public List findByHqlPage(Session session, String hql, int first, int max,
			Map<String, Object> paramMap) {
		Query query = session.createQuery(hql);
		if (paramMap != null && !paramMap.isEmpty()) {
			Iterator<String> it = paramMap.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				Object value = paramMap.get(key);
				if (value instanceof Collection) {
					query.setParameterList(key, (Collection) value);
				} else if (value instanceof Object[]) {
					query.setParameterList(key, (Object[]) value);
				} else {
					query.setParameter(key, value);
				}
			}
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}

	/**
	 * 执行操作
	 * 
	 * @param session
	 * @param hql
	 * @param args
	 * @return
	 */
	public int executeHql(Session session, String hql, Object... args) {
		Query query = session.createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		return query.executeUpdate();
	}

	/**
	 * 保存
	 * 
	 * @param session
	 * @param arg0
	 */
	public void save(Session session, Serializable arg0) {
		session.save(arg0);
	}

	/**
	 * 更新
	 * 
	 * @param session
	 * @param arg0
	 */
	public void update(Session session, Serializable arg0) {
		session.update(arg0);
	}

	/**
	 * 删除
	 * 
	 * @param session
	 * @param arg0
	 */
	public void delete(Session session, Serializable arg0) {
		session.delete(arg0);
	}

	/**
	 * 删除
	 * 
	 * @param session
	 * @param arg0
	 */
	public void deletebysql(Session session, String hsql) {
		session.delete(hsql);
	}
	public int deleteSql(Session session, String hsql) {
		Query query = session.createSQLQuery(hsql);
		
		return query.executeUpdate();
	}
	/**
	 * 
	 * @param session
	 * @param id
	 * @return
	 */
	public Object find(Session session, Class<? extends Serializable> clazz,
			Serializable id) {
		return session.get(clazz, id);
	}

	public List queryBySql(Session session, String sql) {
		List<Object[]> list = session.createSQLQuery(sql).list();
		return list;
	}

	public List findBySqlPage(Class<? extends Serializable> clazz,
			Session session, String sql, int first, int max, Object... args) {
		Query query = session.createSQLQuery(sql).addEntity(clazz);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}

	public List findBySqlPage(Session session, String sql, int first, int max,
			Object... args) {
		Query query = session.createSQLQuery(sql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		query.setFirstResult(first);
		query.setMaxResults(max);
		return query.list();
	}

	public List queryBySql(Session session,
			Class<? extends Serializable> clazz, String sql) {

		return session.createSQLQuery(sql).addEntity(clazz).list();
	}

	public int getTotalRowsByHql(Session session, String hql, Object... args) {

		Query query = session.createQuery(hql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		int totalRows = ((Long) query.iterate().next()).intValue();
		return totalRows;
	}
	
	public int getTotalRowsBySql(Session session, String sql, Object... args) {
		Query query = session.createSQLQuery(sql);
		for (int i = 0; i < args.length; i++) {
			query.setParameter(i, args[i]);
		}
		List<Object> list = query.list();
		Object value=null;
		if(list!=null&&list.size()>0){
			value=list.get(0);
		}
		if(value==null||value.equals("")){
			return 0;
		}
		int totalRows =Integer.parseInt(value+"");
		return totalRows;
	}
	
}
