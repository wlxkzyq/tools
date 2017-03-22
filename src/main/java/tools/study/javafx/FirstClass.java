package tools.study.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
* 第一次学习fx
* @author 作者 : zyq
* 创建时间：2017年3月20日 上午9:59:09 
* @version 
*/
public class FirstClass extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		//新建一个button
		Button btn = new Button();
		//设置button的值
	       btn.setText("Say 'Hello World'");
	    //设置点击事件
	       btn.setOnAction(new EventHandler<ActionEvent>() {
	           @Override
	           public void handle(ActionEvent event) {
	              System.out.println("Hello World!");
	           }
	       });
	       //定义了一种布局规则
	       StackPane root = new StackPane();
	       
	       //布局下添加一个节点
	       root.getChildren().add(btn);
	       //利用布局新建一个场景
	       Scene scene = new Scene(root, 300, 250,Color.AQUA);
	       
	       //primaryStage是最外层容器（window窗口），相当于舞台
	       primaryStage.setTitle("Hello World!");
	       
	       //设置场景
	       primaryStage.setScene(scene);
	       //设置window展示
	       primaryStage.show();
		
	}
	
	//运行代码
	public static void main(String[] args) {
		launch(args);
	}
	

}
