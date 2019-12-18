package com.deo.stark.base.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.deo.stark.base.vo.PageVO;

/**
 * 功能描述:公共Dao接口
 */
public interface CommonDao {

	/**
	 * 获取Session
	 * 
	 * @return
	 */
	public <T> Session getSession();

	/**
	 * 根据传入的实体持久化对象
	 * 
	 * @param entity 实体对象
	 * @return Boolean
	 */
	public <T> Boolean insertEntity(T entity);

	/**
	 * 批量保存数据
	 * 
	 * @param entities 实体对象List
	 * @return Boolean
	 */
	public <T> Boolean insertEntityBatch(List<T> entities);

	/**
	 * 批量保存数据
	 * 
	 * @param entities
	 * @return Boolean
	 */
	@SuppressWarnings("unchecked")
	public <T> Boolean insertEntityBatch(T... entities);

	/**
	 * 更新指定的实体
	 * 
	 * @param entity 实体对象
	 * @return Boolean
	 */
	public <T> Boolean updateEntity(T entity);

	/**
	 * 批量更新数据
	 * 
	 * @param entities 实体对象List
	 * @return Boolean
	 */
	public <T> Boolean updateEntityBatch(List<T> entities);

	/**
	 * 批量更新数据
	 * 
	 * @param entities 实体
	 * @return Boolean
	 */
	@SuppressWarnings("unchecked")
	public <T> Boolean updateEntityBatch(T... entities);

	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param entity 实体对象
	 * @return Boolean
	 */
	public <T> Boolean saveEntity(T entity);

	/**
	 * 根据传入的List批量添加或更新对象
	 * 
	 * @param entities 实体对象List
	 * @return Boolean
	 */
	public <T> Boolean saveEntityBatch(List<T> entities);

	/**
	 * 批量添加或更新对象
	 * 
	 * @param entities 实体对象
	 * @return Boolean
	 */
	@SuppressWarnings("unchecked")
	public <T> Boolean saveEntityBatch(T... entities);

	/**
	 * 删除实体
	 * 
	 * @param entity 实体对象
	 * @return Boolean
	 */
	public <T> Boolean deleteEntity(T entity);

	/**
	 * 根据主键删除指定的实体
	 * 
	 * @param entityClass 实体对象class
	 * @param id          主键
	 * @return Boolean
	 */
	public <T> Boolean deleteEntityById(Class<T> entityClass, Serializable id);

	/**
	 * 批量删除数据
	 * 
	 * @param entities 实体对象集合
	 * @return Boolean
	 */
	public <T> Boolean deleteEntityBatch(List<T> entities);

	/**
	 * 批量删除数据
	 * 
	 * @param entities 实体
	 * @return Boolean
	 */
	@SuppressWarnings("unchecked")
	public <T> Boolean deleteEntityBatch(T... entities);

	/**
	 * 执行HQL语句操作更新
	 * 
	 * @param hql HQL语句
	 * @return Integer 受影响条数
	 */
	public Integer runUpdateByHql(String hql);

	/**
	 * 执行hql语句操作更新 占位符参数
	 * 
	 * @param hql   HQL语句
	 * @param param 占位符参数List
	 * @return Integer 受影响条数
	 */
	public Integer runUpdateByHql(String hql, List<Object> param);

	/**
	 * 执行hql语句操作更新 动态对象参数
	 * 
	 * @param hql   HQL语句
	 * @param param 动态对象参数
	 * @return Integer 受影响条数
	 */
	public Integer runUpdateByHql(String hql, Object... param);

	/**
	 * 执行hql语句操作更新 MAP参数
	 * 
	 * @param hql   HQL语句
	 * @param param MAP参数
	 * @return Integer 受影响条数
	 */
	public Integer runUpdateByHql(String hql, Map<String, Object> param);

	/**
	 * 执行sql语句操作更新
	 * 
	 * @param sql SQL语句
	 * @return Integer 受影响条数
	 */
	public Integer runUpdateBySql(String sql);

	/**
	 * 执行sql语句操作更新 占位符参数
	 * 
	 * @param sql   SQL语句
	 * @param param 占位符参数List
	 * @return Integer 受影响条数
	 */
	public Integer runUpdateBySql(String sql, List<Object> param);

	/**
	 * 执行sql语句操作更新 动态对象参数
	 * 
	 * @param sql   SQL语句
	 * @param param 动态对象参数
	 * @return Integer 受影响条数
	 */
	public Integer runUpdateBySql(String sql, Object... param);

	/**
	 * 执行sql语句操作更新 MAP参数
	 * 
	 * @param sql   SQL语句
	 * @param param MAP参数
	 * @return Integer 受影响条数
	 */
	public Integer runUpdateBySql(String sql, Map<String, Object> param);

