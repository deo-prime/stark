package com.deo.stark.sys.region.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deo.stark.base.service.impl.BaseServiceImpl;
import com.deo.stark.sys.region.service.RegionService;
import com.deo.stark.sys.region.vo.RegionVO;

@Service
@Transactional
public class RegionServiceImpl extends BaseServiceImpl implements RegionService {

	@Override
	public List<RegionVO> tree(String target, String parentCode) {
		String sql = "select * from t_sys_region where 1=1 ";
		Map<String, Object> param = new HashMap<>();
		if (StringUtils.isNotBlank(parentCode)) {
			sql += " and parentCode = :parentCode";
			param.put("parentCode", parentCode);
		} else {
			sql += " and parentCode is null";
		}
		return query(RegionVO.class, sql, param);
	}

	@Override
	public Set<String> getAllChildRegionCode(String parentCode, boolean include) {
		Set<String> result = new HashSet<>();
		if (include) {
			result.add(parentCode);
		}
		String sql = "select code from t_sys_region where parentCode = ?";
		List<String> queryForBasicType = queryForBasicType(String.class, sql, parentCode);
		if (CollectionUtils.isNotEmpty(queryForBasicType)) {
			for (String childRegionCode : queryForBasicType) {
				result.addAll(getAllChildRegionCode(childRegionCode, true));
			}
		} 
		return result;
	}

	@Override
	public RegionVO getRegionByCode(String code) {
		String sql = "select * from t_sys_region where code = ?";
		RegionVO expand = expand(RegionVO.class, sql, code);
		return expand;
	}
	
	@Override
	public RegionVO[] hierarchical(String code) {
		RegionVO[] result = {null, null, null};
		String sql = "select * from t_sys_region where code = ?";
		RegionVO expand = expand(RegionVO.class, sql, code);
		if (expand == null) {
			return result;
		}
		
		RegionVO province = null;
		RegionVO city = null;
		
		Integer level = expand.getLevel();
		switch (level) {
		case 1:
			result[0] = expand;
			break;
		case 2:
			sql = "select * from t_sys_region where code = ?";
			province = expand(RegionVO.class, sql, expand.getParentCode());
			result[0] = province;
			result[1] = expand;
			break;
		case 3:
			sql = "select * from t_sys_region where code = ?";
			city = expand(RegionVO.class, sql, expand.getParentCode());
			if (city != null) {
				province = expand(RegionVO.class, sql, city.getParentCode());
			}
			result[0] = province;
			result[1] = city;
			result[2] = expand;
			break;
		default:
			break;
		}
		
		return result;
	}

}
