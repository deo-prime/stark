package com.deo.stark.sys.role.service;

import java.util.List;
import java.util.Map;

import com.deo.stark.base.service.BaseService;
import com.deo.stark.base.vo.DataGridVO;
import com.deo.stark.sys.role.vo.RoleVO;

public interface RoleService extends BaseService {

	List<RoleVO> getAll();

	DataGridVO<RoleVO> grid(int page, int rows, Map<String, Object> parameter);

	void add(RoleVO vo);

	void edit(RoleVO vo);

	void delete(String roleIds);

	RoleVO detail(String roleId);
}
