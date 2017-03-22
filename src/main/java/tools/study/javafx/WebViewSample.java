package tools.study.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月20日 下午4:40:26 
* @version 
*/
public class WebViewSample extends Application{
	
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//设置图标
		primaryStage.getIcons().add(new Image(WebViewSample.class.getResourceAsStream("icon.jpg")));
		//设置透明度
		primaryStage.setOpacity(0.9);
		primaryStage.setTitle("webView");
		
		Browser browser=new Browser();
		
		 scene = new Scene(new Browser(),900,600,Color.BLACK);
		 primaryStage.setScene(scene);
	        scene.getStylesheets().add("webviewsample/BrowserToolbar.css");        
	        primaryStage.show();
	        
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	

}
class Browser extends Region{
	final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();
    final Button toggleHelpTopics = new Button("Toggle Help Topics");
    public Browser() {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        System.out.println(WebViewSample.class.getResource("test1.html").toExternalForm());
        webEngine.load(WebViewSample.class.getResource("test1.html").toExternalForm());
       // webEngine.load("http://192.168.100.35:9990/remote_debug_ngcs/index.html#port=8080&root=ngwf&host=192.168.95.74");
        
        webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {


			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) {
				if (newValue == State.SUCCEEDED) {
					JSObject win = (JSObject) webEngine.executeScript("window"); // 获取js对象
					win.setMember("app", new JavaApp()); // 然后把应用程序对象设置成为js对象
					//webEngine.executeScript("dd('666')");// 直接执行html中的js脚本
					System.out.println("123");
					}
				
			}
        	
        });
        
        /** 
         * 测试能否获得javascript上面的交互内容。 
         * 可以自己写一个包含window.alert("neirong")的html进行测试。 
         * 返回的是neirong 
         */  
        webEngine.setOnAlert(new EventHandler<WebEvent<String>>() {  
   
             @Override  
             public void handle(WebEvent<String> event) {  
                 System.out.println("this is event"+event);  
             }  
         });  
        
        
        //add the web view to the scene
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
    	return 600;
    }
    @Override
    protected double computePrefWidth(double height) {
    	return 900;
    }
    
    
    
}

