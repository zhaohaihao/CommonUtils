package com.bigsea.bean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 实体工具类
 * @author bigsea
 * 2017-08-04 17:10:43
 */
public class BeanUtils {
	
	/**
	 * 实体对象转成Map
	 * @param obj 实体对象
	 * @return
	 */
	public static Map<String, Object> Object2Map(Object obj) {
		Map<String, Object> map = new HashMap<>();
		if (obj == null) {
			return map;
		}
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
}
