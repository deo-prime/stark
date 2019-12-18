package com.deo.stark.sys.upload.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deo.stark.base.service.impl.BaseServiceImpl;
import com.deo.stark.base.util.IDUtil;
import com.deo.stark.sys.upload.entity.UploadEntity;
import com.deo.stark.sys.upload.service.UploadService;

@Service
@Transactional
public class UploadServiceImpl extends BaseServiceImpl implements UploadService {

	@Override
	public String add(String originFileName, String ossFileName, String type) {
		String id = IDUtil.generate();
		String sql = "insert into t_sys_oss_upload values (?, ?, ?, ?, '0', now())";
		executeSql(sql, id, originFileName, ossFileName, type);
		return id;
	}

	@Override
	public UploadEntity getOSSFile(String id) {
		String sql = "select * from t_sys_oss_upload where id = ?";
		return expand(UploadEntity.class, sql, id);
	}

	@Override
	public String getOSSFileName(String id) {
		return getOSSFile(id).getOssFileName();
	}

}
