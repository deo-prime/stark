package com.deo.stark.base.vo;

import java.io.Serializable;
import java.util.List;

public class DataGridVO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2335119855443687883L;

	private long total;
	private List<T> rows;

	public DataGridVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataGridVO(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
