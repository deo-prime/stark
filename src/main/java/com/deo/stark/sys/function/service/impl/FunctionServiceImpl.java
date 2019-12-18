package com.deo.stark.sys.function.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deo.stark.base.service.impl.BaseServiceImpl;
import com.deo.stark.sys.function.service.FunctionService;
import com.deo.stark.sys.function.vo.FunctionVO;

@Service
@Transactional
public class FunctionServiceImpl extends BaseServiceImpl implements FunctionService {

	@Override
	public List<FunctionVO> all() {
		String sql = "select * from t_sys_function";
		return query(FunctionVO.class, sql);
	}

	@Override
	public List<FunctionVO> transform(List<FunctionVO> src) {
		
		List<FunctionVO> result = new ArrayList<FunctionVO>();
		
		Map<String, FunctionVO> index = src.stream().collect(Collectors.toMap(e -> e.getId(), e -> e));
		
		for (FunctionVO vo : src) {
			if (index.containsKey(vo.getParentId())) {
				index.get(vo.getParentId()).getChildren().add(vo);
			} else {
				result.add(vo);
			}
			
		}
		
		return result;
	}
}
