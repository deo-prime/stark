package com.deo.stark.sys.upload.service;

import com.deo.stark.base.service.BaseService;
import com.deo.stark.sys.upload.entity.UploadEntity;

public interface UploadService extends BaseService {

	String add(String originFileName, String ossFileName, String type);
	
	UploadEntity getOSSFile(String id);
	
	String getOSSFileName(String id);
}
