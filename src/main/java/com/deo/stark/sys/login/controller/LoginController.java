package com.deo.stark.sys.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deo.stark.base.context.Global;
import com.deo.stark.base.context.SessionContext;
import com.deo.stark.base.vo.BasicResponseVO;
import com.deo.stark.sys.region.service.RegionService;
import com.deo.stark.sys.user.service.UserService;
import com.deo.stark.sys.user.vo.UserVO;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private RegionService regionService;

	@ResponseBody
	@RequestMapping("/web")
	public BasicResponseVO<?> web(HttpServletRequest request) {
		BasicResponseVO<?> result = null;
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
				throw new RuntimeException("请填写所有信息后登录");
			}

			// check user
			UserVO userVO = userService.check(username, password);

			SessionContext.setAttribute(request.getSession(), Global.SESSION_USERNAME, username);
			SessionContext.setAttribute(request.getSession(), Global.SESSION_USERID, userVO.getId());
			SessionContext.setAttribute(request.getSession(), Global.SESSION_USERREGIONCODE, userVO.getRegionCode());
			
			if (null != userVO.getRegionCode() && !"".equals(userVO.getRegionCode())) {
				SessionContext.setAttribute(request.getSession(), Global.SESSION_USERREGIONCODE_IN, regionService.getAllChildRegionCode(userVO.getRegionCode(), true));
			}

			// load authority in session
			// int status = registerService.getStatus(phone);

			result = new BasicResponseVO<Integer>(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}

}
