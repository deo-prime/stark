package com.deo.stark.sys.region.vo;

import java.io.Serializable;

public class RegionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8197257055815127717L;

	private String id;
	private String code;
	private String name;
	private String parentCode;
	private Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

}
