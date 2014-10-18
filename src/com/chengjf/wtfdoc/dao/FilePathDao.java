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

import com.chengjf.wtfdoc.bean.resources.FilePath;
import com.chengjf.wtfdoc.common.SqlMapCommonConstants;
import com.chengjf.wtfdoc.db.DatabaseAccess;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 文件路劲Dao
 * 
 * @ClassName: FilePathDao
 * @author: chengjf
 * @date: 2014-10-18
 */
public class FilePathDao {

	/**
	 * 数据库操作
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
	public FilePathDao() {
		client = DatabaseAccess.getSqlMapClient();
	}

	/**
	 * 获取所有的文件路径
	 * 
	 * @Title: getAllFilePaths
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FilePath> getAllFilePaths() {
		List<FilePath> paths = new ArrayList<FilePath>();
		try {
			paths = client
					.queryForList(SqlMapCommonConstants.QUERY_ALL_FILEPATHS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paths;
	}
}
