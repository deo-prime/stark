package com.deo.stark.sys.upload.entity;

import java.io.Serializable;

import com.deo.stark.base.entity.IDEntity;

public class UploadEntity extends IDEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2458107817375753210L;

	private String originFileName;
	private String ossFileName;
	private String type;
	private Character inUse;

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getOssFileName() {
		return ossFileName;
	}

	public void setOssFileName(String ossFileName) {
		this.ossFileName = ossFileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Character getInUse() {
		return inUse;
	}

	public void setInUse(Character inUse) {
		this.inUse = inUse;
	}

}
