package com.bigsea.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件处理工具类
 * @author bigsea
 * 2017-05-23 17:07:45
 */
public class FileUtils {
	
	/** 
	 * 根据路径删除指定的目录或文件，无论存在与否 
	 * @param sPath  要删除的目录或文件 
	 * @return 删除成功返回 true，否则返回 false。
	 */  
	public static boolean deleteFolder(String sPath){
		boolean flag = false;
		File file = new File(sPath);
		if(!file.exists()){
			return flag;
		}else{
			if(file.isFile()){
				return deleteFile(sPath);
			}else {
				return deleteDirectory(sPath);
			}
		}
	}
	 
	/**
	 * 删除单个文件
	 * @param sPath 被删除的文件名
	 * @return 删除成功true，否则false
	 */
	public static boolean deleteFile(String sPath){
		boolean flag = false;
		File file = new File(sPath);
		if(file.isFile() && file.exists()){
			file.delete();
			flag = true;
		}
		return flag;
	}
	 
	/** 
	 * 删除目录（文件夹）以及目录下的文件 
	 * @param   sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath){
		if(!sPath.endsWith(File.separator)){
			sPath = sPath + File.separator;
		}
		 
		File dirFile = new File(sPath);
		if(!dirFile.exists() || !dirFile.isDirectory()){
			return false;
		}
		boolean flag = true;
		 
		File[] files = dirFile.listFiles();
		for(int i = 0; i < files.length ; i++){
			if(files[i].isFile()){
				flag = deleteFile(files[i].getAbsolutePath());
				if(!flag) break;
			}else {
				flag =deleteDirectory(files[i].getAbsolutePath());
				if(!flag) break;
			}
		}
		if(!flag) return false;
		if(dirFile.delete()){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 所有(文件夹下所有文件的路径 | 文件的路径)
	 * @param path	文件夹或者文件的路径
	 * @param pathList	文件夹路径的集合
	 */
	public static void getAllFilePath(String path, List<String> pathList) {
		System.out.println(path);
		if(pathList == null) {
			pathList = new ArrayList<String>();
		}
		File file = new File(path);
		if(file.isDirectory()) {
			File[] ChildFiles = file.listFiles();
			if(ChildFiles != null && ChildFiles.length > 0) {
				for (File f : ChildFiles) {
					if(f.isDirectory()) {
						getAllFilePath(f.getAbsolutePath().replace("\\", "/"), pathList);
					} else {
						pathList.add(f.getAbsolutePath().replace("\\", "/"));
					}
				}
			} 
		} else {
			pathList.add(path);
		}
	}
}
