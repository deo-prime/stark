package com.deo.stark.sys.region.service;

import java.util.List;
import java.util.Set;

import com.deo.stark.base.service.BaseService;
import com.deo.stark.sys.region.vo.RegionVO;

public interface RegionService extends BaseService {

	List<RegionVO> tree(String target, String parentCode);
	
	Set<String> getAllChildRegionCode(String parentCode, boolean include);
	
	/**
	   * 获得参数机构所属的省市区
	 * @param code
	 * @return 结果为三个元素的数组，分别代表省市区，如果为参数为省级单位，则后两个元素为null
	 */
	RegionVO[] hierarchical(String code);

	RegionVO getRegionByCode(String code);
}
