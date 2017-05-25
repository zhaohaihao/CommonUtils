package com.bigsea.csv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import com.opencsv.CSVReader;

/**
 * CSV 文件工具类
 * @author bigsea
 * 2017-05-23 16:59:48
 */
public class CSVUtils {
	
	/**
	 * 将CSV文件转换成Excel
	 * @param csvFilePath  CSV文件路径
	 * @param type  excel文件后缀 1:xlsx 2:xls
	 * @return
	 */
	public static String CSV2Excel(String csvFilePath, int type) {
		List<String[]> csvDataList = readCSV(csvFilePath);
		String suffix;
		String msg;
		switch (type) {
		case 1:
			suffix = "xlsx";
			break;
		case 2:
			suffix = "xls";
			break;
		default:
			msg = "选择需要转换成对应excel文件的后缀!";
			return msg;
		}
		
		msg = writeExcel(csvFilePath, csvDataList, suffix) ? "CSV转换Excel 成功" : "CSV转换Excel 失败!";
		return msg;
	}
	
	/**
	 * 读取CSV文件内容
	 * @param csvFilePath  CSV文件路径
	 * @return
	 */
	public static List<String[]> readCSV(String csvFilePath) {
		CSVReader csvReader = null;
		List<String[]> csvDataList = null;
		try {
			csvReader = new CSVReader(new InputStreamReader(new FileInputStream(csvFilePath), "GBK"));
			
			// 读取csv文件的所有内容
			csvDataList = csvReader.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvDataList;
	}
	
	/**
	 * 内容写入Excel
	 * @param filePath	CSV文件路径
	 * @param dataList	CSV文件内容
	 * @param suffix	生成Excel文档后缀
	 * @return
	 */
	public static boolean writeExcel(String filePath, List<String[]> dataList, String suffix) {
		int maxRow = dataList.size();
		// 创建Excel工作簿对象
		Workbook wb = new HSSFWorkbook();
		// 创建Excel工作表对象
		String sheetName = "sheet1";
		wb.createSheet(sheetName);
		
		// 新建文件
		filePath = filePath.substring(0, filePath.lastIndexOf(".") + 1) + suffix;
		
		FileOutputStream fos = null;
		try {
			for (int i = 0; i < maxRow; i++) {
				Row row = wb.getSheet(sheetName).createRow(i);
				String[] rowDataArray = dataList.get(i);
				for (int j = 0; j < rowDataArray.length; j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(rowDataArray[j]);
					System.out.println(cell.getStringCellValue());
				}
			}
			fos = new FileOutputStream(filePath);
			wb.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				wb.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		String csvFilePath = "C:/Users/Administrator/Desktop/测试文件.csv";
		System.out.println(CSV2Excel(csvFilePath, 2));
	}
	
}
