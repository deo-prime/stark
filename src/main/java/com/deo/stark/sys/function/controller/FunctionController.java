package com.deo.stark.sys.function.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deo.stark.base.vo.BasicResponseVO;
import com.deo.stark.sys.function.service.FunctionService;
import com.deo.stark.sys.function.vo.FunctionVO;

@Controller
@RequestMapping("/sys/function")
public class FunctionController {
	
	@Autowired
	private FunctionService functionService;

	@ResponseBody
	@RequestMapping("/tree")
	public BasicResponseVO<?> tree() {
		BasicResponseVO<?> result = null;
		try {
			List<FunctionVO> all = functionService.all();
			all = functionService.transform(all);
			result = new BasicResponseVO<>(true, "操作成功", all);
		} catch (Exception e) {
			e.printStackTrace();
			result = new BasicResponseVO<>(false, "操作失败: " + e.getMessage());
		}
		
		return result;
	}
	
	
	
	
}
