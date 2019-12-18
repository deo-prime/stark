package com.deo.stark.sys.user.service;

import java.util.List;
import java.util.Map;

import com.deo.stark.base.service.BaseService;
import com.deo.stark.base.vo.DataGridVO;
import com.deo.stark.sys.function.vo.FunctionVO;
import com.deo.stark.sys.user.vo.UserRegisterVO;
import com.deo.stark.sys.user.vo.UserVO;

public interface UserService extends BaseService {

	UserVO check(String username, String password);
	
	DataGridVO<UserVO> grid(int page, int rows, Map<String, Object> parameter);
	
	void add(UserRegisterVO vo);
	
	void edit(UserRegisterVO vo);
	
	void delete(String userIds);
	
	UserVO detail(String userId);
	
	List<FunctionVO> getAuth(String userId);
	
	void updatePassword(String userId, String oldPassword, String password);
	
}
