package com.deo.stark.base.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.deo.stark.base.dao.CommonDao;
import com.deo.stark.base.vo.PageVO;

/**
 * 功能描述:公共Dao
 * 
 * @version 1.0
 */
@SuppressWarnings(value = { "hiding", "unchecked" })
@Repository
public class CommonDaoImpl<T, PK extends Serializable> implements CommonDao {

	/**
	 * 注入一个sessionFactory属性,并注入到父类(HibernateDaoSupport)
	 */
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public <T> Session getSession() {
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}

	@Override
	public <T> Boolean insertEntity(T entity) {
		getSession().save(entity);
		getSession().flush();
		return true;
	}

	@Override
	public <T> Boolean insertEntityBatch(List<T> entities) {
		for (int i = 0; i < entities.size(); i++) {
			getSession().save(entities.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		getSession().flush();
		getSession().clear();
		return true;
	}

	@Override
	public <T> Boolean insertEntityBatch(T... entities) {
		if (entities != null) {
			for (int i = 0; i < entities.length; i++) {
				getSession().save(entities[i]);
				if (i % 20 == 0) {
					// 20个对象后才清理缓存，写入数据库
					getSession().flush();
					getSession().clear();
				}
			}
		}
		getSession().flush();
		getSession().clear();
		return true;
	}

	@Override
	public <T> Boolean updateEntity(T entity) {
		getSession().update(entity);
		getSession().flush();
		return true;
	}

	@Override
	public <T> Boolean updateEntityBatch(List<T> entities) {
		for (int i = 0; i < entities.size(); i++) {
			getSession().update(entities.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		getSession().flush();
		getSession().clear();
		return true;
	}

	@Override
	public <T> Boolean updateEntityBatch(T... entities) {
		if (entities != null) {
			for (int i = 0; i < entities.length; i++) {
				getSession().update(entities[i]);
				if (i % 20 == 0) {
					// 20个对象后才清理缓存，写入数据库
					getSession().flush();
					getSession().clear();
				}
			}
		}
		getSession().flush();
		getSession().clear();
		return true;
	}

	@Override
	public <T> Boolean saveEntity(T entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
		return true;
	}

	@Override
	public <T> Boolean saveEntityBatch(List<T> entities) {
		for (int i = 0; i < entities.size(); i++) {
			getSession().saveOrUpdate(entities.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		getSession().flush();
		getSession().clear();
		return true;
	}

	@Override
	public <T> Boolean saveEntityBatch(T... entities) {
		if (entities != null) {
			for (int i = 0; i < entities.length; i++) {
				getSession().saveOrUpdate(entities[i]);
				if (i % 20 == 0) {
					// 20个对象后才清理缓存，写入数据库
					getSession().flush();
					getSession().clear();
				}
			}
		}
		getSession().flush();
		getSession().clear();
		return true;
	}

	@Override
	public <T> Boolean deleteEntity(T entity) {
		getSession().delete(entity);
		getSession().flush();
		return true;
	}

	@Override
	public <T> Boolean deleteEntityById(Class<T> entityClass, Serializable id) {
		deleteEntity(expandEntity(entityClass, id));
		getSession().flush();
		return true;
	}

	@Override
	public <T> Boolean deleteEntityBatch(List<T> entities) {
		for (T entity : entities) {
			getSession().delete(entity);
			getSession().flush();
		}
		return true;
	}

	@Override
	public <T> Boolean deleteEntityBatch(T... entities) {
		if (entities != null) {
			for (int i = 0; i < entities.length; i++) {
				getSession().delete(entities[i]);
				getSession().flush();
			}
		}
		return true;
	}

	@Override
	public Integer runUpdateByHql(String hql) {
		Query q = getSession().createQuery(hql);
		return q.executeUpdate();
	}

	@Override
	public Integer runUpdateByHql(String hql, List<Object> param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Integer runUpdateByHql(String hql, Object... param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Integer runUpdateByHql(String hql, Map<String, Object> param) {
		Query q = getSession().createQuery(hql);

		if (param != null && param.size() > 0) {
			q.setProperties(param);
		}
		return q.executeUpdate();
	}

	@Override
	public Integer runUpdateBySql(String sql) {
		Query querys = getSession().createSQLQuery(sql);
		return querys.executeUpdate();
	}

	@Override
	public Integer runUpdateBySql(String sql, List<Object> param) {
		if (param != null && param.size() > 0) {
			return this.jdbcTemplate.update(sql, param.toArray());// 王杰飞 20150121 增加 转换list为数组
		} else {
			return this.jdbcTemplate.update(sql, param);
		}
	}

	@Override
	public Integer runUpdateBySql(String sql, Object... param) {
		return this.jdbcTemplate.update(sql, param);
	}

	@Override
	public Integer runUpdateBySql(String sql, Map<String, Object> param) {
		return this.namedParameterJdbcTemplate.update(sql, param);
	}

	@Override
	public <T> T expandEntity(Class<T> entityClass, Serializable id) {
		T t = (T) getSession().get(entityClass, id);
		if (t != null) {
			getSession().flush();
		}
		return t;
	}

	@Override
	public <T> T expandEntityByProperty(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName, "没有这个属性");
		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
	}

	@Override
	public <T> T expandEntityByHql(String hql) {
		T t = null;
		String hqlCountString = getCountString(hql);
		Query queryCount = getSession().createQuery(hqlCountString);
		int allCounts = ((Long) queryCount.uniqueResult()).intValue();

		if (allCounts == 0) {
			return t;
		} else if (allCounts == 1) {
			Query queryObject = getSession().createQuery(hql);
			return (T) queryObject.uniqueResult();
		} else {
			throw new RuntimeException("查询结果数:" + allCounts + "大于1");
		}
	}

	@Override
	public <T> T expandEntityByHql(String hql, List<Object> param) {
		T t = null;
		// 如果为空直接返回
		if (param == null || param.size() == 0) {
			return t;
		}
		// 查询总数
		String hqlCountString = getCountString(hql);
		Query queryCount = getSession().createQuery(hqlCountString);

		for (int i = 0; i < param.size(); i++) {
			queryCount.setParameter(i, param.get(i));
		}
		int allCounts = ((Long) queryCount.uniqueResult()).intValue();
		// 查询
		if (allCounts == 0) {
			return t;
		} else if (allCounts == 1) {
			Query queryObject = getSession().createQuery(hql);
			for (int i = 0; i < param.size(); i++) {
				queryObject.setParameter(i, param.get(i));
			}
			return (T) queryObject.uniqueResult();
		} else {
			throw new RuntimeException("查询结果数:" + allCounts + "大于1");
		}
	}

	@Override
	public <T> T expandEntityByHql(String hql, Object... param) {
		T t = null;
		// 如果为空直接返回
		if (param == null || param.length == 0) {
			return t;
		}
		// 查询总数
		String hqlCountString = getCountString(hql);
		Query queryCount = getSession().createQuery(hqlCountString);

		for (int i = 0; i < param.length; i++) {
			queryCount.setParameter(i, param[i]);
		}
		int allCounts = ((Long) queryCount.uniqueResult()).intValue();
		// 查询
		if (allCounts == 0) {
			return t;
		} else if (allCounts == 1) {
			Query queryObject = getSession().createQuery(hql);
			for (int i = 0; i < param.length; i++) {
				queryObject.setParameter(i, param[i]);
			}
			return (T) queryObject.uniqueResult();
		} else {
			throw new RuntimeException("查询结果数:" + allCounts + "大于1");
		}
	}

	@Override
	public <T> T expandEntityByHql(String hql, Map<String, Object> param) {
		T t = null;
		// 如果为空直接返回
		if (param == null || param.isEmpty() || param.size() == 0) {
			return t;
		}
		// 查询总数
		String hqlCountString = getCountString(hql);
		Query queryCount = getSession().createQuery(hqlCountString);
		queryCount.setProperties(param);
		int allCounts = ((Long) queryCount.uniqueResult()).intValue();
		// 查询
		if (allCounts == 0) {
			return t;
		} else if (allCounts == 1) {
			Query queryObject = getSession().createQuery(hql);
			queryObject.setProperties(param);
			return (T) queryObject.uniqueResult();
		} else {
			throw new RuntimeException("查询结果数:" + allCounts + "大于1");
		}
	}

	@Override
	public Map<String, Object> expandMapBySql(String sql) {
		try {
			return this.jdbcTemplate.queryForMap(sql);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> expandMapBySql(String sql, List<Object> param) {
		try {
			return this.jdbcTemplate.queryForMap(sql, param.toArray());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> expandMapBySql(String sql, Object... param) {
		try {
			return this.jdbcTemplate.queryForMap(sql, param);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Map<String, Object> expandMapBySql(String sql, Map<String, Object> param) {
		try {
			return this.namedParameterJdbcTemplate.queryForMap(sql, param);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Map<Object, Object> queryMapByHql(String hql) {
		Query query = getSession().createQuery(hql);
		List<?> list = query.list();
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0] + "", tm[1] + "");
		}
		return map;
	}

	@Override
	public Map<Object, Object> queryMapByHql(String hql, List<Object> param) {
		Query query = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}
		List<?> list = query.list();

		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0] + "", tm[1] + "");
		}
		return map;
	}

	@Override
	public Map<Object, Object> queryMapByHql(String hql, Object... param) {
		Query query = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}
		List<?> list = query.list();

		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0] + "", tm[1] + "");
		}
		return map;
	}

	@Override
	public Map<Object, Object> queryMapByHql(String hql, Map<String, Object> param) {
		Query query = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			query.setProperties(param);
		}
		List<?> list = query.list();

		Map<Object, Object> map = new HashMap<Object, Object>();
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0] + "", tm[1] + "");
		}
		return map;
	}

