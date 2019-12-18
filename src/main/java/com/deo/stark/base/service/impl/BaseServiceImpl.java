package com.deo.stark.base.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.deo.stark.base.dao.BaseDao;
import com.deo.stark.base.service.BaseService;

public class BaseServiceImpl implements BaseService {
	
	public static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	@Autowired
	private BaseDao baseDao;
	
	public int executeSql(String sql) {
		return baseDao.executeSql(sql);
	}

	@Override
	public long count(String sql, Map<String, ?> param) {
		return baseDao.count(sql, param);
	}

	@Override
	public long count(String sql, Object... param) {
		return baseDao.count(sql, param);
	}

	@Override
	public int executeSql(String sql, Object... param) {
		return baseDao.executeSql(sql, param);
	}

	@Override
	public int executeSql(String sql, Map<String, Object> param) {
		return baseDao.executeSql(sql, param);
	}

	@Override
	public Map<String, Object> expand(String sql, Object... param) {
		return baseDao.expand(sql, param);
	}

	@Override
	public Map<String, Object> expand(String sql, Map<String, ?> param) {
		return baseDao.expand(sql, param);
	}

	@Override
	public List<Map<String, Object>> query(String sql, Object... param) {
		return baseDao.query(sql, param);
	}

	@Override
	public List<Map<String, Object>> query(String sql, Map<String, ?> param) {
		return baseDao.query(sql, param);
	}

	@Override
	public <T> T expand(Class<T> t, String sql, Object... param) {
		return baseDao.expand(t, sql, param);
	}

	@Override
	public <T> T expand(Class<T> t, String sql, Map<String, ?> param) {
		return baseDao.expand(t, sql, param);
	}

	@Override
	public <T> List<T> query(Class<T> t, String sql, Object... param) {
		return baseDao.query(t, sql, param);
	}

	@Override
	public <T> List<T> query(Class<T> t, String sql, Map<String, ?> param) {
		return baseDao.query(t, sql, param);
	}

	@Override
	public <T> int executeSqlWithObject(String sql, T t) {
		return baseDao.executeSqlWithObject(sql, t);
	}

	@Override
	public <T> T expandForBasicType(Class<T> t, String sql, Object... param) {
		return baseDao.expandForBasicType(t, sql, param);
	}

	@Override
	public <T> T expandForBasicType(Class<T> t, String sql, Map<String, ?> param) {
		return baseDao.expandForBasicType(t, sql, param);
	}

	@Override
	public <T> List<T> queryForBasicType(Class<T> t, String sql, Object... param) {
		return baseDao.queryForBasicType(t, sql, param);
	}

	@Override
	public <T> List<T> queryForBasicType(Class<T> t, String sql, Map<String, ?> param) {
		return baseDao.queryForBasicType(t, sql, param);
	}
	
	

}
