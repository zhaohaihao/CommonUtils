package com.bigsea.list;
/**
 * 列表工具类
 * @author bigsea
 * 2017-07-26 17:11:07
 */

import java.util.ArrayList;
import java.util.List;

public class ListUtils {
	
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
