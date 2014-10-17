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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.chengjf.wtfdoc.bean.index.IndexManager;
import com.chengjf.wtfdoc.parser.impl.JavaParser;

/**
 * @ClassName: StartApplication
 * @Description: TODO
 * @author: CJF
 * @date:2014-10-15 下午1:55:22
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

		List<Index> indexs = IndexManager.getIndexManager().getIndexs("java");
		final Map<String, String> map = new HashMap<String, String>();
		Pattern p = Pattern.compile("/[^\n/]*\\.html");
		for (Index index : indexs) {
			System.err.println(index.getUrl());
			Matcher m = p.matcher(index.getUrl());
			while(m.find()) {
//				strList.add(index.getName());
				System.err.println(m.group());
				map.put(index.getName() + "_" + m.group().substring(1), index.getUrl());
			}
			
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

		ObservableList<String> strList = getList(null);
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
						System.err.println(arg2 + "_" + url);
					}
				});

		field.textProperty().addListener(new ChangeListener<String>() {
			public void changed(
					final ObservableValue<? extends String> observableValue,
					final String oldValue, final String newValue) {
				String str = newValue;
				System.err.println(str);
				ObservableList<String> strList = getList(str);
				listView.getItems().clear();
				listView.setItems(strList);
			}
		});
		Scene scene = new Scene(hBox);

		Stage primaryStage = new Stage();
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	public static void main(String[] args) {
		initIndexs();
		launch(args);
	}

	private ObservableList<String> getList(String str) {

		ObservableList<String> strList = FXCollections.observableArrayList();
		List<Index> indexs = IndexManager.getIndexManager().getIndexs("java");
		Pattern p = Pattern.compile("/[^\n/]*\\.html");
		if (str == null) {
			for (Index index : indexs) {
				System.err.println(index.getUrl());
				Matcher m = p.matcher(index.getUrl());
				while(m.find()) {
//					strList.add(index.getName());
					System.err.println(m.group());
					strList.add(index.getName() + "_" + m.group().substring(1));
				}
			}
		} else {
			for (Index index : indexs) {
				if (index.getName().toLowerCase().startsWith(str.toLowerCase())) {
					System.err.println(index.getUrl());
					Matcher m = p.matcher(index.getUrl());
					while(m.find()) {
						System.err.println(m.group());
						strList.add(index.getName() + "_" + m.group().substring(1));
					}
				}
			}
		}

		System.err.println("Index length " + strList.size());
		return strList;
	}

	private void initApplication() {

	}

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
		IndexManager.getIndexManager().addIndexs("java", list);
	}

}
