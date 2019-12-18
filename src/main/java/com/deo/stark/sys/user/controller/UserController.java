package com.deo.stark.sys.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deo.stark.base.context.Global;
import com.deo.stark.base.context.SessionContext;
import com.deo.stark.base.vo.BasicResponseVO;
import com.deo.stark.base.vo.DataGridVO;
import com.deo.stark.sys.function.vo.FunctionVO;
import com.deo.stark.sys.user.service.UserService;
import com.deo.stark.sys.user.vo.UserRegisterVO;
import com.deo.stark.sys.user.vo.UserVO;

@Controller
@RequestMapping("/sys/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@ResponseBody
	@RequestMapping("/grid")
	public DataGridVO<UserVO> grid(int page, int rows, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		/*String jyzxm = request.getParameter("jyzxm");
		String sfzh = request.getParameter("sfzh");
		String qymc = request.getParameter("qymc");
		
		if (StringUtils.isNotBlank(jyzxm)) {
			parameterMap.put("jyzxm", jyzxm);
		}
		
		if (StringUtils.isNotBlank(sfzh)) {
			parameterMap.put("sfzh", sfzh);
		}
		
		if (StringUtils.isNotBlank(qymc)) {
			parameterMap.put("qymc", qymc);
		}*/
		
		return userService.grid(page, rows, parameterMap);
	}
	
	@ResponseBody
	@RequestMapping("/updatePassword")
	public BasicResponseVO<?> updatePassword(String oldPassword, String password, HttpServletRequest request) {
		BasicResponseVO<?> result = null;
		try {
			if (StringUtils.isEmpty(password) || StringUtils.isEmpty(oldPassword)) {
				throw new RuntimeException("密码不能为空");
			}
			userService.updatePassword(SessionContext.getAttribute(request.getSession(), Global.SESSION_USERID), oldPassword, password);
			result = new BasicResponseVO<>(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/auth")
	public BasicResponseVO<?> auth(HttpServletRequest request) {
		BasicResponseVO<?> result = null;
		try {
			List<FunctionVO> auth = userService.getAuth(SessionContext.getAttribute(request.getSession(), Global.SESSION_USERID));
			result = new BasicResponseVO<>(true, "操作成功", auth);
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/detail")
	public BasicResponseVO<?> detail(String id) {
		BasicResponseVO<?> result = null;
		try {
			UserVO detail = userService.detail(id);
			result = new BasicResponseVO<>(true, "操作成功", detail);
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/add")
	public BasicResponseVO<?> add(UserRegisterVO vo) {
		BasicResponseVO<?> result = null;
		try {
			userService.add(vo);
			result = new BasicResponseVO<>(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public BasicResponseVO<?> edit(UserRegisterVO vo) {
		BasicResponseVO<?> result = null;
		try {
			userService.edit(vo);
			result = new BasicResponseVO<>(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public BasicResponseVO<?> delete(String userIds) {
		BasicResponseVO<?> result = null;
		try {
			userService.delete(userIds);
			result = new BasicResponseVO<>(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}
}
