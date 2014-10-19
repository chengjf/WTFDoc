/*    
 * Copyright 2014 chengjf.
 * All rights reserved.
 * project name: WTFDoc
 * version:  WTFDocV1.0          
 *---------------------------------------------------
 * @FileName: StartApplication.java  
 * @Package:com.chengjf.wtfdoc.ui  
 * @Description: TODO 
 * @author: chengjf  
 * @date:2014-10-15 下午1:55:22  
 * @version V1.0    
 */
package com.chengjf.wtfdoc.ui;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import org.apache.commons.io.FileUtils;

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.parser.impl.JavaParser;
import com.chengjf.wtfdoc.service.IndexManager;

/**
 * 程序主入口
 * 
 * @ClassName: StartApplication
 * @author: chengjf
 * @date: 2014-10-18
 */
public class StartApplication extends Application {
	/*
	 * (non-Javadoc)
	 * 
	 * @param arg0
	 * 
	 * @throws Exception
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage arg0) throws Exception {

		List<Index> indexs = IndexManager.getIndexManager().getIndexs("gson");
		final Map<String, String> map = new HashMap<String, String>();
		for (Index index : indexs) {
			map.put(index.getName() + "_" + index.getParent(), index.getUrl());

		}

		final WebView view = new WebView();
		view.getEngine()
				.load("file:///E:/Code/jar-libs/gson/google-gson-2.2.4/gson-2.2.4-javadoc/com/google/gson/JsonArray.html");

		// HBox
		HBox hBox = new HBox();
		VBox vBox = new VBox();
		hBox.getChildren().add(vBox);
		hBox.getChildren().add(view);

		final TextField field = new TextField();
		vBox.getChildren().add(field);

		ObservableList<String> strList = getList(null, null);
		final ListView<String> listView = new ListView<String>(strList);
		listView.setItems(strList);
		listView.setPrefSize(600, 700);
		vBox.getChildren().add(listView);
		listView.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> arg0,
							String arg1, String arg2) {
						String url = "file:///E:/Code/jar-libs/gson/google-gson-2.2.4/gson-2.2.4-javadoc/"
								+ map.get(arg2).substring(1);
						view.getEngine().load(url);
					}
				});

		field.textProperty().addListener(new ChangeListener<String>() {
			public void changed(
					final ObservableValue<? extends String> observableValue,
					final String oldValue, final String newValue) {
				String str = newValue;
				ObservableList<String> strList = getList("gson", str);
				listView.getItems().clear();
				listView.setItems(strList);
			}
		});
		Scene scene = new Scene(hBox);

		Stage primaryStage = new Stage();
		primaryStage.setTitle("WTFDoc");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		try {
			initIndexs();
			launch(args);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 将索引取出，转换成JavaFX识别的List
	 * 该方法会取出给定命名空间下的所有索引
	 * 
	 * @Title: getList
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param str
	 * @return
	 */
	private ObservableList<String> getList(String namespace, String keyword) {

		ObservableList<String> strList = FXCollections.observableArrayList();
		List<Index> indexs = IndexManager.getIndexManager().getIndexs(namespace);
		if (keyword == null) {
			for (Index index : indexs) {
				strList.add(index.getName() + "_" + index.getParent());
			}
		} else {
			for (Index index : indexs) {
				if (index.getName().toLowerCase().startsWith(keyword.toLowerCase())) {
					strList.add(index.getName() + "_" + index.getParent());
				}
			}
		}

		return strList;
	}

	/**
	 * 初始化应用
	 * 
	 * @Title: initApplication
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private void initApplication() {

	}

	/**
	 * 初始化索引
	 * 
	 * @Title: initIndexs
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private static void initIndexs() {
		File input = new File(
				"E:/Code/jar-libs/gson/google-gson-2.2.4/gson-2.2.4-javadoc/index-all.html");
		JavaParser parser = new JavaParser();
		List<Index> list = new ArrayList<Index>();
		try {
			parser.index(list, FileUtils.readFileToString(input, Charset
					.defaultCharset().name()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IndexManager.getIndexManager().addIndexs("gson", list);
	}

}
