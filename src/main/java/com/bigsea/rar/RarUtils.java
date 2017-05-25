package com.bigsea.rar;

import java.io.File;
import java.io.FileOutputStream;

import com.bigsea.file.FileUtils;
import com.github.junrar.Archive;
import com.github.junrar.rarfile.FileHeader;

/**
 * rar压缩文件处理类
 * @author bigsea
 * 2017-05-23 17:13:43
 */
public class RarUtils {
	/**
	 * 解压rar格式的压缩文件
	 * @param srcRarPath 压缩文件路径
	 * @return
	 */
	public static boolean unRarFile(String srcRarPath) {
		String dstRarPath = srcRarPath.substring(0, srcRarPath.lastIndexOf("."));
				
		File dstDiretory = new File(dstRarPath);
		if (dstDiretory.exists()) {// 目标目录存在时 删除文件夹内容重新创建
			FileUtils.deleteFolder(dstRarPath);
		}
		dstDiretory.mkdirs();
		
		File file = null;
		Archive a = null;
		try {
			file = new File(srcRarPath);
			a = new Archive(file);
			if (a != null) {
				// a.getMainHeader().print(); // 打印文件信息.
				FileHeader fh = a.nextFileHeader();
				while (fh != null) {
					// 防止文件名中文乱码问题的处理
					String fileName = fh.getFileNameW().isEmpty() ? fh.getFileNameString() : fh.getFileNameW();
					if (fh.isDirectory()) { // 文件夹
						File fol = new File(dstRarPath + File.separator + fileName);
						fol.mkdirs();
					} else { // 文件
						File out = new File(dstRarPath + File.separator + fileName.trim());
						if (!out.exists()) {
							if (!out.getParentFile().exists()) {// 相对路径可能多级，可能需要创建父目录.
								out.getParentFile().mkdirs();
							}
							out.createNewFile();
						}
						FileOutputStream os = new FileOutputStream(out);
						a.extractFile(fh, os);
						os.close();
					}
					fh = a.nextFileHeader();
				}
				a.close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			// 解压后删除压缩包
			if(file != null) FileUtils.deleteFolder(srcRarPath);
		}
	}
	
	
	public static void main(String[] args) {
		String rarFileName = "C:/Users/Administrator/Desktop/test.rar";
		System.out.println(unRarFile(rarFileName));
	}
}
