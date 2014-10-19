/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: TestFileDao.java  
 * @Package:com.chengjf.wtfdoc.test  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 下午1:06:10  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.test;

import java.nio.charset.Charset;

import junit.framework.TestCase;

import com.chengjf.wtfdoc.bean.resources.File;
import com.chengjf.wtfdoc.dao.FileDao;

/**
 * @ClassName: TestFileDao
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-15 下午1:06:10
 */
public class TestFileDao extends TestCase {

	public void testGetAllFiles() {
//		FileDao dao = new FileDao();
//		List<File> filePaths = dao.getAllFiles();
//		for (File path : filePaths) {
//			System.err.println(path.getKey());
//		}
	}

	public void testGetFileByKey() {
		FileDao dao = new FileDao();
		File file = dao.getFile(5);
		if (file != null) {
			System.err.println(file.getKey());
			Object obj = file.getContent();
			decode((byte[])obj);
		}
	}
	
	public void decode(byte[] bs) {
		String[] charsets = {"US-ASCII","ISO-8859-1","UTF-8","UTF-16BE","UTF-16LE","UTF-16"};
		for(String charset : charsets) {
			String str = new String(bs,Charset.forName(charset));
			System.err.println(str);
		}
		
	}
}
