package com.deo.stark.base.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class IDNumEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4452430336738920399L;

	@Id
	@GeneratedValue(generator = "auto-increment")
	@GenericGenerator(name = "auto-increment", strategy = "identity")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
