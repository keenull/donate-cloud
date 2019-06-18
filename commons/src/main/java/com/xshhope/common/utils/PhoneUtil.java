package com.xshhope.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {

	private static String REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
	private static Pattern P = Pattern.compile(REGEX);

	/**
	 * 校验手机号
	 * 
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		if (phone == null || phone.length() != 11) {
			return Boolean.FALSE;
		}

		Matcher m = P.matcher(phone);
		return m.matches();
	}
}
