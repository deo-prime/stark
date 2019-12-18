package com.deo.stark.sys.role.vo;

import java.io.Serializable;

public class RoleVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4784545183054660082L;

	private String id;
	private String code;
	private String name;
	private String functionIds;

	public String getFunctionIds() {
		return functionIds;
	}

	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
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

}
