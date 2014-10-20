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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.bean.index.IndexRecord;
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

	private final static String INDEX = "index-all.html";

	private Map<String, String> tempFilePath = new HashMap<String, String>();

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

		final Stage primaryStage = new Stage();
		primaryStage.setTitle("WTFDoc");

		// Menu
		MenuBar menuBar = new MenuBar();
		Menu menuSetting = new Menu("Setting");
		MenuItem addItem = new MenuItem("Add API Doc");
		addItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				// System.exit(0);
				addDoc(primaryStage);
			}
		});
		MenuItem removeItem = new MenuItem("Del API Doc");
		removeItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				// System.exit(0);
			}
		});
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0);
			}
		});

		menuSetting.getItems().addAll(addItem, removeItem, exitItem);
		Menu menuAbout = new Menu("About");
		MenuItem aboutItem = new MenuItem("About");
		menuAbout.getItems().add(aboutItem);
		menuBar.getMenus().addAll(menuSetting, menuAbout);

		// Browser
		final WebView view = new WebView();
		view.getEngine().setJavaScriptEnabled(true);

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
						if (arg2 == null || "".equals(arg2)) {

						} else {
							Index index = IndexManager.getIndexManager()
									.getIndex(null, arg2);

							IndexRecord indexRecord = IndexManager
									.getIndexManager().getIndexRecord(
											index.getApi());
							if (indexRecord.getUrl().endsWith("zip")
									|| indexRecord.getUrl().endsWith("jar")) {

								String tempUrl = getTempUrl(index.getApi(),
										indexRecord.getUrl());
								String url = "file:///" + tempUrl + "/" + index.getUrl();
								// String href = index.getUrl().substring(
								// index.getUrl().indexOf("#"));
								view.getEngine().load(url);
								// view.getEngine().executeScript(
								// "window.location=\"" + href + "\";");
							} else {
								String url = indexRecord.getUrl()
										+ index.getUrl();
								view.getEngine().load(url);
							}

						}
					}

				});

		field.textProperty().addListener(new ChangeListener<String>() {
			public void changed(
					final ObservableValue<? extends String> observableValue,
					final String oldValue, final String newValue) {
				String str = newValue;
				ObservableList<String> strList = getList(null, str);
				listView.getItems().clear();
				listView.setItems(strList);
			}
		});

		VBox box = new VBox();
		box.getChildren().add(menuBar);
		box.getChildren().add(hBox);

		Scene scene = new Scene(box);

		primaryStage.setScene(scene);
		// primaryStage.setFullScreen(true);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				System.err.println("Close!");
				// 清除临时文件
				for(Entry<String, String> entry : tempFilePath.entrySet()) {
					String path = entry.getValue();
					FileUtils.deleteQuietly(new File(path));
				}
			}
		});
	}

	public static void main(String[] args) {
		try {
			// initIndexs();
			initApplication();
			launch(args);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 将索引取出，转换成JavaFX识别的List 该方法会取出给定命名空间下的所有索引
	 * 
	 * @Title: getList
	 * @author: chengjf
	 * @date: 2014-10-18
	 * @param str
	 * @return
	 */
	private ObservableList<String> getList(String namespace, String keyword) {

		ObservableList<String> strList = FXCollections.observableArrayList();
		List<Index> indexs = IndexManager.getIndexManager()
				.getIndexs(namespace);
		if (keyword == null) {
			for (Index index : indexs) {
				strList.add(index.getName() + "_" + index.getParent());
			}
		} else {
			for (Index index : indexs) {
				if (index.getName().toLowerCase()
						.startsWith(keyword.toLowerCase())) {
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
	private static void initApplication() {
		// initIndexs();
		IndexManager.getIndexManager().loadFromDatabase();
	}

	/**
	 * 初始化索引
	 * 
	 * @Title: initIndexs
	 * @author: chengjf
	 * @date: 2014-10-18
	 */
	private static void initIndexs() {
		// File input = new File(
		// "E:/Code/jar-libs/gson/google-gson-2.2.4/gson-2.2.4-javadoc/index-all.html");
		// JavaParser parser = new JavaParser();
		// List<Index> list = new ArrayList<Index>();
		// try {
		// parser.index(list, FileUtils.readFileToString(input, Charset
		// .defaultCharset().name()),);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// IndexManager.getIndexManager().addIndexs("gson", list);
	}

	/**
	 * 增加API文档
	 * 
	 * @Title: addDoc
	 * @author: chengjf
	 * @date: 2014-10-20
	 * @param stage
	 */
	private void addDoc(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setInitialDirectory(new File(System
				.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("JAR", "*.jar"),
				new FileChooser.ExtensionFilter("ZIP", "*.zip"));
		fileChooser.setTitle("Open API Doc Zip File");
		File file = fileChooser.showOpenDialog(stage);
		parseJar(file);
	}

	private void parseJar(File file) {
		String namespace = getNamespace(file.getName());
		JarFile jar = null;
		try {
			jar = new JarFile(file);
			for (Enumeration<JarEntry> enums = jar.entries(); enums
					.hasMoreElements();) {
				JarEntry entry = enums.nextElement();
				if (!entry.isDirectory()) {
					if (entry.getName().equals(INDEX)) {
						InputStream inputStream = jar.getInputStream(entry);
						String str = IOUtils.toString(inputStream);
						parseIndex(namespace, file.getAbsolutePath(), str);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (jar != null) {
				try {
					jar.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 解析并加入IndexManager
	 * 
	 * @Title: parseIndex
	 * @author: chengjf
	 * @date: 2014-10-20
	 * @param namespace
	 * @param str
	 */
	private static void parseIndex(String namespace, String url, String str) {
		JavaParser parser = new JavaParser();
		List<Index> list = new ArrayList<Index>();
		parser.index(list, str, namespace);
		IndexManager.getIndexManager().addIndexs(namespace, url, list);
	}

	/**
	 * sqlite table 不能有"-"和 "."
	 * 
	 * @Title: getNamespace
	 * @author: chengjf
	 * @date: 2014-10-20
	 * @param str
	 * @return
	 */
	private static String getNamespace(String str) {
		// - -> _
		str = str.replaceAll("-", "_");
		// remove javadoc
		str = str.substring(0, str.lastIndexOf("_"));

		str = str.replaceAll("\\.", "_");
		return str;
	}

	private static String getHTML(String filePath, String url) {
		JarFile jar = null;
		if (url.startsWith("./")) {
			url = url.substring(2);
		}
		url = url.substring(0, url.indexOf("#"));
		try {
			jar = new JarFile(filePath);
			for (Enumeration<JarEntry> enums = jar.entries(); enums
					.hasMoreElements();) {
				JarEntry entry = enums.nextElement();
				if (url.equals(entry.getName())) {
					InputStream inputStream = jar.getInputStream(entry);
					return IOUtils.toString(inputStream);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				jar.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

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

	public static File createTempDirectory() throws IOException {
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
}
