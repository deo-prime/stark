package com.deo.stark.base.converter;

import java.util.Date;

import org.apache.http.client.utils.DateUtils;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		String[] formatters = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"};
		return DateUtils.parseDate(source, formatters);
	}

}