	/**
	 * 根据实体名称和主键获取实体
	 * 
	 * @param entityClass 实体对象class
	 * @param id          主键
	 * @return T
	 */
	public <T> T expandEntity(Class<T> entityClass, Serializable id);

	/**
	 * 根据实体名字获取唯一记录
	 * 
	 * @param entityClass  实体对象class
	 * @param propertyName 实体对象属性
	 * @param value        属性值
	 * @return T
	 */
	public <T> T expandEntityByProperty(Class<T> entityClass, String propertyName, Object value);

	/**
	 * 通过hql查询唯一对象
	 * 
	 * @param hql HQL语句
	 * @return T
	 */
	public <T> T expandEntityByHql(String hql);

	/**
	 * 通过hql查询唯一对象 占位符参数
	 * 
	 * @param hql   HQL语句
	 * @param param 占位符参数List
	 * @return T
	 */
	public <T> T expandEntityByHql(String hql, List<Object> param);

	/**
	 * 通过hql查询唯一对象 动态对象参数
	 * 
	 * @param hql   HQL语句
	 * @param param 动态对象参数
	 * @return T
	 */
	public <T> T expandEntityByHql(String hql, Object... param);

	/**
	 * 通过hql查询唯一对象 MAP参数
	 * 
	 * @param hql   HQL语句
	 * @param param MAP参数
	 * @return T
	 */
	public <T> T expandEntityByHql(String hql, Map<String, Object> param);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * 
	 * @param sql SQL语句
	 * @return Map
	 */
	public Map<String, Object> expandMapBySql(String sql);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * 
	 * @param sql   SQL语句
	 * @param param list参数
	 * @return Map
	 */
	public Map<String, Object> expandMapBySql(String sql, List<Object> param);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * 
	 * @param sql   SQL语句
	 * @param param 动态OBJ对象
	 * @return Map
	 */
	public Map<String, Object> expandMapBySql(String sql, Object... param);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * 
	 * @param sql   SQL语句
	 * @param param Map参数
	 * @return Map
	 */
	public Map<String, Object> expandMapBySql(String sql, Map<String, Object> param);

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param hql HQL语句
	 * @return Map
	 */
	public Map<Object, Object> queryMapByHql(String hql);

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param hql   HQL语句
	 * @param param list对象
	 * @return Map
	 */
	public Map<Object, Object> queryMapByHql(String hql, List<Object> param);

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param hql   HQL语句
	 * @param param list对象
	 * @return Map
	 */
	public Map<Object, Object> queryMapByHql(String hql, Object... param);

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param hql   HQL语句
	 * @param param map对象
	 * @return Map
	 */
	public Map<Object, Object> queryMapByHql(String hql, Map<String, Object> param);

	/**
	 * 通过sql 查询语句查找HashMap对象
	 * 
	 * @param sql SQL语句
	 * @return Map
	 */
	public Map<Object, Object> queryMapBySql(String sql);

	/**
	 * 通过sql 查询语句查找HashMap对象
	 * 
	 * @param sql   SQL语句
	 * @param param list对象
	 * @return Map
	 */
	public Map<Object, Object> queryMapBySql(String sql, List<Object> param);

	/**
	 * 通过sql 查询语句查找HashMap对象
	 * 
	 * @param sql   SQL语句
	 * @param param list对象
	 * @return Map
	 */
	public Map<Object, Object> queryMapBySql(String sql, Object... param);

	/**
	 * 通过sql 查询语句查找HashMap对象
	 * 
	 * @param sql   SQL语句
	 * @param param map对象
	 * @return Map
	 */
	public Map<Object, Object> queryMapBySql(String sql, Map<String, Object> param);

	/**
	 * 加载全部实体
	 * 
	 * @param entityClass 实体对象class
	 * @return List
	 */
	public <T> List<T> queryListByClass(final Class<T> entityClass);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param hql HQL语句
	 * @return List
	 */
	public <T> List<T> queryListByHql(String hql);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param hql   HQL语句
	 * @param param list参数
	 * @return List
	 */
	public <T> List<T> queryListByHql(String hql, List<Object> param);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param hql   HQL语句
	 * @param param 动态OBJ对象
	 * @return List
	 */
	public <T> List<T> queryListByHql(String hql, Object... param);

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param hql   HQL语句
	 * @param param map参数
	 * @return List
	 */
	public <T> List<T> queryListByHql(String hql, Map<String, Object> param);

