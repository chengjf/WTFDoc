/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: IndexManager.java  
 * @Package:com.chengjf.wtfdoc.bean.index  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-16 上午10:20:54  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.bean.index.IndexRecord;
import com.chengjf.wtfdoc.dao.IndexDao;
import com.chengjf.wtfdoc.dao.IndexRecordDao;

/**
 * 索引管理器 不同的API文档都有自己的一套索引，使用该管理器进行管理
 * 
 * @ClassName: IndexManager
 * @author: chengjf
 * @date: 2014-10-18
 */
public class IndexManager {

	/**
	 * 索引的内部存储
	 * 
	 * @Fields: maps
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private Map<String, Map<String, Index>> maps;

	/**
	 * 索引管理器的实例
	 * 
	 * @Fields: instance
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private static IndexManager instance;

	/**
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private IndexManager() {
		this.maps = new HashMap<String, Map<String, Index>>();
	}

	/**
	 * 获取索引管理器的实例
	 * 
	 * @Title: getIndexManager
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @return
	 */
	public static IndexManager getIndexManager() {
		if (instance == null) {
			synchronized (IndexManager.class) {
				if (instance == null) {
					instance = new IndexManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 添加索引
	 * 
	 * @Title: addIndexs
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param namespace
	 * @param indexs
	 */
	public void addIndexs(String namespace, List<Index> indexs) {

		if (this.maps.containsKey(namespace)) {
			this.removeIndexs(namespace);
		}
		Map<String, Index> map = this.listToMap(indexs);
		this.maps.put(namespace, map);
		//
		IndexRecordDao dao = new IndexRecordDao();
		IndexRecord indexRecords = new IndexRecord();
		indexRecords.setName(namespace);
		indexRecords.setUrl(namespace);
		dao.addIndexRecord(indexRecords);
		//
		IndexDao indexDao = new IndexDao(namespace);
		for (Index index : indexs) {
			indexDao.addIndex(index);
		}
	}

	/**
	 * 删除索引 该方法会删除给定API下的所有索引
	 * 
	 * @Title: removeIndexs
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param namespace
	 */
	public void removeIndexs(String namespace) {
		this.maps.remove(namespace);
		//
		IndexRecordDao dao = new IndexRecordDao();
		dao.removeIndexRecord(namespace);
		//
		IndexDao indexDao = new IndexDao(namespace);
		indexDao.remove();
	}

	/**
	 * 根据给定的命名空间获取索引 如果给定的命名空间为null或空，返回所有的索引 如果没有找到给定命名空间的所有，返回空的列表
	 * 
	 * @Title: getIndexs
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param namespace
	 * @return
	 */
	public List<Index> getIndexs(String namespace) {
		if (namespace == null || "".equals(namespace)) {
			return this.getAllIndexs();
		} else {
			return this
					.mapToList((Map<String, Index>) this.maps.get(namespace));

		}
	}

	/**
	 * 获取所有命名空间下的索引
	 * 
	 * @Title: getAllIndexs
	 * @author: chengjf
	 * @date: 2014-10-19
	 * @return
	 */
	public List<Index> getAllIndexs() {
		List<Index> indexs = new ArrayList<Index>();
		for (Entry<String, Map<String, Index>> entry : this.maps.entrySet()) {
			List<Index> index = this.mapToList(entry.getValue());
			indexs.addAll(index);
		}
		return indexs;
	}

	/**
	 * 从数据库加载数据
	 * 
	 * @Title: loadFromDatabase
	 * @author: chengjf
	 * @date: 2014-10-19
	 */
	public void loadFromDatabase() {
		IndexRecordDao dao = new IndexRecordDao();
		List<IndexRecord> indexRecords = dao.getAllIndexRecord();
		for (IndexRecord indexRecord : indexRecords) {
			String name = indexRecord.getName();
			IndexDao indexDao = new IndexDao(name);
			this.maps.put(name, this.listToMap(indexDao.getAllIndexs()));
		}
	}

	/**
	 * @Title: getIndex
	 * @author: chengjf
	 * @date: 2014-10-19
	 * @param namespace
	 * @param key
	 * @return
	 */
	public Index getIndex(String namespace, String key) {

		Index index = null;
		if (this.maps.containsKey(namespace)) {
			index = this.maps.get(namespace).get(key);
		}
		return index;
	}

	/**
	 * @Title: listToMap
	 * @author: chengjf
	 * @date: 2014-10-19
	 * @param indexs
	 * @return
	 */
	private Map<String, Index> listToMap(List<Index> indexs) {
		Map<String, Index> map = new HashMap<String, Index>();
		for (Index index : indexs) {
			map.put(index.getName()+"_" + index.getParent(), index);
		}
		return map;
	}

	/**
	 * @Title: mapToList
	 * @author: chengjf
	 * @date: 2014-10-19
	 * @param map
	 * @return
	 */
	private List<Index> mapToList(Map<String, Index> map) {
		List<Index> indexs = new ArrayList<Index>();
		for (Map.Entry<String, Index> entry : map.entrySet()) {
			indexs.add(entry.getValue());
		}

		return indexs;
	}
}
