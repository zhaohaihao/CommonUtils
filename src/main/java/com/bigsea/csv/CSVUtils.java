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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.opencsv.CSVReader;

/**
 * CSV 文件工具类
 * @author bigsea
 * 2017-05-23 16:59:48
 */
public class CSVUtils {
	
	/**
	 * 读取CSV文件内容
	 * @param csvFilePath CSV文件路径
	 * @return
	 */
	public static List<String[]> readCSV(String filePath) {
		CSVReader csvReader = null;
		List<String[]> csvDataList = null;
		try {
			csvReader = new CSVReader(new InputStreamReader(new FileInputStream(filePath), "GBK"));
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
	 * csv文件转xls文件
	 * @param filePath csv文件路径
	 * @return 转换成功返回true, 否则返回false
	 */
	public static boolean CSV2XLS(String filePath) {
		return writeExcel(filePath, ".xls");
	}
	
	/**
	 * csv文件转xlsx文件
	 * @param filePath csv文件路径
	 * @return 转换成功返回true, 否则返回false
	 */
	public static boolean CSV2XLSX(String filePath) {
		return writeExcel(filePath, ".xlsx");
	}
	
	/**
	 * CSV内容写入Excel
	 * @param filePath	CSV文件路径
	 * @param suffix	生成Excel文档后缀 .xls, .xlsx
	 * @return
	 */
	private static boolean writeExcel(String filePath, String suffix) {
		List<String[]> dataList = readCSV(filePath);
		int maxRow = dataList.size();
		// 创建Excel工作簿对象
		Workbook wb = null;
		if (".xls".equals(suffix)) {
			wb = new HSSFWorkbook();
		} else if (".xlsx".equals(suffix)) {
			wb = new XSSFWorkbook();
		}
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
					// System.out.println(cell.getStringCellValue());
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
		System.out.println(CSV2XLSX(csvFilePath));
	}
	
}
