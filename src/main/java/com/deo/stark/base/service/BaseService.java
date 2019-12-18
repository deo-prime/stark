package com.deo.stark.base.service;

import java.util.List;
import java.util.Map;

public interface BaseService {
	
	int executeSql(String sql);
	
	long count(String sql, Object... param);
	
	long count(String sql, Map<String, ?> param);
	
	int executeSql(String sql, Object... param);
	
	int executeSql(String sql, Map<String, Object> param);
	
	<T> int executeSqlWithObject(String sql, T t);
	
	Map<String, Object> expand(String sql, Object... param);
	
	Map<String, Object> expand(String sql, Map<String, ?> param);
	
	List<Map<String, Object>> query(String sql, Object... param);
	
	List<Map<String, Object>> query(String sql, Map<String, ?> param);
	
	<T> T expand(Class<T> t, String sql, Object... param);
	
	<T> T expand(Class<T> t, String sql, Map<String, ?> param);
	
	<T> List<T> query(Class<T> t, String sql, Object... param);
	
	<T> List<T> query(Class<T> t, String sql, Map<String, ?> param);
	
	<T> T expandForBasicType(Class<T> t, String sql, Object... param);
	
	<T> T expandForBasicType(Class<T> t, String sql, Map<String, ?> param);
	
	<T> List<T> queryForBasicType(Class<T> t, String sql, Object... param);
	
	<T> List<T> queryForBasicType(Class<T> t, String sql, Map<String, ?> param);

}
