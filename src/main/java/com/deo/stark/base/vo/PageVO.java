package com.deo.stark.base.vo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageVO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1767789312937770931L;

	private List<T> data;
	private Long total;
	private Long index;
	private Integer pageSize;
	private Integer pageNo;
}
