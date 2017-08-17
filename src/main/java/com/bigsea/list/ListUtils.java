package com.bigsea.list;
/**
 * 列表操作常用方法集
 * @author bigsea
 * 2017-07-26 17:11:07
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtils {
	
	/**
	 * 列表对象是否为无效值
	 * @param list 要判断的列表
	 * @return 是否为有效值
	 */
	public static boolean isNullOrEmpty(List list) {
		return list == null || list.isEmpty();
	}
	
	/**
	 * 字符串切割转换成列表, 默认逗号分割
	 * @param str 所需转换字符串
	 * @return
	 */
	public static List<String> str2List(String str) {
		return str2List(str, ",");
	}
	
	/**
	 * 字符串切割转换成列表
	 * @param str 所需转换字符串
	 * @param regex 分割符号
	 * @return
	 */
	public static List<String> str2List(String str, String regex) {
		return str2List(str, regex, 0, str.length());
	}
	
	/**
	 * 字符串切割转换成列表, 默认逗号分割
	 * @param str 所需转换字符串
	 * @param beginIndex 切割起始位置
	 * @param endIndex 切割结束位置
	 * @return
	 */
	public static List<String> str2List(String str, int beginIndex, int endIndex) {
		return str2List(str, ",", beginIndex, endIndex);
	}
	
	/**
	 * 字符串切割转换成列表
	 * @param str 所需转换字符串
	 * @param regex 分割符号
	 * @param beginIndex 切割起始位置
	 * @param endIndex 切割结束位置
	 * @return
	 */
	public static List<String> str2List(String str , String regex, int beginIndex, int endIndex) {
		if (str != null && regex != null) {
			str = str.substring(beginIndex, endIndex);
			String[] strArr = str.split(regex);
			List<String> arrList = Arrays.asList(strArr);
			return arrList;
		}
		return null;
	}
	
	/**
	 * 平分list成n份 数据量尽可能相等
	 * 例如：
	 * 		原始数据:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]
	 * 		分组后数据:[[0, 1], [2, 3], [4, 5], [6, 7], [8], [9], [10], [11], [12], [13]]
	 * @param list 需要平分的list
	 * @param n    平分成n分
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> list, int n) {
		List<List<T>> strList = new ArrayList<>();
		if (list == null) return strList;
		int size = list.size();
		int quotient = size / n; // 商
		int remainder = size % n; // 余
		int offset = 0; // 偏移量
		int len = quotient > 0 ? n : remainder; // 循环长度
		int start = 0;	// 起始下标
		int end = 0;	// 结束下标
		List<T> tempList = null;
		for (int i = 0; i < len; i++) {
			if (remainder != 0) {
				remainder--;
				offset = 1;
			} else {
				offset = 0;
			}
			end = start + quotient + offset;
			tempList = list.subList(start, end);
			start = end;
			strList.add(tempList);
		}
		return strList;
	}
	
	public static void main(String[] args) {
		List<Integer> integerList = new ArrayList<>();
		for (int i = 0; i < 14; i++) {
			integerList.add(i);
		}
		List<List<Integer>> splitList = splitList(integerList, 10);
		System.out.println(splitList);
	}
}
