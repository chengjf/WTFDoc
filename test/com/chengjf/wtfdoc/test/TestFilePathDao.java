/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: TestFilePathsDao.java  
 * @Package:com.chengjf.wtfdoc.test  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 下午12:55:07  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.test;

import java.util.List;

import com.chengjf.wtfdoc.bean.resources.FilePath;
import com.chengjf.wtfdoc.dao.FilePathDao;

/**
 * @ClassName: TestFilePathsDao
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-15 下午12:55:07
 */
public class TestFilePathDao{
	public static void main(String[] args) {
		FilePathDao dao = new FilePathDao();
		List<FilePath> filePaths = dao.getAllFilePaths();
		for(FilePath path : filePaths) {
			System.err.println(path.getKey());
		}
	}
}
