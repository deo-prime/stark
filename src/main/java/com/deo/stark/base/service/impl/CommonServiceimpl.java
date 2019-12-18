package com.deo.stark.base.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deo.stark.base.dao.CommonDao;
import com.deo.stark.base.service.CommonService;
import com.deo.stark.base.vo.PageVO;


@Service("commonService")
@Transactional
public class CommonServiceimpl  implements CommonService {
	
	@Autowired
	private CommonDao commonDao;

	/**
	 * 获取Session
	 * @return
	 */
	@Override
	public <T> Session getSession(){
		return commonDao.getSession();
	}
	
	@Override 
	public <T> Boolean insertEntity(T entity) {
		return commonDao.insertEntity(entity);
	}

	@Override
	public <T> Boolean insertEntityBatch(List<T> entities) {
		return commonDao.insertEntityBatch(entities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Boolean insertEntityBatch(T... entities) {
		return commonDao.insertEntityBatch(entities);
	}

	@Override
	public <T> Boolean updateEntity(T entity) {
		return commonDao.updateEntity(entity);
	}

	@Override
	public <T> Boolean updateEntityBatch(List<T> entities) {
		return commonDao.updateEntityBatch(entities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Boolean updateEntityBatch(T... entities) {
		return commonDao.updateEntityBatch(entities);
	}

	@Override
	public <T> Boolean saveEntity(T entity) {
		return commonDao.saveEntity(entity);
	}

	@Override
	public <T> Boolean saveEntityBatch(List<T> entities) {
		return commonDao.saveEntityBatch(entities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Boolean saveEntityBatch(T... entities) {
		return commonDao.saveEntityBatch(entities);
	}

	@Override
	public <T> Boolean deleteEntity(T entity) {
		return commonDao.deleteEntity(entity);
	}

	@Override
	public <T> Boolean deleteEntityById(Class<T> entityClass, Serializable id) {
		return commonDao.deleteEntityById(entityClass, id);
	}

	@Override
	public <T> Boolean deleteEntityBatch(List<T> entities) {
		return commonDao.deleteEntityBatch(entities);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Boolean deleteEntityBatch(T... entities) {
		return commonDao.deleteEntityBatch(entities);
	}

	@Override
	public Integer runUpdateByHql(String hql) {
		return commonDao.runUpdateByHql(hql);
	}

	@Override
	public Integer runUpdateByHql(String hql, List<Object> param) {
		return commonDao.runUpdateByHql(hql, param);
	}

	@Override
	public Integer runUpdateByHql(String hql, Object... param) {
		return commonDao.runUpdateByHql(hql, param);
	}

	@Override
	public Integer runUpdateByHql(String hql, Map<String, Object> param) {
		return commonDao.runUpdateByHql(hql, param);
	}

	@Override
	public Integer runUpdateBySql(String sql) {
		return commonDao.runUpdateBySql(sql);
	}

	@Override
	public Integer runUpdateBySql(String sql, List<Object> param) {
		return commonDao.runUpdateBySql(sql, param);
	}

	@Override
	public Integer runUpdateBySql(String sql, Object... param) {
		return commonDao.runUpdateBySql(sql, param);
	}

	@Override
	public Integer runUpdateBySql(String sql, Map<String, Object> param) {
		return commonDao.runUpdateBySql(sql, param);
	}

	@Override
	public <T> T expandEntity(Class<T> entityClass, Serializable id) {
		return commonDao.expandEntity(entityClass, id);
	}

	@Override
	public <T> T expandEntityByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.expandEntityByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> T expandEntityByHql(String hql) {
		return commonDao.expandEntityByHql(hql);
	}

	@Override
	public <T> T expandEntityByHql(String hql, List<Object> param) {
		return commonDao.expandEntityByHql(hql, param);
	}

	@Override
	public <T> T expandEntityByHql(String hql, Object... param) {
		return commonDao.expandEntityByHql(hql, param);
	}

	@Override
	public <T> T expandEntityByHql(String hql, Map<String, Object> param) {
		return commonDao.expandEntityByHql(hql, param);
	}

	@Override
	public Map<String, Object> expandMapBySql(String sql) {
		return commonDao.expandMapBySql(sql);
	}

	@Override
	public Map<String, Object> expandMapBySql(String sql, List<Object> param) {
		return commonDao.expandMapBySql(sql, param);
	}

	@Override
	public Map<String, Object> expandMapBySql(String sql, Object... param) {
		return commonDao.expandMapBySql(sql, param);
	}

	@Override
	public Map<String, Object> expandMapBySql(String sql, Map<String, Object> param) {
		return commonDao.expandMapBySql(sql, param);
	}

	@Override
	public Map<Object, Object> queryMapByHql(String hql) {
		return commonDao.queryMapByHql(hql);
	}

	@Override
	public Map<Object, Object> queryMapByHql(String hql, List<Object> param) {
		return commonDao.queryMapByHql(hql, param);
	}

	@Override
	public Map<Object, Object> queryMapByHql(String hql, Object... param) {
		return commonDao.queryMapByHql(hql, param);
	}

	@Override
	public Map<Object, Object> queryMapByHql(String hql, Map<String, Object> param) {
		return commonDao.queryMapByHql(hql, param);
	}

	@Override
	public Map<Object, Object> queryMapBySql(String sql) {
		return commonDao.queryMapBySql(sql);
	}

	@Override
	public Map<Object, Object> queryMapBySql(String sql, List<Object> param) {
		return commonDao.queryMapBySql(sql, param);
	}

	@Override
	public Map<Object, Object> queryMapBySql(String sql, Object... param) {
		return commonDao.queryMapBySql(sql, param);
	}

	@Override
	public Map<Object, Object> queryMapBySql(String sql, Map<String, Object> param) {
		return commonDao.queryMapBySql(sql, param);
	}

	@Override
	public <T> List<T> queryListByClass(Class<T> entityClass) {
		return commonDao.queryListByClass(entityClass);
	}

	@Override
	public <T> List<T> queryListByHql(String hql) {
		return commonDao.queryListByHql(hql);
	}

	@Override
	public <T> List<T> queryListByHql(String hql, List<Object> param) {
		return commonDao.queryListByHql(hql, param);
	}

	@Override
	public <T> List<T> queryListByHql(String hql, Object... param) {
		return commonDao.queryListByHql(hql, param);
	}

	@Override
	public <T> List<T> queryListByHql(String hql, Map<String, Object> param) {
		return commonDao.queryListByHql(hql, param);
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String sql) {
		return commonDao.queryListBySql(sql);
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String sql, List<Object> param) {
		return commonDao.queryListBySql(sql, param);
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String sql, Object... param) {
		return commonDao.queryListBySql(sql, param);
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> param) {
		return commonDao.queryListBySql(sql, param);
	}

	@Override
	public <T> List<T> queryListByProperty(Class<T> entityClass, String propertyName, Object value) {
		return commonDao.queryListByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> List<T> queryListByPropertyWithOrder(Class<T> entityClass, String propertyName, Object value, boolean isAsc, String orderField) {
		return commonDao.queryListByProperty(entityClass, propertyName, value);
	}

	@Override
	public <T> List<T> queryListByEntity(Class<T> entityClass, Object exampleEntity) {
		return commonDao.queryListByEntity(entityClass, exampleEntity);
	}
	


	@Override
	public <T> Integer countByClass(Class<T> clazz) {
		return commonDao.countByClass(clazz);
	}

	@Override
	public Integer countByHql(String hql) {
		return commonDao.countByHql(hql);
	}

	@Override
	public Integer countByHql(String hql, List<Object> param) {
		return commonDao.countByHql(hql, param);
	}

	@Override
	public Integer countByHql(String hql, Object... param) {
		return commonDao.countByHql(hql, param);
	}

	@Override
	public Integer countByHql(String hql, Map<String, Object> param) {
		return commonDao.countByHql(hql, param);
	}

	@Override
	public Integer countBySql(String sql) {
		return commonDao.countBySql(sql);
	}

	@Override
	public Integer countBySql(String sql, List<Object> param) {
		return commonDao.countBySql(sql, param);
	}

	@Override
	public Integer countBySql(String sql, Object... param) {
		return commonDao.countBySql(sql, param);
	}

	@Override
	public Integer countBySql(String sql, Map<String, Object> param) {
		return commonDao.countBySql(sql, param);
	}

	@Override
	public String queryUniqueBySql(String sql, Object... param) {
		return commonDao.queryUniqueBySql(sql, param);
	}

	@Override
	public String queryUniqueBySql(String sql) {
		return commonDao.queryUniqueBySql(sql);
	}

	@Override
	public <T> String queryUniqueByHql(String hql) {
		return commonDao.queryUniqueByHql(hql);
	}

	@Override
	public <T> String queryUniqueByHql(String hql, Object... param) {
		return commonDao.queryUniqueByHql(hql,param);
	}

	@Override
	public <T> PageVO<T> queryPageByHql(String hql, Map<String, Object> param, int pageNo, int pageSize) {
		return commonDao.queryPageByHql(hql, param, pageNo, pageSize);
	}

}
