package com.bigsea.base;
/**
 * 字符串操作工具类
 * @author bigsea
 * 2017-08-07 08:57:24
 */
public class StringUtils {
	
	/**
	 * 对象是否为无效值
	 * @param obj 要判断的对象
	 * @return 是否为有效值(不为null 和 ""字符串)
	 */
	public static boolean isNullOrEmpty(Object obj) {
		return obj == null || "".equals(obj.toString());
	}
}
