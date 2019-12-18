package com.deo.stark.sys.region.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deo.stark.sys.region.service.RegionService;
import com.deo.stark.sys.region.vo.RegionVO;

@Controller
@RequestMapping("/sys/region")
public class RegionController {
	
	@Autowired
	private RegionService regionService;

	@ResponseBody
	@RequestMapping("/tree")
	public List<RegionVO> tree(String target, String parentCode) {
		List<RegionVO> result = new ArrayList<>();
		try {
			RegionVO regionVO = new RegionVO();
			regionVO.setCode("");
			regionVO.setName("请选择");
			result.add(regionVO);
			result.addAll(regionService.tree(target, parentCode));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
