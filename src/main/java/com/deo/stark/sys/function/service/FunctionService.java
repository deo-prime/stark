package com.deo.stark.sys.function.service;

import java.util.List;

import com.deo.stark.base.service.BaseService;
import com.deo.stark.sys.function.vo.FunctionVO;

public interface FunctionService extends BaseService {

	List<FunctionVO> all();
	
	List<FunctionVO> transform(List<FunctionVO> src);
}
