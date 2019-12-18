package com.deo.stark.sys.role.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deo.stark.base.vo.BasicResponseVO;
import com.deo.stark.base.vo.DataGridVO;
import com.deo.stark.sys.role.service.RoleService;
import com.deo.stark.sys.role.vo.RoleVO;

@Controller
@RequestMapping("/sys/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping("/all")
	public List<RoleVO> all() {
		return roleService.getAll();
	}
	
	@ResponseBody
	@RequestMapping("/grid")
	public DataGridVO<RoleVO> grid(int page, int rows, HttpServletRequest request, HttpServletResponse response) {
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
		
		return roleService.grid(page, rows, parameterMap);
	}
	
	@ResponseBody
	@RequestMapping("/detail")
	public BasicResponseVO<?> detail(String id) {
		BasicResponseVO<?> result = null;
		try {
			RoleVO detail = roleService.detail(id);
			result = new BasicResponseVO<>(true, "操作成功", detail);
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/add")
	public BasicResponseVO<?> add(RoleVO vo) {
		BasicResponseVO<?> result = null;
		try {
			roleService.add(vo);
			result = new BasicResponseVO<>(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public BasicResponseVO<?> edit(RoleVO vo) {
		BasicResponseVO<?> result = null;
		try {
			roleService.edit(vo);
			result = new BasicResponseVO<>(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public BasicResponseVO<?> delete(String roleIds) {
		BasicResponseVO<?> result = null;
		try {
			roleService.delete(roleIds);
			result = new BasicResponseVO<>(true, "操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, e.getMessage());
		}
		return result;
	}
	
}
