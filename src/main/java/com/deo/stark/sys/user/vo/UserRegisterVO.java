package com.deo.stark.sys.user.vo;

import java.io.Serializable;

public class UserRegisterVO extends UserVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8961739448380918445L;

	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
