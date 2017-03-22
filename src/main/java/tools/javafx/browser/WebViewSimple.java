package tools.javafx.browser;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import tools.study.javafx.JavaApp;

/** 
* 自定义简易javafx浏览器 
* @author 作者 : zyq
* 创建时间：2017年3月22日 下午3:38:24 
* @version 
*/
public class WebViewSimple extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		initStage(primaryStage);
		Browser browser=new Browser();
		Scene scene= new Scene(browser,1200,800,Color.BROWN);
		scene.getStylesheets().add("tools/javafx/browser/css/BrowserToolbar.css"); 
		primaryStage.setScene(scene);
		
	}
	
	
	/**
	 * 初始化浏览器
	 * 赋值一些参数
	 * @param primaryStage
	 */
	public void initStage(Stage primaryStage){
		//设置图标
		primaryStage.getIcons().add(new Image(WebViewSimple.class.getResourceAsStream("icon6.png")));
		//设置透明度
		primaryStage.setOpacity(0.9);
		primaryStage.setTitle("简易浏览器");
		//设置始终显示在其他窗口之上
		primaryStage.setAlwaysOnTop(true);
		primaryStage.centerOnScreen();
		
		primaryStage.setWidth(1200);
		primaryStage.setHeight(800);
		primaryStage.show();
	}
	class Browser extends Region{
		final WebView browser = new WebView();
	    final WebEngine webEngine = browser.getEngine();
	    
	    //构造方法
	    public Browser() {
	    	getStyleClass().add("browser");
	    	String url=WebViewSimple.class.getResource("html/test1.html").toExternalForm();
	    	webEngine.load(url);
	    	
	    	webEngine.getLoadWorker().stateProperty().addListener(
	    			(ObservableValue<? extends State> ov, State oldState, 
	    	                State newState) -> {
	    	                	 if (newState == State.SUCCEEDED) {
	    	                     	System.out.println(123);
	    	                         JSObject win
	    	                                 = (JSObject) webEngine.executeScript("window");
	    	                         win.setMember("app", new JavaApp());
	    	                     }	
	    	                }
	    			);
	    	
	    	
	    	getChildren().add(browser);
	    }
	    
	    @Override
	    protected void layoutChildren() {
	    	double w = getWidth();
	        double h = getHeight();
	        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
	    }
	    @Override
	    protected double computePrefHeight(double width) {
	    	return 800;
	    }
	    @Override
	    protected double computePrefWidth(double height) {
	    	return 1200;
	    }
	}
	public static void main(String[] args) {
		launch(args);
	}

}
