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
 * 文件Dao
 * 
 * @ClassName: FileDao
 * @author: chengjf
 * @date: 2014-10-18
 */
public class FileDao {

	/**
	 * 数据库操作Client
	 * 
	 * @Fields: client
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private SqlMapClient client = null;

	/**
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	public FileDao() {
		client = DatabaseAccess.getSqlMapClient();
	}

	/**
	 * 获取所有File
	 * 
	 * @Title: getAllFiles
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return 查询到File
	 */
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

	/**
	 * 根据给定的key查询File
	 * 
	 * @Title: getFile
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param key
	 * @return 返回查询到的File
	 */
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