	@Override
	public Map<Object, Object> queryMapBySql(String sql) {
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		for (Map<String, Object> map : list) {
			Object[] values = map.values().toArray();
			rtnMap.put(values[0] + "", values[1] + "");
		}
		return rtnMap;
	}

	@Override
	public Map<Object, Object> queryMapBySql(String sql, List<Object> param) {
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, param.toArray());
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		for (Map<String, Object> map : list) {
			Object[] values = map.values().toArray();
			rtnMap.put(values[0] + "", values[1] + "");
		}
		return rtnMap;
	}

	@Override
	public Map<Object, Object> queryMapBySql(String sql, Object... param) {
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, param);
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		for (Map<String, Object> map : list) {
			Object[] values = map.values().toArray();
			rtnMap.put(values[0] + "", values[1] + "");
		}
		return rtnMap;
	}

	@Override
	public Map<Object, Object> queryMapBySql(String sql, Map<String, Object> param) {
		List<Map<String, Object>> list = this.namedParameterJdbcTemplate.queryForList(sql, param);
		Map<Object, Object> rtnMap = new HashMap<Object, Object>();
		for (Map<String, Object> map : list) {
			Object[] values = map.values().toArray();
			rtnMap.put(values[0] + "", values[1] + "");
		}
		return rtnMap;
	}

	@Override
	public <T> List<T> queryListByClass(Class<T> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		return criteria.list();
	}

	@Override
	public <T> List<T> queryListByHql(String hql) {
		Query queryObject = getSession().createQuery(hql);
		List<T> list = queryObject.list();
		if (list.size() > 0) {
			getSession().flush();
		}
		return list;
	}

	@Override
	public <T> List<T> queryListByHql(String hql, List<Object> param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}

	@Override
	public <T> List<T> queryListByHql(String hql, Object... param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.list();
	}

	@Override
	public <T> List<T> queryListByHql(String hql, Map<String, Object> param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			q.setProperties(param);
		}
		return q.list();
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String sql) {
		return this.jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String sql, List<Object> param) {
		return this.jdbcTemplate.queryForList(sql, param.toArray());
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String sql, Object... param) {
		return this.jdbcTemplate.queryForList(sql, param);
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> param) {
		return this.namedParameterJdbcTemplate.queryForList(sql, param);
	}

	@Override
	public <T> List<T> queryListByProperty(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName, "没有这个属性");
		return (List<T>) createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
	}

	@Override
	public <T> List<T> queryListByPropertyWithOrder(Class<T> entityClass, String propertyName, Object value,
			boolean isAsc, String orderField) {
		Assert.hasText(propertyName, "没有这个属性");
		return createCriteria(entityClass, isAsc, orderField, Restrictions.eq(propertyName, value)).list();
	}

	@Override
	public <T> List<T> queryListByEntity(Class<T> entityClass, Object exampleEntity) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		Criteria executableCriteria = (entityClass != null ? getSession().createCriteria(entityClass)
				: getSession().createCriteria(exampleEntity.getClass()));
		executableCriteria.add(Example.create(exampleEntity));
		return executableCriteria.list();
	}

	@Override
	public <T> Integer countByClass(Class<T> clazz) {
		return DataAccessUtils.intResult(getSession().createQuery("select count(*) from " + clazz.getName()).list());
	}

	@Override
	public Integer countByHql(String hql) {
		Query query = getSession().createQuery(hql);
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public Integer countByHql(String hql, List<Object> param) {
		Query query = getSession().createQuery(hql);

		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}

		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public Integer countByHql(String hql, Object... param) {
		Query query = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				query.setParameter(i, param[i]);
			}
		}

		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public Integer countByHql(String hql, Map<String, Object> param) {
		Query query = getSession().createQuery(hql);

		if (param != null && param.size() > 0) {
			query.setProperties(param);
		}

		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public Integer countBySql(String sql) {
		return this.jdbcTemplate.queryForObject(sql, java.lang.Integer.class);
	}

	@Override
	public Integer countBySql(String sql, List<Object> param) {
		return this.jdbcTemplate.queryForObject(sql, param.toArray(), java.lang.Integer.class);
	}

	@Override
	public Integer countBySql(String sql, Object... param) {
		return this.jdbcTemplate.queryForObject(sql, param, java.lang.Integer.class);
	}

	@Override
	public Integer countBySql(String sql, Map<String, Object> param) {
		return this.namedParameterJdbcTemplate.queryForObject(sql, param, java.lang.Integer.class);
	}

	/**
	 * 创建Criteria对象，有排序功能
	 * 
	 * @param entityClass
	 * @param orderField
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc, String orderField,
			Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderField));
		} else {
			criteria.addOrder(Order.desc(orderField));
		}
		return criteria;
	}

	/**
	 * 创建Criteria对象带属性比较
	 * 
	 * @param             <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			if (c != null) {
				criteria.add(c);
			}
		}
		return criteria;
	}

	/**
	 * 创建单一Criteria对象
	 * 
	 * @param             <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}

	/**
	 * 替换SQL关键字
	 * 
	 * @param queryString
	 * @return
	 */
	private String getCountString(String queryString) {
		int index_low = queryString.indexOf("from");
		int index_uper = queryString.indexOf("FROM");

		if (index_low != -1)
			queryString = "select count(*) " + queryString.substring(index_low);
		if (index_uper != -1)
			queryString = "select count(*) " + queryString.substring(index_uper);

		int _low = queryString.lastIndexOf("order by");
		int _uper = queryString.lastIndexOf("ORDER BY");

		if (_low != -1)
			queryString = queryString.substring(0, _low);
		if (_uper != -1)
			queryString = queryString.substring(0, _uper);
		return queryString;

	}

	@Override
	public <T> String queryUniqueByHql(String hql) {
		Query queryObject = getSession().createQuery(hql);
		List<T> list = queryObject.list();

		int len = list.size();

		if (list.size() > 0) {
			getSession().flush();
		}

		if (len > 1) {
			throw new RuntimeException("查询结果数:" + len + "大于1");
		}

		return list.size() > 0 ? list.get(0).toString() : "";
	}

	@Override
	public <T> String queryUniqueByHql(String hql, Object... param) {
		Query q = getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		List<T> list = q.list();
		int len = list.size();
		if (len > 0) {
			getSession().flush();
		}

		if (len > 1) {
			throw new RuntimeException("查询结果数:" + len + "大于1");
		}

		return list.size() > 0 ? list.get(0).toString() : "";
	}

	@Override
	public String queryUniqueBySql(String sql, Object... param) {
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql, param);

		int len = list.size();

		if (len > 1) {
			throw new RuntimeException("查询结果数:" + len + "大于1");
		}

		for (Map<String, Object> map : list) {
			Object[] values = map.values().toArray();
			return values[0].toString();
		}
		return "";
	}

	@Override
	public String queryUniqueBySql(String sql) {
		List<Map<String, Object>> list = this.jdbcTemplate.queryForList(sql);

		int len = list.size();
		if (len > 1) {
			throw new RuntimeException("查询结果数:" + len + "大于1");
		}

		for (Map<String, Object> map : list) {
			Object[] values = map.values().toArray();
			return values[0].toString();
		}
		return "";
	}

	@Override
	public <T> PageVO<T> queryPageByHql(String hql, Map<String, Object> param, int pageNo, int pageSize) {
		String countHql = getCountString(hql);
		Query query = getSession().createQuery(hql);
		Query queryCount = getSession().createQuery(countHql);
		
		if (param != null && param.size() > 0){
			queryCount.setProperties(param);
			query.setProperties(param);
		}
		
		Long total = (Long)queryCount.uniqueResult();
		if (pageNo != -1) {
			Integer index = (pageNo - 1) * pageSize;
			query.setFirstResult(index);
			query.setMaxResults(pageSize);
			return PageVO.builder().data(query.list()).index(index.longValue()).pageNo(pageNo).pageSize(pageSize).total(total).build();
		} else {
			return PageVO.builder().data(query.list()).index(new Long(0)).pageNo(pageNo).pageSize(pageSize).total(total).build();
		}
	}
	
	

}
