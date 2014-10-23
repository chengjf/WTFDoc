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
import java.util.Collections;
import java.util.List;

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

import com.chengjf.wtfdoc.bean.index.Index;
import com.chengjf.wtfdoc.service.IndexManager;
import com.chengjf.wtfdoc.service.ParseManager;
import com.chengjf.wtfdoc.service.UrlManager;

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
							String url = UrlManager.getInstance().getUrl(arg2);
							view.getEngine().load(url);
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
				UrlManager.getInstance().clear();

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

		// sort
		Collections.sort(strList, String.CASE_INSENSITIVE_ORDER);
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
				new FileChooser.ExtensionFilter("*", "*.*"),
				new FileChooser.ExtensionFilter("JAR", "*.jar"),
				new FileChooser.ExtensionFilter("ZIP", "*.zip"));
		fileChooser.setTitle("Open API Doc Zip File");
		File file = fileChooser.showOpenDialog(stage);
		ParseManager.getInstance().parse(file);
	}

}
