package com.lb.dao;

import java.io.Serializable;
import java.util.List;


/**
 * @author LiBing
 * @usage	数据持久化管理
 * @param <T>
 */
public interface BaseDao<T> {

	
	/**
	 * @param o
	 * @return
	 * @usage	存储数据库映射对象
	 */
	public Serializable save(T o);

	
	/**
	 * @param o
	 * @usage	删除数据库映射对象
	 */
	public void delete(T o);

	
	/**
	 * @param o
	 * @usage	数据库映射对象
	 */
	public void update(T o);

	
	/**
	 * @param o
	 * @usage	更新数据库映射对象（如果不存在则存储）
	 */
	public void saveOrUpdate(T o);

	/**
	 * @param hql
	 * @return
	 * @usage	根据查询语句进行查询
	 */
	public List<T> find(String hql);

	
	/**
	 * @param hql
	 * @param param
	 * @return
	 * @usage	根据查询语句进行查询
	 */
	public List<T> find(String hql, Object[] param);

	
	/**
	 * @param hql
	 * @param param
	 * @return
	 * @usage	根据查询语句进行查询
	 */
	public List<T> find(String hql, List<Object> param);

	
	/**
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 * @usage	根据查询语句进行查询
	 */
	public List<T> find(String hql, Object[] param, Integer page, Integer rows);

	/**
	 * @param hql
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 * @usage	根据查询语句进行查询
	 */
	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows);

	
	/**
	 * @param c
	 * @param id
	 * @return
	 * @usage	根据主键获取持久化对象
	 */
	public T get(Class<T> c, Serializable id);

	
	/**
	 * @param hql
	 * @param param
	 * @return
	 * @usage	根据主键获取持久化对象
	 */
	public T get(String hql, Object[] param);

	
	/**
	 * @param hql
	 * @param param
	 * @return
	 * @usage	根据主键获取持久化对象
	 */
	public T get(String hql, List<Object> param);

	
	/**
	 * @param hql
	 * @return
	 * @usage	查询记录数目
	 */
	public Long count(String hql);

	
	/**
	 * @param hql
	 * @param param
	 * @return
	 * @usage	查询记录数目
	 */
	public Long count(String hql, Object[] param);

	
	/**
	 * @param hql
	 * @param param
	 * @return
	 * @usage	查询记录数目
	 */
	public Long count(String hql, List<Object> param);

	
	/**
	 * @param hql
	 * @return
	 * @usage	执行hql语句
	 */
	public Integer executeHql(String hql);

	
	/**
	 * @param hql
	 * @param param
	 * @return
	 * @usage	执行hql语句
	 */
	public Integer executeHql(String hql, Object[] param);

	
	/**
	 * @param hql
	 * @param param
	 * @return
	 * @usage	执行hql语句
	 */
	public Integer executeHql(String hql, List<Object> param);

}
