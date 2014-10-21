/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version: WTFDocV1.0
 *---------------------------------------------------
 * 
 * @FileName: UrlManager.java
 * @Package:com.chengjf.wtfdoc.service
 * @author: chengjf
 * @date: 2014-10-21
 * @version: V1.0
 */
package com.chengjf.wtfdoc.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.bean.index.IndexRecord;

/**
 * URL管理类
 * 
 * @ClassName: UrlManager
 * @author: chengjf
 * @date: 2014-10-21
 */
public class UrlManager {

	/**
	 * @Fields: instance
	 * @author: chengjf
	 * @date: 2014-10-21
	 */
	private static final UrlManager instance = new UrlManager();

	/**
	 * 临时文件路径存储
	 * 
	 * @Fields: tempFilePath
	 * @author: chengjf
	 * @date: 2014-10-21
	 */
	private Map<String, String> tempFilePath = new HashMap<String, String>();

	/**
	 * @author: chengjf
	 * @date: 2014-10-21
	 */
	private UrlManager() {

	}

	/**
	 * @Title: getInstance
	 * @author: chengjf
	 * @date: 2014-10-21
	 * @return
	 */
	public static final UrlManager getInstance() {
		return instance;
	}

	/**
	 * 获取Index的URL
	 * 
	 * @Title: getUrl
	 * @author: chengjf
	 * @date: 2014-10-21
	 * @param indexName
	 * @return
	 */
	public String getUrl(String indexName) {
		Index index = IndexManager.getIndexManager().getIndex(null, indexName);

		IndexRecord indexRecord = IndexManager.getIndexManager()
				.getIndexRecord(index.getApi());
		if (indexRecord.getUrl().endsWith("zip")
				|| indexRecord.getUrl().endsWith("jar")) {

			String tempUrl = getTempUrl(index.getApi(), indexRecord.getUrl());
			String url = "file:///" + tempUrl + "/" + index.getUrl();
			return url;
		} else {
			String url = indexRecord.getUrl() + index.getUrl();
			return url;
		}

	}

	/**
	 * 获取解压后的文件路径
	 * 
	 * @Title: getTempUrl
	 * @author: chengjf
	 * @date: 2014-10-21
	 * @param api
	 * @param url
	 * @return
	 */
	private String getTempUrl(String api, String url) {
		String tempUrl = this.tempFilePath.get(api);
		if (tempUrl == null) {

			try {
				ZipFile zipFile = new ZipFile(url);
				tempUrl = createTempDirectory().getAbsolutePath();
				Enumeration<? extends ZipEntry> entries = zipFile.entries();
				while (entries.hasMoreElements()) {
					ZipEntry entry = entries.nextElement();
					File entryDestination = new File(tempUrl, entry.getName());
					entryDestination.getParentFile().mkdirs();
					if (entry.isDirectory())
						entryDestination.mkdirs();
					else {
						InputStream in = zipFile.getInputStream(entry);
						OutputStream out = new FileOutputStream(
								entryDestination);
						IOUtils.copy(in, out);
						IOUtils.closeQuietly(in);
						IOUtils.closeQuietly(out);
					}
				}
				this.tempFilePath.put(api, tempUrl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tempUrl;
	}

	/**
	 * 创建临时文件夹
	 * 
	 * @Title: createTempDirectory
	 * @author: chengjf
	 * @date: 2014-10-21
	 * @return
	 * @throws IOException
	 */
	private static File createTempDirectory() throws IOException {
		final File temp;

		temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

		if (!(temp.delete())) {
			throw new IOException("Could not delete temp file: "
					+ temp.getAbsolutePath());
		}

		if (!(temp.mkdir())) {
			throw new IOException("Could not create temp directory: "
					+ temp.getAbsolutePath());
		}

		return (temp);
	}

	/**
	 * 程序关闭是会调用此方法，删除临时文件
	 * 
	 * @Title: clear
	 * @author: chengjf
	 * @date: 2014-10-21
	 */
	public void clear() {
		// 清除临时文件
		for (Entry<String, String> entry : tempFilePath.entrySet()) {
			String path = entry.getValue();
			FileUtils.deleteQuietly(new File(path));
		}
	}
}
