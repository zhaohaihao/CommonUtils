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
	 * 解压压缩文件至当前目录
	 * @param srcRarPath 压缩文件路径
	 * @return
	 */
	public static boolean unCompress(String srcRarPath) {
		return unCompress(srcRarPath, null);
	}
	
	/**
	 * 解压压缩文件至指定目录
	 * @param srcRarPath 压缩文件路径
	 * @param dstRarPath 解压文件指定目录
	 * @return
	 */
	public static boolean unCompress(String srcRarPath, String dstRarPath) {
		if (srcRarPath == null) {
			return false;
		}
		if (dstRarPath == null) {
			dstRarPath = FileUtils.removeSuffix(srcRarPath);
		}

		File dstDiretory = new File(dstRarPath);
		if (dstDiretory.exists()) {
			// 目标目录存在时 删除文件夹内容重新创建
			FileUtils.deleteFolder(dstRarPath);
		}
		dstDiretory.mkdirs();

		Archive a = null;
		File f = null;
		try {
			f = new File(srcRarPath);
			a = new Archive(f);
			if (a != null) {
				// a.getMainHeader().print();
				FileHeader fh = a.nextFileHeader();
				while (fh != null) {
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
			if (f.exists())
				FileUtils.deleteFolder(srcRarPath);
		}
	}
	
}
