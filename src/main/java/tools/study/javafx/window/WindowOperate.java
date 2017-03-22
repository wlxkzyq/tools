package tools.study.javafx.window;

import com.sun.javafx.css.Declaration;
import com.sun.javafx.css.Selector;
import com.sun.javafx.css.Style;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月22日 上午10:40:35 
* @version 
*/
public class WindowOperate extends Application{
	
//	3.1. stage.hide() 与 stage.close() 等价。 
//	详见http://docs.oracle.com/javafx/2/api/javafx/stage/Stage.html#close()
//
//	3.2. 如果要阻止Fx窗口在按下关闭按钮后退出，的确需要setOnCloseRequest，可是在handler函数中需要调用event.consume()来阻止事件进一步传递，这样才能真正阻止Window Close事件的默认处理，如果不加这个方法，窗口是会直接关闭的。 
//	详见http://docs.oracle.com/javafx/2/api/javafx/stage/Window.html#onCloseRequestProperty
//
//	3.3. 默认情况下，JavaFx运行时会在最后一个stage close(或hide)后自动关闭，即自动调用Application.stop()，除非通过Platform.setImplicitExit(false)取消这个默认行为。这样，即使所有Fx窗口关闭（或隐藏），Fx运行时还在正常运行，可以再次显示原来的窗口或打开新的窗口。 
//	详见http://docs.oracle.com/javafx/2/api/javafx/application/Platform.html#setImplicitExit(boolean)

	@Override
	public void start(Stage stage) throws Exception {
	   Button btn = new Button();
       btn.setText("Say 'Hello World'");
       btn.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
              System.out.println("Hello World!");
           }
       });
       VBox root = new VBox();
       root.getChildren().add(btn);
       Scene scene = new Scene(root, 300, 250,Color.BISQUE);
		
		//设置标题
		stage.setTitle("窗口学习");
		
		
		//设置最大化显示
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//		stage.setX(primaryScreenBounds.getMinX());
//		stage.setY(primaryScreenBounds.getMinY());
//		stage.setWidth(primaryScreenBounds.getWidth());
//		stage.setHeight(primaryScreenBounds.getHeight());
		
		//指定窗口大小
		stage.setWidth(900);
		stage.setHeight(600);
		
		//指定窗口居中
//		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//		stage.setX((primaryScreenBounds.getWidth()-stage.getWidth())/2);
//		stage.setY((primaryScreenBounds.getHeight()-stage.getHeight())/2);
		stage.centerOnScreen();
		
		//全屏显示，Esc退出
		//stage.setFullScreen(true);
		
		//设置始终显示在其他窗口之上
		stage.setAlwaysOnTop(true);
		
		//最小化，任务栏可见图标
		//stage.setIconified(true);
		
		//设置窗口风格
		//DECORATED  白色背景，带有最小化/最大化/关闭等有操作系统平台装饰（ 默认设置）
		//TRANSPARENT  透明背景，没有操作系统平台装饰
		//UNDECORATED  白色背景，没有操作系统平台装饰
		//UNIFIED  有操作系统平台装饰，消除装饰和内容之间的边框，内容背景和边框背景一致，（但要视平台支持），可以通过javafx.application.Platform.isSupported(javafx.application.ConditionalFeature)判断
		//UTILITY  白色背景，只有关闭操作系统平台装饰
		stage.initStyle(StageStyle.DECORATED);
		
		//设置透明度（0-1）
		stage.setOpacity(0.8);
		
		//设置是否可以调整大小
		stage.setResizable(false);
		
		//设置关闭进行的操作
		stage.setOnCloseRequest(new EventHandler<WindowEvent>(){

			@Override
			public void handle(WindowEvent event) {
				System.out.println(event.toString());
				//阻止关闭
				//event.consume();
			}
			
		});
		
		//设置场景
		stage.setScene(scene);
		
		
		//设置显示
		stage.show();
	}
	
	public static void main(String[] args) {
		Runnable r= () -> {System.out.println("Hello World!");};
		r.run();
		launch(args);
		
	}

}
