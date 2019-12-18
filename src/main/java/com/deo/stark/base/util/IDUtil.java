package com.deo.stark.base.util;

import java.util.UUID;

/**
 * id generator
 * @author Deo
 *
 */
public class IDUtil {

	public static String generate() {
		return UUID.randomUUID().toString();
	}
}
