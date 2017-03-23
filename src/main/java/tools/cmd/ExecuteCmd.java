package tools.cmd;

import java.io.IOException;

/** 
* 类说明 
* @author 作者 : zyq
* 创建时间：2017年3月23日 下午9:01:00 
* @version 
*/
public class ExecuteCmd {
	
	/**
	 * 执行关机命令
	 * @param seconds	剩余多少秒关机
	 */
	public void shutdown(int seconds){
		exec(CmdConstants.shutdown+seconds);
	}
	
	/**
	 * 执行cmd命令
	 * @param command
	 */
	public void exec(String command){
		Runtime rt=Runtime.getRuntime();
		
		try {
			rt.exec(command);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("执行命令错误："+command);
		}
	}
	
	public static void main(String[] args) {
		new ExecuteCmd().shutdown(600);
	}
	
	

}
