/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version: WTFDocV1.0
 *---------------------------------------------------
 * 
 * @FileName: IndexManagerDao.java
 * @Package:com.chengjf.wtfdoc.dao
 * @author: chengjf
 * @date: 2014-10-19
 * @version: V1.0
 */
package com.chengjf.wtfdoc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chengjf.wtfdoc.bean.index.IndexRecord;
import com.chengjf.wtfdoc.common.SqlMapCommonConstants;
import com.chengjf.wtfdoc.db.DatabaseAccess;
import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * @ClassName: IndexManagerDao
 * @author: chengjf
 * @date: 2014-10-19
 */
public class IndexRecordDao {

	private SqlMapClient client = null;

	public IndexRecordDao() {
		this.client = DatabaseAccess.getSqlMapClient();
	}

	@SuppressWarnings("unchecked")
	public List<IndexRecord> getAllIndexRecord() {
		List<IndexRecord> list = null;
		try {
			list = this.client
					.queryForList(SqlMapCommonConstants.QUERY_ALL_INDEXRECORDS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list == null) {
			list = new ArrayList<IndexRecord>();
		}
		return list;
	}

	public void addIndexRecord(IndexRecord indexRecord) {
		try {
			this.client.insert(SqlMapCommonConstants.INSERT_INDEXRECORD,
					indexRecord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
