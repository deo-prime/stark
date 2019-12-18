package com.deo.stark.base.enums;

import org.apache.commons.lang3.StringUtils;

public enum OrderType {

	ASC("asc"), DESC("desc");
	
	private String type;

	public String getType() {
		return type;
	}

	private OrderType(String type) {
		this.type = type;
	}
	
	public static OrderType getByType(String type) {
		OrderType result = null;
		for(OrderType oe : OrderType.values()) {
			if (StringUtils.equals(type, oe.getType())) {
				result = oe;
			}
		}
		return result;
	}
}
