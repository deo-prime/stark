package com.deo.stark.base.context;

import javax.servlet.http.HttpSession;

/**
 * session上下文，所有对session的数据操作都使用当前类完成，后期它的实现将使用内存数据库替代
 * @author pengde
 *
 */
public class SessionContext {

	public static void setAttribute(HttpSession session, String key, Object value) {
		session.setAttribute(key, value);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getAttribute(HttpSession session, String key) {
		T result = (T)session.getAttribute(key);
		return result;
	}
	
	public static void remove(HttpSession session, String key) {
		session.removeAttribute(key);
	}
}
