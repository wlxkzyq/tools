package tools.javafx.browser;

import org.apache.commons.lang3.StringUtils;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import netscape.javascript.JSObject;
import tools.cmd.CmdConstants;
import tools.cmd.ExecuteCmd;

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
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				//TODO
			}
		});
		
		primaryStage.setScene(scene);
		
	}
	
	
	/**
	 * 初始化浏览器
	 * 赋值一些参数
	 * @param primaryStage
	 */
	public void initStage(Stage primaryStage){
		//设置图标
		primaryStage.getIcons().add(new Image(WebViewSimple.class.getResourceAsStream("image/icon6.png")));
		//设置透明度
		primaryStage.setOpacity(0.9);
		primaryStage.setTitle("简易浏览器");
		//设置始终显示在其他窗口之上
		//primaryStage.setAlwaysOnTop(true);
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
	    	String url=WebViewSimple.class.getResource("html/generator.html").toExternalForm();
	    	//webEngine.load(url);
	    	webEngine.getLoadWorker().stateProperty().addListener(
	    			(ObservableValue<? extends State> ov, State oldState, 
	    	                State newState) -> {
	    	                	 if (newState == State.SUCCEEDED) {
	    	                         JSObject win
	    	                                 = (JSObject) webEngine.executeScript("window");
	    	                         win.setMember("app", new App(webEngine));
	    	                         
	    	                         //webEngine.executeScript("out(465)");
	    	                     }
	    	                }
	    			);
	    	
	    	
	    	webEngine.load(url);
	    	webEngine.load(url);
	    	
	    	
	    	getChildren().add(browser);
	    	
	    	/**
	    	 * 设置浏览器alert()方法执行
	    	 */
	    	webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {
				
				@Override
				public void handle(WebEvent<String> event) {
					Alert alert=new Alert(AlertType.INFORMATION);
//					alert.setTitle("这是title");
//					alert.setHeaderText("这是headerText");
					alert.setContentText(event.getData());
					alert.showAndWait();
					
				}
			});
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
	    
	    public class App{
	    	public App(WebEngine webEngine){
	    		this.webEngine=webEngine;
	    	}
	    	private WebEngine webEngine;
	    	private boolean isFirst=true;
	    	

			public int lll(String s){
	    		System.out.println(s);
	    		webEngine.executeScript("out('"+s+"')");
	    		//webEngine.executeScript("out('')");
	    		return 18;
	    	}
			
			public void shutdownCancel(){
				new ExecuteCmd().exec(CmdConstants.shutdownCancel);
			}
			public void shutdown(int seconds){
				new ExecuteCmd().shutdown(seconds);
			}
	    }
	}
	public static void main(String[] args) {
		launch(args);
	}

}
