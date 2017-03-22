package tools.study.javafx;

import javafx.application.Platform;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月22日 上午9:33:09 
* @version 
*/
public class JavaApp {
	public void exit() {
		Platform.exit();
	}
	public void login() {
		System.out.println("login...");
	}
}
