package com.deo.stark.sys.function.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FunctionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4998830070364494688L;

	private String id;
	private String code;
	private String name;
	private String url;
	private String parentId;
	private List<FunctionVO> children = new ArrayList<FunctionVO>();

	public List<FunctionVO> getChildren() {
		return children;
	}

	public void setChildren(List<FunctionVO> children) {
		this.children = children;
	}

	public String getText() {
		return this.name;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
