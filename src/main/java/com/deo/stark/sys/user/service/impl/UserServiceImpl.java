package com.deo.stark.sys.user.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deo.stark.base.service.impl.BaseServiceImpl;
import com.deo.stark.base.util.IDUtil;
import com.deo.stark.base.vo.DataGridVO;
import com.deo.stark.sys.function.service.FunctionService;
import com.deo.stark.sys.function.vo.FunctionVO;
import com.deo.stark.sys.region.service.RegionService;
import com.deo.stark.sys.region.vo.RegionVO;
import com.deo.stark.sys.user.service.UserService;
import com.deo.stark.sys.user.vo.UserRegisterVO;
import com.deo.stark.sys.user.vo.UserVO;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private RegionService regionService;
	
	private void preCheck(UserRegisterVO vo) {
		if (StringUtils.isBlank(vo.getUsername())) {
			throw new RuntimeException("用户名不能为空");
		}
		
		if (StringUtils.isBlank(vo.getRegionCode())) {
			throw new RuntimeException("用户名所属地区不能为空");
		}
		
		// TODO
		// password cannot be empty
		// roleIds coannot be empty
		// every role id is available (in db)
	}
	
	@Override
	public void add(UserRegisterVO vo) {
		
		preCheck(vo);
		
		if (StringUtils.isNotEmpty(vo.getId())) {
			edit(vo);
			return;
		}
		
		if (StringUtils.isBlank(vo.getPassword())) {
			throw new RuntimeException("密码无效");
		}
		
		
		String checkSql = "select * from t_sys_user where username = ?";
		List<UserRegisterVO> query = query(UserRegisterVO.class, checkSql, vo.getUsername());
		if (CollectionUtils.isNotEmpty(query)) {
			throw new RuntimeException("用户名不允许重复");
		}
		
		String userId = IDUtil.generate();
		vo.setId(userId);
		
		String addSql = "insert into t_sys_user(id, username, password, regionCode, realname, status, lastUpdateTime) "
				+ " values (:id, :username, :password, :regionCode, :realname, '1', now());";
		
		String[] roleIdArray = vo.getRoleIdString().split(",");
		for (String roleId : roleIdArray) {
			String userRoleSql = "insert into t_sys_role_user(id, roleId, userId) values (?, ?, ?)";
			executeSql(userRoleSql, IDUtil.generate(), roleId, userId);
		}
		
		executeSqlWithObject(addSql, vo);
		
	}
	
	private void cleanUserRole(String userId) {
		String sql = "delete from t_sys_role_user where userId = ?";
		executeSql(sql, userId);
	}

	@Override
	public void edit(UserRegisterVO vo) {
		
		preCheck(vo);
		
		if (StringUtils.isBlank(vo.getId())) {
			throw new RuntimeException("提交数据非法，请刷新页面重试");
		}
		
		String checkSql = "select * from t_sys_user where username = ? and id != ?";
		List<Map<String,Object>> query = query(checkSql, vo.getUsername(), vo.getId());
		if (CollectionUtils.isNotEmpty(query)) {
			throw new RuntimeException("用户名不允许重复");
		}
		
		cleanUserRole(vo.getId());
		
		String sql = "select * from t_sys_user where id = ?";
		UserRegisterVO userInDB = expand(UserRegisterVO.class, sql, vo.getId());
		if (StringUtils.isBlank(vo.getPassword())) {
			vo.setPassword(userInDB.getPassword());
		}
		
		String updateSql = "update t_sys_user set username = :username, password = :password, regionCode = :regionCode, realname = :realname, lastUpdateTime = now() where id = :id";
		executeSqlWithObject(updateSql, vo);

		String[] roleIdArray = vo.getRoleIdString().split(",");
		for (String roleId : roleIdArray) {
			String userRoleSql = "insert into t_sys_role_user(id, roleId, userId) values (?, ?, ?)";
			executeSql(userRoleSql, IDUtil.generate(), roleId, vo.getId());
		}
	}

	@Override
	public void delete(String userIds) {
		String[] split = userIds.split(",");
		for (String userId : split) {
			String userRoleSql = "delete from t_sys_role_user where userId = ?";
			executeSql(userRoleSql, userId);
			
			String sql = "delete from t_sys_user where id = ?";
			executeSql(sql, userId);
		}
	}

	@Override
	public DataGridVO<UserVO> grid(int page, int rows, Map<String, Object> parameter) {
		String countSql = "select count(1) from t_sys_user where id != '1' ";
		String sql = "select * from t_sys_user where id != '1' ";
		sql += "limit :pageNo, :pageSize";
		
		parameter.put("pageNo", (page - 1) * rows);
		parameter.put("pageSize", rows);

		long count = count(countSql, parameter);
		List<UserVO> query = query(UserVO.class, sql, parameter);

		return new DataGridVO<UserVO>(count, query);
	}
	
	@Override
	public UserVO check(String username, String password) {
		String sql = "select * from t_sys_user where username = ? and password = ?";
		UserVO user = null;
		try {
			List<UserVO> query = query(UserVO.class, sql, username, password);
			if (CollectionUtils.isEmpty(query) || query.size() != 1) {
				throw new RuntimeException("用户验证失败，可能原因：用户名密码错误、用户被禁用");
			}
			user = query.get(0);
		} catch (Exception e) {
			throw new RuntimeException("用户验证失败，可能原因：用户名密码错误、用户被禁用");
		}
		return user;
		
	}

	@Override
	public UserVO detail(String userId) {
		String sql = "select * from t_sys_user where id = ?";
		UserVO userVO = expand(UserVO.class, sql, userId);
		sql = "select roleId from t_sys_role_user where userId = ?";
		List<String> query = queryForBasicType(String.class, sql, userId);
		userVO.setRoleIdString(StringUtils.join(query, ","));
		
		RegionVO[] hierarchical = regionService.hierarchical(userVO.getRegionCode());
		if (hierarchical[0] != null) {
			userVO.setProvinceCode(hierarchical[0].getCode());
			userVO.setProvinceName(hierarchical[0].getName());
		}
		if (hierarchical[1] != null) {
			userVO.setCityCode(hierarchical[1].getCode());
			userVO.setCityName(hierarchical[1].getName());
		}
		if (hierarchical[2] != null) {
			userVO.setCountyCode(hierarchical[2].getCode());
			userVO.setCountyName(hierarchical[2].getName());			
		}
		return userVO;
	}

	@Override
	public List<FunctionVO> getAuth(String userId) {
		String sql = "SELECT DISTINCT f.* FROM t_sys_role_user ru LEFT JOIN t_sys_role_function rf ON rf.roleId = ru.roleId LEFT JOIN t_sys_function f ON f.id = rf.functionId WHERE ru.userId = ? order by f.id";
		List<FunctionVO> query = query(FunctionVO.class, sql, userId);
		return functionService.transform(query);
	}

	@Override
	public void updatePassword(String userId, String oldPassword, String password) {
		String sql = "update t_sys_user set password = ? where id = ? and password = ?";
		int executeSql = executeSql(sql, password, userId, oldPassword);
		if (executeSql != 1) {
			throw new RuntimeException("修改密码失败，请输入正确的原密码");
		}
	}

}