	/**
	 * 根据sql查找List
	 * 
	 * @param sql SQL语句
	 * @return List
	 */
	public List<Map<String, Object>> queryListBySql(String sql);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * 
	 * @param sql   SQL语句
	 * @param param list对象
	 * @return List
	 */
	public List<Map<String, Object>> queryListBySql(String sql, List<Object> param);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * 
	 * @param sql   SQL语句
	 * @param param 动态OBJ对象
	 * @return List
	 */
	public List<Map<String, Object>> queryListBySql(String sql, Object... param);

	/**
	 * 通过JDBC查找对象集合 使用指定的检索标准检索数据返回数据
	 * 
	 * @param sql   SQL语句
	 * @param param map对象
	 * @return List
	 */
	public List<Map<String, Object>> queryListBySql(String sql, Map<String, Object> param);

	/**
	 * 根据实体对象属性查找对象列表
	 * 
	 * @param entityClass  实体对象class
	 * @param propertyName 实体对象属性
	 * @param value        属性值
	 * @return List
	 */
	public <T> List<T> queryListByProperty(Class<T> entityClass, String propertyName, Object value);

	/**
	 * 根据实体对象属性查找对象列表
	 * 
	 * @param entityClass  实体对象class
	 * @param propertyName 实体对象属性
	 * @param value        属性值
	 * @param isAsc
	 * @return List
	 */
	public <T> List<T> queryListByPropertyWithOrder(Class<T> entityClass, String propertyName, Object value,
			boolean isAsc, String orderField);

	/**
	 * 根据实体模版查找
	 * 
	 * @param entityName
	 * @param entityClass
	 * @return List
	 */
	public <T> List<T> queryListByEntity(final Class<T> entityClass, final Object exampleEntity);

	/**
	 * 查询数据记录数
	 * 
	 * @param clazz
	 * @return Integer 总条数
	 */

	public <T> Integer countByClass(Class<T> clazz);

	/**
	 * 查询数据记录数
	 * 
	 * @param hql HQL语句
	 * @return Integer 总条数
	 */
	public Integer countByHql(String hql);

	/**
	 * 查询数据记录数
	 * 
	 * @param hql   HQL语句
	 * @param param list参数
	 * @return Integer 总条数
	 */
	public Integer countByHql(String hql, List<Object> param);

	/**
	 * 查询数据记录数
	 * 
	 * @param hql   HQL语句
	 * @param param 动态OBJ
	 * @return Integer 总条数
	 */
	public Integer countByHql(String hql, Object... param);

	/**
	 * 查询数据记录数
	 * 
	 * @param hql   HQL语句
	 * @param param map参数
	 * @return Integer 总条数
	 */
	public Integer countByHql(String hql, Map<String, Object> param);

	/**
	 * 查询数据记录数
	 * 
	 * @param sql SQL语句
	 * @return Integer 总条数
	 */
	public Integer countBySql(String sql);

	/**
	 * 查询数据记录数
	 * 
	 * @param sql   SQL语句
	 * @param param list参数
	 * @return Integer 总条数
	 */
	public Integer countBySql(String sql, List<Object> param);

	/**
	 * 查询数据记录数
	 * 
	 * @param sql   SQL语句
	 * @param param 动态OBJ
	 * @return Integer 总条数
	 */
	public Integer countBySql(String sql, Object... param);

	/**
	 * 查询数据记录数
	 * 
	 * @param sql   SQL语句
	 * @param param map参数
	 * @return Integer 总条数
	 */
	public Integer countBySql(String sql, Map<String, Object> param);

	/**
	 * 获取唯一值
	 * 
	 * @Title: queryUniqueBySql
	 * @Description: [获取唯一值]
	 * @param sql
	 * @return
	 */
	public String queryUniqueBySql(String sql, Object... param);

	/**
	 * 获取唯一值
	 * 
	 * @Title: queryUniqueBySql
	 * @Description: [获取唯一值]
	 * @param sql
	 * @return
	 */
	public String queryUniqueBySql(String sql);

	/**
	 * 获取唯一值
	 * 
	 * @Title: queryUniqueBySql
	 * @Description: [获取唯一值]
	 * @param sql
	 * @return
	 */
	public <T> String queryUniqueByHql(String hql);

	/**
	 * 获取唯一值
	 * 
	 * @Title: queryUniqueBySql
	 * @Description: [获取唯一值]
	 * @param sql
	 * @return
	 */
	public <T> String queryUniqueByHql(String hql, Object... param);
	
	/**
	 * 分页查询hql
	 * @param hql
	 * @param param
	 * @return
	 */
	public <T> PageVO<T> queryPageByHql(String hql, Map<String, Object> param, int pageNo, int pageSize);

}
