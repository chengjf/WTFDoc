/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: FilePathsDao.java  
 * @Package:com.chengjf.wtfdoc.dao  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 下午12:46:54  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chengjf.wtfdoc.bean.resources.File;
import com.chengjf.wtfdoc.common.SqlMapCommonConstants;
import com.chengjf.wtfdoc.db.DatabaseAccess;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @ClassName: FilePathsDao
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-15 下午12:46:54
 */
public class FileDao {

	SqlMapClient client = null;

	public FileDao() {
		client = DatabaseAccess.getSqlMapClient();
	}

	@SuppressWarnings("unchecked")
	public List<File> getAllFiles() {
		List<File> paths = new ArrayList<File>();
		try {
			paths = client.queryForList(SqlMapCommonConstants.QUERY_ALL_FILES);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paths;
	}

	public File getFile(int key) {
		File file = null;
		try {
			file = (File) client.queryForObject(
					SqlMapCommonConstants.QUERY__FILE_BY_KEY, key);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file;
	}
}
