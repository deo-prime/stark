package com.deo.stark.sys.role.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deo.stark.base.service.impl.BaseServiceImpl;
import com.deo.stark.base.util.IDUtil;
import com.deo.stark.base.vo.DataGridVO;
import com.deo.stark.sys.role.service.RoleService;
import com.deo.stark.sys.role.vo.RoleVO;

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {

	@Override
	public List<RoleVO> getAll() {
		String sql = "select * from t_sys_role where id != '1'";
		return query(RoleVO.class, sql);
	}

	@Override
	public DataGridVO<RoleVO> grid(int page, int rows, Map<String, Object> parameter) {
		String countSql = "select count(1) from t_sys_role where id != '1' ";
		String sql = "select * from t_sys_role where id != '1' ";
		sql += "limit :pageNo, :pageSize";
		
		parameter.put("pageNo", (page - 1) * rows);
		parameter.put("pageSize", rows);

		long count = count(countSql, parameter);
		List<RoleVO> query = query(RoleVO.class, sql, parameter);

		return new DataGridVO<RoleVO>(count, query);
	}
	
	private void preCheck(RoleVO vo) {
		if (StringUtils.isBlank(vo.getName())) {
			throw new RuntimeException("角色名称不能为空");
		}
		
		if (StringUtils.isBlank(vo.getCode())) {
			throw new RuntimeException("角色编码不能为空");
		}
		
		// TODO check functionId available
	}
	
	@Override
	public void add(RoleVO vo) {
		
		preCheck(vo);
		
		if (StringUtils.isNotEmpty(vo.getId())) {
			edit(vo);
			return;
		}
		
		String checkSql = "select * from t_sys_role where code = ?";
		List<RoleVO> query = query(RoleVO.class, checkSql, vo.getCode());
		if (CollectionUtils.isNotEmpty(query)) {
			throw new RuntimeException("角色不允许重复");
		}
		
		String roleId = IDUtil.generate();
		vo.setId(roleId);
		
		String addSql = "insert into t_sys_role(id, name, code) values (:id, :name, :code);";
		executeSqlWithObject(addSql, vo);
		
		String[] functionIdArray = vo.getFunctionIds().split(",");
		for (String functionId : functionIdArray) {
			String authSql = "insert into t_sys_role_function(id, roleId, functionId) values (?, ?, ?)";
			executeSql(authSql, IDUtil.generate(), roleId, functionId);
		}
		
	}
	
	private void cleanRoleFunction(String roleId) {
		String sql = "delete from t_sys_role_function where roleId = ?";
		executeSql(sql, roleId);
	}
	
	private void cleanRoleUser(String roleId) {
		String sql = "delete from t_sys_role_user where roleId = ?";
		executeSql(sql, roleId);
	}

	@Override
	public void edit(RoleVO vo) {
		
		preCheck(vo);
		
		if (StringUtils.isBlank(vo.getId())) {
			throw new RuntimeException("提交数据非法，请刷新页面重试");
		}
		
		String checkSql = "select * from t_sys_role where code = ? and id != ?";
		List<Map<String,Object>> query = query(checkSql, vo.getCode(), vo.getId());
		if (CollectionUtils.isNotEmpty(query)) {
			throw new RuntimeException("角色名称不允许重复");
		}
		
		String updateSql = "update t_sys_role set name = :name, code = :code where id = :id";
		executeSqlWithObject(updateSql, vo);
		
		cleanRoleFunction(vo.getId());
		String[] functionIdArray = vo.getFunctionIds().split(",");
		for (String functionId : functionIdArray) {
			String authSql = "insert into t_sys_role_function(id, roleId, functionId) values (?, ?, ?)";
			executeSql(authSql, IDUtil.generate(), vo.getId(), functionId);
		}
		
	}

	@Override
	public void delete(String roleIds) {
		String[] split = roleIds.split(",");
		for (String roleId : split) {
			cleanRoleUser(roleId);
			cleanRoleFunction(roleId);
			String sql = "delete from t_sys_role where id = ?";
			executeSql(sql, roleId);
		}
	}
	
	@Override
	public RoleVO detail(String roleId) {
		String sql = "select * from t_sys_role where id = ?";
		RoleVO roleVO = expand(RoleVO.class, sql, roleId);
		
		sql = "select functionId from t_sys_role_function rf left join t_sys_function fun on fun.id = rf.functionId where roleId = ? and fun.type = '2'";
		List<String> functionIdList = queryForBasicType(String.class, sql, roleId);
		roleVO.setFunctionIds(StringUtils.join(functionIdList, ","));
		
		return roleVO;
	}

}
