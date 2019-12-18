package com.deo.stark.base.vo;

import java.io.Serializable;
import java.util.List;

public class TreeNodeVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8391131515804432849L;

	private String id;
	private String text;
	private String url;
	private String parentId;
	private List<TreeNodeVO> children;

	public TreeNodeVO(String id, String text, String url) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public List<TreeNodeVO> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeVO> children) {
		this.children = children;
	}

}
