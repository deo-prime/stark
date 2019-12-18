package com.deo.stark.sys.region.service.impl;

import org.springframework.stereotype.Service;

import com.deo.stark.base.service.impl.BaseServiceImpl;
import com.deo.stark.sys.region.service.SysSettingService;
import com.deo.stark.sys.region.vo.SysSetting;

@Service
public class SysSettingServiceImpl extends BaseServiceImpl implements SysSettingService{

	@Override
	public boolean needAuth() {
		String sql="select * from t_sys_setting where type='needAuth'";
		String isNeedAuth = expand(SysSetting.class,sql).getValue();
		if("1".equals(isNeedAuth)) {
			return true;
		}
		return false;
	}

	@Override
	public String getCheckBankChannl() {
		String sql="select * from t_sys_setting where type='checkBankChannl'";
		return expand(SysSetting.class,sql).getValue();
	}

	@Override
	public String getSmsChannl() {
		String sql="select * from t_sys_setting where type='sendSmsChannal'";
		return expand(SysSetting.class,sql).getValue();
	}

}
