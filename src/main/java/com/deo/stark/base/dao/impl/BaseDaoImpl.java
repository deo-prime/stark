package com.deo.stark.base.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.deo.stark.base.dao.BaseDao;
import com.deo.stark.base.vo.DataGridVO;

@Repository
public class BaseDaoImpl implements BaseDao {
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public int executeSql(String sql) {
		return jdbcTemplate.update(sql);
	}

	@Override
	public long count(String sql, Object... param) {
		return jdbcTemplate.queryForObject(sql, Long.class, param);
	}
	
	@Override
	public long count(String sql, Map<String, ?> param) {
		return namedJdbcTemplate.queryForObject(sql, param, Long.class);
	}

	@Override
	public int executeSql(String sql, Object... param) {
		return jdbcTemplate.update(sql, param);
	}

	@Override
	public int executeSql(String sql, Map<String, ?> param) {
		return namedJdbcTemplate.update(sql, param);
	}

	@Override
	public Map<String, Object> expand(String sql, Object... param) {
		List<Map<String,Object>> query = query(sql, param);
		if (CollectionUtils.isEmpty(query)) {
			return null;
		}
		return query.get(0);
	}

	@Override
	public Map<String, Object> expand(String sql, Map<String, ?> param) {
		List<Map<String,Object>> query = query(sql, param);
		if (CollectionUtils.isEmpty(query)) {
			return null;
		}
		return query.get(0);
	}

	@Override
	public List<Map<String, Object>> query(String sql, Object... param) {
		return jdbcTemplate.queryForList(sql, param);
	}

	@Override
	public List<Map<String, Object>> query(String sql, Map<String, ?> param) {
		return namedJdbcTemplate.queryForList(sql, param);
	}

	@Override
	public <T> T expand(Class<T> t, String sql, Object... param) {
		List<T> query = query(t, sql, param);
		if (CollectionUtils.isEmpty(query)) {
			return null;
		}
		return query.get(0);
	}

	@Override
	public <T> T expand(Class<T> t, String sql, Map<String, ?> param) {
		List<T> query = query(t, sql, param);
		if (CollectionUtils.isEmpty(query)) {
			return null;
		}
		return query.get(0);
	}

	@Override
	public <T> List<T> query(Class<T> t, String sql, Object... param) {
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(t));
	}

	@Override
	public <T> List<T> query(Class<T> t, String sql, Map<String, ?> param) {
		return namedJdbcTemplate.query(sql, param, new BeanPropertyRowMapper<>(t));
	}

	@Override
	public <T> int executeSqlWithObject(String sql, T t) {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		return namedJdbcTemplate.update(sql, paramSource);
	}

	@Override
	public <T> DataGridVO<T> grid(Class<T> t, String sql, int pageNo, int pageSize, Object... param) {
		
		return null;
	}

	@Override
	public <T> DataGridVO<T> grid(Class<T> t, String sql, int pageNo, int pageSize, Map<String, ?> param) {
		
		return null;
	}

	@Override
	public <T> T expandForBasicType(Class<T> t, String sql, Object... param) {
		return jdbcTemplate.queryForObject(sql, t, param);
	}

	@Override
	public <T> T expandForBasicType(Class<T> t, String sql, Map<String, ?> param) {
		return namedJdbcTemplate.queryForObject(sql, param, t);
	}

	@Override
	public <T> List<T> queryForBasicType(Class<T> t, String sql, Object... param) {
		return jdbcTemplate.queryForList(sql, t, param);
	}

	@Override
	public <T> List<T> queryForBasicType(Class<T> t, String sql, Map<String, ?> param) {
		return namedJdbcTemplate.queryForList(sql, param, t);
	}


}
