package tools.common;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/** 
* 获取路径
* @author 作者 : zyq
* 创建时间：2016年11月14日 上午11:05:41 
* @version 
*/
public class PathUtil {
	/**
	 * 获取当前线程所在的class的classpath根目录URL（需处理第一个字符\）
	 * 例子：D:/workingSpace2/tools/target/classes/
	 * @return
	 */
	public static String getClassPath(){
		String classPath="";
		URL url = Thread.currentThread().getContextClassLoader().getResource("");
		String os = System.getProperty("os.name");  
		if( os.toLowerCase().startsWith("win") ) {  
			classPath = url.getPath().substring(1, url.getPath().length());
		} else {
			classPath = url.getPath();
		}
		return classPath;
	}
	/**
	 * 获取当前项目的web 路径，必须依赖 springmvc 和servlet的jar包<br>
	 * web.xml 配置的监听器需要是这个ContextLoaderListener<br>
	 * 路径会以 '/' 结尾<br>
	 * @return
	 */
	public static String getWebPath(){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();  
        ServletContext servletContext = webApplicationContext.getServletContext(); 
        String file_real_path=servletContext.getRealPath("");
		if(!file_real_path.endsWith(File.separator))file_real_path=file_real_path+File.separator;
		return file_real_path;
	}
	public static String getWebPath(HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("");
		if(path.endsWith(File.separator)){
		}else{
			path+=File.separator;
		}
		return path;
	}
	
	public static String getVisitPath(HttpServletRequest request){
		String path = request.getContextPath();
		if(path.endsWith("/")){
		}else{
			path+="/";
		}
		return path;
	}
}
