package com.bigsea.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.bigsea.file.FileUtils;

/**
 * zip压缩文件处理类
 * @author bigsea
 * 2017-05-23 17:13:30
 */
public class ZipUtils {
	
	/** 避免 压缩|解压 中文文件名乱码 */
	private static final String CHINESE_CHARSET = "GBK";
	
	/** 文件读取的缓冲区的大小 */
	private static final int CACHE_SIZE = 1024;
	
	/**
	 * 解压zip格式的压缩文件
	 * @param srcZipPath 压缩文件路径
	 * @return
	 */
	public static boolean unZipFiles(String srcZipPath) {
		// 解压目录
		String dstZipPath = srcZipPath.substring(0, srcZipPath.lastIndexOf("."));
		
		File file = new File(dstZipPath);
		if (file.exists()) {
			FileUtils.deleteFolder(dstZipPath);
		}
		file.mkdirs();
		
		ZipFile zipFile = null;
		File f = null;
		try {
			zipFile = new ZipFile(srcZipPath, CHINESE_CHARSET);
			f = new File(srcZipPath);
			if ((!f.exists()) && (f.length() <= 0)) {
				throw new Exception("要解压的文件不存在!");
			}
			String strPath, gbkPath, strtemp;
			File tempFile = new File(dstZipPath);
			strPath = tempFile.getAbsolutePath();
			Enumeration<?> e = zipFile.getEntries();
			while (e.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) e.nextElement();
				gbkPath = zipEntry.getName();
				if (zipEntry.isDirectory()) {
					strtemp = strPath + File.separator + gbkPath;
					File dir = new File(strtemp);
					dir.mkdirs();
					continue;
				} else {
					// 读写文件
					InputStream is = zipFile.getInputStream(zipEntry);
					BufferedInputStream bis = new BufferedInputStream(is);
					gbkPath = zipEntry.getName();
					strtemp = strPath + File.separator + gbkPath;

					// 建目录
					String strsubdir = gbkPath;
					for (int i = 0; i < strsubdir.length(); i++) {
						if (strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {
							String temp = strPath + File.separator + strsubdir.substring(0, i);
							File subdir = new File(temp);
							if (!subdir.exists())
								subdir.mkdir();
						}
					}
					FileOutputStream fos = new FileOutputStream(strtemp);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					int c;
					byte[] b = new byte[CACHE_SIZE];
					while ((c = bis.read(b, 0, CACHE_SIZE)) != -1) {
						bos.write(b, 0, c);
					}
					bos.flush();
					bos.close();
					fos.close();
					bis.close();
					is.close();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(zipFile != null){
				try {
					zipFile.close();	// 压缩包被占用, 需关闭后才能删除
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 解压后删除压缩包
			if(f != null) FileUtils.deleteFolder(srcZipPath);
		}
	}
	
	public static void main(String[] args) {
		String zipFileName = "C:/Users/Administrator/Desktop/test.zip";
		System.out.println(unZipFiles(zipFileName));
	}
}
