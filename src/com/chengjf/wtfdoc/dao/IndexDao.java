/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version: WTFDocV1.0
 *---------------------------------------------------
 * 
 * @FileName: IndexDao.java
 * @Package:com.chengjf.wtfdoc.dao
 * @author: chengjf
 * @date: 2014-10-19
 * @version: V1.0
 */
package com.chengjf.wtfdoc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.common.SqlMapCommonConstants;
import com.chengjf.wtfdoc.db.DatabaseAccess;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 索引Dao
 * 
 * @ClassName: IndexDao
 * @author: chengjf
 * @date: 2014-10-19
 */
public class IndexDao {

	public static final String DATABASE_NAME = "database";
	public static final String INDEX_NAME = "name";
	public static final String INDEX_TYPE = "type";
	public static final String INDEX_URL = "url";
	public static final String INDEX_PARENT = "parent";

	/**
	 * @Fields: client
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	private SqlMapClient client = null;

	/**
	 * 命名空间，同时也是该Index的表名称
	 * 
	 * @Fields: namespace
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	private String namespace = null;

	public IndexDao(String namespace) {
		this.namespace = namespace;
		this.client = DatabaseAccess.getSqlMapClient();
		createTable();
	}

	/**
	 * @Title: createTable
	 * @author: chengjf
	 * @throws SQLException
	 * @date: 2014-10-19
	 */
	private void createTable() {
		try {
			this.client.update(SqlMapCommonConstants.CREATE_INDEX_TABLE,
					this.namespace);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Index> getAllIndexs() {
		List<Index> indexs = null;
		try {
			indexs = this.client.queryForList(
					SqlMapCommonConstants.QUERY_ALL_INDEXS, this.namespace);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (indexs == null) {
			indexs = new ArrayList<Index>();
		}
		return indexs;

	}

	public void addIndex(Index index) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(DATABASE_NAME, namespace);
		parameters.put(INDEX_NAME, index.getName());
		parameters.put(INDEX_TYPE, index.getType().toString());
		parameters.put(INDEX_URL, index.getUrl());
		parameters.put(INDEX_PARENT, index.getParent());
		try {
			this.client.insert(SqlMapCommonConstants.INSERT_INDEX, index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
